package gpsve.gpsve;

import java.util.Random;

import processing.core.PApplet;

/**
 * Created by Petter on 2017-04-06.
 */

public class PatternPidde implements PatternInterface {
    private PApplet parent;
    private int[] currentLine, previousLine, drawLine;
    private int line1, line2, line3, line4;
    private boolean okToDraw = true;
    private Random rand = new Random();

    public PatternPidde(PApplet parent) {
        this.parent = parent;
        currentLine = new int[8];
        previousLine = new int[8];
        drawLine = new int[8];
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

        for(int i = 0; i<currentLine.length; i++){
            currentLine[i] = 0;
        }

        for(int i = 0; i<fft.length;i++) {
            if (fft[i] < 0) {
                int k = fft[i];
                k *= -1;
                fft[i] = (byte) k;
            }
            if (fft[i] >= 0 && fft[i] < 16) {
                currentLine[0] += fft[i];
            }
            if (fft[i] >= 16 && fft[i] < 32) {
                currentLine[1] += fft[i];
            }
            if (fft[i] >= 32 && fft[i] < 48) {
                currentLine[2] += fft[i];
            }
            if (fft[i] >= 48 && fft[i] < 64 ) {
                currentLine[3] += fft[i];
            }
            if (fft[i] >= 64 && fft[i] < 80 ) {
                currentLine[4] += fft[i];
            }
            if (fft[i] >= 80 && fft[i] < 96 ) {
                currentLine[5] += fft[i];
            }
            if (fft[i] >= 96 && fft[i] < 112 ) {
                currentLine[6] += fft[i];
            }
            if (fft[i] >= 122 ) {
                currentLine[7] += fft[i];
            }
        }
        for(int i =0; i<currentLine.length; i++){
            if(currentLine[i] > previousLine[i]){
                drawLine[i] = currentLine[i];
                previousLine[i] = currentLine[i];
            }else{
                previousLine[i] = decay(previousLine[i],5);
                drawLine[i] = previousLine[i];
            }

        }

        setOkToDraw(true);
    }

    @Override
    public void drawPattern() {

        float linePos1 =(float)0.0625, linePos2 =(float)0.9375;

        parent.background(50, 0, 79);
        parent.strokeWeight(125);
        parent.stroke(0, 0, 0);
        for(int i =0; i<drawLine.length;i++) {

            parent.line(parent.width * linePos1, parent.height - drawLine[i]-100, parent.width * linePos1, parent.height);
            parent.line(parent.width * linePos2, 0 + drawLine[i]+100, parent.width * linePos2, 0);
            linePos1 += 0.125;
            linePos2 -= 0.125;

        }

        parent.strokeWeight(75);
        linePos1 = (float)0.0625;
        linePos2 =(float)0.9375;
        for(int i =0; i<drawLine.length;i++){
            float r=0,g=0,b=0;
            if(i==0 || i==4){
            r = 255;
            }else if(i==1 || i==5){
                r=255;
                g=255;
            }else if(i==2 || i==6){
                g=255;
            }else if(i==3 || i ==7){
                b=255;
            }
            parent.stroke(r,g,b,lineAlpha(drawLine[i]));

            parent.line(parent.width * linePos1, parent.height - drawLine[i]-100, parent.width * linePos1, parent.height);
            parent.line(parent.width * linePos2, 0 + drawLine[i]+100, parent.width * linePos2, 0);
            linePos1 += 0.125;
            linePos2 -= 0.125;

        }

    }

    public int lineAlpha(int lineValue){

        if(lineValue>255) {
            return decay(255,10);
        }else{
            return decay(lineValue,10);
        }
    }

    @Override
    public boolean okToDraw() {
        return okToDraw;
    }


    public int decay(int drawLine, int lineDecay){
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
