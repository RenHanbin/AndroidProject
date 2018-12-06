package com.example.celia.demo1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class FragmentTab3 extends Fragment {
    //创建View时调用
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page3,container,false);//根据布局文件产生布局控件
        //false 不放到第二个参数中，true 放到第二个参数中
        super.onCreate(savedInstanceState);
        //专栏圆角效果
        TextView text1=view.findViewById(R.id.tv_zhuanlan);
        text1.setBackgroundResource(R.drawable.corner_view);
        //专栏1
        ImageView image1=(ImageView) view.findViewById(R.id.iv_first);
        image1.setImageResource(R.drawable.pic2);
        //专栏2
        ImageView image2=(ImageView)view.findViewById(R.id.iv_second);
        image2.setImageResource(R.drawable.pic2);
        //专栏3
        ImageView image3=(ImageView)view.findViewById(R.id.iv_third);
        image3.setImageResource(R.drawable.pic2);

        ImageView image4=(ImageView)view.findViewById(R.id.iv_left);
        image4.setImageResource(R.drawable.pic2);
        ImageView image5=(ImageView)view.findViewById(R.id.iv_center);
        image5.setImageResource(R.drawable.pic2);
        ImageView image6=(ImageView)view.findViewById(R.id.iv_right);
        image6.setImageResource(R.drawable.pic2);
        /*TextView tvgaokao=view.findViewById(R.id.tv_gaokao);
        tvgaokao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity().getApplicationContext(),ZiXun2.class);
                startActivity(intent);
            }
        });
        TextView tvsister=view.findViewById(R.id.tv_sister);
        tvsister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity().getApplicationContext(),ZiXun2.class);
                startActivity(intent);
            }
        });*/

        /*TextView tvnews=view.findViewById(R.id.tv_news);
        TextView tvnews2=view.findViewById(R.id.tv_news2);
        TextView tvnews3=view.findViewById(R.id.tv_news3);
        tvnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),ZiXun3.class);
                startActivity(intent);
            }
        });
        tvnews2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),ZiXun3.class);
                startActivity(intent);
            }
        });
        tvnews3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),ZiXun3.class);
                startActivity(intent);
            }
        });*/

        /*TextView tvspeak=view.findViewById(R.id.tv_speak);
        TextView tvspeak2=view.findViewById(R.id.tv_speak2);
        TextView tvspeak3=view.findViewById(R.id.tv_speak3);
        tvspeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),ZiXun3.class);
                startActivity(intent);
            }
        });
        tvspeak2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),ZiXun3.class);
                startActivity(intent);
            }
        });
        tvspeak3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),ZiXun3.class);
                startActivity(intent);
            }
        });*/
        final ImageView imdianzan=(ImageView)view.findViewById(R.id.iv_dianzan);
        imdianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == imdianzan)
                    imdianzan.setImageDrawable(getResources().getDrawable(R.drawable.dianzan2));
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
