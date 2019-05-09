package com.elchefapp.elchefapp.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elchefapp.elchefapp.R;

import java.util.ArrayList;
import java.util.List;

public class HomeContentFragment extends android.support.v4.app.Fragment {

    private TabLayout tabLayout;
    private ViewPager viewpagerFragmentViewer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_home_content, container, false);

        tabLayout = view.findViewById(R.id.tab_layout);
        viewpagerFragmentViewer = view.findViewById(R.id.viewpager_fragmentViewer);


        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new NearByChefFragment(),"Near by");
        adapter.addFragment(new CustomerPlacesFragment(),"Places");
        adapter.addFragment(new ProfileFragment(),"Profile");

        viewpagerFragmentViewer.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpagerFragmentViewer);

        return view;
    }




    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
