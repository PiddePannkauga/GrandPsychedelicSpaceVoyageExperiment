package gpsve.gpsve;

import processing.core.PApplet;

/**
 * Created by bajs on 2017-03-22.
 */

public class PatternController extends PApplet {
    private SoundConverter soundConverter;
    private PatternPidde pidde;
    private PatternCircle circle;
    private String chosenPattern;

    public PatternController(SoundConverter soundConverter,String chosenPattern){
        this.soundConverter = soundConverter;
        this.chosenPattern = chosenPattern;
        pidde = new PatternPidde(this);
        circle = new PatternCircle(this);

    }

    public static void main(String[] args) {
        PApplet.main(new String[]{"PatternController"});
    }

    public void settings() {
        size(1080,1920);
    }

    public void setup() {
        pidde.updatePattern(soundConverter.getFftBytes());
    }

    public void draw() {
        stroke(255);
        background(0,0,150);

        if(chosenPattern=="Pidde"){
        if(pidde.okToDraw()) {
            pidde.updatePattern(soundConverter.getFftBytes());
            pidde.drawShape();
        }
        }else if(chosenPattern == "Circle"){
        circle.updatePattern(soundConverter.getFftBytes());
    }
    }
}

