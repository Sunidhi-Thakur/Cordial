package com.example.cordial;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class StressPageAdapter extends FragmentPagerAdapter {

    private int numberOfTabs;

    StressPageAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new stress_listen();
            case 1:
                return new stress_read();
            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
