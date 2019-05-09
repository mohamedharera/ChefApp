package com.elchefapp.elchefapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elchefapp.elchefapp.R;


public class NearByChefFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view =  inflater.inflate(R.layout.fragment_chef_near_by, container, false);


         return view;
    }
}
