package gpsve.gpsve;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Petter "Pidde" Månsson on 2017-05-09.
 */
public class AboutFragment extends Fragment {

    public static Button aboutButton;

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
        aboutButton = (Button)getActivity().findViewById(R.id.aboutbutton);


        aboutButton.setEnabled(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        aboutButton.setEnabled(true);


    }
}
