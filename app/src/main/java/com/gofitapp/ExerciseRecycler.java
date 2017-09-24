package com.gofitapp;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;
import jp.wasabeef.recyclerview.animators.FlipInRightYAnimator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExerciseRecycler.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExerciseRecycler#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExerciseRecycler extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recycler=null;
    NavigationListener navigationListener;
    LinearLayoutManager layoutManager = null;
    // TODO: Rename and change types of parameters
    private ArrayList mParam1;
    private String mParam2;
    MyAdapter adapter =null;
    ExerciseData data;
    private OnFragmentInteractionListener mListener;

    public ExerciseRecycler() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExerciseRecycler.
     */
    // TODO: Rename and change types and number of parameters
    public static ExerciseRecycler newInstance(String param1, String param2) {
        ExerciseRecycler fragment = new ExerciseRecycler();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public static ExerciseRecycler newInstance(ArrayList list) {
        ExerciseRecycler fragment = new ExerciseRecycler();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, list);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (ArrayList) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_exercise_recycler, container, false);
         data = new ExerciseData(mParam1);
        setHasOptionsMenu(true);
        if (mParam1.size() ==0)
        {
            return view;
        }
        recycler = (RecyclerView)view.findViewById(R.id.cardList);

        if (getActivity().getClass().getSimpleName().equals("TrackActivity"))
        {
            layoutManager = new LinearLayoutManager(getContext());
        }
        else
        {
            layoutManager = new GridLayoutManager(getContext(),2);
        }
        //navigationListener = (NavigationListener) getActivity();
        recycler.setLayoutManager(layoutManager);
        adapter = new MyAdapter(mParam1,getContext());
        recycler.setAdapter(adapter);
       itemAnimation();
      //  adapterAnimation();



        MyAdapter.OnItemClickListener listener = new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (mListener!=null) {
                    mListener.onFragmentInteraction(v, position);
                }
            }
            @Override
            public void onItemLongClick(View v, int position)
            {
                v.setBackgroundColor(Color.parseColor("#567845"));
                HashMap map = (HashMap) mParam1.get(position);
                map.put("selected",true);
                mParam1.add(position,map);

            }

        };
        adapter.setOnItemClickListener(listener,getActivity());
        return view;


    }

    private void adapterAnimation() {
        SlideInRightAnimationAdapter alphaAdapter = new SlideInRightAnimationAdapter (adapter);
        alphaAdapter.setDuration(2000);
        alphaAdapter.setFirstOnly(false);
        recycler.setAdapter(new SlideInRightAnimationAdapter(alphaAdapter));
    }

    private void itemAnimation() {
        FlipInRightYAnimator animator = new FlipInRightYAnimator();
        animator.setAddDuration(800);
        animator.setRemoveDuration(800);
        animator.setMoveDuration(800);
        animator.setChangeDuration(800);
        recycler.setItemAnimator(animator);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
           // mListener.onFragmentInteraction(uri);
        }
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.toolbar, menu);

        // Locate MenuItem with ShareActionProvider
        SearchView srch = (SearchView) menu.findItem(R.id.action_search).getActionView();
        if(srch!=null)
        {
            srch.setOnQueryTextListener(new SearchView.OnQueryTextListener()
            {

                @Override
                public boolean onQueryTextSubmit(String query) {

                    int pod = data.findString(query);
                    Log.i("in serach","Hello"+ pod);
                    if (pod >= 0)
                        recycler.scrollToPosition(pod);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("in serach","Hello");
                    return true;
                }
            });
        }

        if(getActivity().getClass().getSimpleName().equals("TrackActivity")) {
            menu.findItem(R.id.action_search).setVisible(true);
            menu.findItem(R.id.delete).setVisible(true);
        }
        if(getActivity().getClass().getSimpleName().equals("ExerciseListActivity")) {
            menu.findItem(R.id.action_search).setVisible(false);
            menu.findItem(R.id.delete).setVisible(false);
        }
        // Fetch and store ShareActionProvider



    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                Iterator iter = mParam1.iterator();
                HashMap temp = null;
                int count =0;
                try {
                    while (iter.hasNext()) {

                        temp = (HashMap) iter.next();
                        if ((temp.get("selection") !=null && (Boolean) temp.get("selection")==true)) {

                            count = mParam1.indexOf(temp);
                            iter.remove();
                            data.removeItemFromServer(temp);
                            adapter.notifyItemRemoved(count);
                            adapter.notifyItemRangeChanged(count, adapter.getItemCount() - count);
                        }

                    }
                }
                catch (Exception  e)
                {
                    Log.d("stack",e.getStackTrace()+"");
                }

        }

        return super.onOptionsItemSelected(item);
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
        void onFragmentInteraction(View v , int position);
    }
}
