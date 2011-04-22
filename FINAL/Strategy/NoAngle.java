package Strategy;
import Main.Launcher;
import Shared.Constants;
import Shared.ObjectItem;


public class NoAngle implements Behaviour {
	private ObjectItem[] objects;
	public boolean takeControl(ObjectItem[] o) {
		objects = o;
		return (o[us].getAngle() == 720);
	}
	
	public void action() {
		System.out.println("Cannot find angle");
		Launcher.sendCommand(Constants.BACKWARD+10);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

		}				
		
	}

	public void suppress() {
	}

	

	
}
