package Strategy;

import java.awt.Point;

import Shared.ObjectItem;

// Basic behavior to adopt if nothing else is happening

public class Strategy implements Behaviour {
	
	MyArbitrator arby;
	@Override
	public void action() {
//start new arbiter
		Behaviour b1 = new DefensiveStrategy();
		Behaviour b2 = new OffensiveStrategy();
		Behaviour[] bArray = {b1,b2};
		arby = new MyArbitrator(bArray);
		arby.start();	
		
	}

	@Override
	public void suppress() {
	//kill arbiter
	arby.killArbitrator();
	}

	@Override
	public boolean takeControl(ObjectItem o[]) {
//there is ball and there are objects

		return (o[us].getAngle()!=720 || !o[2].getCentre().equals(new Point(499,499)));
	}

}
