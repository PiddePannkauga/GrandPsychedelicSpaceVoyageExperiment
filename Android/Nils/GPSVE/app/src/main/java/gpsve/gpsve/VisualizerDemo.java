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

    public VisualizerDemo(SoundConverter sC){
        soundBytes = new byte[1024];
        this.sC = sC;
    }

    public static void main(String[] args) {
        PApplet.main(new String[]{"VisualizerDemo"});
    }

    public void settings() {
        size(1080,1920);
    }

    public void setup() {

        soundBytes = sC.getSoundBytes();
    }


    public void draw() {
        soundBytes = sC.getSoundBytes();
        stroke(255);
        background(0);

        if(soundBytes!=null) {
            for (int i = 0; i < 1024; i++) {
                ellipse(width / 2, height / 2, soundBytes[i] * 10, soundBytes[i] * 10);

                Log.i("VAD FINNS I DENA", "" + soundBytes[i]);


            }
        }
//
//
    }


}

