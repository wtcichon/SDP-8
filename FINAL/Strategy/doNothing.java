package Strategy;
import Main.Launcher;
import Shared.Constants;
import Shared.ObjectItem;


public class doNothing implements Behaviour {
	private ObjectItem[] objects;
	
	public boolean takeControl(ObjectItem[] o) {
		objects = o;
		return true;
	}
	
	public void action() {

try {


			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void suppress() {
	}

	

	
}
