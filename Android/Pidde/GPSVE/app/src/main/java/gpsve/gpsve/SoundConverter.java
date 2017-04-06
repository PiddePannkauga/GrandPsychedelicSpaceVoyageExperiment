package gpsve.gpsve;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;

import android.util.Log;


/**
 * Created by Petter on 2017-04-03.
 */

public class SoundConverter{

    private Visualizer vis;
    private byte[] mFFTBytes;
    private byte[] mBytes;
    

    public SoundConverter(){
        mFFTBytes = new byte[1024];
        mBytes = new byte[1024];
        link();
    }
    public void updateVisualizerFFT(byte[] bytes) {

        for(int i = 0; i<bytes.length;i++) {
            if(bytes[i]<0){
                int k=bytes[i];
                k = -k;
                bytes[i]=(byte)k;            }
            if (bytes[i]>=1){
                mFFTBytes[i] = bytes[i];

            }
        }

    }

    public void updateVisualizer(byte[] bytes) {
        for(int i = 0; i<bytes.length;i++) {
            if(bytes[i]<0){
                int k=bytes[i];
                k = -k;
                bytes[i]=(byte)k;            }
            if (bytes[i]>=1){
                mBytes[i] = bytes[i];

            }
        }


    }

    public void link(){

        // Create the Visualizer object and attach it to our media player.

        vis = new Visualizer(0);
        vis.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);

        // Pass through Visualizer data to VisualizerView
        Visualizer.OnDataCaptureListener captureListener = new Visualizer.OnDataCaptureListener()
        {
            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] bytes,
                                              int samplingRate)
            {
                updateVisualizer(bytes);
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
        Log.i("WTF", Visualizer.getMaxCaptureRate()+"");
        // Enabled Visualizer and disable when we're done with the stream
        vis.setEnabled(true);

    }

    public byte[] getSoundBytes(){
        return mFFTBytes;
    }
}
