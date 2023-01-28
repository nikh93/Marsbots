
public class ObservedRobot {
	private Robot robot;
	private float observedDistance;
	private float orientation;
	private float speed;

	
	public ObservedRobot(Robot robot, float observedDistance) {
		this.robot = robot;
		this.observedDistance = observedDistance;
	}
	
	public float getOrientation() {
		return orientation;
	}

	public void setOrientation(float orientation) {
		this.orientation = orientation;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	
	
	public Robot getRobot() {
		return robot;
	}
	public void setRobot(Robot robot) {
		this.robot = robot;
	}
	public float getObservedDistance() {
		return observedDistance;
	}
	public void setObservedDistance(float observedDistance) {
		this.observedDistance = observedDistance;
	}
	
}
