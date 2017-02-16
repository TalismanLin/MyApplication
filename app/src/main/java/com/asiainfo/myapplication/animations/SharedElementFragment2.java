package com.asiainfo.myapplication.animations;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.asiainfo.myapplication.R;

/**
 * Created by Asiainfo-zhujl3 on 2017/2/8/008.
 */

public class SharedElementFragment2 extends Fragment{

    private final static String EXTRA_SAMPLE = "sample";

    public static SharedElementFragment2 newInstance(Sample sample){
        SharedElementFragment2 fragment2 = new SharedElementFragment2();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_SAMPLE, sample);
        fragment2.setArguments(args);
        return fragment2;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sharedelement2, container, false);
        Sample sample = (Sample) getArguments().getSerializable(EXTRA_SAMPLE);
        ImageView squareBlue = (ImageView) view.findViewById(R.id.square_blue);
        DrawableCompat.setTint(squareBlue.getDrawable(),sample.getColor());
        return view;
    }
}
