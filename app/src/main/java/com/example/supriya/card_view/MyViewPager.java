package com.example.supriya.card_view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



class MyViewPager extends FragmentPagerAdapter {
    private String tabTitles[]=new String[]{"Albums","Songs","Playlists"};
    public MyViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Main22Activity();
            case 1:
                return new Main2Activity();
            case 2:
                return  new Main23Activity();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
    public CharSequence getPageTitle(int position){
        return tabTitles[position];
    }
}