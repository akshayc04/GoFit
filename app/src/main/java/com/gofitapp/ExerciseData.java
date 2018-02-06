package com.gofitapp;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;



public class ExerciseData {
    String name;
    String description;
    String youtubelink;
    HashMap map;
    ArrayList list =null;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference userRef =userRef =ref.child("users/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Beginner");
    public ExerciseData(HashMap data)
    {
//        this.name = name;
//        this.description =description;
//        this.youtubelink = link;
        this.map = data;
    }

    public ExerciseData(ArrayList mParam1) {
        this.list = mParam1;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getDescription()
    {
        return description;

    }
    public int findString(String query)
    {
        int pos=0;
        HashMap temp = null;
        int count = 0;
        Iterator iter = list.iterator();
        while (iter.hasNext()) {

            temp = (HashMap) iter.next();
            Log.i("in while", temp.get("name") + "");
            String str = (String) temp.get("name");
            if (str.toLowerCase().contains(query.toLowerCase())) {

                pos = list.indexOf(temp);
                return pos;
            }
        }
        return pos;
    }
    public String getLink()
    {
        return youtubelink;
    }

    public void removeItemFromServer(HashMap temp) {
        if (temp!=null) {
            String id = (String) temp.get("id");
            userRef.child(id).removeValue();
        }
    }
}
