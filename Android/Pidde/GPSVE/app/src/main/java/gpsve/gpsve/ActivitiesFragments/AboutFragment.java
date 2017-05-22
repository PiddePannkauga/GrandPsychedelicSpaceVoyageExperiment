package gpsve.gpsve.ActivitiesFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import gpsve.gpsve.R;

/**
 * Created by Petter Månsson on 2017-05-09.
 * Fragment that is used to show the backstory.
 */
public class AboutFragment extends Fragment {

    public static ImageButton aboutButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.aboutfragment, container, false);
        view.setBackgroundColor(Color.WHITE);


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        aboutButton = (ImageButton)getActivity().findViewById(R.id.aboutbutton);


        aboutButton.setEnabled(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        aboutButton.setEnabled(true);


    }
}
