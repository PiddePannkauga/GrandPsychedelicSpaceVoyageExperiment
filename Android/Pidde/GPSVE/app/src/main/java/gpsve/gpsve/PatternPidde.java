package gpsve.gpsve;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Created by Petter on 2017-04-06.
 */

public class PatternPidde implements PatternInterface {
    private PApplet parent;
    private int[] currentLine, previousLine, drawLine;
    float quadLenghtY,quadLenghtX,y2;
    float thinnerLine1=50;
    float thinnerLine2=50;

    private int line1, line2, line3, line4;
    private boolean okToDraw = true;
    private Random rand = new Random();

    public PatternPidde(PApplet parent) {
        this.parent = parent;
        currentLine = new int[8];
        previousLine = new int[8];
        drawLine = new int[8];

        quadLenghtY=parent.height;
        quadLenghtX=parent.width;
        y2 = parent.height;

    }

    public int getLine1() {
        return line1;
    }

    public int getLine2() {
        return line2;
    }

    public int getLine3() {
        return line3;
    }

    public int getLine4() {
        return line4;
    }

    @Override
    public void updatePattern(byte[] fft, byte[] wave) {
        setOkToDraw(false);
//        fft = wave;
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

        parent.background(0,0,0);


        galacticHighway(25);


        parent.beginShape(PConstants.LINE);
        for(int i =0; i<drawLine.length;i++) {

            parent.strokeWeight(125);
            parent.stroke(22, 22, 22,lineAlpha(drawLine[i]));
            parent.line(parent.width * linePos1, parent.height - drawLine[i]+50, parent.width * linePos1, parent.height);
            parent.line(parent.width * linePos2, 0 + drawLine[i]+50, parent.width * linePos2, 0);
            linePos1 += 0.125;
            linePos2 -= 0.125;

        }
        parent.endShape();
        parent.beginShape(PConstants.LINE);
        linePos1 = (float)0.0625;
        linePos2 =(float)0.9375;

        for(int i =0; i<drawLine.length;i++){
            float r=0,g=0,b=0;
            if(i%2==0){
                r=247;
                g=14;
                b=204;
            }else{
                r=13;
                g=247;
                b=243;

            }
//            if(i==0 || i==4){
//            r = 255;
//            }else if(i==1 || i==5){
//                r=255;
//                g=255;
//            }else if(i==2 || i==6){
//                g=255;
//            }else if(i==3 || i ==7){
//                b=255;
//            }
            parent.strokeWeight(75);
            parent.stroke(r,g,b,lineAlpha(drawLine[i]));

            parent.line(parent.width * linePos1, parent.height - drawLine[i]+50, parent.width * linePos1, parent.height);
            parent.line(parent.width * linePos2, 0 + drawLine[i]+50, parent.width * linePos2, 0);
            linePos1 += 0.125;
            linePos2 -= 0.125;


        }
        parent.endShape();



    }

    public int lineAlpha(int lineValue){

        if(lineValue>255) {
            return decay(255,10);
        }else{
            return decay(lineValue,10)+50;
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

    public void galacticHighway(float quadLenghtY){


        parent.noStroke();
        parent.beginShape(PConstants.QUAD);
        parent.fill(78, 237, 252,150);
        parent.quad(0,parent.height,parent.width/2,parent.height*(float)0.55,parent.width/2,parent.height*(float)0.55,parent.width,parent.height);
        parent.endShape();
        parent.beginShape(PConstants.QUAD);
        parent.fill(244, 134, 66,200);
        parent.quad(quadLenghtX/2-thinnerLine1,y2,parent.width/2,this.quadLenghtY,parent.width/2,this.quadLenghtY,quadLenghtX/2+thinnerLine2,y2);
        parent.endShape(PConstants.CLOSE);

        if(!(this.quadLenghtY<parent.height*(float)0.55)){
            this.quadLenghtY -=quadLenghtY;
        }
        if(this.quadLenghtY<parent.height*(float)0.55){
            y2-=quadLenghtY;
            if(y2<parent.height*(float)0.55){
                this.quadLenghtY=parent.height;
                y2=this.quadLenghtY;
                thinnerLine1 -=10;
                thinnerLine2 -=10;
            }else{
                thinnerLine1=50;
                thinnerLine2=50;
            }
        }


    }

}
