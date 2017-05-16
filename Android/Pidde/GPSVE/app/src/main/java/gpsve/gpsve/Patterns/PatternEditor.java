package gpsve.gpsve.Patterns;

import gpsve.gpsve.Interface.PatternInterface;
import gpsve.gpsve.PatternLibrary.Circle;
import processing.core.PApplet;

/**
 * Created by Petter on 2017-05-16.
 */

public class PatternEditor implements PatternInterface {
    private Circle circle;
    private PApplet parent;
    private byte[] wave, fft;
    private float circleSize=0;
    public boolean okToDraw = true;

    public PatternEditor(PApplet parent){
        this.parent=parent;
        circle = new Circle(this.parent);
    }

    @Override
    public void updatePattern(byte[] fft, byte[] wave) {

            for(int i =0; i<fft.length;i++){
                circleSize+=fft[i];
                circle.update(circleSize);
            }

        circleSize=0;


        System.out.println("UpdatePatternSLUT");
    }

    @Override
    public void drawPattern() {
        parent.background(0);
        System.out.println("Draw");
        circle.show();

    }

    @Override
    public boolean okToDraw() {
        return okToDraw;
    }

    public boolean getCheckCircle(){
        return true;
    }
}
