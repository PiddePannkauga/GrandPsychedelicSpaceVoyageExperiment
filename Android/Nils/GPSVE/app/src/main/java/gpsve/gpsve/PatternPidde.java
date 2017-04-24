package gpsve.gpsve;

import processing.core.PApplet;

/**
 * Created by Petter on 2017-04-06.
 */

public class PatternPidde implements PatternInterface {
    private PApplet parent;
    private int line1, line2, line3, line4;
    private int previousLine1=0,previousLine2=0,previousLine3=0,previousLine4=0;
    private int drawLine1, drawLine2, drawLine3,drawLine4;
    private int lineDecay = 5;
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
    public void updatePattern(byte[] fft, byte[] wave) {
        setOkToDraw(false);

        for(int i = 0; i<fft.length;i++) {
            if (fft[i] < 0) {
                int k = fft[i];
                k *= -1;
                fft[i] = (byte) k;
            }
            if (fft[i] >= 0 && fft[i] < 32) {
                setLine1(getLine1() + fft[i]);
            }
            if (fft[i] >= 32 && fft[i] < 64) {
                setLine2(getLine2() + fft[i]);
            }
            if (fft[i] >= 64 && fft[i] < 96) {
                setLine3(getLine3() + fft[i]);
            }
            if (fft[i] >= 96) {
                setLine4(getLine4() + fft[i]);
            }
        }

        if(line1 > previousLine1){
            drawLine1 = line1;
            previousLine1 = line1;
        }else{
            previousLine1 = decay(previousLine1);
            drawLine1 = previousLine1;
        }
        if(line2 > previousLine2){
            drawLine2 = line2;
            previousLine2 = line2;
        }else{
            previousLine2 = decay(previousLine2);
            drawLine2 = previousLine2;
        }
        if(line3 > previousLine3){
            drawLine3 = line3;
            previousLine3 = line3;
        }else{
            previousLine3 = decay(previousLine3);
            drawLine3 = previousLine3;
        }
        if(line4 > previousLine4){
            drawLine4 = line4;
            previousLine4 = line4;
        }else{
            previousLine4 = decay(previousLine4);
            drawLine4 = previousLine4;
        }


        setOkToDraw(true);
    }

    @Override
    public void drawPattern() {


        parent.background(0,0,150);
        parent.strokeWeight(100);
        parent.stroke(250,200,0);

        parent.line(parent.width * (float) 0.8, parent.height - drawLine4, parent.width * (float) 0.8, parent.height);
        parent.line(parent.width * (float) 0.2, 0 + drawLine4, parent.width * (float) 0.2, 0);

        parent.line(parent.width * (float) 0.6, parent.height - drawLine3, parent.width * (float) 0.6, parent.height);
        parent.line(parent.width * (float) 0.4, 0 + drawLine3, parent.width * (float) 0.4, 0);

        parent.line((parent.width * (float) 0.4), parent.height - drawLine2, (parent.width * (float) 0.4), parent.height);
        parent.line((parent.width * (float) 0.6), 0 + drawLine2, (parent.width * (float) 0.6), 0);

        parent.line(parent.width * (float) 0.2, parent.height - drawLine1, parent.width * (float) 0.2, parent.height);
        parent.line(parent.width * (float) 0.8, 0 + drawLine1, parent.width * (float) 0.8, 0);

        setLine1(0);
        setLine2(0);
        setLine3(0);
        setLine4(0);



    }

    @Override
    public boolean okToDraw() {
        return okToDraw;
    }


    public int decay(int drawLine){
        if(drawLine > 0) {
            drawLine = drawLine - lineDecay;
        }
        if (drawLine <0) {
           drawLine = 0;
        }
        return drawLine;
    }


    public void setOkToDraw(boolean okToDraw) {
        this.okToDraw = okToDraw;
    }





}
