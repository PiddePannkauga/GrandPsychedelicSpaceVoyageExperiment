package gpsve.gpsve.Interface;

/**
 * Created by Nils on 2017-05-16.
 */

public interface PatternLibraryInterface {
    public void update(byte[] fft, byte[] wave);
    public void show();
    public void setVisible(boolean visible);
}
