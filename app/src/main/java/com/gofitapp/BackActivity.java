package com.gofitapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;

public class BackActivity extends MainActivity {

    MediaPlayer mediaPlayer;
    ArrayList backData = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
        super.createDrawer();
//        DatabaseReference childRef = FirebaseDatabase.getInstance().getReference().child("exercisedata").getRef();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference newref =ref.child("users/"+user.getUid());
//
//
//        final ExerciseData data;
//        childRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                HashMap map = (HashMap) dataSnapshot.getValue();
//                Iterator it = map.entrySet().iterator();
//                while (it.hasNext()) {
//                    Map.Entry pair = (Map.Entry)it.next();
//
//                    if(pair.getKey().equals("shoulder"))
//                    {
//                        shoulder.add(pair.getValue());
//                    }
//                    else if(pair.getKey().equals("biceps"))
//                    {
//                        biceps.add(pair.getValue());
//                    }
//                    else if(pair.getKey().equals("abs"))
//                    {
//                        abs.add(pair.getValue());
//                    }
//
//                    it.remove(); // avoids a ConcurrentModificationException
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.d("err","err");
//            }
//        });
        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.getSerializable("shoulder") != null) {
                backData = (ArrayList) extras.getSerializable("shoulder");
            }
        }
        TextView view = (TextView) findViewById(R.id.triceps);
        TextView harmStringview = (TextView) findViewById(R.id.harmstring);
        TextView latsview = (TextView) findViewById(R.id.lats);
        TextView lowerbackview = (TextView) findViewById(R.id.lowerback);
        TextView calvesview = (TextView) findViewById(R.id.calves);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ExerciseListActivity.class);
                ArrayList traplist;
                if (traps!=null && traps.size()>0) {
                    traplist = getSelectedList(traps);
                    intent.putExtra("shoulder", traplist);
                    mediaPlayer = MediaPlayer.create(BackActivity.this, R.raw.textclick);
                    mediaPlayer.start();
                    startActivity(intent);
                }
            }
        });
        harmStringview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ExerciseListActivity.class);
                ArrayList templist;
                if (harmstring!=null && harmstring.size()>0) {
                    templist = getSelectedList(harmstring);
                    intent.putExtra("shoulder", templist);
                    mediaPlayer = MediaPlayer.create(BackActivity.this, R.raw.textclick);
                    mediaPlayer.start();
                    startActivity(intent);
                }
            }
        });
//        latsview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),ExerciseListActivity.class);
//                ArrayList templist;
//                if (lats!=null && lats.size()>0) {
//                    templist = getSelectedList(lats);
//                    intent.putExtra("shoulder", templist);
//                    mediaPlayer = MediaPlayer.create(BackActivity.this, R.raw.textclick);
//                    mediaPlayer.start();
//                    startActivity(intent);
//                }
//            }
//        });
        lowerbackview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ExerciseListActivity.class);
                ArrayList templist;
                if (lowerback!=null && lowerback.size()>0) {
                    templist = getSelectedList(lowerback);
                    intent.putExtra("shoulder", templist);
                    mediaPlayer = MediaPlayer.create(BackActivity.this, R.raw.textclick);
                    mediaPlayer.start();
                    startActivity(intent);
                }
            }
        });
//        calvesview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),ExerciseListActivity.class);
//                ArrayList templist = null;
//                if (calves!=null && calves.size()>0)
//                templist = getSelectedList(calves);
//                intent.putExtra("shoulder",templist);
//                mediaPlayer = MediaPlayer.create(BackActivity.this, R.raw.textclick);
//                mediaPlayer.start();
//                startActivity(intent);
//            }
//        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                mediaPlayer = MediaPlayer.create(BackActivity.this, R.raw.float2);
                view.startAnimation(animScale);
                mediaPlayer.start();
                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //do nothing
        }
    }
}
