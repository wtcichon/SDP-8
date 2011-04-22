package Strategy;

import Shared.ObjectItem;

// Basic behavior to adopt if nothing else is happening

public class DefensiveStrategy implements Behaviour {
	
	MyArbitrator arby;
	@Override
	public void action() 
	{
	
	//start new arbiter
	
	Behaviour b1 = new BlockOpponent();
	Behaviour b2 = new myRetreat();
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
		// TODO Auto-generated method stub
		return true;
	}

}
