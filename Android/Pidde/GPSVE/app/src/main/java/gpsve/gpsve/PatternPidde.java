package gpsve.gpsve;

import processing.core.PApplet;

/**
 * Created by Petter on 2017-04-06.
 */

public class PatternPidde {
    private PApplet parent;
    private int line1, line2, line3, line4;
    private boolean okToDraw = true;

    public PatternPidde(PApplet parent) {
        this.parent = parent;
    }

    public int getLine1() {
        return line1;
    }

    public void setLine1(int line1) {
        this.line1 = line1;
    }

    public int getLine2() {
        return line2;
    }

    public void setLine2(int line2) {
        this.line2 = line2;
    }

    public int getLine3() {
        return line3;
    }

    public void setLine3(int line3) {
        this.line3 = line3;
    }

    public int getLine4() {
        return line4;
    }

    public void setLine4(int line4) {
        this.line4 = line4;
    }

    public void updatePattern(byte[] soundBytes){
        setLine1(0);
        setLine2(0);
        setLine3(0);
        setLine4(0);

        for(int i = 0; i<soundBytes.length;i++) {
            if (soundBytes[i] < 0) {
                int k = soundBytes[i];
                k *= -1;
                soundBytes[i] = (byte) k;
            }
            if(soundBytes[i]>=0 && soundBytes[i]<32){
                setLine1(getLine1()+soundBytes[i]);
            }
            if(soundBytes[i]>=32 && soundBytes[i]<64){
                setLine2(getLine2()+soundBytes[i]);
            }
            if(soundBytes[i]>=64 && soundBytes[i]<96){
                setLine3(getLine3()+soundBytes[i]);
            }
            if(soundBytes[i]>=96) {
                setLine4(getLine4()+soundBytes[i]);
            }
        }
    }

    public void setOk(boolean okToDraw){
        this.okToDraw = okToDraw;
    }

    public boolean okToDraw(){
        return this.okToDraw;
    }

    public void drawShape(){
        setOk(false);
        parent.strokeWeight(100);
        parent.stroke(250,200,0);

        parent.line(parent.width * (float) 0.8, parent.height - getLine4(), parent.width * (float) 0.8, parent.height);
        parent.line(parent.width * (float) 0.2, 0 + getLine4(), parent.width * (float) 0.2, 0);

        parent.line(parent.width * (float) 0.6, parent.height - getLine3(), parent.width * (float) 0.6, parent.height);
        parent.line(parent.width * (float) 0.4, 0 + getLine3(), parent.width * (float) 0.4, 0);

        parent.line((parent.width * (float) 0.4), parent.height - getLine2(), (parent.width * (float) 0.4), parent.height);
        parent.line((parent.width * (float) 0.6), 0 + getLine2(), (parent.width * (float) 0.6), 0);

        parent.line(parent.width * (float) 0.2, parent.height - getLine1(), parent.width * (float) 0.2, parent.height);
        parent.line(parent.width * (float) 0.8, 0 + getLine1(), parent.width * (float) 0.8, 0);
        setOk(true);
    }
}
