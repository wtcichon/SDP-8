package Vision;


import java.awt.Point;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.border.MatteBorder;

import Shared.ObjectItem;
import au.edu.jcu.v4l4j.V4L4JConstants;
import au.edu.jcu.v4l4j.exceptions.V4L4JException;

    public class InVision {
//comments are pure evil !!!!
     
    //ugly way to define constant values - don't do this at home!!!
    int RED= 255<<16;
    int GRE= 255<<8;
    int BLU = 255;
    int YEL = RED+GRE;
    int WH = RED+GRE+BLU;
   
 //two points for define size of pitch - why is not rectangle? i don't have any idea
    Point PSt;
    Point Pen;


    
    //constructor where i set up connection to camera
public InVision()   
{
   System.out.println("   ***     ***  ");
   System.out.println("  *   *   *   *");
   System.out.println(" *  *  * *  *   *");
   System.out.println("*  *I*  *  *N*  *");
   System.out.println(" *  *  * *  *  *");
   System.out.println("  *   *   *   *");
   System.out.println("   ***     ***  VISION ");
   System.out.println("");
   System.out.println("All systems on");
   //setting up size of pitch
     PSt = new Point(10,82);
     Pen = new Point(630,406);

     String dev = "/dev/video0";
 	int w=640, h=480, std=V4L4JConstants.STANDARD_PAL, channel = 2, qty = 60;
    try 
 	{
	vis = new LiveStream(dev,w,h,std,channel,qty);
    } catch (V4L4JException e) 
 	{
 	//	System.out.println("Error " + e.getMessage());
    }


     }

LiveStream vis;

public ObjectItem[] findObjects()
{
    //calls vision to find objects
        return findObjects(
                        //stolenClass.removeDistortion
                        ((vis.getImage())));

}

public ObjectItem[] findObjects(BufferedImage img)    {


       
       
int imga[][] = new int [img.getWidth()][img.getHeight()];



//filtring colour to reduce number of c   
int red,gre,blu,pix;
for (int x= PSt.x; x<Pen.x;x++)
    for (int y= PSt.y; y<Pen.y;y++)
       {
        pix =   img.getRGB(x, y);
        //getting colours
        red = ((pix & 0x00ff0000) >>16);
        gre = ((pix & 0x0000ff00) >>8);
        blu = (pix & 0x000000ff);

        //big and ugly condition how i'm searching and replacing colours
       
        // black red blue yellow white
       
       
        /*
        if ((gre + red +blu <128)) pix = 0;
        else
        if ((gre > red) && (gre>blu+40)) pix = 0;
        else
        if ((gre <50) && (gre > red+10) && (gre>blu+10)) pix = 0;
        else
        if ((red < 80) && (blu <80) && (gre < 110) && (red >= 20)
                     && (blu >= 10) && (gre >= 60)  ) pix = 0;
        else
        if ((red > 165) && (gre >165)&& (blu <165)) pix = 3;
            else
        if ((red > 150) && (gre <160)&& (blu <120)) pix = 1;
        else
        if ((red > 85) && (gre <70)&& (blu <70)) pix = 1;
        else
      //  if ((red < 160) && (gre <160)&& (blu >140)) pix = BLU;
            if (gre>210 && blu >210 && red >210 ) pix = 4;
            else
        if ((red < blu) && (red<160) && (blu >160)&& (blu >gre)) pix = 2;
       
        //else
        //if ((red < 160) && (blu >190)&& (gre > 190) ) pix = BLU;
       
        else pix = 0;// img.getRGB(x, y);//0;//red+gre+blu;    
        */
  

/*
if ((gre + red +blu <128)) pix = 0;
else
if ((gre > red) && (gre>blu+40)) pix = 0;
else
if ((gre <50) && (gre > red+10) && (gre>blu+10)) pix = 0;
else
if ((red < 80) && (blu <80) && (gre < 110) && (red >= 20)
&& (blu >= 10) && (gre >= 60) ) pix = 0;
else
if ((red > 165) && (gre >165)&& (blu <165)) pix = 3;
else*/
// if ((red > 150) && (gre <160)&& (blu <120)) pix = 1;

// if ((red > 5)) pix = 1;
/* else
// if ((red < 160) && (gre <160)&& (blu >140)) pix = BLU;
if (gre>210 && blu >210 && red >210 ) pix = 4;
else
if ((red < blu) && (red<160) && (blu >160)&& (blu >gre)) pix = 2;

//else
//if ((red < 160) && (blu >190)&& (gre > 190) ) pix = BLU;

else pix = 0;// img.getRGB(x, y);//0;//red+gre+blu;
*/
// black red blue yellow white
// if (red > 100 && Math.abs(red -gre)<50 && blu <80 && Math.abs(blu-gre) > 50 ) pix = 3; //new
//else
// if (red > 210 && gre >210 && blu <210 ) pix = 3; //new
// else

        
 //there is lot of scrolling because different condition is for different pitch
if (red > 80 && red >gre && gre < 60 && Math.abs(blu-gre)<50 ) pix = 1; //new
// else
if ((gre > red) && (gre>blu+40)) pix = 0;
else
if ((gre <50) && (gre > red+10) && (gre>blu+10)) pix = 0;
else
if ((red < 80) && (blu <80) && (gre < 110) && (red >= 20)
&& (blu >= 10) && (gre >= 60) ) pix = 0;
//else
// if ((red > 215) && (gre >215)&& (blu <105)) pix = 3;

//else
// if ((red > 130) && (gre >130)&& (blu <100)) pix = 3; //condition for 2'nd lab

else
if ((red > 205) && (gre >205)&& (blu <205)) pix = 3;
else
if ((red > 150) && (gre <160)&& (blu <120)) pix = 1;
else
if ((red > 90) && (gre <80)&& (blu <80)) pix = 1;
else
if ((red < blu) && (blu >gre) && blu >50) pix = 2;
//if ((red < blu) && (red<160) && (blu >160)&& (blu >gre)) pix = 2;
// if ((red < 160) && (gre <160)&& (blu >140)) pix = BLU;


// else //canceled; good for 1st pitch just.
// if (gre>210 && blu >210 && red >210 ) pix = 4;
/// else
// if (red > 240 && gre >240 && blu > 231 ) pix = 4;
// else
// if (red > 240 && gre > 240 && blu < 221 ) pix = 3;



else pix = 0;



/* Working in lab 1
      // black red blue yellow white   
       // if (red >  100 && Math.abs(red -gre)<50  && blu <80  &&  Math.abs(blu-gre) > 50  ) pix = 3; //new
        //else
     //   if (red >  210 && gre >210 && blu <210  ) pix = 3; //new
      //  else
     //   if (red >  80 && red >gre && gre < 60 &&  Math.abs(blu-gre)<50  ) pix = 1; //new
     //   else
        if ((gre > red) && (gre>blu+40)) pix = 0;
        else
        if ((gre <50) && (gre > red+10) && (gre>blu+10)) pix = 0;
        else
        if ((red < 80) && (blu <80) && (gre < 110) && (red >= 20)
                     && (blu >= 10) && (gre >= 60)  ) pix = 0;
        //else
      //  if ((red > 215) && (gre >215)&& (blu <105)) pix = 3;

        //else
            //if ((red > 130) && (gre >130)&& (blu <100)) pix = 3; //condition for 2'nd lab
        
        else
        if ((red > 165) && (gre >165)&& (blu <165)) pix = 3;
        else
        if ((red > 150) && (gre <160)&& (blu <120)) pix = 1;
        else
        if ((red > 90) && (gre <80)&& (blu <80)) pix = 1;
        else
            if ((red < blu) && (blu >gre) && blu >50) pix = 2;
*/
        //if ((red < blu) && (red<160) && (blu >160)&& (blu >gre)) pix = 2;
      //  if ((red < 160) && (gre <160)&& (blu >140)) pix = BLU;
       
       
//            else //canceled; good for 1st pitch just.
//                if (gre>210 && blu >210 && red >210 ) pix = 4;
             //   else
             //       if (red > 240 && gre >240 && blu > 231  ) pix = 4;
            //        else
           //             if (red > 240 && gre > 240 && blu < 221  ) pix = 3;
           
               

    //    else pix = 0;
       
       
        if    (pix !=0) {
            //convert BufferedImage into integer matrix   
        	imga[x][y] = pix;
                
       }
      
       }
   

//                        {blue, yellow, ball };
//1... 2... 3.. find me objects hm... eeee?
ObjectItem obj[] = LabelImage.labelImage(imga, 2);

    //step two : quest for circles
    obj[0].setAngle(LabelImage.findBlack(imga, obj[0].getPosition().x, obj[0].getPosition().y, obj[0].getRealSize().x, obj[0].getRealSize().y));
	obj[1].setAngle(LabelImage.findBlack(imga, obj[1].getPosition().x, obj[1].getPosition().y, obj[1].getRealSize().x, obj[1].getRealSize().y));
  
    //reverse colours --> use for debug
	/*
    for (int x= PSt.x; x<Pen.x;x++){
        for (int y= PSt.y; y<Pen.y;y++)
        {
            //getting colours
            //red = ((pix & 0x00ff0000) >>16);
            //gre = ((pix & 0x0000ff00) >>8);
            //blu = (pix & 0x000000ff);

           
            if (imga[x][y] == 3) {
                img.setRGB(x, y,(0x00ffff00));
            } //new
            else
                if (imga[x][y] == 1) {
                    img.setRGB(x, y,(0x00ff0000));
            } //new
            else
                if (imga[x][y] == 0) {
                    img.setRGB(x, y,(0x0000000));
                }
                else
            if (imga[x][y] == 2) {
                img.setRGB(x, y,(0x000000ff));
            }
            else
            if (imga[x][y] == 4) {
                img.setRGB(x, y,(0x00ffffff));
            }
        }
    }
   //capture image --> debug only
    File post = new File("post.jpg");
    try
        {
         
         ImageIO.write(img, "JPG", post);
        } catch (IOException e)
        {
              System.out.print("Error");
              e.printStackTrace();
        }
*/

	return obj;
}
}      
