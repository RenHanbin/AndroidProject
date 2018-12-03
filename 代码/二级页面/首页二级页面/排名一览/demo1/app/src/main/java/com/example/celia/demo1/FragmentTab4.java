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

import com.example.celia.demo1.my.MyUser;


public class FragmentTab4 extends Fragment{
    //创建View时调用
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page4,container,false);//根据布局文件产生布局控件
        //false 不放到第二个参数中，true 放到第二个参数中
        ImageView ivUser=view.findViewById(R.id.iv_user);
        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),MyUser.class);
                startActivity(intent);
            }
        });
        LinearLayout llQuestion=view.findViewById(R.id.ll_question);
        llQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("test","问题");
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