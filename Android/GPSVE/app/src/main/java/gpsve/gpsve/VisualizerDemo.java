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
    private int i=0;
    private int integer;

    public static void main(String[] args) {
        PApplet.main(new String[]{"VisualizerDemo"});
    }

    public void settings() {
        size(1080,1920);
    }

    public void setup() {
        soundBytes = new byte[1024];
    }




    public void draw() {
        //       mFFTBytes = new byte[1024];
        stroke(255);
        background(0);

        ellipse(width / 2, height / 2, soundBytes[i] * 128, soundBytes[i] * 128);

        Log.i("VAD FINNS I DENA", ""+soundBytes[i]);
        i++;
        if(i==1023){
            i=0;
        }
//
//
    }


}

