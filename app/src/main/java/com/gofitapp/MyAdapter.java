package com.gofitapp;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by anarasim on 2/12/2017.
 */

public class MyAdapter extends android.support.v7.widget.RecyclerView.Adapter<MyAdapter.ViewHolder> {

    List selectedList =null;
    StorageReference storageRef;
    Context context;
    public void addItem(int position) {

    }



    public interface OnItemClickListener {
        public void onItemClick(View v, int position);
        public void onItemLongClick(View v, int position);
    }
    Boolean selected = false;
    Boolean deletion = false;

    private int lastPosition = -1;

    OnItemClickListener listener;
    static OnItemClickListener mItemClickListener ;
    public MyAdapter(ArrayList list,Context context) {
        selectedList = list;
        storageRef = FirebaseStorage.getInstance().getReference();
        this.context = context;
    }


    public void setOnItemClickListener(final OnItemClickListener listener, FragmentActivity activity)
    {
        mItemClickListener = listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (context.getClass().getSimpleName().equals("BeginnerActivity"))
        {
            view =LayoutInflater.from(parent.getContext()).inflate(R.layout.beginner_item_layout, parent,false);
        }
        else if (context.getClass().getSimpleName().equals("TrackActivity"))
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trackitemlayout, parent, false);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout, parent, false);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final HashMap map = (HashMap) selectedList.get(position);

        Picasso.with(context).load((String) map.get("imageurl")).into(holder.image);
        holder.name.setText(map.get("name").toString());

        if (context.getClass().getSimpleName().equals("BeginnerActivity"))
        {
            holder.name.setText(map.get("name").toString());
            if (holder.timerText!=null)
                holder.timerText.setText("Time : 10:00");
        }
        else if (context.getClass().getSimpleName().equals("TrackActivity"))
        {
            holder.name.setText(map.get("name").toString());
            holder.timerText.setText("Time : "+map.get("time").toString());
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                        map.put("selection", true);
                    else
                        map.put("selection",false);
                }



            });
        }
        //holder.image.setImageResource(R.drawable.img1);
    }



    @Override
    public int getItemCount() {
       return selectedList.size();
    }
    @Override
    public int getItemViewType ( int position ){
        return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        TextView timerText;
        public TextView description;
        CheckBox checkBox;
        int row_index=0;
        public ViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.btxt1);
            image = (ImageView) itemView.findViewById(R.id.img1);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
            timerText = (TextView) itemView.findViewById(R.id.btxt2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mItemClickListener != null)
                        mItemClickListener.onItemClick(image,getPosition());
                }
            });
//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//
//
//                    if (mItemClickListener != null)
//                        mItemClickListener.onItemLongClick(itemView,getPosition());
//                    return true;
//                }
//            });
        }


    }

}
