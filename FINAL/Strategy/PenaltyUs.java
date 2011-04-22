package Strategy;

import java.util.Random;

import Main.Launcher;
import Shared.Constants;
import Shared.ObjectItem;


public class PenaltyUs implements Behaviour {
	//private ObjectItem[] objects;

	@Override
	public boolean takeControl(ObjectItem[] o) {
		//objects = o;
		return true;
	}
	@Override
	public void action() {
		System.out.println("Penalty us");
		Random random = new Random();
		int i = random.nextInt(1);
		if (i == 1){
		Launcher.sendCommand(Constants.TURN+25);
		
		}
		else{
			Launcher.sendCommand(Constants.TURN_BACK+25);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Launcher.sendCommand(Constants.KICK);
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}

	

}
