package gpsve.gpsve;

import processing.core.PApplet;

/**
 * Created by Nils on 2017-03-31.
 */

public class Square extends PApplet {
    public static void main(String args) {
        PApplet.main(new String[]{"gpsve.gpsve.Circle"});
    }

    public void settings() {
        size(1080,1536);
    }

    public void setup() {
        background(0,0,0);
    }

    public void draw() {
        if(mousePressed) {
            fill(0,100,150);
            rect(mouseX-25, mouseY-25, 50, 50);
        }
    }
}
