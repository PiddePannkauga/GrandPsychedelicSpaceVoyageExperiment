package gpsve.gpsve;

import processing.core.PApplet;

import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.util.Log;

/**
 * Created by bajs on 2017-03-22.
 */

public class VisualizerDemo extends PApplet {

    private Visualizer vis;
    private MediaPlayer mMediaPlayer;
    private byte[] mFFTBytes;
    private byte[] mBytes;
    private int i=0;
    private int integer;

    public static void main(String[] args) {
        PApplet.main(new String[]{"VisualizerDemo"});
    }

    public void settings() {
        size(1080,1920);
    }

    public void setup() {
        mFFTBytes = new byte[1024];
    }

    public void updateVisualizerFFT(byte[] bytes) {
        for(int i = 0; i<bytes.length;i++) {
            if (bytes[i]>=1){
                mFFTBytes = bytes;
            }
        }
    }

    public void updateVisualizer(byte[] bytes) {

        mBytes = bytes;
    }


    public void draw() {
        //       mFFTBytes = new byte[1024];
        stroke(255);
        background(0);

        ellipse(width / 2, height / 2, mFFTBytes[i] * 128, mFFTBytes[i] * 128);

        Log.i("VAD FINNS I DENA", ""+mFFTBytes[i]);
        i++;
        if(i==1023){
            i=0;
        }
//
//
    }

    public void link(MediaPlayer player)
    {
        if(player == null)
        {
            throw new NullPointerException("Cannot link to null MediaPlayer");
        }

        // Create the Visualizer object and attach it to our media player.

        vis = new Visualizer(player.getAudioSessionId());
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
                Visualizer.getMaxCaptureRate() / 2, true, true);

        // Enabled Visualizer and disable when we're done with the stream
        vis.setEnabled(true);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer)
            {
                vis.setEnabled(false);
            }
        });
    }
}

