package gpsve.gpsve.PatternLibrary;

import gpsve.gpsve.Interface.PatternLibraryInterface;
import processing.core.PApplet;

/**
 * Created by Nils Lindkvist on 2017-05-17.
 */

public class BackgroundLazer implements PatternLibraryInterface {
    private PApplet parent;
    private byte[] fft, wave;
    private boolean visible = false;
    private float speed;

    public BackgroundLazer(PApplet parent) {
        this.parent = parent;
    }

    @Override
    public void update(byte[] fft, byte[] wave) {
        this.fft = fft;
        this.wave = wave;
    }

    @Override
    public void show() {
        if (visible) {
            parent.background(0, 10);
            drawGrid();
        } else {
            parent.background(0);
        }
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    private void drawGrid() {
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

    private float sin(float size, float speed) {
        float pos = size * parent.sin(speed);// + parent.width/2;
        return pos;
    }

    private float cos(float size, float speed) {
        float pos = size * parent.cos(speed);// + parent.width/2;
        return pos;
    }
}
