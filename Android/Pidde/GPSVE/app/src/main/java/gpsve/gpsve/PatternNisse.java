package gpsve.gpsve;

import processing.core.*;

/**
 * Created by Nils on 2017-03-28.
 */

public class PatternNisse implements PatternInterface {
    private PApplet parent;
    private byte[] fft, wave;
    private int r, g, b, x1, y1, x2, y2, constant, fftAmp, waveAmp;
    private float x3, y3, radius, angle;
    private final int SPEED_SLOW = 12, SPEED_FAST = 20;
    private boolean okToDraw = true;

    public PatternNisse(PApplet parent) {
        this.parent = parent;
        setup();
    }

    public void setup() {
        r = 0;
        g = 25;
        b = 50;
        x1 = parent.width/2;
        x2 = x1;
        x3 = 0;
        y1 = parent.height/2;
        y2 = y1;
        y3 = 0;
        constant = SPEED_SLOW;
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
        System.out.println("fftAmp = " + fftAmp);
    }

    public void calculateWaveAmp() {
        waveAmp = 15;

        for(int i = 0; i < wave.length; i++) {
            if(wave[i] > waveAmp) {
                waveAmp = wave[i];
            }
        }
        System.out.println("waveAmp = " + waveAmp);
    }

    @Override
    public void updatePattern(byte[] fft, byte[] wave) {
        this.fft = fft;
        this.wave = wave;

        calculateFftAmp();
        calculateWaveAmp();
    }

    @Override
    public void drawPattern() {
        okToDraw = false;

        parent.background(0,3);
        if(parent.mousePressed){
            constant = SPEED_FAST;
        } else {
            constant = SPEED_SLOW;
        }

        parent.fill(r,15,15);
        parent.ellipse(x1, y1, fftAmp, fftAmp);
        parent.fill(15,g,15);
        parent.ellipse(x1, y2, fftAmp, fftAmp);
        parent.fill(15,15,b);
        parent.ellipse(x2, y1, fftAmp, fftAmp);
        parent.fill(r,50,b);
        parent.ellipse(x2, y2, fftAmp, fftAmp);
        parent.fill(50,g,b);
        parent.ellipse(x3+parent.width/2, y3+parent.height/2, 40, 40);

        r = (r+2)%25+230;
        g = (g+2)%25+230;
        b = (b+2)%25+230;

        x1 = (x1+constant)%parent.width;
        y1 = (y1+constant)%parent.height;
        x2 = (x2-constant);
        y2 = (y2-constant);
        if(x2<0)
            x2 = x2+parent.width;
        if(y2<0)
            y2 = y2+parent.height;

        angle += 0.05;
        radius += 0.5;
        x3 = radius * parent.cos(angle);
        y3 = radius * parent.sin(angle);
        if(y3+parent.height/2 > parent.height) {
            x3 = 0;
            y3 = 0;
            angle = 0;
            radius = 0;
        }

        okToDraw = true;
    }



    @Override
    public boolean okToDraw() {
        return okToDraw;
    }
}
