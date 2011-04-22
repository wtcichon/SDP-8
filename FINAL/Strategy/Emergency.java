package Strategy;
import Shared.ObjectItem;

// Basic behavior to adopt if nothing else is happening

public class Emergency implements Behaviour {
	MyArbitrator arby;
	@Override
	public void action() {
//start new arbiter
		Behaviour b1 = new NoBall();
		Behaviour b2 = new NoAngle();
		Behaviour[] bArray = {b1,b2};
		arby = new MyArbitrator(bArray);
		arby.start();	
		
	}

	@Override
	public void suppress() {
		arby.killArbitrator();
//kill arbiter
	}

	@Override
	public boolean takeControl(ObjectItem o[]) {
		// TODO Auto-generated method stub
		return true;
	}

}
