package gpsve.gpsve.Patterns;

import gpsve.gpsve.Interface.PatternInterface;
import processing.core.*;

/**
 * Created by Nils on 2017-03-28.
 */

public class PatternNisse implements PatternInterface {
    private PApplet parent;
    private byte[] fft, wave;
    private int fftAmp, waveAmp;
    private float[] ellipse = new float[6], ellipseDecay = new float[6], ellipseColor = new float[6];
    private float speed;
    private boolean okToDraw = true;

    public PatternNisse(PApplet parent) {
        this.parent = parent;
        setup();
    }

    public void setup() {
        parent.background(0);
        for(int i = 0; i < ellipseColor.length; i++) {
            ellipseColor[i] = 175;
        }
    }

    public void calculateFftAmp() {
        fftAmp = 15;
        int temp = 0;

        for(int i = 0; i < fft.length; i++) {
            if (fft[i]<0) {
                temp = fft[i];
                temp = -temp;
            }
            if (fft[i]> fftAmp){
                fftAmp = fft[i];
            }
            if (temp > fftAmp) {
                fftAmp = temp;
            }
        }
//        System.out.println("fftAmp = " + fftAmp);
    }

    public void calculateWaveAmp() {
        waveAmp = 15;

        for(int i = 0; i < wave.length; i++) {
            if(wave[i] > waveAmp) {
                waveAmp = wave[i];
            }
        }
//        System.out.println("waveAmp = " + waveAmp);
    }

    public void calculateFftEllipse() {
        for(int i = 0; i < ellipse.length; i++) {
            ellipse[i] = Math.min(parent.width, parent.height)*(float)0.05;
        }

        int mod;
        for(int i = 0; i < fft.length; i++) {
            if(fft[i] > 0) {
                mod = i % 6;
                ellipse[mod] += fft[i]*1.1;
            }
        }
    }

    public float[] ellipseDecay(float[] newEllipse) {
        for(int i = 0; i < newEllipse.length; i++) {
            if (newEllipse[i] <= ellipseDecay[i]) {
                newEllipse[i] = ellipseDecay[i] * (float)0.98;
                if (ellipseColor[i] > 100) {
                    ellipseColor[i] -= 5;
                }
            } else {
                ellipseColor[i] = 175;
//                System.out.println("Ej DeCaY");
            }
        }

        ellipseDecay = newEllipse.clone();

        return newEllipse;
    }

    public void drawGrid() {
        if(parent.mousePressed){
            speed += 0.3;
        } else {
            speed += 0.15;
        }

        parent.pushMatrix();
        parent.translate(parent.width/2, 0);

        parent.noStroke();
        parent.fill(255,0,225,100);
        parent.ellipse(cos(parent.width/2, speed), parent.height*(float)0.1, parent.width*(float)0.2, 15);
        parent.fill(0,255,235,100);
        parent.ellipse(cos(parent.width/2, speed), parent.height*(float)0.2, parent.width*(float)0.2, 15);
        parent.fill(255,0,225,100);
        parent.ellipse(cos(parent.width/2, speed), parent.height*(float)0.3, parent.width*(float)0.2, 15);
        parent.fill(0,255,235,100);
        parent.ellipse(cos(parent.width/2, speed), parent.height*(float)0.4, parent.width*(float)0.2, 15);
        parent.fill(255,0,225,100);
        parent.ellipse(cos(parent.width/2, speed), parent.height*(float)0.5, parent.width*(float)0.2, 15);
        parent.fill(0,255,235,100);
        parent.ellipse(cos(parent.width/2, speed), parent.height*(float)0.6, parent.width*(float)0.2, 15);
        parent.fill(255,0,225,100);
        parent.ellipse(cos(parent.width/2, speed), parent.height*(float)0.7, parent.width*(float)0.2, 15);
        parent.fill(0,255,235,100);
        parent.ellipse(cos(parent.width/2, speed), parent.height*(float)0.8, parent.width*(float)0.2, 15);
        parent.fill(255,0,225,100);
        parent.ellipse(cos(parent.width/2, speed), parent.height*(float)0.9, parent.width*(float)0.2, 15);

        parent.popMatrix();

        parent.pushMatrix();
        parent.translate(0, parent.height/2);

        parent.noStroke();
        parent.fill(0,255,235,100);
        parent.ellipse(parent.width*(float)0.1, sin(parent.height/2, speed), 15, parent.height*(float)0.2);
        parent.fill(255,0,225,100);
        parent.ellipse(parent.width*(float)0.2, sin(parent.height/2, speed), 15, parent.height*(float)0.2);
        parent.fill(0,255,235,100);
        parent.ellipse(parent.width*(float)0.3, sin(parent.height/2, speed), 15, parent.height*(float)0.2);
        parent.fill(255,0,225,100);
        parent.ellipse(parent.width*(float)0.4, sin(parent.height/2, speed), 15, parent.height*(float)0.2);
        parent.fill(0,255,235,100);
        parent.ellipse(parent.width*(float)0.5, sin(parent.height/2, speed), 15, parent.height*(float)0.2);
        parent.fill(255,0,225,100);
        parent.ellipse(parent.width*(float)0.6, sin(parent.height/2, speed), 15, parent.height*(float)0.2);
        parent.fill(0,255,235,100);
        parent.ellipse(parent.width*(float)0.7, sin(parent.height/2, speed), 15, parent.height*(float)0.2);
        parent.fill(255,0,225,100);
        parent.ellipse(parent.width*(float)0.8, sin(parent.height/2, speed), 15, parent.height*(float)0.2);
        parent.fill(0,255,235,100);
        parent.ellipse(parent.width*(float)0.9, sin(parent.height/2, speed), 15, parent.height*(float)0.2);
        parent.fill(255,0,225,100);

        parent.popMatrix();
    }

