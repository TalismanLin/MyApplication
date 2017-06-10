package com.asiainfo.myapplication.animations;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.asiainfo.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SharedElementFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SharedElementFragment1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String EXTRA_SAMPLE = "sample";
    @Bind(R.id.square_blue)
    ImageView mSquareBlue;
    @Bind(R.id.sample2_button1)
    Button mSample2Button1;
    @Bind(R.id.sample2_button2)
    Button mSample2Button2;

    // TODO: Rename and change types of parameters
    private Sample sample;


    public SharedElementFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param sample Parameter 1.
     * @return A new instance of fragment SharedElementFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static SharedElementFragment1 newInstance(Sample sample) {
        SharedElementFragment1 fragment = new SharedElementFragment1();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_SAMPLE, sample);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sample = (Sample) getArguments().getSerializable(EXTRA_SAMPLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment1_shared_element, container, false);
        ButterKnife.bind(this, view);


        DrawableCompat.setTint(mSquareBlue.getDrawable(), sample.getColor());
        mSample2Button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addNextFragment(sample, mSquareBlue, false);
            }
        });

        mSample2Button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addNextFragment(sample, mSquareBlue, true);
            }
        });
        return view;
    }



    @SuppressLint("NewApi")
    private void addNextFragment(Sample sample, ImageView squareBlue, boolean overlap){
        SharedElementFragment2 sharedElementFragment2 = SharedElementFragment2.newInstance(sample);

        Slide slideTransition = new Slide(Gravity.RIGHT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));

        ChangeBounds changeBoundsTransition = new ChangeBounds();
        changeBoundsTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));

        sharedElementFragment2.setEnterTransition(slideTransition);
        sharedElementFragment2.setAllowEnterTransitionOverlap(overlap);
        sharedElementFragment2.setAllowReturnTransitionOverlap(overlap);
        sharedElementFragment2.setSharedElementEnterTransition(changeBoundsTransition);

        getFragmentManager().beginTransaction()
                .replace(R.id.sample2_content, sharedElementFragment2)
                .addToBackStack(null)
                .addSharedElement(squareBlue, getString(R.string.square_blue_name))
                .commit();
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
