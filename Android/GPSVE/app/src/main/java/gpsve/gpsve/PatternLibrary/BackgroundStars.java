package gpsve.gpsve.PatternLibrary;

import gpsve.gpsve.Interface.PatternLibraryInterface;
import processing.core.PApplet;

/**
 * Created by Nils Lindkvist and Petter Månsson on 2017-05-17.
 */

public class BackgroundStars implements PatternLibraryInterface {
    private PApplet parent;
    private byte[] fft, wave;
    private boolean visible = false;
    private float x;
    private float y;
    private float z;
    private float pz;
    private float speed;
    private int colorChoose =1;
    private int colorChoose2 =1;
    private int delay=0;
    private Star[] stars1 = new Star[250];
    private Star[] stars2 = new Star[250];
    private Star[] stars3 = new Star[250];

    public BackgroundStars(PApplet parent){
        this.parent = parent;
        for (int i = 0; i < stars1.length; i++) {
            stars1[i] = new Star(this.parent);
            stars2[i] = new Star(this.parent);
            stars3[i] = new Star(this.parent);
        }

    }

    @Override
    public void update(byte[] fft, byte[] wave) {
        this.fft = fft;
        this.wave = wave;
    }

    @Override
    public void show() {
        if (visible) {
            parent.background(0);
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
            parent.popMatrix();
        }
    }


    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}







