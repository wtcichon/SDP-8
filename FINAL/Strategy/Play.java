package Strategy;

import Shared.ObjectItem;

public class Play implements Behaviour {
	//Second level of subsumption architecture calling the different strategies
	//to be used during play (will be attack, defend etc eventually)
	
	@Override
	public void action() {
		System.out.println("play");
	//	Behaviour b2 = new InterceptDemo();
		/*Behaviour b1 = new BasicBehavior();
		Behaviour b2 = new GoToBall();
		Behaviour b3 = new NoBall();
		Behaviour b4 = new ForwardChargeBehavior();
		Behaviour b5 = new ShootBehavior();
		Behaviour b6 = new TooCloseToGoal();
		Behaviour b7 = new NoAngle();
		Behaviour b8 = new DropToAttack();
		Behaviour b9 = new MoveToFrontBall();
		Behaviour b10 = new MiniCharge();
		Behaviour b11 = new TurnToAngle();
		Behaviour b12 = new ContinuousDrop();*/
		
		//need to add demo arrays

		Behaviour b1 = new FindBall();
//		Behaviour b2 = new RetreatToGoal(); //needed ?
		Behaviour b3 = new BlockOpponent();
		
		Behaviour[] bArray = {b1,b3};
		//	Behaviour[] bArray = {b2};
		MyArbitrator arby = new MyArbitrator(bArray);
		arby.start();
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean takeControl(ObjectItem[] o) {
		// TODO Auto-generated method stub
		return true;
	}

}
