import java.io.Serializable;

public class Settings extends MarsConstants implements Serializable{
	private float scaleReducion;
	private int robotNumber;
	private int robotViewDistance;
	private int observationDistance;
	private int robotRadian;
	private int LaunchWaitTime;
	private int moveWaitTime;
	private int screenXSize;
	private int screenYSize;
	private float orientVestorEnlargement;
	private float moveDistance;
	private float speedReduction;
	private float combinationDistance;

	private boolean viewCirclesOn;
	private boolean animatedLaunch;
	private boolean test;

	puplic void writeToMarsConstants (){

	}

	public float getScaleReducion() {

		return scaleReducion;
	}

	public void setScaleReducion(float scaleReducion) {
		this.scaleReducion = scaleReducion;
	}

	public int getRobotNumber() {
		return robotNumber;
	}

	public void setRobotNumber(int robotNumber) {
		this.robotNumber = robotNumber;
	}

	public int getRobotViewDistance() {
		return robotViewDistance;
	}

	public void setRobotViewDistance(int robotViewDistance) {
		this.robotViewDistance = robotViewDistance;
	}

	public int getObservationDistance() {
		return observationDistance;
	}

	public void setObservationDistance(int observationDistance) {
		this.observationDistance = observationDistance;
	}

	public int getRobotRadian() {
		return robotRadian;
	}

	public void setRobotRadian(int robotRadian) {
		this.robotRadian = robotRadian;
	}

	public int getLaunchWaitTime() {
		return LaunchWaitTime;
	}

	public void setLaunchWaitTime(int launchWaitTime) {
		LaunchWaitTime = launchWaitTime;
	}

	public int getMoveWaitTime() {
		return moveWaitTime;
	}

	public void setMoveWaitTime(int moveWaitTime) {
		this.moveWaitTime = moveWaitTime;
	}

	public int getScreenXSize() {
		return screenXSize;
	}

	public void setScreenXSize(int screenXSize) {
		this.screenXSize = screenXSize;
	}

	public int getScreenYSize() {
		return screenYSize;
	}

	public void setScreenYSize(int screenYSize) {
		this.screenYSize = screenYSize;
	}

	public float getOrientVestorEnlargement() {
		return orientVestorEnlargement;
	}

	public void setOrientVestorEnlargement(float orientVestorEnlargement) {
		this.orientVestorEnlargement = orientVestorEnlargement;
	}

	public float getMoveDistance() {
		return moveDistance;
	}

	public void setMoveDistance(float moveDistance) {
		this.moveDistance = moveDistance;
	}

	public float getSpeedReduction() {
		return speedReduction;
	}

	public void setSpeedReduction(float speedReduction) {
		this.speedReduction = speedReduction;
	}

	public float getCombinationDistance() {
		return combinationDistance;
	}

	public void setCombinationDistance(float combinationDistance) {
		this.combinationDistance = combinationDistance;
	}

	public boolean isViewCirclesOn() {
		return viewCirclesOn;
	}

	public void setViewCirclesOn(boolean viewCirclesOn) {
		this.viewCirclesOn = viewCirclesOn;
	}

	public boolean isAnimatedLaunch() {
		return animatedLaunch;
	}

	public void setAnimatedLaunch(boolean animatedLaunch) {
		this.animatedLaunch = animatedLaunch;
	}

	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

	public boolean isStopWhenFinished() {
		return stopWhenFinished;
	}

	public void setStopWhenFinished(boolean stopWhenFinished) {
		this.stopWhenFinished = stopWhenFinished;
	}

	boolean stopWhenFinished;
}
