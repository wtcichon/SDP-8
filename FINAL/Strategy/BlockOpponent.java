package Strategy;

import Shared.*;

// Behaviour to block the opponent if he has the ball and can be blocked
public class BlockOpponent implements Behaviour {
	ObjectItem[] objects;
	
	@Override
	public boolean takeControl(ObjectItem[] o) {
		objects = o;
		float distOppBall = Ally.distance(objects[them].getCentre(), objects[2].getCentre());
		float distOppOurGoal = Ally.distance(objects[them].getCentre(), ourGoal);
		float distUsOpp = Ally.distance(objects[us].getCentre(), objects[them].getCentre());
		return (distOppBall < 20 && distOppOurGoal > 100 && distUsOpp < distOppOurGoal); //random values, need to be set properly with tests and condition needs to be made better
	}
	
	@Override
	public void action() {
		//to be implemented

	}

	@Override
	public void suppress() {
		

	}

	

}
