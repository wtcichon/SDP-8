package Strategy;
import Main.Launcher;
import Shared.Constants;
import Shared.ObjectItem;


public class BlobAlmighty implements Behaviour {
	private ObjectItem[] objects;
	
	public boolean takeControl(ObjectItem[] o) {
	System.out.println("Blob Checking... Blocking???");
		return true;
	}
	
	public void action() {
		System.out.println("Blob 4Ever!!!!");

	}

	public void suppress() {
		System.out.println("SBlob 4Ever!!!!");
	}

	

	
}
