
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import lejos.nxt.LCD;

import lejos.robotics.navigation.*;
import lejos.nxt.*;
/*
This class is independent process which reads data from BT stream and adds them to commands queue

*/

class BTReader extends Thread 
{

public DataInputStream dataInStr;

public void run() {

int nextInt=0;		
// create data stream

LCD.drawString("creating data stream", 0, 1);		
		dataInStr = PilotWrapper.conn.openDataInputStream();
		
		if (dataInStr == null) {
			
		LCD.clear();
		LCD.drawString("NoDataStream", 0, 0);
		try{
		Thread.sleep(5000);		
		} catch (Exception e) {}
		}

while (true) //data is readed in non-breaking loop
{

try {	
//read data
nextInt = dataInStr.readInt();
LCD.drawInt(nextInt, 0, 2);
} catch (Exception ioe) 
{
		LCD.clear();
		LCD.drawString("CantReadFromStream", 0, 0);
		try{
		Thread.sleep(5000);		
		} catch (Exception e) {}

}
//execute command or add it to queue
if (nextInt == 101010) 
{
PilotWrapper.pilot.stop();
PilotWrapper.commands.clear();
}
else
PilotWrapper.commands.add(nextInt);


}



}
}
