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
    private int lineDecay = 20;
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

            if(line1 > previousLine1){
                drawLine1 = line1;
            }else{
                decay(previousLine1);
                drawLine1 = previousLine1;
                System.out.println(previousLine1);
            }
            if(line2 > previousLine2){
                drawLine2 = line2;
            }else{
                decay(previousLine2);
                drawLine2 = previousLine2;
            }
            if(line3 > previousLine3){
                drawLine3 = line3;
            }else{
                decay(previousLine3);
                drawLine3 = previousLine3;
            }
            if(line4 > previousLine4){
                drawLine4 = line4;
            }else{
                decay(previousLine4);
                drawLine4 = previousLine4;

            }
        }
    }

    @Override
    public void drawPattern() {

        setOkToDraw(false);
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

        previousLine1 = drawLine1;
        previousLine2 = drawLine2;
        previousLine3 = drawLine3;
        previousLine4 = drawLine4;
        setOkToDraw(true);

    }


    public void decay(int drawLine){
        drawLine -= lineDecay;

    }

    @Override
    public void setOkToDraw(boolean okToDraw) {
        this.okToDraw = okToDraw;
    }

    @Override
    public boolean getOkToDraw() {
        return okToDraw;
    }



}
