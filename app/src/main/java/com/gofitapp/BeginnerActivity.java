package com.gofitapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BeginnerActivity extends MainActivity implements BeginnerRoutineFragment.OnFragmentInteractionListener,Start_Workout_Fragment1.OnFragmentInteractionListener {
    final ArrayList beginner = new ArrayList();

    ArrayList tracker = new ArrayList();
    DatabaseReference childRef;
    DatabaseReference userRef;
    Fragment mContent;
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //mContent = getSupportFragmentManager().getFragment(outState, "myFragmentName");
        //Save  the fragment's instance

        if(mContent != null)
            getSupportFragmentManager().putFragment(outState, "frag", mContent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(savedInstanceState != null)
        {


            mContent = getSupportFragmentManager().getFragment(savedInstanceState, "frag");
        }
        setContentView(R.layout.activity_beginner);
        Bundle extras = getIntent().getExtras();
        String type = (String) extras.get("type");
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        if (type!=null && collapsingToolbar!=null)
            collapsingToolbar.setTitle(type);
        if (type.equals("EXPERT"))
            type = "beginner";
        super.createDrawer();
        try {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            childRef = ref.child(type.toLowerCase()).getRef();
            userRef = ref.child("users/" + FirebaseAuth.getInstance().getCurrentUser().getUid());
            userRef.child("Beginner").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() != null) {

                        HashMap map = (HashMap) dataSnapshot.getValue();
                        Iterator it = map.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry) it.next();
                            tracker.add(pair.getValue());
                            it.remove(); // avoids a ConcurrentModificationException
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d("err", "err");
                }
            });


            childRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d("ins", "ins");
                    HashMap map = (HashMap) dataSnapshot.getValue();
                    Iterator it = map.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        beginner.add(pair.getValue());
                        it.remove(); // avoids a ConcurrentModificationException
                    }
                    if (tracker.size() == beginner.size()) {
                        showAlert(1);
                    } else if (savedInstanceState == null) {

                        getSupportFragmentManager().beginTransaction().replace(R.id.beginnercontainer, BeginnerRoutineFragment.newInstance(beginner, tracker.size())).commit();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d("err", "err");
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error Downloading data", Toast.LENGTH_LONG).show();
        }

    }

    private void showAlert(final int x) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(BeginnerActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle("Routine Complete");
        dialog.setMessage("You have Completed the routine!! Do you want to start a new Routine? Selecting Yes will clear your Exercise history" );
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                //Action for "Delete".
                userRef.removeValue();
                getSupportFragmentManager().beginTransaction().replace(R.id.beginnercontainer,BeginnerRoutineFragment.newInstance(beginner,0)).commit();
            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Action for "Cancel".
                        if (x ==1)
                            onBackPressed();
                    }
                });

        final AlertDialog alert = dialog.create();
        alert.show();
    }

    @Override
    public void onFragmentInteraction(View v, int position) {

        Intent intent = new Intent(getApplicationContext(),ViewPagerActivity.class);


        intent.putExtra("selectedlist",beginner);
        intent.putExtra("position",position);
        startActivity(intent);
    }

    @Override
    public void beginWorkout(ArrayList list, int size) {
        getSupportFragmentManager().beginTransaction().replace(R.id.beginnercontainer, Start_Workout_Fragment1.newInstance(list,0)).addToBackStack(null).commit();
    }

    @Override
    public void onFragmentInteraction(int position,HashMap data) {
        writeData(position,data);
        if (position == beginner.size())
        {
            showAlert(2);
        }
        else {
            getSupportFragmentManager().beginTransaction().replace(R.id.beginnercontainer, Start_Workout_Fragment1.newInstance(beginner, position)).addToBackStack(null).commit();
        }

    }
    public void writeData(int position,HashMap data)
    {
       // history.setValue(data);
        userRef.child("Beginner/"+data.get("id").toString()).setValue(data);
    }

}
