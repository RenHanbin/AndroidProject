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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.celia.demo1.my.MyAtten;
import com.example.celia.demo1.my.MyComm;
import com.example.celia.demo1.my.MyMagor;
import com.example.celia.demo1.my.MyQuest;
import com.example.celia.demo1.my.MyScho;
import com.example.celia.demo1.my.MySetUp;
import com.example.celia.demo1.my.MyTopic;
import com.example.celia.demo1.my.MyUser;
import com.example.celia.demo1.my.MyWallet;


public class FragmentTab4 extends Fragment{
    //创建View时调用
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page4,container,false);//根据布局文件产生布局控件
        //false 不放到第二个参数中，true 放到第二个参数中
        //用户头像
        ImageView ivUser=view.findViewById(R.id.iv_user);
        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),MyUser.class);
                startActivity(intent);
            }
        });
        //问题
        LinearLayout llQuestion=view.findViewById(R.id.ll_question);
        llQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),MyQuest.class);
                startActivity(intent);
            }
        });
        //评论
        LinearLayout llComment=view.findViewById(R.id.ll_comment);
        llComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),MyComm.class);
                startActivity(intent);
            }
        });
        //专业
        LinearLayout llMagor=view.findViewById(R.id.ll_major);
        llMagor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),MyMagor.class);
                startActivity(intent);
            }
        });
        //学校
        LinearLayout llScho=view.findViewById(R.id.ll_university);
        llScho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),MyScho.class);
                startActivity(intent);
            }
        });
        //关注
        LinearLayout llAtten=view.findViewById(R.id.ll_follow);
        llAtten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),MyAtten.class);
                startActivity(intent);
            }
        });
        //话题
        LinearLayout llTopic=view.findViewById(R.id.ll_topic);
        llTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),MyTopic.class);
                startActivity(intent);
            }
        });
        //钱包
        LinearLayout llWallet=view.findViewById(R.id.ll_wallet);
        llWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),MyWallet.class);
                startActivity(intent);
            }
        });
        //设置
        LinearLayout llSetUp=view.findViewById(R.id.ll_setup);
        llSetUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),MySetUp.class);
                startActivity(intent);
            }
        });

        return view;
    }
    //当view创建完成
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}