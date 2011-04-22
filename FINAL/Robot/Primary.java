import lejos.nxt.*;
import lejos.nxt.comm.Bluetooth;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
//main class which starts all processes

public class Primary {


	private static RobotThread elim;
	private static SensorThread st;
	private static 	BTReader btr;
	
	public static void main(String[] args) {
		PilotWrapper.commands = new ArrayList<Integer>();
	
		LCD.clear();
		LCD.drawString("Waiting for connection...", 0, 0);
		//create connection
		PilotWrapper.conn = Bluetooth.waitForConnection();		
		if (PilotWrapper.conn == null) {
		LCD.clear();
		LCD.drawString("NoConnection", 0, 0);
		try{
		Thread.sleep(5000);		
		} catch (Exception e) {}
		}

		//create processes
		BTSender.create();	
		st = new SensorThread();
		elim = new RobotThread();
		btr = new BTReader();
			
		//create start processes
		st.start();
		elim.start();
		btr.start();
		
		while(!Button.ESCAPE.isPressed()){
			//Empty
		}
		LCD.drawString("Finished",0, 7); 
		LCD.refresh();
		System.exit(0);
	}
}
