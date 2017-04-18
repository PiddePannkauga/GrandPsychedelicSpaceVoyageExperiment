package gpsve.gpsve;

import processing.core.PApplet;

/**
 * Created by bajs on 2017-03-22.
 */

public class VisualizerDemo extends PApplet {

    private byte[] fftBytes;
    private SoundConverter sC;
    private PatternPidde pidde;
    private PatternCircle circle;

    public VisualizerDemo(SoundConverter sC){
        fftBytes = new byte[512];
        this.sC = sC;
        pidde = new PatternPidde(this);
        circle = new PatternCircle(this);

    }

    public static void main(String[] args) {
        PApplet.main(new String[]{"VisualizerDemo"});
    }

    public void settings() {
        size(1080,1920);
    }

    public void setup() {

        pidde.updatePattern(sC.getFftBytes());
    }


    public void draw() {

        stroke(255);
        background(0,0,150);

        if(fftBytes !=null) {
            if(pidde.okToDraw()) {
                pidde.updatePattern(sC.getFftBytes());
                pidde.drawShape();
            }else{
                circle.updatePattern(sC.getFftBytes());
            }

        }
    }
}

