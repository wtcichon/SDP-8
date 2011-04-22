package Simulator;
import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Queue;

public class Client {

    Socket kkSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    int i;
	private Queue<Integer> queue = new LinkedList<Integer>();

	
	public void establish()
	{
	//create connection with strategy module
		try {
            kkSocket = new Socket("localhost", 4444);
            out = new PrintWriter(kkSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
            Reader r = new Reader(in,queue);
            new Thread(r).start();
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to host.");
            System.exit(1);
        }

		
	}

	public void send(String msg) throws IOException {
    //send state of the world
		out.println(msg);
        }
        
    public boolean commandsWaiting(){
    	return !queue.isEmpty();
    }
    
    public int rec() throws IOException
//read command from command queue
    {
    		return queue.remove();
	}

    public void kill() throws IOException
//shut connection
{
	
		out.close();
        in.close();
        kkSocket.close();
    }
}
