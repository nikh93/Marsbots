
public class MarsConstants {

	static float SCALE_REDUCTION = 2;
	public static int ROBOT_NUMBER=100;
	static int ROBOT_VIEW_DISTANCE= 200;
	static int OBSERVATION_DISTANCE= 5;
	static int ROBOT_RADIAN = 4;
	static int LAUNCH_WAIT_TIME = 100;
	static int MOVE_WAIT_TIME = 10;
	static int SCREEN_X_SIZE = 1800;
	static int SCREEN_Y_SIZE = 1000;
	static int MAP_X_POSITION = SCREEN_X_SIZE/2;
	static int MAP_Y_POSITION = SCREEN_Y_SIZE/2;
	static int TEST_BOT = 1;
	static int FINISHED_DISTANCE = 1;
	static float ORIENT_VECTOR_ENLAGEMENT = 20;
	static float MOVE_DISTANCE = 1;
	static float SPEED_REDUCTION = 0.002f;
	static float MINIMAL_ORIENTATION_DIFFERENCE = 0.1f;
	static float COMBINATION_DISTANCE = 2;


	static boolean VIEW_CIRCLES_ON = true;
	static boolean ANIMATED_LAUNCH = true;
	static boolean TEST = false;
	static boolean STOP_WHEN_FINISHED = true;

	MarsConstants(){
		MarsConstants.ANIMATED_LAUNCH = 1;
	}
}
