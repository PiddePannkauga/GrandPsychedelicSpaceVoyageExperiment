package gpsve.gpsve;

import processing.core.PApplet;

import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.util.Log;

/**
 * Created by bajs on 2017-03-22.
 */

public class VisualizerDemo extends PApplet {

    private byte[] soundBytes;
    private SoundConverter sC;
    private int k=0;
    private PiddePattern pidde;
    public VisualizerDemo(SoundConverter sC){
        soundBytes = new byte[256];
        this.sC = sC;
        pidde = new PiddePattern(this);

    }

    public static void main(String[] args) {
        PApplet.main(new String[]{"VisualizerDemo"});
    }

    public void settings() {
        size(1080,1920);
    }

    public void setup() {

        pidde.setSoundBytes(sC.getSoundBytes());
    }


    public void draw() {

        stroke(255);
        background(0);
        pidde.setSoundBytes(sC.getSoundBytes());
        if(soundBytes!=null) {

            pidde.drawShape();

        }

    }


}

