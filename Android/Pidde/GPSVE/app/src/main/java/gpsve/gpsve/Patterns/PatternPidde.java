package gpsve.gpsve.Patterns;

import java.util.Random;

import gpsve.gpsve.Interface.PatternInterface;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Created by Petter Månsson on 2017-04-06.
 * A pattern created for music visualization
 */

public class PatternPidde implements PatternInterface {
    private PApplet parent;
    private int[] currentLine, previousLine, drawLine;
    private float lineThickness;
    private Star[] stars1 = new Star[250];
    private Star[] stars2 = new Star[250];
    private Star[] stars3 = new Star[250];
    private int delay = 0;

    private int line1, line2, line3, line4;
    private boolean okToDraw = true;

    public PatternPidde(PApplet parent) {
        this.parent = parent;
        currentLine = new int[8];
        previousLine = new int[8];
        drawLine = new int[8];

        for (int i = 0; i < stars1.length; i++) {
            stars1[i] = new Star();
            stars2[i] = new Star();
            stars3[i] = new Star();
        }
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

    /**
     * Getter used for testing
     *
     * @param i
     * @return int in array currentLine at specified  pos
     */
    public int getCurrentLineAtPos(int i) {
        return currentLine[i];
    }

    @Override
    public void updatePattern(byte[] fft, byte[] wave) {
        setOkToDraw(false);
//        fft = wave;
        for (int i = 0; i < currentLine.length; i++) {
            currentLine[i] = 0;
        }

        for (int i = 0; i < fft.length; i++) {
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
            if (fft[i] >= 48 && fft[i] < 64) {
                currentLine[3] += fft[i];
            }
            if (fft[i] >= 64 && fft[i] < 80) {
                currentLine[4] += fft[i];
            }
            if (fft[i] >= 80 && fft[i] < 96) {
                currentLine[5] += fft[i];
            }
            if (fft[i] >= 96 && fft[i] < 112) {
                currentLine[6] += fft[i];
            }
            if (fft[i] >= 112) {
                currentLine[7] += fft[i];
            }
        }
        for (int i = 0; i < currentLine.length; i++) {
            if (currentLine[i] > previousLine[i]) {
                drawLine[i] = currentLine[i];
                previousLine[i] = currentLine[i];
            } else {
                previousLine[i] = decay(previousLine[i], 5);
                drawLine[i] = previousLine[i];
            }
        }
        setOkToDraw(true);
    }

    @Override
    public void drawPattern() {

        lineThickness = (float) (parent.width * 0.075);
        float linePos1 = (float) 0.0625, linePos2 = (float) 0.9375;
        parent.background(0);


//        galacticHighway(25);

        parent.pushMatrix();
        parent.translate(parent.width / 2, parent.height / 2);
        for (int i = 0; i < stars1.length; i++) {
            stars1[i].update();
            stars1[i].show();
        }
        if (delay > 5) {
            for (int i = 0; i < stars2.length; i++) {
                stars2[i].update();
                stars2[i].show();
            }
        }
        if (delay > 10) {
            for (int i = 0; i < stars3.length; i++) {
                stars3[i].update();
                stars3[i].show();
            }
        }
        delay++;
        parent.popMatrix();

        parent.pushStyle();
        for (int i = 0; i < drawLine.length; i++) {

            parent.strokeWeight(lineThickness + 25);
            parent.stroke(22, 22, 22,230);
            parent.line(parent.width * linePos1, parent.height - drawLine[i] - 50, parent.width * linePos1, parent.height);
            parent.line(parent.width * linePos2, 0 + drawLine[i] + 50, parent.width * linePos2, 0);
            linePos1 += 0.125;
            linePos2 -= 0.125;

        }

        parent.popStyle();
        linePos1 = (float) 0.0625;
        linePos2 = (float) 0.9375;
        parent.pushStyle();
        for (int i = 0; i < drawLine.length; i++) {
            float r, g, b;
            if (i % 2 == 0) {
                r = 255;
                g = 0;
                b = 223;
            } else {
                r = 0;
                g = 255;
                b = 233;

            }

            parent.strokeWeight(lineThickness);
            parent.stroke(r, g, b, lineAlpha(currentLine[i], previousLine[i]));

            parent.line(parent.width * linePos1, parent.height - drawLine[i] - 50, parent.width * linePos1, parent.height);
            parent.line(parent.width * linePos2, 0 + drawLine[i] + 50, parent.width * linePos2, 0);
            linePos1 += 0.125;
            linePos2 -= 0.125;


        }
        parent.popStyle();


    }


    public int lineAlpha(int lineValue, int previousline) {
        if (lineValue>255){
            return 255;
        }
        else if (lineValue > previousline) {
            return decay(lineValue,10)+100;
        } else if (previousline > 0 && previousline < 255) {
            return decay(previousline, 10)+100;
        }else{
            return lineValue+50;
        }
    }


    @Override
    public boolean okToDraw() {
        return okToDraw;
    }


    public int decay(int drawLine, int lineDecay){
        if(drawLine >= 0) {
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


    private class Star{
        float x;
        float y;
        float z;
        float pz;
        float speed;
        int colorChoose =1;
        int colorChoose2 =1;

        public void Star(){
            x = parent.random(-parent.width/2,parent.width/2);
            y = parent.random(-parent.height/2, parent.height/2);
            z = parent.random(parent.width/2);
            pz = z;

        }

        public void update(){

            if(parent.height>parent.width){
                speed = 30;
            }else{
                speed = 55;
            }
            z = z-speed;

            if(z<1){
                z=parent.width/2;
                x = parent.random(-parent.width/2, parent.width/2);
                y = parent.random(-parent.height/2, parent.height/2);
                pz = z;
            }
        }
        public void show(){

//            parent.fill(255);
            parent.noStroke();
            float sx = parent.map(x / z, 0, 1, 0, parent.width/2);
            float sy = parent.map(y / z, 0, 1, 0, parent.height/2);
            float r = parent.map(z, 0, parent.width/2, 16, 0);
            parent.pushStyle();
            if(colorChoose == 1) {
                parent.fill(255,0,223);
                colorChoose = 2;
            }else{
                parent.fill(0,255,233);
                colorChoose = 1;
            }
            parent.ellipse(sx, sy, r, r);
            parent.popStyle();
            float px = parent.map(x / pz, 0, 1, 0, parent.width/2);
            float py = parent.map(y / pz, 0, 1, 0, parent.height/2);
            pz = z;
            parent.pushStyle();
            parent.stroke(255);

            parent.strokeWeight(2);
            parent.line(px, py, sx, sy);
            parent.popStyle();
        }
    }
}
