package com.example.cordial;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class DepressionPageAdapter extends FragmentPagerAdapter {


    private int numberOfTabs;


    DepressionPageAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new depression_listen();
            case 1:
                return new depression_read();
            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
