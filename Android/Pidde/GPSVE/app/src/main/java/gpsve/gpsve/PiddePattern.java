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
        int line1=0,line2=0,line3=0,line4=0;
        for(int i = 0; i<soundBytes.length; i++) {
            parent.strokeWeight(50);
            if(soundBytes[i]>32){
            line1+=soundBytes[i];
            parent.line(50,parent.height-line1,50,parent.height);
            }
            if(soundBytes[i]>=32 && soundBytes[i]<64){
                line2+=soundBytes[i];
                parent.line(parent.width/4+50,parent.height-line2,parent.width/4+50,parent.height);

            }
            if(soundBytes[i]>=64 && soundBytes[i]<96){
                line3+=soundBytes[i];
                parent.line(parent.width/2+50,parent.height-line3,parent.width/2+50,parent.height);
            }
            if(soundBytes[i]>=96) {
                line4+=soundBytes[i];
                parent.line(parent.width-50,parent.height-line4, parent.width-50, parent.height);
            }
        }
    }
}
