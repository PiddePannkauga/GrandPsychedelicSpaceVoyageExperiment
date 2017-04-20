package gpsve.gpsve;

import processing.core.PApplet;

/**
 * Created by bajs on 2017-03-22.
 */

public class PatternController extends PApplet {
    private SoundConverter soundConverter;
    private PatternPidde pidde;

    private String chosenPattern;

    public PatternController(SoundConverter soundConverter,String chosenPattern){
        this.soundConverter = soundConverter;
        this.chosenPattern = chosenPattern;
        pidde = new PatternPidde(this);


    }

    public static void main(String[] args) {
        PApplet.main(new String[]{"PatternController"});
    }

    public void settings() {
        size(1080,1920);
    }

    public void setup() {
        pidde.updatePattern(soundConverter.getFftBytes());
        noLoop();
        frame();
    }

    public void frame() {
        pidde.updatePattern(soundConverter.getFftBytes());
        pidde.drawPattern();
        redraw();
    }

    public void draw() {
        stroke(255);
        background(0,0,150);

        if(chosenPattern=="Pidde"){
        if(pidde.getOkToDraw()) {
            frame();
        }
        }
    }
}

