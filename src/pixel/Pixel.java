/**
 * Nick Robinson
 * CIS 2168 Section 1
 * Assignment 1: Pixelization
 * This project takes in an image and pixelizes it depending on the position of the slider
 * 
 */



package pixel;
//---------------------- Imports for Today ----------------------------------
import java.awt.Color;
import simplegui.*;
//---------------------Start of Class-------------------------
public class Pixel {
//---------------------Pixelates Image-------------------------
    private static void pixelateImage(DrwImage im, SimpleGUI sg, int gridSize) {
        //sg.drawFilledBox(0.0, 0.0, 100, 100, Color.red, 1.0, null);
        sg.eraseAllDrawables("box");
        for (int row = 0; row < im.getHeight(); row = row + gridSize) {
            for ( int column = 0; column < im.getWidth(); column = column + gridSize) {
                Color color = determineAveragecolor(im, column, row, gridSize);
                sg.drawFilledBox(column, row, gridSize, gridSize, color, 1.0, "box");
            }
            
        }
    }
    //-----------------------Computation of Average Color -------------------------------------
    public static Color determineAveragecolor(DrwImage im, int column, int row, int gridSize) {
        int sumR = 0;
        int sumG = 0;
        int sumB = 0;
        
        for (int bRow = row; bRow < row + gridSize; bRow++) {
            for (int bColumn = column; bColumn < column + gridSize; bColumn++) {
                RGB rgb = im.getPixelRGB(bColumn, bRow);
                if (rgb != null) {
                    int r = rgb.r;
                    sumR += r;
                    int g = rgb.g;
                    sumG += g;
                    int b = rgb.b;
                    sumB += b;
                }
                //end if
                
            }
        }
        
        sumR = sumR / (gridSize * gridSize);
        sumG = sumG / (gridSize * gridSize);
        sumB = sumB / (gridSize * gridSize);
        
        Color c = new Color(sumR, sumG, sumB);
        return c;
 }
            
 
//---------------------Start of Main-------------------------
    public static void main(String[] args) {
    
        DrwImage im = new DrwImage("test.jpg");
        SimpleGUI sg = new SimpleGUI (im.getWidth(), im.getHeight());
        
         
        sg.labelButton1("Pixelize");
        sg.labelButton2(" ");
         
        sg.drawImage(im,0,0,im.getWidth(),im.getHeight(),null);
        

        
        while (true) {
            sg.waitForButton1();
            //---------- Loop that runs through and draws the image ----------
            
            sg.eraseAllDrawables("label");
            int grid_size = sg.getSliderValue();
            //makes sure that slider is not set completely to 0, if so, throw an error and rerun
            if (grid_size > 0) {
            pixelateImage(im, sg, grid_size);
            } else {
                sg.drawText("Slider cannot be set to 0. Reset Slider and try again.", im.getHeight()/ 2, im.getWidth()/2, Color.white, 1.0, "label");
                
            }  
        }
        
        
    
    
    
    //----------------------End of Main----------------------
    }

    
    //----------------------End of Class----------------------    
}