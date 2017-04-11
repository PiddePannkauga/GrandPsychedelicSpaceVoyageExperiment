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
    private int currentLine1=0,currentLine2=0,currentLine3=0,currentLine4=0;
    private int line1=0,line2=0,line3=0,line4=0;
    private boolean ok = true;


    public PiddePattern(PApplet parent) {
        this.parent = parent;
        soundBytes = new byte[256];

    }

    public void setSoundBytes(byte[] bytes){
        line1=0;
        line2=0;
        line3=0;
        line4=0;
        for(int i = 0; i<bytes.length;i++) {
            if (bytes[i] < 0) {
                int k = bytes[i];
                k *= -1;
                bytes[i] = (byte) k;
            }
            soundBytes[i]=bytes[i];
            if(soundBytes[i]>=0 && soundBytes[i]<32){
                line1+=soundBytes[i];
                if(currentLine1 >= line1){
                    line1 = currentLine1;
                }
            }
            if(soundBytes[i]>=32 && soundBytes[i]<64){
                line2+=soundBytes[i];
            }
            if(soundBytes[i]>=64 && soundBytes[i]<96){
                line3+=soundBytes[i];
            }
            if(soundBytes[i]>=96) {
                line4+=soundBytes[i];

            }

        }
//       / currentLine1 = line1;

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
