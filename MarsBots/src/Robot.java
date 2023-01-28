import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTextArea;

public class Robot implements MarsPaintable {
	private float x, y;
	private float orientation, speed;
	private ArrayList<Robot> bots, combinedBots;
	private ArrayList<ObservedRobot> observedZoneBots, observedNeighbors;
	private Vector orientVector;
	private JTextArea testOut, testOutBuffer;
	private boolean test, finished, almostFinished;
	private int testIndex;

	Robot(float x, float y) {
		setX(x);
		setY(y);
		speed = 1;
		observedZoneBots = new ArrayList<ObservedRobot>();
		orientVector = new Vector(0,0);
		testOutBuffer = new JTextArea(20, 70);
		combinedBots = new ArrayList<Robot>();
	}

	public static float calcDistance(Robot a, Robot b) {
		return (float) Point.distance(a.getX(), a.getY(), b.getX(), b.getY());
	}

	public int getTestIndex() {
		return testIndex;
	}

	public void setTestIndex(int testIndex) {
		this.testIndex = testIndex;
	}

	public boolean getFinished() {
		return finished;
	}

	public void setTest(boolean test, JTextArea testOut) {
		this.test = test;
		this.testOut = testOut;
		
	}

	public float getX() {
		return x;
	}

	private void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	private void setY(float y) {
		this.y = y;
	}

	public float getOrientation() {
		return orientation;
	}

	private void setOrientation(float orientation) {
		this.orientation = orientation;
	}

	@SuppressWarnings("unchecked")
	public void setBots(ArrayList<Robot> bots) {
		//TODO arrayList.clone() werden referenzen übernommen?
		this.bots = bots;
		this.bots = (ArrayList<Robot>) bots.clone();
		
	}
	
	

	public String getTestOutText() {
		return testOutBuffer.getText();
	}

	public void move(float speed) {
		
			println("movespeed" + this.speed);
			setX((float) (getX() + (Math.cos(orientation * Math.PI) * this.speed)));
			setY((float) (getY() + (Math.sin(orientation * Math.PI) * this.speed)));
			println("x: "+x+"y: "+y);
		
	}

	public static float toOrient(Vector a) {
		if (Vector.isNullVector(a)) {
			return 0;
		}
		float g = (float) (Vector.angle(a, Vector.NORM_RIGHT) / Math.PI);
		float k = (float) (Vector.angle(a, Vector.NORM_UP) / Math.PI);

		if (k > 0.5) {
			g = 2 - g;
		}
		if(g>2){
			System.out.println("ERROR: orientation>2");
		}
		return g;
	}

	private ArrayList<Robot> checkNeighbors() {
		
		ArrayList<Robot> neighbors = new ArrayList<Robot>();
		ArrayList<ObservedRobot> neighborsInObsZone = new ArrayList<ObservedRobot>();

		for (Robot r : bots) {
			float dist = Robot.calcDistance(this, r);
			if(!(dist <= MarsConstants.COMBINATION_DISTANCE)){
				if (dist <= MarsConstants.ROBOT_VIEW_DISTANCE) {
				neighbors.add(r);
				if (dist > (MarsConstants.ROBOT_VIEW_DISTANCE - MarsConstants.OBSERVATION_DISTANCE)) {
					neighborsInObsZone.add(new ObservedRobot(r, dist));
				}
			}
			}
			else{
				if(!combinedBots.contains(r)){
					combinedBots.add(r);
				}
				
				//Achtung! enthält this
			}
			
		}
		
		
		if(false){
			
			System.out.println("combinedBots: "+combinedBots.size());
			System.out.println("neighbors: "+neighbors.size());
			System.out.println("bots: "+bots.size());
		}
		
		// nachbarn und observationBots wurden bestimmt
		float[] distances = new float[neighborsInObsZone.size()];
		int n = 0;
		for (ObservedRobot r : neighborsInObsZone) {
			for (Robot neighbor : neighbors) {
				
				if (!inShadow(r.getRobot())&&Robot.calcDistance(r.getRobot(), neighbor) < MarsConstants.ROBOT_VIEW_DISTANCE
						&& Robot.calcDistance(this, neighbor) < r
								.getObservedDistance()) {
					
				} else {
					for (ObservedRobot oR : observedZoneBots) {
						if (r.getRobot().equals(oR.getRobot())
								&& (oR.getObservedDistance() - r
										.getObservedDistance()) < 0) {
							distances[n] = r.getObservedDistance();
							break;
						}
					}
				}
			}

			n++;
		}
		// vergleicht aktuelle position jedes zoneBots mit vorangehender und
		// speichert entfernung von denen die sich auf this zubewegen
		Arrays.sort(distances);
		for (float d : distances) {
			print(" " + d + ",");
		}
		if (distances.length > 0 && distances[n - 1] > 0) {
			float x = (MarsConstants.ROBOT_VIEW_DISTANCE) - distances[n - 1];
			if (x < 0)
				x = 0;
			speed = MarsConstants.SPEED_REDUCTION * x;

			println("largest distance: " + distances[n - 1]);
			println("x: " + x);
			println("speed: " + speed);
			if (speed > 1) {
				System.out.println("ERROR: speed>1");
			}
		}
		// bestimmt die größte entfernung und reduziert speed in abhängigkeit
		// von dieser
		else {
			speed = 1;
		}

		if (combinedBots.size() == bots.size()) {
			almostFinished = true;
		}

		observedZoneBots = neighborsInObsZone;
		
		return neighbors;
	}

