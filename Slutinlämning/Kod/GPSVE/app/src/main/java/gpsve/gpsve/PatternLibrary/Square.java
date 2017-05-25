package gpsve.gpsve.PatternLibrary;

import gpsve.gpsve.Interface.PatternLibraryInterface;
import processing.core.PApplet;

/**
 * Created by Nils Lindkvist on 2017-05-17.
 */

public class Square implements PatternLibraryInterface {
    private PApplet parent;
    private byte[] fft, wave;
    private boolean visible = false;

    public Square(PApplet parent) {
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
            parent.translate(parent.width/2, parent.height/2);
            parent.fill(0, 255, 0);
            parent.rect(0, 0, wave[100], wave[100]);
            parent.fill(255, 150, 0);
            parent.rect(0, 0, -wave[100], wave[100]);
            parent.fill(255,0,0);
            parent.rect(0, 0, wave[100], -wave[100]);
            parent.fill(0, 150, 255);
            parent.rect(0, 0, -wave[100], -wave[100]);
        }
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
