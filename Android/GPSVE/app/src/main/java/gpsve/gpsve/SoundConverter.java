package gpsve.gpsve;

import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Petter on 2017-04-03.
 */

public class SoundConverter extends AppCompatActivity{

    private Visualizer vis;
    private MediaPlayer player;
    private byte[] mFFTBytes;
    private byte[] mBytes;


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

    public void chooseSong() {

        player = MediaPlayer.create(this, R.raw.disco);
        player.setLooping(true);
        player.start();


        link(player);
    }
}
