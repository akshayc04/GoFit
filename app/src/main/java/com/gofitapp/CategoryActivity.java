package com.gofitapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class CategoryActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        getWindow().setEnterTransition(new Slide(Gravity.TOP).setDuration(1000));

        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        Button button = (Button) findViewById(R.id.catbtn1);
        Button interm = (Button) findViewById(R.id.catbtn2);
        Button expert = (Button) findViewById(R.id.catbtn3);
        interm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BeginnerActivity.class);
                mediaPlayer = MediaPlayer.create(CategoryActivity.this, R.raw.clicks);
                v.startAnimation(animAlpha);
                mediaPlayer.start();
                intent.putExtra("type","INTERMEDIATE");
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BeginnerActivity.class);
                mediaPlayer = MediaPlayer.create(CategoryActivity.this, R.raw.clicks);
                v.startAnimation(animAlpha);
                mediaPlayer.start();
                intent.putExtra("type","BEGINNER");
                startActivity(intent);
            }
        });
        expert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BeginnerActivity.class);
                mediaPlayer = MediaPlayer.create(CategoryActivity.this, R.raw.clicks);
                v.startAnimation(animAlpha);
                mediaPlayer.start();
                intent.putExtra("type","EXPERT");
                startActivity(intent);
            }
        });
    }


}
