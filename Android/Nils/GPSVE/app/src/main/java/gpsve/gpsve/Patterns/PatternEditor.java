package gpsve.gpsve.Patterns;

import gpsve.gpsve.Interface.PatternInterface;
import gpsve.gpsve.Interface.PatternLibraryInterface;
import gpsve.gpsve.PatternLibrary.BackgroundStars;
import gpsve.gpsve.PatternLibrary.BackgroundLazer;
import gpsve.gpsve.PatternLibrary.BackgroundAlien;
import gpsve.gpsve.PatternLibrary.Circle;
import gpsve.gpsve.PatternLibrary.Line;
import gpsve.gpsve.PatternLibrary.Square;
import gpsve.gpsve.R;
import processing.core.PApplet;

/**
 * Created by Petter on 2017-05-16.
 */

public class PatternEditor implements PatternInterface {
    private PApplet parent;
    private byte[] wave, fft;
    private float circleSize=0;
    private boolean okToDraw = true;

    private PatternLibraryInterface bg1, bg2, bg3, circle, line, square;

    public PatternEditor(PApplet parent){
        this.parent = parent;
        bg1 = new BackgroundStars(this.parent);
        bg2 = new BackgroundLazer(this.parent);
        bg3 = new BackgroundAlien(this.parent);
        circle = new Circle(this.parent);
        line = new Line(this.parent);
        square = new Square(this.parent);

    }

    @Override
    public void updatePattern(byte[] fft, byte[] wave) {
        circle.update(fft, wave);
    }

    @Override
    public void drawPattern() {

//        for(PatternLibraryInterface pattern : patternList) {
//            pattern.show();
//        }
        bg1.show();
        bg2.show();
        bg3.show();
        circle.show();
        line.show();
        square.show();
    }

    @Override
    public boolean okToDraw() {
        return okToDraw;
    }

    public void setVisible(int menuitem, boolean visible) {
        switch (menuitem) {
            case R.id.background1check:
                bg1.setVisible(true);
                bg2.setVisible(false);
                bg3.setVisible(false);
                break;
            case R.id.background2check:
                bg1.setVisible(false);
                bg2.setVisible(true);
                bg3.setVisible(false);
                break;
            case R.id.background3check:
                bg1.setVisible(false);
                bg2.setVisible(false);
                bg3.setVisible(visible);
                System.out.println("bgPurp");
                break;
            case R.id.circleCheck:
                circle.setVisible(true);
                line.setVisible(false);
                square.setVisible(false);
                break;
            case R.id.lineCheck:
                circle.setVisible(false);
                line.setVisible(true);
                square.setVisible(false);
                break;
            case R.id.squareCheck:
                circle.setVisible(false);
                line.setVisible(false);
                square.setVisible(true);
                break;
        }
    }
}
