package gpsve.gpsve.Interface;

/**
 * Created by Petter Månsson on 2017-04-18.
 */

public interface PatternInterface {

    public void updatePattern(byte[] fft, byte[] wave);
    public void drawPattern();
    public boolean okToDraw();
}
