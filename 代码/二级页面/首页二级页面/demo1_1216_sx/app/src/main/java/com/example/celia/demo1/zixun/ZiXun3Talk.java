package com.example.celia.demo1.zixun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.Writer;
import com.example.celia.demo1.showImgAsyncTask.ShowImgAsyncTask;

public class ZiXun3Talk extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.zixun3);
        Intent intent=getIntent();
        TextView articleTitle=findViewById(R.id.tv_articleTitle);
        TextView articleTime=findViewById(R.id.tv_articleTime);
        TextView articleContent=findViewById(R.id.tv_articleContent);
        ImageView writerImg=findViewById(R.id.writerImg);
        TextView writerName=findViewById(R.id.tv_writerName);
        articleTitle.setText(intent.getStringExtra("articleTitle"));
        articleContent.setText(intent.getStringExtra("articleContent"));
        articleTime.setText(intent.getStringExtra("articleTime"));
        Writer writer= (Writer) intent.getSerializableExtra("writer");
        writerName.setText(writer.getWriterName());
        ShowImgAsyncTask showImg=new ShowImgAsyncTask(writer.getWriterImg(),writerImg);
        showImg.execute();
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
