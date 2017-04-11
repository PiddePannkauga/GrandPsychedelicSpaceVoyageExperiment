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
        link();
    }

    public void updateVisualizerFFT(byte[] bytes) {
        fftBytes = bytes;
    }

    public void updateVisualizerWave(byte[] bytes) {
        for(int i = 0; i<bytes.length;i++) {
            if(bytes[i]<0){
                int k=bytes[i];
                k = -k;
                bytes[i]=(byte)k;            }
            if (bytes[i]>=0){
                waveBytes[i] = bytes[i];
            }
        }
    }

    public void link(){

        // Create the Visualizer object and attach it to our media player.

        vis = new Visualizer(0);
        vis.setCaptureSize(Visualizer.getCaptureSizeRange()[0]);

        // Pass through Visualizer data to VisualizerView
        Visualizer.OnDataCaptureListener captureListener = new Visualizer.OnDataCaptureListener()
        {
            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] bytes,
                                              int samplingRate)
            {
                updateVisualizerWave(bytes);
            }

            @Override
            public void onFftDataCapture(Visualizer visualizer, byte[] bytes,
                                         int samplingRate)
            {
                updateVisualizerFFT(bytes);
            }
        };

        vis.setDataCaptureListener(captureListener,
                Visualizer.getMaxCaptureRate(), true, true);
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
