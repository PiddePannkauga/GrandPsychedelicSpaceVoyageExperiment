package gpsve.gpsve.Patterns;

import android.graphics.Color;

import gpsve.gpsve.Interface.PatternInterface;
import processing.core.PApplet;

/**
 * Created by Miran Amin on 2017-04-18.
 */

public class PatternMirre implements PatternInterface {
    private PApplet parent;
    private Color color;
    private boolean okToDraw = true;
    private byte[] wave, fft;
    private float rectWidth, rectHeight;


    public PatternMirre(PApplet parent){
        this.parent=parent;

    }


    public void updatePattern(byte[] fft, byte[] wave) {
        this.fft = fft;
        this.wave = wave;
        orientation(wave[100]);
    }

    public void orientation(float rectSize){
        if(parent.height>parent.width) {
            rectWidth = rectSize * 4;
            rectHeight = rectSize * 6;
        } else {
            rectWidth = rectSize * (float) 6.8;
            rectHeight = rectSize * (float)3.7;
        }
    }


    public void drawPattern() {
        okToDraw = false;
        drawCircle();
        okToDraw = true;
    }


    public boolean okToDraw() {
        return okToDraw;
    }

    public void drawCircle(){
        parent.background(0);

        parent.delay(60);
        parent.strokeWeight(10);
        parent.stroke(0 , 0 , 255);
        parent.fill(parent.color(0, 255, 0));
        parent.rect(parent.width /2,parent.height /2, rectWidth, rectHeight);
        parent.fill(parent.color(255, 150, 0));
        parent.rect(parent.width /2, parent.height /2, -rectWidth, rectHeight);
        parent.fill(parent.color(255,0,0));
        parent.rect(parent.width /2, parent.height / 2, rectWidth, -rectHeight );
        parent.fill(parent.color(0, 150, 255));
        parent.rect(parent.width /2, parent.height /2, -rectWidth, -rectHeight);
        parent.fill(parent.color(0,0,255));
        parent.ellipse(parent.width * (float) 0.5, parent.height * (float) 0.5, wave[100]* 2, wave[100]* 2);


    }
}
