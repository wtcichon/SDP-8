import lejos.nxt.*;

public class SensorThread extends Thread {

	private TouchSensor front_1;
	private TouchSensor front_2;
	private TouchSensor back;
	public SensorThread() {
// this process is sensors listener
//if sensors are pressed it took over controll on robot
		
		
		front_1 = new TouchSensor(SensorPort.S1);
		front_2 = new TouchSensor(SensorPort.S2);
		back = new TouchSensor(SensorPort.S3);
	}
	
	public void run() {
		
		while(true) {
			if (front_1.isPressed() && front_2.isPressed() && back.isPressed())
			{
			//if all sensors pressed stop
				PilotWrapper.pilot.stop();
				PilotWrapper.commands.clear();
			
			}
			else
			if (front_1.isPressed() && front_2.isPressed())
			{
			//front sensors pressed - move back
			
				PilotWrapper.pilot.stop();
				PilotWrapper.commands.clear();
				PilotWrapper.commands.add(122020);

			}
			else

			if (front_1.isPressed())
			{			
			//front left sensor pressed - move back and right
				PilotWrapper.pilot.stop();
				PilotWrapper.commands.clear();
				PilotWrapper.commands.add(122020);
				PilotWrapper.commands.add(123045);
				PilotWrapper.commands.add(121020);

		
			}
			else
			if (front_2.isPressed()) 
			{
		//front right sensor pressed - move back and left
				PilotWrapper.pilot.stop();
				PilotWrapper.commands.clear();
				PilotWrapper.commands.add(122020);
				PilotWrapper.commands.add(223045);
				PilotWrapper.commands.add(121020);

			}
			else
			if (back.isPressed()) 
			{
//back sensor pressed - move forward
				PilotWrapper.pilot.stop();
				PilotWrapper.commands.clear();
				PilotWrapper.commands.add(121020);
			}	



		}
	}
}
