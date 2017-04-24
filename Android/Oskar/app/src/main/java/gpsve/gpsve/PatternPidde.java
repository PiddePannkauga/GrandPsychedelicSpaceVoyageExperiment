package gpsve.gpsve;

import processing.core.PApplet;

/**
 * Created by Petter on 2017-04-06.
 */

public class PatternPidde implements PatternInterface {
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
    
    @Override
    public void updatePattern(byte[] fft, byte[] wave){

        setLine1(0);
        setLine2(0);
        setLine3(0);
        setLine4(0);

        for(int i = 0; i<fft.length;i++) {
            if (fft[i] < 0) {
                int k = fft[i];
                k *= -1;
                fft[i] = (byte) k;
            }
            if(fft[i]>=0 && fft[i]<32){
                setLine1(getLine1()+fft[i]);
            }
            if(fft[i]>=32 && fft[i]<64){
                setLine2(getLine2()+fft[i]);
            }
            if(fft[i]>=64 && fft[i]<96){
                setLine3(getLine3()+fft[i]);
            }
            if(fft[i]>=96) {
                setLine4(getLine4()+fft[i]);
            }
        }
    }

    @Override
    public void drawPattern() {
        okToDraw = false;

        parent.background(0,0,150);

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

        okToDraw = true;
    }

    @Override
    public boolean okToDraw() {
        return okToDraw;
    }
}
