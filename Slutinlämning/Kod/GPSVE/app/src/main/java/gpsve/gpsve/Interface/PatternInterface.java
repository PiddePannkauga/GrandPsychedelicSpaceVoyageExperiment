package gpsve.gpsve.Interface;

/**
 * Created by Petter Månsson on 2017-04-18.
 * Interface to be used when creating a static pattern
 */

public interface PatternInterface {

    public void updatePattern(byte[] fft, byte[] wave);
    public void drawPattern();
    public boolean okToDraw();
}