	private void checkFinished() {
		int n = 0;
		for (Robot r : bots) {
			float dist = (float) Point.distance(this.getX(), this.getY(), r.getX(),
					r.getY());
			if (dist <= MarsConstants.FINISHED_DISTANCE) {
				n++;
			}
		}
		if (n == bots.size()) {
			finished = true;
		}
	}

	public void orient() {
		if (almostFinished) {
			checkFinished();
		}
		if (!finished) {
			ArrayList<Vector> forces = new ArrayList<Vector>();
			int n = 0;
			for (Robot r : checkNeighbors()) {
				forces.add(Physics.calcForce(this, r));
				n++;
			}
			println("neighbors:" + n);

			if (n == 0) {
				setOrientation((float) Math.random() * 2);
			} else {
				//group(forces);
				Vector resForce = Vector.addition( forces.toArray(new Vector[forces.size()]));

				if(!(Vector.absolute(resForce)>-1)){
					System.out.println("ERROR: Robot: abs(resForce)="+Vector.absolute(resForce)+" resForce="+resForce);
					for(Vector v : forces){
						System.out.println(v);
					}
				}
				
					//resForce = Vector.linearProduct(1 / Vector.absolute(resForce), resForce);
							
					this.orientVector = Vector.linearProduct(
							MarsConstants.ORIENT_VECTOR_ENLAGEMENT, resForce);
				
				setOrientation(Robot.toOrient(resForce));
			}
		}
	}
	
	
	
	private boolean inShadow(Robot a){
		if(Vector.isNullVector(orientVector)){
			return true;
		}
		return Vector.angle(Vector.calcBotVector(this, a), orientVector)>=0.49*Math.PI;
	}
	
	public void print(String text) {
		if (MarsConstants.TEST) {
			//testOutBuffer.append(text);
		}
		if (test) {
			testOut.append(text);
		}
	}

	public void println(String text) {
		if (MarsConstants.TEST) {
			//testOutBuffer.append(text + "\n");
		}
		if (test) {
			testOut.append(text + "\n");
		}
	}

	@Override
	public void paint(Graphics2D g) {
		if (test) {
			g.setColor(Color.red);
		}
		int screenX = (int) ((getX() / MarsConstants.SCALE_REDUCTION) + MarsConstants.MAP_X_POSITION);
		int screenY = (int) ((getY() / MarsConstants.SCALE_REDUCTION) + MarsConstants.MAP_Y_POSITION);
		int screenRad = (int) (MarsConstants.ROBOT_RADIAN / MarsConstants.SCALE_REDUCTION);
		g.fillOval(screenX - screenRad, screenY - screenRad, screenRad * 2,
				screenRad * 2);
		if (MarsConstants.VIEW_CIRCLES_ON) {
			int screenViewDist = (int) (MarsConstants.ROBOT_VIEW_DISTANCE / MarsConstants.SCALE_REDUCTION);
			g.drawOval(screenX - screenViewDist, screenY - screenViewDist,
					screenViewDist * 2, screenViewDist * 2);
		}
		
		
		if (MarsConstants.TEST){
			g.drawString(""+this.testIndex, screenX - screenRad+7, screenY - screenRad+15);
			if (orientVector != null) {
			g.drawLine(screenX, screenY, (int) (screenX + orientVector.getX()),
					(int) (screenY + orientVector.getY()));
		}
		}
		
		if (test) {
			g.setColor(Color.black);
			
		}
	}
}
