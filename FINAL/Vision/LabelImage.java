package Vision;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Stack;

import Shared.Ally;
import Shared.ObjectItem;

/* Modified class, originally used to label all objects in binary image
 * i'm using to identyfie different objects in my maped image
 *
 * To find all object methos is using Breadth-first search, 
 * cheching all connected pisels in same colour to join them as objects
 */


public class LabelImage {

	
/** Creates a new instance of LabelImage */

  static public ObjectItem[] labelImage(int[][] img, int stackSize) {
/* Finds biggest object in each colour
 * 
 * 
 */
    	Stack<Point> stack ;
    
    	ObjectItem red = new ObjectItem();
    	ObjectItem blu = new ObjectItem();
    	ObjectItem yel = new ObjectItem();
    	int col;
    	
    	int nrow = img.length ;
        int ncol = img[0].length ;
        int lab = 1;
        int rad =0;
        
        int memr = 0;
        int memy = 0;
        int memb = 0;
        
        Point pos ;
        stack = new Stack<Point>() ;
        int[][] label = new int[nrow][ncol] ;
        for (int r = 1; r < nrow-1; r++)
            for (int c = 1; c < ncol-1; c++) {
            if (img[r][c] == 0) continue ;
            if (label[r][c] > 0) continue; 
            int p = 0,a=0;
            col = img[r][c];
        	ObjectItem o = new ObjectItem();
            
        	
            
        	/* encountered unlabeled foreground pixel at position r, c */
            /* push the position on the stack and assign label */
            stack.push(new Point(r, c)) ;
            label[r][c] = lab ;
            /* start the float fill */
            while ( !stack.isEmpty()) {
            
            	int n=0;
               	pos = stack.pop() ;
                int i = pos.x; int j = pos.y;
                o.SearchNewPos(i, j);
            	o.SearchNewSize(i, j);
                //   System.out.println("i= "+i+" j="+j);
                if (img[i-1][j-1] == col && label[i-1][j-1] == 0) {
                     stack.push( new Point (i-1,j-1) );
                     label[i-1][j-1] = lab ;
                }
                  if (img[i-1][j] == col && label[i-1][j] == 0) {
                     stack.push( new Point (i-1,j) );
                     label[i-1][j] = lab ;
                }
                  if (img[i-1][j+1] == col && label[i-1][j+1] == 0) {
                     stack.push( new Point (i-1,j+1) );
                     label[i-1][j+1] = lab ;
                }
                  if (img[i][j-1] == col && label[i][j-1] == 0) {
                     stack.push(new Point (i,j-1) );
                     label[i][j-1] = lab ;
                }
                  if (img[i][j+1] == col && label[i][j+1] == 0) {
                    stack.push( new Point (i,j+1) );
                    label[i][j+1] = lab ;
                }
                if (img[i+1][j-1] == col && label[i+1][j-1] == 0) {
                	stack.push( new Point (i+1,j-1) );
                    label[i+1][j-1] = lab ;
                }
                if (img[i+1][j] == 1 && label[i+1][j] == 0) {
                	stack.push( new Point (i+1,j) );
                    label[i+1][j] = lab ;
                }
                if (img[i+1][j+1] == 1 && label[i+1][j+1] == 0) {
                	stack.push( new Point (i+1,j+1) );
                    label[i+1][j+1] = lab ;
                }
                  a++;
                
            } /* end while */
           
            if (col == 1) //red
            {
            	if (a>memr)
            	{
            	red = Ally.new_instance(o);
            	memr = a;
            	}
            }
            if (col == 2) //blue
            {
            	if (a>memb)
            	{
                	blu = Ally.new_instance(o);
                	memb=a;
            	}	
            	
            }
            if (col == 3) //yellow
            {
            	if (a>memy)
            	{
            		yel = Ally.new_instance(o);
            		memy=a;	
            	}
            }

            
           
            }
     //   System.out.println("red :" + memr);
        //System.out.println("labels :" + lab);
        ObjectItem obj [] = {blu, yel, red }; 
    	return obj;
         //label ;
       }

    

    
    static public Point findWhite(int[][] img,int x0,int y0,int size) {
 /* this method returns centre of biggest white object
  *  in image img, in tile started in position (x0,y0) and size size
  */
    	
    	Stack<Point> stack ;
    
    	ObjectItem white = new ObjectItem();

    	int col;
    	
    	int nrow = img.length ;
        int ncol = img[0].length ;
        int lab = 1;
        int rad =0;
        int blob = 0;
        int memw = 0;
        
        Point pos ;
        stack = new Stack<Point>() ;
        int[][] label = new int[nrow][ncol] ;
        for (int r = x0; r < x0+size; r++)
            for (int c = y0; c < y0+size; c++) {
            	if (r<0 || c<0 )continue;
                if (r>=label.length) continue;
                if (c>=label[0].length) continue;
            	if (img[r][c] == 0) continue ;
            if (label[r][c] > 0) continue; 
      
            if (img[r][c] != 4 ) continue;
            int p = 0,a=0;
            col = img[r][c];
        	ObjectItem o = new ObjectItem();
            
        	
            
        	/* encountered unlabeled foreground pixel at position r, c */
            /* push the position on the stack and assign label */
            stack.push(new Point(r, c)) ;
            label[r][c] = lab ;
            /* start the float fill */
            while ( !stack.isEmpty()) {
            
            	int n=0;
               	pos = stack.pop() ;
                int i = pos.x; int j = pos.y;
                o.SearchNewPos(i, j);
            	o.SearchNewSize(i, j);
                //   System.out.println("i= "+i+" j="+j);
                if (img[i-1][j-1] == col && label[i-1][j-1] == 0) {
                     stack.push( new Point (i-1,j-1) );
                     label[i-1][j-1] = lab ;
                }
                  if (img[i-1][j] == col && label[i-1][j] == 0) {
                     stack.push( new Point (i-1,j) );
                     label[i-1][j] = lab ;
                }
                  if (img[i-1][j+1] == col && label[i-1][j+1] == 0) {
                     stack.push( new Point (i-1,j+1) );
                     label[i-1][j+1] = lab ;
                }
                  if (img[i][j-1] == col && label[i][j-1] == 0) {
                     stack.push(new Point (i,j-1) );
                     label[i][j-1] = lab ;
                }
                  if (img[i][j+1] == col && label[i][j+1] == 0) {
                    stack.push( new Point (i,j+1) );
                    label[i][j+1] = lab ;
                }
                if (img[i+1][j-1] == col && label[i+1][j-1] == 0) {
                	stack.push( new Point (i+1,j-1) );
                    label[i+1][j-1] = lab ;
                }
                if (img[i+1][j] == 1 && label[i+1][j] == 0) {
                	stack.push( new Point (i+1,j) );
                    label[i+1][j] = lab ;
                }
                if (img[i+1][j+1] == 1 && label[i+1][j+1] == 0) {
                	stack.push( new Point (i+1,j+1) );
                    label[i+1][j+1] = lab ;
                }
                  a++;
                
            } /* end while */
           
            if (col == 4) //red
            {
            	if (a>memw)
            	{
            		blob++;
            	white = Ally.new_instance(o);
            	memw = a;
            	}
            }
            
            
           
            }
        
        System.out.println("There was  :" + blob + " white objects");
        //System.out.println("labels :" + lab);
        return white.getCentre();
         //label ;
       }
    
    

