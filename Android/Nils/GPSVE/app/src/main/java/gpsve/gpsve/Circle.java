package gpsve.gpsve;

import java.util.Random;
import processing.core.*;

/**
 * Created by Nils on 2017-03-28.
 */

public class Circle extends PApplet {
    private int r, g, b, x1, y1, x2, y2, constant;
    private float x3, y3, radius, angle;

    Random random = new Random();

    public static void main(String args) {
        PApplet.main(new String[]{"gpsve.gpsve.Circle"});
    }

    public void settings() {
        size(1080,1536);
    }

    public void setup() {
        background(0);
        r = 0;
        g = 25;
        b = 50;
        x1 = width/2;
        System.out.println(x1);
        x2 = x1;
        x3 = 0;
        y1 = height/2;
        System.out.println(y1);
        y2 = y1;
        y3 = 0;
        constant = 15;
    }

    public void draw() {
        background(0,3);
        if(mousePressed){
            //background(0,60);
            constant = random.nextInt(16)+5;
        }
        fill(r,15,15);
        ellipse(x1, y1, 25, 25);
        fill(15,g,15);
        ellipse(x1, y2, 25, 25);
        fill(15,15,b);
        ellipse(x2, y1, 25, 25);
        fill(r,50,b);
        ellipse(x2, y2, 25, 25);
        fill(50,g,b);
        ellipse(x3+width/2, y3+height/2, 40, 40);

        r = (r+2)%25+230;
        g = (g+2)%25+230;
        b = (b+2)%25+230;

        x1 = (x1+constant)%width;
        y1 = (y1+constant)%height;
        x2 = (x2-constant);
        if(x2<0)
        x2 = x2+width;
        y2 = (y2-constant);
        if(y2<0)
        y2 = y2+height;
        angle += 0.05;
        radius += 0.5;
        x3 = radius * cos(angle);
        y3 = radius * sin(angle);
        if(y3+height/2 > height*1.12) {
            x3 = 0;
            y3 = 0;
            angle = 0;
            radius = 0;
        }
    }
}
