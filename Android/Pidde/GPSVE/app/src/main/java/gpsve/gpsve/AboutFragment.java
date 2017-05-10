package gpsve.gpsve;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bajs on 2017-05-09.
 */
public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.aboutfragment, container, false);
        // Inflate the layout for this fragment

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }
}
