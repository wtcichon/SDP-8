package Simulator;
import java.net.*;
import java.io.*;
/*
 * Server side of sim module
 * 
 */
public class Server {
	
	
	ServerSocket serverSocket = null;
    Socket clientSocket = null;
    PrintWriter out;
    BufferedReader inStr;
    Boolean connected = false; 
    
	public void create() throws IOException
	{
	    try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }
	
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
         out = new PrintWriter(clientSocket.getOutputStream(), true);
         inStr = new BufferedReader(
				new InputStreamReader(
				clientSocket.getInputStream()));
         connected = true;
	}
	
	
	public void send(int args) throws IOException {
        out.println(args);
	} 
	public String rec() throws IOException {

	     return inStr.readLine();
		} 
	
      public void kill() throws IOException
      {
    	connected = false;
        out.close();
        inStr.close();
        clientSocket.close();
        serverSocket.close();
    }

      
  public boolean getConnectedStatus(){
	  return connected;
  }
}
