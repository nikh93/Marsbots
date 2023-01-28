import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Shuttle {
	ArrayList<Robot> bots;
	Shuttle(){
		bots = new ArrayList<Robot>();
	}
	
	public ArrayList<Robot> getRobots(){
		return bots;
	}
	
	public void start(Screen screen){
		ArrayList<Float> xPositions = new ArrayList<Float>();
		ArrayList<Float> yPositions = new ArrayList<Float>();
		Robot bot = new Robot(0,0);
		bot.setBots(bots);
		bots.add(bot);
		screen.add(bots.get(0));
		xPositions.add(0.0f);
		yPositions.add(0.0f);
		for(int n=1; n<MarsConstants.ROBOT_NUMBER;n++){
			try {
				Thread.sleep(MarsConstants.LAUNCH_WAIT_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Collections.sort(xPositions);
			Collections.sort(yPositions);
			//System.out.println(" xPos:"+xPositions);
			//System.out.println(" yPos:"+yPositions);
			float xMin = xPositions.get(0)-MarsConstants.ROBOT_VIEW_DISTANCE;
			float xMax = xPositions.get(xPositions.size()-1)+MarsConstants.ROBOT_VIEW_DISTANCE;
			float yMin = yPositions.get(0)-MarsConstants.ROBOT_VIEW_DISTANCE;
			float yMax = yPositions.get(xPositions.size()-1)+MarsConstants.ROBOT_VIEW_DISTANCE;
			float deltaX = xMax-xMin;
			float deltaY = yMax-yMin;
		    float randX;
		    float randY;
		    //System.out.println(" xMin:"+xMin+" yMin:"+yMin+" xMax:"+xMax+" yMax:"+yMax);
		   // System.out.println(" deltaX:"+deltaX+" deltaY:"+deltaY);
			while(true){
				randX = (float) ((Math.random()*deltaX)+xMin);
				randY = (float) ((Math.random()*deltaY)+yMin);
				//System.out.println(" randX"+randX+" randY"+randY);
				boolean validPosition = false;
				for(Robot r : bots){
					if(Point.distance(randX, randY, r.getX(), r.getY())<=MarsConstants.ROBOT_VIEW_DISTANCE){
						validPosition = true;
						break;
					}
				}
				if(validPosition) {
					//System.out.println("valid Position");
					break;
				}
				else{
					//System.out.println("not valid Position");
				}
			}
			xPositions.add(randX);
			yPositions.add(randY);
			bot = new Robot(randX,randY);
			bot.setBots(bots);
			bots.add(bot);
			screen.add(bot);
			
		}
		
	}
	
	
}
