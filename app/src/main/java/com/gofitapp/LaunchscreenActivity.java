package com.gofitapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LaunchscreenActivity extends AppCompatActivity implements LaunchFragment.OnFragmentInteractionListener{
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    mediaPlayer = MediaPlayer.create(LaunchscreenActivity.this, R.raw.launch);
     //   mediaPlayer.start();
        setContentView(R.layout.activity_launchscreen);
        getSupportFragmentManager().beginTransaction().replace(R.id.launch,LaunchFragment.newInstance()).commit();

    }

    @Override
    public void onFragmentInteraction(View v, ImageView sharedView) {
        if (v.getId() == R.id.btn_letsgo)
        {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }
        else if (v.getId()==R.id.btn_about)
        {
            AboutFragment fragment = AboutFragment.newInstance();

            fragment.setSharedElementEnterTransition(new DetailsTransition());
            fragment.setEnterTransition(new android.transition.Fade());
            fragment.setExitTransition(new android.transition.Fade());
            fragment.setSharedElementReturnTransition(new DetailsTransition());
            getSupportFragmentManager().beginTransaction().addSharedElement(sharedView,"shared").replace(R.id.launch,fragment).addToBackStack(null).commit();
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
