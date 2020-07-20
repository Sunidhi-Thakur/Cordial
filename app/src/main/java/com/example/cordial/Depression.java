package com.example.cordial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class Depression extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem tab1, tab2;
    DepressionPageAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depression);

        tabLayout = findViewById(R.id.tabLayout);
        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new DepressionPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0)
                    pagerAdapter.notifyDataSetChanged();
                else if (tab.getPosition() == 1)
                    pagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



    }
}
