package Strategy;

import Shared.Ally;
import Shared.ObjectItem;

// Basic behavior to adopt if nothing else is happening

public class OffensiveStrategy implements Behaviour {
	
	MyArbitrator arby;
	@Override
	public void action() {
//start new arbiter
		Behaviour b1 = new ForwardChargeBehavior();
		Behaviour b2 = new MiniCharge();
		Behaviour b3 = new myGoToBall();
		Behaviour[] bArray = {b1,b2,b3};
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
/* switch to offensive mode if
 * we are closer to ball than opponent
 * we are in front of ball
 */
		
		return (Ally.distance(o[us].getCentre(),o[2].getCentre())<Ally.distance(o[them].getCentre(),o[2].getCentre()) && 
				(Ally.distance(o[us].getCentre(),ourGoal)<Ally.distance(o[2].getCentre(),ourGoal))); 
	}

}
