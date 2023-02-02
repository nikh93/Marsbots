import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Physics implements MarsPaintable{
	private ArrayList<Robot> bots;
	private Screen screen;
	private static boolean forceNegation, forceVariation, pause;
	private int seconds, minutes;


	Physics(ArrayList<Robot> bots, Screen screen) {
		this.bots = bots;
		this.screen = screen;
		//pause = true;
	}
	
	public static Vector calcForce(Robot a, Robot b){
		float f = (float) (1/Point.distance(a.getX(), a.getY(), b.getX(), b.getY()));
		
		
		
		if(forceNegation){
			f = -f;
		}
		if(forceVariation){
			f = 1/f;
		}
		
		
		//System.out.println(f);
		if(f>0.9){
			return new Vector(0,0);
		}
		Vector botVec = Vector.calcBotVector(a, b);
		Vector forceVec = Vector.linearProduct(1/Vector.absolute(botVec), botVec);
		forceVec = Vector.linearProduct(f, botVec);
		//TODO maximalkraft bestimmen oder absto�ung einbauen
		if((!(forceVec.getX()>-1))|| (!(forceVec.getY()>-1))){
			//System.out.println( "ERROR: Physics: calcForce(): forceVec="+forceVec+" f="+f+" Point.distance="+Point.distance(a.getX(), a.getY(), b.getX(), b.getY())+" aX="+a.getX()+" aY="+a.getY()+" bX"+" botVec="+Vector.absolute(botVec));
		}
		return forceVec;
	}

	public static void changePause() {
		pause = !pause;
	}
	
	public static void changeForceNegation() {
		forceNegation = !forceNegation;
	}
	
	public static void changeForceVariation() {
		forceVariation = !forceVariation;
	}

	public void start() {
		while (true) {
			while(pause){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			boolean allFinished = true;
			for(Robot r : bots){
				allFinished = allFinished && r.getFinished();
			}
			if(MarsConstants.STOP_WHEN_FINISHED && allFinished){
				break;
			}
			try {
				Thread.sleep(MarsConstants.MOVE_WAIT_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Robot r : bots) {
				if(!r.getFinished()){
					r.orient();
				}
			}
			
			for (Robot r : bots) {
				if(!r.getFinished()){
					r.move(MarsConstants.MOVE_DISTANCE);
				}
			}
			
			seconds++;
			if(seconds>59){
				minutes++;
				seconds=0;
			}
			screen.repaint();
			
		}

	}

	@Override
	public void paint(Graphics2D g) {
		String time ="minutes: "+minutes+":"+seconds;
		g.drawString(time, (int) (MarsConstants.SCREEN_X_SIZE-(MarsConstants.SCREEN_X_SIZE*0.2)), (int) (MarsConstants.SCREEN_Y_SIZE-(MarsConstants.SCREEN_Y_SIZE*0.9)));
	}
}
