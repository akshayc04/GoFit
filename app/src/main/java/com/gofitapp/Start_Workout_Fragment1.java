package com.gofitapp;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Start_Workout_Fragment1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Start_Workout_Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Start_Workout_Fragment1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String timerText=null;
    final int[] isRunning = new int[1];
    Chronometer chrono;
    long timeWhenStopped = 0;
    int position =0;
    // TODO: Rename and change types of parameters
    private ArrayList mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Start_Workout_Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Start_Workout_Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Start_Workout_Fragment1 newInstance(String param1, String param2) {
        Start_Workout_Fragment1 fragment = new Start_Workout_Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public static Start_Workout_Fragment1 newInstance(ArrayList beginner, int position) {
        Start_Workout_Fragment1 fragment = new Start_Workout_Fragment1();
        Bundle args = new Bundle();
        args.putSerializable("beginnerlist",beginner);
        args.putInt("position",position);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
    @Override
     public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        System.out.println("TAG, onSavedInstanceState");

//        chrono.stop();
//        timeWhenStopped = chrono.getBase() - SystemClock.elapsedRealtime();
//
//        timerText = chrono.getText().toString();
//        outState.putString("savedText", timerText);
//        outState.putLong("stopTime",timeWhenStopped);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState!=null)
        {
//            timerText = savedInstanceState.get("savedText").toString();
//            timeWhenStopped = (long) savedInstanceState.get("stopTime");
//            chrono.setText(timerText);
//            chrono.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
//            chrono.start();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = (ArrayList) getArguments().getSerializable("beginnerlist");
            position = getArguments().getInt("position");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_start__workout1, container, false);
        final SeekBar seekBar = (SeekBar) rootView.findViewById(R.id.strt_seek);
        ImageView image = (ImageView) rootView.findViewById(R.id.strt_img1);
        final HashMap data =(HashMap)mParam1.get(position);
        Picasso.with(getContext()).load((String) data.get("imageurl")).into(image);
        Button begin = (Button) rootView.findViewById(R.id.strt_btn1);
        Button done = (Button) rootView.findViewById(R.id.strt_btn2);

        final Animation animAlpha = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_alpha);
        chrono = ((Chronometer) rootView.findViewById(R.id.strt_chrono));
        chrono.setText("00:00");
        seekBar.setMax(50);

        isRunning[0] = 0;
        try {
            chrono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                    int h = (int) (time / 3600000);
                    int m = (int) (time - h * 3600000) / 60000;
                    int s = (int) (time - h * 3600000 - m * 60000) / 1000;
                    String hh = h < 10 ? "0" + h : h + "";
                    String mm = m < 10 ? "0" + m : m + "";
                    String ss = s < 10 ? "0" + s : s + "";
                    chrono.setText(mm + ":" + ss);
                    seekBar.incrementProgressBy(s);
                   // seekBar.setProgress(10);
                    if (chronometer.getText().toString().equalsIgnoreCase("00:10")) {
                        Toast.makeText(getContext(),
                                "time reached", Toast.LENGTH_SHORT).show();
                        chrono.stop();
                        isRunning[0] = 1;
                    }
                }
            });
        }
        catch (Exception e)
        {
            Log.d("exception","chrono"+e.getStackTrace());
        }
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timeWhenStopped ==0){
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();

                }
                else if (isRunning[0]==2)
                {
                    int h = (int) (timeWhenStopped / 3600000);
                    int m = (int) (timeWhenStopped - h * 3600000) / 60000;
                    int s = (int) (timeWhenStopped - h * 3600000 - m * 60000) / 1000;
                    long time = SystemClock.elapsedRealtime();
                    int hh = (int) (time / 3600000);
                    int mh = (int) (time - h * 3600000) / 60000;
                    int ss = (int) (time - h * 3600000 - m * 60000) / 1000;
                    //chrono.setBase(SystemClock.elapsedRealtime() - timeWhenStopped);
                    if (s<10)
                        chronoStart();

                    isRunning[0]=0;
                }
                else
                {
                    Long temp = SystemClock.elapsedRealtime() - chrono.getBase() ;
                    int h = (int) (temp / 3600000);
                    int m = (int) (temp - h * 3600000) / 60000;
                    int s = (int) (temp - h * 3600000 - m * 60000) / 1000;
                    if (s<10) {
                        chrono.start();
                        seekBar.setProgress(s);
                    }

                }
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               chrono.stop();
                long elapsedMillis = SystemClock.elapsedRealtime() - chrono.getBase();
                String chronoText = chrono.getText().toString();
                v.startAnimation(animAlpha);
                String array[] = chronoText.split(":");

                data.put("time",array[0]+":"+array[1]);
                data.put("completed",true);
               if (position+1 <= mParam1.size())
                    mListener.onFragmentInteraction(position+1,data);
            }
        });
        return rootView;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int position) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPause() {
        super.onPause();
//        chrono.stop();
//        if (isRunning[0] != 1)
//            timeWhenStopped = chrono.getBase() - SystemClock.elapsedRealtime();
        chronoPause();
        timerText = chrono.getText().toString();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (chrono!=null && timerText!=null) {


            chrono.setText(timerText);
            isRunning[0]=2;
//            if (isRunning[0] != 1)
//                chrono.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
            chrono.stop();
        }
    }
    private void chronoStart()
    {
        // on first start
        if ( timeWhenStopped == 0 )
            chrono.setBase( SystemClock.elapsedRealtime() );
            // on resume after pause
        else
        {
           // long intervalOnPause = (SystemClock.elapsedRealtime() - timeWhenStopped);
            chrono.setBase( SystemClock.elapsedRealtime() - timeWhenStopped );
        }

        chrono.start();
    }

    private void chronoPause()
    {
        chrono.stop();
        //timeWhenStopped = chrono.getBase() - SystemClock.elapsedRealtime();
        timeWhenStopped = SystemClock.elapsedRealtime() - chrono.getBase() ;
        int h = (int) (timeWhenStopped / 3600000);
        int m = (int) (timeWhenStopped - h * 3600000) / 60000;
        int s = (int) (timeWhenStopped - h * 3600000 - m * 60000) / 1000;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int position, HashMap data);
    }
}
