package gpsve.gpsve;

import processing.core.PApplet;

/**
 * Created by Nils on 2017-03-31.
 */

public class Square extends PApplet {
    private int r, g, b, factor;

    public static void main(String args) {
        PApplet.main(new String[]{"gpsve.gpsve.Circle"});
    }

    public void settings() {
        size(1080,1536);
    }

    public void setup() {
        background(0,0,0);
        r = 50;
        g = 0;
        b = 220;
        factor = 1;
    }

    public void draw() {
        r += factor;
        if (r >= 255 || r <= 50) {
            factor *= -1;
        }

        if(mousePressed) {
            //fill(0,100,150);
            fill(r,g,b);
            rect(mouseX-25, mouseY-25, 50, 50);
        }
    }
}
