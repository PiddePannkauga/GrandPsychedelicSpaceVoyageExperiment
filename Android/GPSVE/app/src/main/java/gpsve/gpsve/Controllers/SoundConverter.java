package gpsve.gpsve.Controllers;

import android.media.audiofx.Visualizer;

/**
 * Created by Petter Månsson and Nils Lindkvist on 2017-04-03.
 * Class used to convert digital soundsignal to byte data.
 */

public class SoundConverter{
    private Visualizer vis;
    private byte[] fftBytes;
    private byte[] waveBytes;

    /**
     * Constructor for instantiating the Soundconverter.
     */
    public SoundConverter(){
        fftBytes = new byte[128];
        waveBytes = new byte[128];
        initiateVisualizer();
    }

    /**
     * Method contains code to setup android.media.audiofx.Visualizer;
     */
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

    /**
     * Getter for FFT byte[]
     * @return
     */
    public byte[] getFftBytes(){
        return fftBytes;
    }

    /**
     * Getter for wave byte[]
     * @return
     */
    public byte[] getWaveBytes(){
        return waveBytes;
    }

    /**
     * Method to be used for disabling the visualizer when changing activity.
     */
    public void disableVisualizer() {
        vis.release();
    }
}
