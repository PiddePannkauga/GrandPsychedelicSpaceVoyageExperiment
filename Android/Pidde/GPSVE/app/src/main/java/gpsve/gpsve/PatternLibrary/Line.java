package gpsve.gpsve.PatternLibrary;

import gpsve.gpsve.Interface.PatternLibraryInterface;
import processing.core.PApplet;

/**
 * Created by Nils Lindkvist on 2017-05-17.
 */

public class Line implements PatternLibraryInterface {
    private PApplet parent;
    private byte[] fft, wave;
    private boolean visible = false;
    private float r, g, b;

    public Line(PApplet parent, int colorChoose) {
        this.parent = parent;

        if(colorChoose == 1){
            r = 255;
            g = 0;
            b = 223;
        }else{
            r = 0;
            g = 255;
            b = 233;
        }
    }

    @Override
    public void update(byte[] fft, byte[] wave) {
        this.fft = fft;
        this.wave = wave;
    }

    @Override
    public void show() {

    }


    public void show(float linePos1,float linePos2, int drawLine,int currentLine,int previousLine,float lineThickness) {

            parent.pushStyle();


                parent.strokeWeight(lineThickness + 25);
                parent.stroke(22, 22, 22,230);
                parent.line(parent.width * linePos1, parent.height - drawLine - 50, parent.width * linePos1, parent.height);
                parent.line(parent.width * linePos2, 0 + drawLine + 50, parent.width * linePos2, 0);




            parent.popStyle();
            parent.pushStyle();




                parent.strokeWeight(lineThickness);
                parent.stroke(r, g, b, lineAlpha(currentLine, previousLine));

                parent.line(parent.width * linePos1, parent.height - drawLine - 50, parent.width * linePos1, parent.height);
                parent.line(parent.width * linePos2, 0 + drawLine + 50, parent.width * linePos2, 0);




            parent.popStyle();

    }

    public int lineAlpha(int lineValue, int previousline) {
        if (lineValue>255){
            return 255;
        }
        else if (lineValue > previousline) {
            return decay(lineValue,10)+100;
        } else if (previousline > 0 && previousline < 255) {
            return decay(previousline, 10)+100;
        }else{
            return lineValue+50;
        }
    }





    public int decay(int drawLine, int lineDecay){
        if(drawLine >= 0) {
            drawLine = drawLine - lineDecay;
        }
        if (drawLine <0) {
            drawLine = 0;
        }
        return drawLine;
    }



    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
