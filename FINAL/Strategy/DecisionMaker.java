package Strategy;

import Main.Launcher;
import Shared.Constants;
import Shared.ObjectItem;


/* 
 * Arbitrator class deciding which behaviour
 * should be used next. 
 */

public class DecisionMaker{

	public static void launchMatch(){

		/*Launcher.sendCommand(345456);
		ObjectItem ox[] = Launcher.findObjects();
		System.out.println(" read  angle "+ox[Constants.set_colour].getAngle());
		Launcher.sendCommand(123456);
		for (int i = 0 ;i<10; i++)
		{
			ObjectItem o[] = Launcher.findObjects();
			System.out.println(" read " + i+ " angle "+o[Constants.set_colour].getAngle());
			
		}
		*/
		//Set of top level emergency behaviour which check if all is ok before proceeding
//		Behaviour test = new InterceptDemoFixedTurn();
//		Behaviour b2 = new NoAngle();
//		Behaviour b3 = new NoBall();
//		
//		Behaviour b1 = new Play(); // to be changed to intercept demos fir mileston 4 if wanted
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
		Behaviour b12 = new ContinuousDrop();
		//		   Behaviour[] bArray = { b1, b4, b5,b2,b3}; this was used Sunday 20th
		//   Behaviour[] bArray = { b1, b5, b4,b2,b3};
		//  Behaviour[] bArray = {b1,b2,b9,b8,b4,b6,b7,b3};	//following chris' desired order
		//		   Behaviour[] bArray = {b1,b2, b9, b4, b7, b3};
		//		   Behaviour[] bArray = {b1,b2,b8,b10,b4,b6,b7,b3};*/
		//Behaviour b13 = new TestBehav();
		//Behaviour test = new BlobAlmighty();
		Behaviour b1 = new Strategy();
		Behaviour b2 = new Emergency();
//		Behaviour b3 = new doNothing();
		
	//	Behaviour[] bArray = {b3,b2,b1};
		Behaviour[] bArray = {b2,b1};
		MyArbitrator arby = new MyArbitrator(bArray);
		arby.start();	
	}

	public static void launchPenaltyUs(){
		Behaviour b1 = new PenaltyUs();

		Behaviour[] bArray = {b1};
		MyArbitrator arby = new MyArbitrator(bArray);
		arby.start();
	}

	public static void launchPenaltyThem(){
		Behaviour b1 = new PenaltyThem();

		Behaviour[] bArray = {b1};
		MyArbitrator arby = new MyArbitrator(bArray);
		arby.start();
	}

	/*public static void main(String [] args) {
	   //Launcher.setUp();

	   Behaviour b1 = new BasicBehavior();
	   Behaviour b2 = new GoToBall();
	   Behaviour b3 = new NoBall();
	   Behaviour b4 = new ForwardChargeBehavior();
	   Behaviour b5 = new ShootBehavior();
	   Behaviour b6 = new tooClosetoGoal();
	   Behaviour b7 = new NoAngle();
	   Behaviour b8 = new DropToAttack();
	   Behaviour b9 = new MoveToFrontBall();
	   Behaviour b10 = new MiniCharge();
	   Behaviour b11 = new TurnToAngle();

//	   Behaviour[] bArray = { b1, b4, b5,b2,b3}; this was used Sunday 20th
	//   Behaviour[] bArray = { b1, b5, b4,b2,b3};
	 //  Behaviour[] bArray = {b1,b2,b9,b8,b4,b6,b7,b3};	//following chris' desired order
//	   Behaviour[] bArray = {b1,b2, b9, b4, b7, b3};
//	   Behaviour[] bArray = {b1,b2,b8,b10,b4,b6,b7,b3};

	   Behaviour[] bArray = {b1,b2,b9,b8,b10,b4,b6,b7,b3};
	   myArbitrator arby = new myArbitrator(bArray);
	   arby.start();

	 //  Launcher.sendCommand(Constants.FORWARD+20);	
	//   System.out.println("Program finished.");
   }*/

}

