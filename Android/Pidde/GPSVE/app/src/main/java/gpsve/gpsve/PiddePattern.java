package gpsve.gpsve;

import processing.core.PApplet;

/**
 * Created by bajs on 2017-04-06.
 */

public class PiddePattern {
    private PApplet parent;
    private byte[] soundBytes;

    public PiddePattern(PApplet parent){
        this.parent = parent;


    }

    public void setSoundBytes(byte[] bytes){
        soundBytes = bytes;
    }

    public void drawShape(){

        for(int i = 0; i<soundBytes.length; i++) {
            parent.fill(soundBytes[i]*2);
            parent.ellipse(parent.width / 2, parent.height / 2, soundBytes[i], soundBytes[i]);
        }
    }

}
