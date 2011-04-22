public class DataExchange {
//* UNUSED CLASS

	private boolean obstacleDetected = false;
	
	private int CMD = 0;
	
	public DataExchange() {
	}
	
	public synchronized void setObstacleDetected(boolean status) {
		obstacleDetected = status;
	}
	
	public synchronized boolean getObstacleDetected() {
		return obstacleDetected;
	}
	
	public synchronized void setCMD(int command) {
		CMD = command;
	}
	
	public synchronized int getCMD() {
		return CMD;
	}
}
