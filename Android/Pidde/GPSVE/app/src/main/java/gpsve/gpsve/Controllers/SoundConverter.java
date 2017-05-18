package gpsve.gpsve.Controllers;

import android.media.audiofx.Visualizer;

/**
 * Created by Petter Månsson on 2017-04-03.
 */

public class SoundConverter{
    private Visualizer vis;
    private byte[] fftBytes;
    private byte[] waveBytes;

    public SoundConverter(){
        fftBytes = new byte[128];
        waveBytes = new byte[128];
        initiateVisualizer();
    }

    public void initiateVisualizer(){
        // Create the Visualizer object and set audio session to 0 (listen to system audio)
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

    public void disableVisualizer() {
        vis.release();
    }
}
