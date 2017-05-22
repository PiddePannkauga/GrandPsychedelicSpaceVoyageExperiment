package gpsve.gpsve.Interface;

/**
 * Created by Nils Lindkvist on 2017-05-16.
 * Interface to be used when creating patterns for the PatternEditor.
 */

public interface PatternLibraryInterface {
    public void update(byte[] fft, byte[] wave);
    public void show();
    public void setVisible(boolean visible);
}
