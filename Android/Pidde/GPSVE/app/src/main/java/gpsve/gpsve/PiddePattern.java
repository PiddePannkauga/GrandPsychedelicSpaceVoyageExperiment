package gpsve.gpsve;

import android.graphics.Color;
import android.util.Log;

import processing.core.PApplet;

/**
 * Created by bajs on 2017-04-06.
 */

public class PiddePattern {
    private PApplet parent;
    private byte[] soundBytes;
    private int line1,line2,line3,line4;
    private boolean ok = true;


    public PiddePattern(PApplet parent) {
        this.parent = parent;
        soundBytes = new byte[256];

    }

    public void setLine1(int line1) {
        this.line1 += line1;
    }

    public int getLine1() {
        return line1;
    }

    public int getLine2() {
        return line2;
    }

    public void setLine2(int line2) {
        this.line2 += line2;
    }

    public int getLine3() {
        return line3;
    }

    public void setLine3(int line3) {
        this.line3 += line3;
    }

    public int getLine4() {
        return line4;
    }

    public void setLine4(int line4) {
        this.line4 += line4;
    }

    public void setSoundBytes(byte[] bytes){

        setLine1(0);
        setLine2(0);
        setLine3(0);
        setLine4(0);

        for(int i = 0; i<bytes.length;i++) {
            if (bytes[i] < 0) {
                int k = bytes[i];
                k *= -1;
                bytes[i] = (byte) k;
            }
            soundBytes[i]=bytes[i];
            if(soundBytes[i]>=0 && soundBytes[i]<32){
                setLine1(soundBytes[i]);
            }
            if(soundBytes[i]>=32 && soundBytes[i]<64){
                setLine2(soundBytes[i]);
            }
            if(soundBytes[i]>=64 && soundBytes[i]<96){
                setLine3(soundBytes[i]);
            }
            if(soundBytes[i]>=96) {
                setLine4(soundBytes[i]);
            }

        }


    }



    public void setOK(boolean ok){
        this.ok = ok;
    }
    public boolean getOK(){
        return this.ok;
    }

    public void drawShape(){

        setOK(false);

            parent.strokeWeight(100);
            parent.stroke(250,200,0);

                parent.line(parent.width * (float) 0.8, parent.height - line4, parent.width * (float) 0.8, parent.height);
                parent.line(parent.width * (float) 0.2, 0 + line4, parent.width * (float) 0.2, 0);

                parent.line(parent.width * (float) 0.6, parent.height - line3, parent.width * (float) 0.6, parent.height);
                parent.line(parent.width * (float) 0.4, 0 + line3, parent.width * (float) 0.4, 0);

                parent.line((parent.width * (float) 0.4), parent.height - line2, (parent.width * (float) 0.4), parent.height);
                parent.line((parent.width * (float) 0.6), 0 + line2, (parent.width * (float) 0.6), 0);

                parent.line(parent.width * (float) 0.2, parent.height - line1, parent.width * (float) 0.2, parent.height);
                parent.line(parent.width * (float) 0.8, 0 + line1, parent.width * (float) 0.8, 0);

        setOK(true);
    }


}
