package com.gofitapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ToxicBakery.viewpager.transforms.TabletTransformer;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewPagerActivity extends AppCompatActivity {
    ArrayList selectedList;
    int position;
    HashMap map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selectedList  = (ArrayList) extras.getSerializable("selectedlist");
            position = (int) extras.get("position");


        }
       map = (HashMap) selectedList.get(position);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pager.setPageTransformer(true, new TabletTransformer() {
        });


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
      //  getSupportFragmentManager().beginTransaction().replace(R.id.layout1,ImageFragment.newInstance());

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                case 1: return ImageFragment.newInstance(map);
                case 2: return VideoFragment.newInstance(map);
                case 0: return DescriptionFragment.newInstance(map);

                default: return ImageFragment.newInstance(map);
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
