package gpsve.gpsve;

/**
 * Created by Petter on 2017-04-18.
 */

public interface PatternInterface {

    public void updatePattern(byte[] bytes);
    public void drawPattern();
    public void setOkToDraw(boolean okToDraw);
    public boolean getOkToDraw();

}
