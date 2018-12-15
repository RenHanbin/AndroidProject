package com.example.celia.demo1.index;

import android.content.Intent;
import com.example.celia.demo1.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.celia.demo1.showImgAsyncTask.ShowImgAsyncTask;

public class Index_school extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.index3_school);
        Intent intent = getIntent();
        ImageView img = findViewById(R.id.iv_school_img);
        TextView name = findViewById(R.id.tv_school_name);
        TextView num = findViewById(R.id.tv_school_num);
        TextView content = findViewById(R.id.tv_school_content);
        TextView major = findViewById(R.id.tv_school_major);
        ShowImgAsyncTask showImg=new ShowImgAsyncTask(intent.getStringExtra("schoolImg"),img);
        showImg.execute();
        TextView type = findViewById(R.id.tv_type);
        type.setText(intent.getStringExtra("schoolType"));
        name.setText(intent.getStringExtra("schoolName"));
        num.setText(intent.getStringExtra("schoolNum"));
        content.setText(intent.getStringExtra("schoolContent"));
        major.setText(intent.getStringExtra("schoolBestMajor"));

        //点击返回按钮返回
        LinearLayout linearLayout=findViewById(R.id.ll_return1);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
