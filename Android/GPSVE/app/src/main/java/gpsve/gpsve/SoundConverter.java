package gpsve.gpsve;

import android.media.audiofx.Visualizer;

/**
 * Created by Petter on 2017-04-03.
 */

public class SoundConverter{
    private Visualizer vis;
    private byte[] fftBytes;
    private byte[] waveBytes;

    public SoundConverter(){
        fftBytes = new byte[256];
        waveBytes = new byte[256];
        init();
    }

    public void init(){
        // Create the Visualizer object and attach it to our media player.
        vis = new Visualizer(0);
        vis.setCaptureSize(Visualizer.getCaptureSizeRange()[0]);

        // Pass through Visualizer data to VisualizerView
        Visualizer.OnDataCaptureListener captureListener = new Visualizer.OnDataCaptureListener() {
            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] bytes, int samplingRate) {
                waveBytes = bytes;
            }

            @Override
            public void onFftDataCapture(Visualizer visualizer, byte[] bytes, int samplingRate) {
                fftBytes = bytes;
            }
        };

        vis.setDataCaptureListener(captureListener, Visualizer.getMaxCaptureRate(), true, true);
        // Enabled Visualizer and disable when we're done with the stream
        vis.setEnabled(true);
    }

    public byte[] getFftBytes(){
        return fftBytes;
    }

    public byte[] getWaveBytes(){
        return waveBytes;
    }
}
