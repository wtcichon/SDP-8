package Strategy;
import Main.Launcher;
import Shared.Constants;
import Shared.ObjectItem;


public class TooCloseToGoal implements Behaviour {
	private ObjectItem[] objects;

	public boolean takeControl(ObjectItem[] o) {
		objects = o;
		return (objects[us].getCentre().x < 30);
	}
	
	public void action() {
		System.out.println("Too close to goal.");
		Launcher.sendCommand(Constants.STOP);
	}

	public void suppress() {
		// TODO Auto-generated method stub

	}

}
