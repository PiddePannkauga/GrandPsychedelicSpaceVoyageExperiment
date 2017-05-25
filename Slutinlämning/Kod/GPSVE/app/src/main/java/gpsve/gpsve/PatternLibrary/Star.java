package gpsve.gpsve.PatternLibrary;

import processing.core.PApplet;

/**
 * Created by Petter Månsson on 2017-05-19.
 */

public class Star{
    float x;
    float y;
    float z;
    float pz;
    float speed;
    int colorChoose = 1;
    private PApplet parent;

    public Star(PApplet parent){
        this.parent = parent;
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
        parent.noStroke();
        float sx = parent.map(x / z, 0, 1, 0, parent.width/2);
        float sy = parent.map(y / z, 0, 1, 0, parent.height/2);
        float r = parent.map(z, 0, parent.width/2, 16, 0);

        parent.pushStyle();
        if(colorChoose == 1) {
            parent.fill(255,0,223);
            colorChoose = 2;
        } else {
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
