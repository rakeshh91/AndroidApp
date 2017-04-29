package com.possiblemobile.booksexercise.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.possiblemobile.booksexercise.R;
import com.possiblemobile.booksexercise.entity.ConnectionDetector;
import com.possiblemobile.booksexercise.interfaces.HandleNavigationListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private Button searchButton;
    private TextView errorTextInternet;
    private HandleNavigationListener handleNavigationListener = null;
    private ConnectionDetector connectionDetector;

    public MainFragment() {
        // Required empty public constructor
    }

    /* Returns an instance of this fragment */
    public static Fragment newInstance(){
        MainFragment mainFragment = new MainFragment();
        return mainFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);
        searchButton = (Button) rootView.findViewById(R.id.searchButton);
        errorTextInternet = (TextView)rootView.findViewById(R.id.errorTextInternet);
        errorTextInternet.setVisibility(View.GONE);
        connectionDetector = new ConnectionDetector(getActivity());
        try{
            handleNavigationListener = (HandleNavigationListener)rootView.getContext();
        }catch(ClassCastException e){
            throw new ClassCastException(getString(R.string.classcast_exception_listener));
        }

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(connectionDetector.isNetworkConnectedPP()) {
                    errorTextInternet.setVisibility(View.GONE);
                    handleNavigationListener.navigateToItemSelected(R.id.searchButton);
                }else{
                    errorTextInternet.setBackgroundColor(getResources().getColor(R.color.errorInternet, null));
                    errorTextInternet.setText(getString(R.string.no_network));
                    errorTextInternet.setVisibility(View.VISIBLE);
                }
            }
        });

        return rootView;
    }

}