    static public double findBlack(int[][] img, int fromX, int fromY,int toX,int toY) {
   	/* this method search for 2biggest black objects,
   	 * and returns angle of vector perpendicular to vector from centre of first object to second
     *  
     */
    	 
    	Stack<Point> stack ;
    
    	ObjectItem bl1 = new ObjectItem();
    	ObjectItem bl2 = new ObjectItem();
    	int size1= 0;
    	int size2=0;
    	int col;
    	
    	int nrow = img.length ;
        int ncol = img[0].length ;
        int lab = 1;
        int rad =0;
        
        
        Point pos ;
        stack = new Stack<Point>() ;
        int[][] label = new int[nrow][ncol] ;
        for (int r = fromX+1; r < toX-1; r++)
            for (int c = fromY+1; c < toY-1; c++) {
            if (img[r][c] != 0) continue ;
            if (label[r][c] > 0) continue; 
            int p = 0,a=0;
            col = img[r][c];
        	ObjectItem o = new ObjectItem();
            
        	
            
        	/* encountered unlabeled foreground pixel at position r, c */
            /* push the position on the stack and assign label */
            stack.push(new Point(r, c)) ;
         //   System.out.println(" Pushed ["+r+","+ c + "]");
            label[r][c] = lab ;
            /* start the float fill */
            while ( !stack.isEmpty()) {
            
            	int n=0;
               	pos = stack.pop() ;
               	if ((pos.x<fromX) || (pos.x>toX)) continue;
               	if ((pos.y<fromY) || (pos.y>toY)) continue;
            //   	System.out.println(" Poped ["+pos.x+","+pos.y + "]");
         //      	img[pos.x ][pos.y] = 5;
                int i = pos.x; int j = pos.y;
                o.SearchNewPos(i, j);
            	o.SearchNewSize(i, j);
                //   System.out.println("i= "+i+" j="+j);
                if (img[i-1][j-1] == col && label[i-1][j-1] == 0) {
                     stack.push( new Point (i-1,j-1) );
                     label[i-1][j-1] = lab ;
                }
                  if (img[i-1][j] == col && label[i-1][j] == 0) {
                     stack.push( new Point (i-1,j) );
                     label[i-1][j] = lab ;
                }
                  if (img[i-1][j+1] == col && label[i-1][j+1] == 0) {
                     stack.push( new Point (i-1,j+1) );
                     label[i-1][j+1] = lab ;
                }
                  if (img[i][j-1] == col && label[i][j-1] == 0) {
                     stack.push(new Point (i,j-1) );
                     label[i][j-1] = lab ;
                }
                  if (img[i][j+1] == col && label[i][j+1] == 0) {
                    stack.push( new Point (i,j+1) );
                    label[i][j+1] = lab ;
                }
                if (img[i+1][j-1] == col && label[i+1][j-1] == 0) {
                	stack.push( new Point (i+1,j-1) );
                    label[i+1][j-1] = lab ;
                }
                if (img[i+1][j] == 1 && label[i+1][j] == 0) {
                	stack.push( new Point (i+1,j) );
                    label[i+1][j] = lab ;
                }
                if (img[i+1][j+1] == 1 && label[i+1][j+1] == 0) {
                	stack.push( new Point (i+1,j+1) );
                    label[i+1][j+1] = lab ;
                }
                  a++;
                
            } /* end while */
           
            if (col == 0) //black
            {
            	if (a>size1) // if area > biggest black object
            	{
            	size2 = size1; //set size as smaller
            	bl2 =Ally.new_instance(bl1); 
            	size1 = a; //set biggest 
            	bl1 =Ally.new_instance(o);
            	
            	
            	}
            	else
            	if (a>size2)
                {
            		size2 = a;
                	bl2 =Ally.new_instance(o);
                }
                	
            }
            
            
           
            }
        //problem with facing
        Point v = Ally.vectorFromTo(bl1.getCentre(),bl2.getCentre()); //get vector between two centers
        int h = v.x; 
        v.x = -v.y;
        v.y = h;
        Point v2 = new Point(-v.x,-v.y);
        Point centre =  new Point(toX-fromX,toY-fromY);
    //if (Ally.distance(Ally.PointAdd(bl1.getCentre(),v),centre)>
    	//Ally.distance(Ally.PointAdd(bl1.getCentre(),v2),centre))
    	//v =v2;
        Point vp1,vp2;
        int num1=0, num2=0;
        for (double i= 0.01;i<5.0;i=i+0.01)
        {
        vp1 = Ally.addVectors(bl2.getCentre(),new Point( (int) (v.x*i),(int) (v.y*i)) );
        vp2 = Ally.addVectors(bl2.getCentre(),new Point( (int) (v2.x*i),(int) (v2.y*i)));

        if ((vp2.x)>=0 && (vp2.x)<640 && (vp2.y)>=0 && (vp2.y)<480 ) //check if within pitch
        if (img[(int) (vp2.x)][(int) (vp2.y)] != 0)
        num2++;
        if ((vp1.x)>=0 && (vp1.x)<640 && (vp1.y)>=0 && (vp1.y)<480 ) //check if within pitch
        if (img[(int) (vp1.x)][(int) (vp1.y)] != 0)
        num1++;

        }

        if (num1 > num2)
        v = v2;
        
        
    //    System.out.println(" p1" +bl1.getCentre());
      //  System.out.println(" p2" +bl2.getCentre());
        Double a = Ally.vectorToAngle(v);
        
//        System.out.println("a " +a);
      //  if (Ally.distance(Ally.PointAdd(bl1.getCentre(),v),centre)>
    //   	Ally.distance(Ally.PointAdd(bl1.getCentre(),v2),centre))
      //  a= -a;  
    
        //System.out.println("labels :" + lab);
        //ObjectItem obj [] = {blu, yel, red }; 
    	return a;
         //label ;
       }
    
    
   }
