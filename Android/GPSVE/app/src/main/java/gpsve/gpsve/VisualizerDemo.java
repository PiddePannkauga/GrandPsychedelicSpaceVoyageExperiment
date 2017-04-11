package gpsve.gpsve;

import processing.core.PApplet;

/**
 * Created by bajs on 2017-03-22.
 */

public class VisualizerDemo extends PApplet {

    private byte[] fftBytes;
    private SoundConverter sC;
    private PatternPidde pidde;

    public VisualizerDemo(SoundConverter sC){
        fftBytes = new byte[256];
        this.sC = sC;
        pidde = new PatternPidde(this);

    }

    public static void main(String[] args) {
        PApplet.main(new String[]{"VisualizerDemo"});
    }

    public void settings() {
        size(1080,1920);
    }

    public void setup() {

        pidde.setSoundBytes(sC.getFftBytes());
    }


    public void draw() {

        stroke(255);
        background(0,0,150);

        if(fftBytes !=null) {
            if(pidde.getOK()) {
                pidde.setSoundBytes(sC.getFftBytes());
                pidde.drawShape();
            }
        }
    }
}

