package Vision;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import au.edu.jcu.v4l4j.FrameGrabber;
import au.edu.jcu.v4l4j.exceptions.V4L4JException;
import au.edu.jcu.v4l4j.VideoDevice;
import au.edu.jcu.v4l4j.V4L4JConstants;
import au.edu.jcu.v4l4j.VideoFrame;
/* This class is used to capture image from camera using v4l4j library
 * it's copied from v4l4j sample viewer
 * 
 * 
 */
public class LiveStream extends WindowAdapter{
        private VideoDevice vd;
        private JLabel l;
        private JFrame f;
        private FrameGrabber fg;
        

        private int width, height;
        
        /**
         * Builds a WebcamViewer object
         * @param dev the video device file to capture from
         * @param w the desired capture width
         * @param h the desired capture height
         * @param std the capture standard
         * @param channel the capture channel
         * @param qty the JPEG compression quality
         * @throws V4L4JException if any parameter if invalid
         */
    public LiveStream(String dev, int w, int h, int std, int channel, int qty) throws V4L4JException{
        initFrameGrabber(dev, w, h, std, channel, qty);
    //    initGUI();
   
    }
    
    /** 
     * Creates the graphical interface components and initialises them
     */
   
    
    /**
     * Initialises the FrameGrabber object with the given parameters
         * @param dev the video device file to capture from
         * @param w the desired capture width
         * @param h the desired capture height
         * @param std the capture standard
         * @param channel the capture channel
         * @param qty the JPEG compression quality
         * @throws V4L4JException if any parameter if invalid
     */
    private void initFrameGrabber(String dev, int w, int h, int std, int channel, int qty) throws V4L4JException{
        vd = new VideoDevice(dev);
        fg = vd.getJPEGFrameGrabber(w, h, channel, std, qty);
        fg.startCapture();
        width = fg.getWidth();
        height = fg.getHeight();
        System.out.println("Starting capture at "+fg.getWidth()+"x"+fg.getHeight());            
    }
    
    /**
     * Updates the image shown in the JLabel
     * @param b
     */
    public void setImage(BufferedImage image) {
        l.getGraphics().drawImage(image, 0, 0, width, height, null);
    }
    
    public BufferedImage getImage()
    {
    	 BufferedImage a;
    	  VideoFrame frame;
    	try {
    //		System.out.println("capture frame");
    		
    		frame = fg.getVideoFrame();
    	//	System.out.println("frame 2 img");
    		
    		 a = frame.getBufferedImage();
    	//	System.out.println("bin frame");
    		
    		frame.recycle();
    	
    		
           
    	} catch (V4L4JException e) {
    		
    		System.out.println("Failed to capture image");
    		return null;
    	}
    	 return a;
    }
    /**
     * Implements the capture thread: get a frame from the FrameGrabber, and display it
     */
 
    /*
    public void run(){
        VideoFrame frame;
        try {                   
                while(!stop){
                        frame = fg.getVideoFrame();
                        setImage(frame.getBufferedImage());
                        frame.recycle();
                }
        } catch (V4L4JException e) {
                e.printStackTrace();
                System.out.println("Failed to capture image");
        }
    }
*/
    /**
     * Catch window closing event so we can free up resources before exiting
     * @param e
     */
  /*      public void windowClosing(WindowEvent e) {
                if(captureThread.isAlive()){
                        stop = true;
                        try {
                                captureThread.join();
                        } catch (InterruptedException e1) {}
                }
                
                fg.stopCapture();
                vd.releaseFrameGrabber();
                
                f.dispose();            
        }
    */    
        

   /*     public static void main(String[] args) throws V4L4JException, IOException {

                String dev = "/dev/video0";
                int w=640, h=480, std=V4L4JConstants.STANDARD_PAL, channel = 2, qty = 60;
          //      .STANDARD_WEBCAM, channel = 0, qty = 60;
                new liveStream(dev,w,h,std,channel,qty);
*/        }