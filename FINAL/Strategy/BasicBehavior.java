package Strategy;
import Shared.ObjectItem;

// Basic behavior to adopt if nothing else is happening

public class BasicBehavior implements Behaviour {
	
	@Override
	public void action() {
		//System.out.println("I think I am done.");
		//Launcher.sendCommand(Constants.STOP);
		/*try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//Launcher.sendCommand(Constants.FORWARD + 20);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Launcher.sendCommand(Constants.SHUT);
	//	Launcher.rcvTranslate("stop,+000");
		// Launcher.rcvTranslate("play,+000");
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean takeControl(ObjectItem o[]) {
		// TODO Auto-generated method stub
		return true;
	}

}
