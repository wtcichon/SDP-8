package Strategy;

import java.awt.Point;

import Main.Launcher;
import Shared.Constants;
import Shared.ObjectItem;


public class NoBall implements Behaviour {
	private ObjectItem[] objects;
	
	public boolean takeControl(ObjectItem[] o) {
		objects = o;
	
		return (o[2].getCentre().equals(new Point(499,499)) ||o[2].getCentre().x < 25);
	}
	
	public void action() {
		System.out.println("Cannot find ball");
		Launcher.sendCommand(Constants.STOP);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
	}
	
	public void suppress() {
	}

	

	
}
