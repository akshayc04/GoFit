package com.gofitapp;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class ExerciseListActivity extends MainActivity implements NavigationListener , ExerciseRecycler.OnFragmentInteractionListener{
    ArrayList selectedList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        super.createDrawer();
        Bundle extras = getIntent().getExtras();
        String type = (String) extras.get("type");
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(type);
        if (extras != null) {
            if (extras.getSerializable("shoulder")!= null)
            {
                selectedList  = (ArrayList) extras.getSerializable("shoulder");
            }

            //The key argument here must match that used in the other activity
            if (selectedList!=null)
            {
               getSupportFragmentManager().beginTransaction().replace(R.id.exercisecontainer, ExerciseRecycler.newInstance(selectedList)).commit();
            }
        }
    }

    @Override
    public void navigate(View v, int position) {

        ImageView imageView = (ImageView)v;
        int id = imageView.getId();
        String idStr = getResources().getResourceName(id);
        Intent intent = new Intent(getApplicationContext(),ViewPagerActivity.class);
        intent.putExtra("id",idStr);

        intent.putExtra("selectedlist",selectedList);
        intent.putExtra("position",position);
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(View v , int position) {
        ImageView imageView = (ImageView)v;
        int id = imageView.getId();
        String idStr = getResources().getResourceName(id);
        Intent intent = new Intent(getApplicationContext(),ViewPagerActivity.class);
        intent.putExtra("id",idStr);

        intent.putExtra("selectedlist",selectedList);
        intent.putExtra("position",position);
        startActivity(intent);
    }
}
