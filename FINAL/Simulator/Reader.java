package Simulator;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Queue;
/* Reads commands sended by strategy and adds them to queue
 * runs as separated process
 * 
 */

public class Reader implements Runnable{

	
	private BufferedReader input;
	private Queue<Integer> q;

	
	public Reader(BufferedReader in, Queue<Integer> queue){
		input = in;
		q = queue;
	}
	
	@Override
	public void run() {
		while(true){
		try {
			int cmd = Integer.parseInt(input.readLine());
			if (cmd == 101010)
			{
				q.clear();
			}
			else
			q.add(cmd);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

}