    public void drawLines() {
        parent.pushMatrix();
        parent.translate(parent.width/2, parent.height/2);

        parent.stroke(200,200,200);
        parent.strokeWeight(Math.min(parent.width, parent.height)*(float)0.02);
        parent.line(-parent.width*(float)0.2, -parent.height*(float)0.3, parent.width*(float)0.2, -parent.height*(float)0.3);
        parent.line(-parent.width*(float)0.2, -parent.height*(float)0.3, -parent.width*(float)0.3, 0);
        parent.line(parent.width*(float)0.2, -parent.height*(float)0.3, parent.width*(float)0.3, 0);
        parent.line(-parent.width*(float)0.3, 0, -parent.width*(float)0.2, parent.height*(float)0.3);
        parent.line(parent.width*(float)0.3, 0, parent.width*(float)0.2, parent.height*(float)0.3);
        parent.line(-parent.width*(float)0.2, parent.height*(float)0.3, parent.width*(float)0.2, parent.height*(float)0.3);
        parent.noStroke();

        parent.popMatrix();
    }

    public void drawCircles() {
        parent.pushMatrix();
        parent.translate(parent.width/2, parent.height/2);

        parent.stroke(0);
        parent.fill(ellipseColor[0]);
        parent.ellipse(-parent.width*(float)0.2, -parent.height*(float)0.3, ellipse[0], ellipse[0]);
        parent.fill(ellipseColor[1]);
        parent.ellipse(parent.width*(float)0.2, -parent.height*(float)0.3, ellipse[1], ellipse[1]);
        parent.fill(ellipseColor[2]);
        parent.ellipse(-parent.width*(float)0.3, 0, ellipse[2], ellipse[2]);
        parent.fill(ellipseColor[3]);
        parent.ellipse(parent.width*(float)0.3, 0, ellipse[3], ellipse[3]);
        parent.fill(ellipseColor[4]);
        parent.ellipse(-parent.width*(float)0.2, parent.height*(float)0.3, ellipse[4], ellipse[4]);
        parent.fill(ellipseColor[5]);
        parent.ellipse(parent.width*(float)0.2, parent.height*(float)0.3, ellipse[5], ellipse[5]);

        parent.popMatrix();
    }

    @Override
    public void updatePattern(byte[] fft, byte[] wave) {
        this.fft = fft;
        this.wave = wave;

        calculateFftAmp();
        calculateWaveAmp();
        calculateFftEllipse();
        ellipse = ellipseDecay(ellipse);
    }

    @Override
    public void drawPattern() {
        okToDraw = false;
        parent.background(0,5);

        drawLines();
        drawGrid();
        drawCircles();

        okToDraw = true;
    }

    @Override
    public boolean okToDraw() {
        return okToDraw;
    }

    public float sin(float size, float speed) {
        float pos = size * parent.sin(speed);// + parent.width/2;
        return pos;
    }

    public float cos(float size, float speed) {
        float pos = size * parent.cos(speed);// + parent.width/2;
        return pos;
    }
}
