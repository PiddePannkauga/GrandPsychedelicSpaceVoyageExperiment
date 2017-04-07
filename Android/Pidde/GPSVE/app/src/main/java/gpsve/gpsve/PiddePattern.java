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
        soundBytes = new byte[1024];


    }

    public void setSoundBytes(byte[] bytes){

        for(int i = 0; i<bytes.length;i++) {
            if (bytes[i] < 0) {
                int k = bytes[i];
                k *= -1;
                bytes[i] = (byte) k;
            }

                soundBytes[i] = bytes[i];


        }

    }

    public void drawShape(){
        int line1=0,line2=0,line3=0,line4=0;
        for(int i = 0; i<soundBytes.length; i++) {
            parent.strokeWeight(50);

            if(soundBytes[i]>32){
                line1+=soundBytes[i];
                parent.stroke(250,200,0);
                parent.line(parent.width*(float)0.2,parent.height-line1*2,parent.width*(float)0.2,parent.height);
                parent.line(parent.width*(float)0.8,0+line1*2,parent.width*(float)0.8,0);
            }
            if(soundBytes[i]>=32 && soundBytes[i]<64){

                line2+=soundBytes[i];
                parent.line((parent.width*(float)0.4),parent.height-line2*2,(parent.width*(float)0.4),parent.height);
                parent.line((parent.width*(float)0.6),0+line2*2,(parent.width*(float)0.6),0);
            }
            if(soundBytes[i]>=64 && soundBytes[i]<96){
                line3+=soundBytes[i];
                parent.line(parent.width*(float)0.6,parent.height-line3*2,parent.width*(float)0.6,parent.height);
                parent.line(parent.width*(float)0.4,0+line3*2,parent.width*(float)0.4,0);
            }
            if(soundBytes[i]>=96) {
                line4+=soundBytes[i];
                parent.line(parent.width*(float)0.8,parent.height-line4*2,parent.width*(float)0.8, parent.height);
                parent.line(parent.width*(float)0.2,0+line4*2,parent.width*(float)0.2, 0);
            }
        }
    }
}
