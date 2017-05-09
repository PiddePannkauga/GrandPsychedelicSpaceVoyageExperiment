package gpsve.gpsve;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("main.onCreate()");
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.MODIFY_AUDIO_SETTINGS}, 0);

    }

    public void startPatternActivity(View view) {
        intent = new Intent(this, PatternActivity.class);
        startActivity(intent);
    }

    public void showAbout(View view){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        setContentView(R.layout.aboutfragment);
        getSupportFragmentManager().beginTransaction()
                .add(new AboutFragment(), null ).addToBackStack(null).commit();

    }


    protected void onStart() {
        super.onStart();
        System.out.println("main.onStart()");
    }

    protected void onResume() {
        super.onResume();
        System.out.println("main.onResume()");
    }

    protected void onStop() {
        super.onStop();
        System.out.println("main.onStop()");
    }

    protected void onPause() {
        super.onPause();
        System.out.println("main.onPause()");
    }

    protected void onDestroy() {
        super.onDestroy();
        System.out.println("main.onDestroy()");
    }

    protected void onRestart() {
        super.onRestart();
        System.out.println("main.onRestart()");
    }

    public static class AboutFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.aboutfragment, container, false);
        }


    }
}
