package Strategy;

import Shared.ObjectItem;

//Behaviour calling the classes needed to go to the ball 
public class FindBall implements Behaviour {
	
	@Override
	public boolean takeControl(ObjectItem[] o) {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public void action() {
		System.out.println("Find Ball");
		//Behaviour b1 = new DropToAttack();
		Behaviour b1 = new ContinuousDrop();
		Behaviour b2 = new MiniCharge();
		Behaviour b3 = new ForwardChargeBehavior();
		
		Behaviour[] bArray = {b1,b2,b3}; //need confirmation from chris whether these are the right behaviours in the right order or not
		MyArbitrator arby = new MyArbitrator(bArray);
		arby.start();
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}

	

}
