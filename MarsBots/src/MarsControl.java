
public class MarsControl {
	MarsControl(){
		Screen screen = new Screen();
		MarsView view = new MarsView(screen);
		Shuttle shuttle = new Shuttle();
		shuttle.start(screen);
		
		Physics physics = new Physics(shuttle.getRobots(), screen);
		screen.add(physics);
		if(MarsConstants.TEST) view.setTestSets(shuttle.getRobots());
		physics.start();
	}
	public static void main(String[] args) {
		new MarsControl();
	}
}
