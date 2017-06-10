package com.asiainfo.myapplication.animations;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asiainfo.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SharedElementFragment2 extends Fragment {

    private static final String EXTRA_SAMPLE = "sample";
    @Bind(R.id.square_blue)
    ImageView mSquareBlue;
    @Bind(R.id.fragment2_body1)
    TextView mFragment2Body1;
    @Bind(R.id.fragment2_title)
    TextView mFragment2Title;

    public static SharedElementFragment2 newInstance(Sample sample) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_SAMPLE, sample);
        SharedElementFragment2 fragment = new SharedElementFragment2();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment2_shared_element, container, false);
        ButterKnife.bind(this, view);
        Sample sample = (Sample) getArguments().getSerializable(EXTRA_SAMPLE);
        DrawableCompat.setTint(mSquareBlue.getDrawable(), sample.getColor());

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
