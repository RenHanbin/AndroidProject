package com.example.celia.demo1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.celia.demo1.index.Derection;
import com.example.celia.demo1.index.HotMajor;
import com.example.celia.demo1.index.IndexJobs;
import com.example.celia.demo1.index.IndexLearn;
import com.example.celia.demo1.index.IndexScore;
import com.example.celia.demo1.index.MajorProspect;
import com.example.celia.demo1.index.PaimingSchool;
import com.example.celia.demo1.index.RegionalEconomy;
import com.example.celia.demo1.index.RegionalProfile;
import com.example.celia.demo1.index.Select;
import com.example.celia.demo1.index.UniversityIntroduce;
import com.example.celia.demo1.index.paiming;

public class FragmentTab1 extends Fragment{
    private ImageView learn;
    private ImageView stats;
    private ImageView job;
    private ImageView hot;
    private ImageView school;
    private ImageView stats1;
    private ImageView derection;
    private ImageView select;
    private ImageView area;
    private ImageView eco;
    private ImageView jobs;
    private ImageView score;
    //创建View时调用
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page1,container,false);//根据布局文件产生布局控件
        //false 不放到第二个参数中，true 放到第二个参数中
        IndexListener listener = new IndexListener();
        learn = view.findViewById(R.id.iv_learn);
        stats = view.findViewById(R.id.iv_stats);
        job = view.findViewById(R.id.iv_job);
        hot = view.findViewById(R.id.iv_hot);
        school = view.findViewById(R.id.iv_school);
        stats1 = view.findViewById(R.id.iv_stats1);
        derection = view.findViewById(R.id.iv_derection);
        select = view.findViewById(R.id.iv_select);
        area = view.findViewById(R.id.iv_area);
        eco = view.findViewById(R.id.iv_eco);
        jobs = view.findViewById(R.id.iv_jobs);
        score = view.findViewById(R.id.iv_score);
        learn.setOnClickListener(listener);
        stats.setOnClickListener(listener);
        job.setOnClickListener(listener);
        hot.setOnClickListener(listener);
        school.setOnClickListener(listener);
        stats1.setOnClickListener(listener);
        derection.setOnClickListener(listener);
        select.setOnClickListener(listener);
        area.setOnClickListener(listener);
        eco.setOnClickListener(listener);
        jobs.setOnClickListener(listener);
        score.setOnClickListener(listener);
        return view;
    }
    //当view创建完成
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    //监听器
    private class IndexListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_learn:{
                    Intent intent = new Intent();
                    intent.setClass(getActivity().getApplicationContext(),IndexLearn.class);
                    startActivity(intent);
                    break;
                }
                case R.id.iv_stats:{
                    Intent intent = new Intent();
                    intent.setClass(getActivity().getApplicationContext(),paiming.class);
                    startActivity(intent);
                    break;
                }
                case R.id.iv_job:{
                    Intent intent = new Intent();
                    intent.setClass(getActivity().getApplicationContext(),MajorProspect.class);
                    startActivity(intent);
                    break;
                }
                case R.id.iv_hot:{
                    Intent intent = new Intent();
                    intent.setClass(getActivity().getApplicationContext(),HotMajor.class);
                    startActivity(intent);
                    break;
                }
                case R.id.iv_school:{
                    Intent intent = new Intent();
                    intent.setClass(getActivity().getApplicationContext(),UniversityIntroduce.class);
                    startActivity(intent);
                    break;
                }
                case R.id.iv_stats1:{
                    Intent intent = new Intent();
                    intent.setClass(getActivity().getApplicationContext(),PaimingSchool.class);
                    startActivity(intent);
                    break;
                }
                case R.id.iv_derection:{
                    Intent intent = new Intent();
                    intent.setClass(getActivity().getApplicationContext(),Derection.class);
                    startActivity(intent);
                    break;
                }
                case R.id.iv_select:{
                    Intent intent = new Intent();
                    intent.setClass(getActivity().getApplicationContext(),Select.class);
                    startActivity(intent);
                    break;
                }
                case R.id.iv_area:{
                    Intent intent = new Intent();
                    intent.setClass(getActivity().getApplicationContext(),RegionalProfile.class);
                    startActivity(intent);
                    break;
                }
                case R.id.iv_eco:{
                    Intent intent = new Intent();
                    intent.setClass(getActivity().getApplicationContext(),RegionalEconomy.class);
                    startActivity(intent);
                    break;
                }
                case R.id.iv_jobs:{
                    Intent intent = new Intent();
                    intent.setClass(getActivity().getApplicationContext(),IndexJobs.class);
                    startActivity(intent);
                    break;
                }
                case R.id.iv_score:{
                    Intent intent = new Intent();
                    intent.setClass(getActivity().getApplicationContext(),IndexScore.class);
                    startActivity(intent);
                    break;
                }
            }
        }
    }
}
