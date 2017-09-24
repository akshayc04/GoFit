package com.gofitapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import app.num.numandroidpagecurleffect.PageCurlView;

public class DietActivity extends AppCompatActivity implements DietFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        Bundle extras = getIntent().getExtras();
        String type = (String) extras.get("type");
        PageCurlView pageCurlView = (PageCurlView) findViewById(R.id.pagecurl_view);
        List<Integer> pages_id = new ArrayList<>();
        if (type.equals("breakfast"))
        {
            pages_id.add(R.drawable.brkfst);
            pages_id.add(R.drawable.brk2);
            pages_id.add(R.drawable.brk3);
            pages_id.add(R.drawable.brk4);
            pages_id.add(R.drawable.brk5);
            pages_id.add(R.drawable.brk6);
            pages_id.add(R.drawable.brk7);
            pages_id.add(R.drawable.brk8);
            pages_id.add(R.drawable.brk9);
            pages_id.add(R.drawable.brk10);
            pages_id.add(R.drawable.brk11);
            pages_id.add(R.drawable.brk12);
        }
        else if (type.equals("lunch"))
        {
            pages_id.add(R.drawable.l1);
            pages_id.add(R.drawable.l2);
            pages_id.add(R.drawable.l3);
            pages_id.add(R.drawable.l4);
            pages_id.add(R.drawable.l5);
            pages_id.add(R.drawable.l6);
            pages_id.add(R.drawable.l7);
            pages_id.add(R.drawable.l8);
            pages_id.add(R.drawable.l9);

        }
        else if (type.equals("dinner"))
        {
            pages_id.add(R.drawable.d1);
            pages_id.add(R.drawable.d2);
            pages_id.add(R.drawable.d3);
            pages_id.add(R.drawable.d4);
            pages_id.add(R.drawable.d5);
            pages_id.add(R.drawable.d6);
            pages_id.add(R.drawable.d7);
            pages_id.add(R.drawable.d8);
            pages_id.add(R.drawable.d9);
            pages_id.add(R.drawable.d10);
            pages_id.add(R.drawable.d11);
        }
        else if (type.equals("supplements"))
        {
            pages_id.add(R.drawable.s1);
            pages_id.add(R.drawable.s2);
            pages_id.add(R.drawable.s3);
            pages_id.add(R.drawable.s4);
            pages_id.add(R.drawable.s5);
            pages_id.add(R.drawable.s6);
            pages_id.add(R.drawable.s7);
            pages_id.add(R.drawable.s8);
            pages_id.add(R.drawable.s9);
            pages_id.add(R.drawable.s10);
            pages_id.add(R.drawable.s11);
        }



        pageCurlView.setCurlView(pages_id);
        pageCurlView.setCurlSpeed(65);
     //   getSupportFragmentManager().beginTransaction().replace(R.id.dietcontainer,DietFragment.newInstance()).addToBackStack(null).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
