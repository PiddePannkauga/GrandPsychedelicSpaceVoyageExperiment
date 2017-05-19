package gpsve.gpsve.PatternLibrary;

import gpsve.gpsve.Interface.PatternLibraryInterface;
import processing.core.PApplet;

/**
 * Created by Petter on 2017-05-16.
 */

public class Circle implements PatternLibraryInterface {
    private PApplet parent;
    private byte[] fft, wave;
    private boolean visible = false;
    private float size = 0, ellipseDecay = 0;

    public Circle(PApplet parent){
        this.parent = parent;
    }

    @Override
    public void update(byte[] fft, byte[] wave) {
        this.fft = fft;
        this.wave = wave;

        size = Math.min(parent.width, parent.height)*(float)0.05;

        for(int i = 0; i < fft.length; i++){
            if(fft[i] > 0) {
                size += fft[i]*(float)0.05;
            }
        }

        if(size > Math.min(parent.width, parent.height)) {
            size = Math.min(parent.width, parent.height);
        }

        size = ellipseDecay(size);
    }

    public void show(){
        if(visible) {
            parent.noStroke();
            parent.fill(255, 255, 0);
            parent.ellipse(parent.width / 2, parent.height / 2, size, size);
        }
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public float ellipseDecay(float newEllipse) {
        if (newEllipse <= ellipseDecay) {
            newEllipse = ellipseDecay * (float)0.98;
        } else {

        }
        ellipseDecay = newEllipse;

        return newEllipse;
    }
}
