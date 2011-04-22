package Shared;

import java.awt.Point;
import java.awt.image.*; 
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;

public class Ally 
{
//library contains shared static functions
// created for memory of all who fall in battle against Vision :)

static int rVal = 230;
static int bVal = 230;
static int gVal = 230;
	

static int RED= 255<<16;
static int GRE = 255<<8;
static int BLU = 255;
static int WH = RED+ GRE +BLU;

public static double getBallAngleAxis(int botx, int boty, int ballx, int bally)
//returns angle of line between robot and ball
{
	double a = Math.sqrt((Math.pow((ballx - botx),2) + Math.pow(boty,2))) ;
	double b = Math.sqrt(Math.pow((ballx - botx),2) + Math.pow((bally - boty),2));
	double c = bally;
	double radians = Math.acos( ( Math.pow(b,2) + Math.pow(c,2) - Math.pow(a,2) ) / (2*b*c) );
	double degrees = (180/Math.PI) * radians ;
	return degrees;
}
	public static double getBallBotAngleToTurn(double botangle, int botx, int boty, int ballx, int bally){
	//returns angle which robot needed to turn to face ball
		return botangle - getBallAngleAxis(botx,boty,ballx,bally);
	}

	public static double getDistanceBotBall(int botx, int boty, int ballx, int bally){
	//gets eucledean distance between point and ball
		return Math.sqrt( Math.pow((ballx - botx),2) + Math.pow(bally-boty,2) );
	}

//converts size from pixes to cm
public static double convertPxToCm(int pixels){
	//used to work on main pitch return pixels * (19.8+12.7) /(54+34);
	// mainly used 243.84+19.8+12.7+3)/(586.0+54+34
	return ((double) pixels) * ((243.84+19.8+12.7)/(586.0+54+34));
}

//converts size from cm to pixes
public static double convertCmToPx(int cm){
	//used to work on main pitch return pixels * (19.8+12.7) /(54+34);
	return ((double) cm) * ((586.0+54+34)/(243.84+19.8+12.7));
}
public static BufferedImage bgRemoval(BufferedImage pic, BufferedImage bg)
/* removes background from image
 * takes 2 arguments type BufferedImage
 *  pic - actual picture
 *  bg - picture of background withput objects
 *  
 *  returns 
 *  picture of objects
 *  * 
 */
{
  int r=0,g=0,b=0;

 for (int x= 0; x<pic.getWidth();x++)
   for (int y= 0; y<pic.getHeight(); y++)
		 {
            r = Math.abs((
                    pic.getRGB(x, y) & 0x00ff0000)-(bg.getRGB(x, y) & 0x00ff0000)) >>16;
            if (r<50) r = 0; else 	r = pic.getRGB(x, y) & 0x00ff0000 ;//r = 0x00ff0000;

            g = Math.abs((
                    pic.getRGB(x, y) & 0x0000ff00)-(bg.getRGB(x, y) & 0x0000ff00)) >>8;
            if (g<50) g = 0; else 	g = pic.getRGB(x, y) & 0x0000ff00 ; //g = 0x0000ff00;

            b = Math.abs((
                    pic.getRGB(x, y) & 0x000000ff)-(bg.getRGB(x, y) & 0x000000ff));

            if (b<50) b = 0; else	b = pic.getRGB(x, y) & 0x000000ff;
            
            int pix = r + g +r;
            pic.setRGB(x, y, pix);
            }
        
            return pic;
        }

 





public static double vectorToAngle(Point a)
//returns angle of given vector 
{
double Angle = 0;

Angle = Math.toDegrees(Math.acos( 
		a.y/Math.sqrt(a.x*a.x+a.y*a.y)
			));
if (a.x<0) Angle = -Angle;	

return Angle;
}

public static Point vectorFromTo(Point a, Point b)
//returns vector from point a to point b
{
if (a == null || b== null) 	
{
System.out.println("Error : no points");
return new Point();

}
return new Point(b.x-a.x,b.y-a.y);
}

public static Point addVectors(Point a, Point b)
{
//returns sum of given vectors
return new Point(b.x+a.x,b.y+a.y);
}

public static BufferedImage RGB2Gray(BufferedImage pic)
//returns vonvert given image to grayscale
{
		BufferedImageOp op = new ColorConvertOp(
		ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		return op.filter(pic, null);
}

public static BufferedImage bgRemoval_old(BufferedImage pic, BufferedImage bg)
{
   int r=0,g=0,b=0;
    for (int x= 0; x<pic.getWidth();x++)
        for (int y= 0; y<pic.getHeight(); y++)
        {
        	r = Math.abs((
        			pic.getRGB(x, y) & 0x00ff0000)-(bg.getRGB(x, y) & 0x00ff0000)) >>16;
        	if (r<20) r =0;
        	g = Math.abs((
        			pic.getRGB(x, y) & 0x0000ff00)-(bg.getRGB(x, y) & 0x0000ff00)) >>8;
        	if (g<20) g =0;
        	b = Math.abs((
        			pic.getRGB(x, y) & 0x000000ff)-(bg.getRGB(x, y) & 0x000000ff));
        	if (b<20) b =0;
        	if (r+b+g ==0)pic.setRGB(x, y, 0x00ffffff);
        	else
        	pic.setRGB(x, y, 
        			((((r<<8)+g)<<8)+b));
        } 
return pic;
}



public static int distance(Point a,Point b)
//calculates Euclidean distance between given points
{
	int x = Math.abs(a.x-b.x);
	int y = Math.abs(a.y-b.y);
	int dist = (int) Math.sqrt(x*x+y*y);
	return dist;
}


static int	kNN(BufferedImage img)
//assign colour of pixel in centre of tile based on colours of other pixels
{
int RED,GRE,BLU;
	RED = 255<<16;
    GRE = 255<<8;
    BLU = 255;
int col[] = new int[9];//{0 R G B RG RB GR RGB UNKNOWN} 	
    
for (int x = 0; x< img.getWidth();x++)    
	for (int y = 0; y< img.getHeight();y++)
	{
		int pix = img.getRGB(x, y);
	     pix  = (pix & 0x00ffffff);
	     
		switch(pix)
		{
			case 0: 	 //Black
				col[0]++;
				break;
			case 255<<16: //Red
			
				col[1]++;
				break;
			case 255<<8:  //green
				col[2]++;
				break;
			case 255:   	//Blue
				col[3]++;
				break;
			case ((255<<16)+(255<<8)): // Red + Green
				col[4]++;
				break;
			case ((255<<16)+255): //Red + Blue
				col[5]++;
				break;
			case ((255<<8) + 255): //Green + Blue
				col[6]++;
				break;
			case ((255<<16)+(255<<8)+255):   //white
				col[7]++;
				break;
			default:
				col[8]++;
				break;
		}//end of switch
	} //end of big ugly for loop

int max=0, poz =0;
for (int i = 0; i<9;i++)
	if (col[i] > max ) { max = col[i]; poz = i;}
// 1 -red
//4 yellow
if (poz == 1 && col[4] >0 ) poz = 0; 
if (poz == 0 && col[4]>  col[1] ) poz = 0; 

//if (poz == 4 && col[0] >3 ) poz = 0;

switch(poz)
{
	case 0:  //Black
		return 0;
	//	return img.getRGB(img.getWidth()/2, img.getHeight()/2 );
		
	case 1: //Red
		return RED;
	case 2:  //green
		return GRE;
	case 3:   //Blue
		return BLU;
	case 4: // Red + Green
		return RED+GRE;
	case 5: //Red + Blue
		return RED+BLU;
	case 6: //Green + Blue
		return GRE+BLU;
	case 7:   //white
		return RED+BLU+GRE;
	case 8:   //if unknown rules don't do anything
		return 0;//img.getRGB(img.getWidth()/2, img.getHeight()/2);
		
}//end of switch


return -1;
}
	
static int	kNN2(int a[][],int x,int y,int size)
{
//add out of boundry safety exit !!! 
// black red blue yellow white
//System.out.println("KNN");
int col[] = new int[5];
	
for (int i = x-size; i<= x+size;i++)    
	for (int j = y-size; j<= y+size;j++)  
	{

		//System.out.println("pix ["+i+","+j+"] = "+a[i][j]);
		if (i>0 && i<a.length &&j>0 &&j<a[0].length)
		{
			System.out.println(" "+i+"+"+j);
			col[a[i][j]]++;
		
		
		}
	}
int max=0, poz =0;
for (int i = 0; i<5;i++)
	if (col[i] > max ) { max = col[i]; poz = i;}
return poz;
}


//barrel distortion conversion
private final static int width = 640;
private final static int height = 480;
private final static double ax = -0.016;
private final static double ay = -0.06;



public static Point convertPixel(Point p) {
//recalculate points coordinates to remove barrel distortion
	// first normalise pixel
	double px = (2 * p.x - width) / (double) width;
	double py = (2 * p.y - height) / (double) height;

	// then compute the radius of the pixel you are working with
	double rad = px*px + py*py;

	// then compute new pixel'
	double px1 = px * (1 - ax * rad);
	double py1 = py * (1 - ay * rad);

	// then convert back
	int pixi = (int) ((px1 + 1) * width / 2);
	int pixj = (int) ((py1 + 1) * height / 2);

	return new Point ((int) pixi, (int) pixj);
}






public static Point getCircle(BufferedImage img) {
//search for white object in given image
// white blocks were used as orientation points for getting angle
	
	ObjectItem o = new ObjectItem();
	Point c = new Point(img.getWidth()/2,img.getHeight()/2);
	for (int x= 0; x<img.getWidth();x++)
		for (int y= 0; y<img.getHeight();y++)
           {

			int pix = img.getRGB(x, y) & 0x00ffffff;
       	    
	   	    //searching for objects 
       	  	if ( pix == WH ) {o.SearchNewPos(x, y);o.SearchNewSize(x, y);}
}

	return o.getCentre();
}



public static Point getCircle(int img[][] ,int x,int y,int range) {
//search for white object in picture in square tile placed in point (x,y) and size range
	ObjectItem o = new ObjectItem();
	for (int i = x-range; i<= x+range;i++)    
		for (int j = y-range; j<= y+range;j++) 
           {
			if (i>0 && i<640 &&j>0 &&j<480)
			  	  	if ( img[i][j] == 4 ) {o.SearchNewPos(i, j);o.SearchNewSize(i, j);}
           }

	return o.getCentre();
}



public static ObjectItem new_instance(ObjectItem o) {
	ObjectItem new_o = new ObjectItem();
	
	new_o.setPosition(o.getPosition().x ,o.getPosition().y) ;
	new_o.setSize(o.getSize().x+o.getPosition().x,o.getSize().y+o.getPosition().y);
	
	return new_o;
	}

}



/* old methods
static boolean	isCircle(int x, int y,BufferedImage img)
{
	int col = (img.getRGB(x, y) & 0x00ffffff);
	int N=0, E=0, S=0, W=0,NE=0,NW = 0,SE=0,SW=0;
	for (int i = 1; i<10;i++)
	{
		if ((W==i-1)&&(col == (img.getRGB(x-i, y) & 0x00ffffff))) W++;
		if ((E==i-1)&&(col == (img.getRGB(x+i, y) & 0x00ffffff))) E++;
		if ((N==i-1)&&(col == (img.getRGB(x, y-i) & 0x00ffffff))) N++;
		if ((S==i-1)&&(col == (img.getRGB(x, y+i) & 0x00ffffff))) S++;
		if (col == (img.getRGB(x-i, y-i) & 0x00ffffff)) NW++;
		if (col == (img.getRGB(x+i, y+i) & 0x00ffffff)) SE++;
		if (col == (img.getRGB(x+i, y-i) & 0x00ffffff)) NE++;
		if (col == (img.getRGB(x-i, y+i) & 0x00ffffff)) SW++;
		if (
				//(i>(W+2)) &&(i>(E+2))&&(i>(S+2))&&(i>(N+2)) &&
				
				(Math.abs(S-N)<2) && (Math.abs(W-E)<2) &&
				(Math.abs(S-W)<2) && (Math.abs(E-N)<2) &&
				(Math.abs(W-N)<2) && (Math.abs(N-E)<2) &&
				
			//	(Math.abs(SW-NW)<2) && (Math.abs(SE-NE)<2) &&
			//	(Math.abs(SW-SE)<2) && (Math.abs(NE-NW)<2) &&
			//	(Math.abs(SW-NE)<2) && (Math.abs(NW-SE)<2) &&
			
				(SW+NW+SE+NE < N+S+E+W)	&&
		
				
					(W<6)&&(S<6)&&(N<6)&&(E<6)
					&& (W>3)&&(S>3)&&(N>3)&&(E>3) &&
				// (SW<5)&&(SE<6)&&(NW<6)&&(NE<6)
					//&& (NE>3)&&(SW>3)&&(NW>3)&&(SE>3)&& 
					
					//??? don't know why i add this one cos it's do same like first  condition
					 (N*E*S*W != 0) 
				//	&& (NE*SE*SE*NW != 0)
					) 
					
		{
					//	System.out.println("circle ar "+x+","+y);
						return true;
						}
	}
	
	return false;
}

static Point ClosestTo(ArrayList<Point> points, Point p) //to check
{
	Point x =  new Point(-1,-1);
	int d = distance(p,x);
	
	for(int i = 0; i<points.size();i++)
	{
		int a =distance(p, points.get(i));
	
		if (
				(a<d) && (a>5) && (a<25))
				
		{
			d = a; 
			x =points.get(i);
			
		} 
	}	
	
	
	
	
	return x;
}
*/
	
