package com.example.celia.demo1.index;

import android.content.Intent;
import android.os.Bundle;
import com.example.celia.demo1.R;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class Index_major extends AppCompatActivity {
    private ImageView iv_return;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.index3_major);
        //返回
        iv_return = findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Index_major.this, paiming.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        TextView name = findViewById(R.id.tv_major_name);
        TextView info = findViewById(R.id.tv_info);
        TextView major = findViewById(R.id.tv_main_major);
        TextView subject = findViewById(R.id.tv_subjects);
        TextView derection = findViewById(R.id.tv_derection);
        name.setText(intent.getStringExtra("majorName"));
        info.setText(intent.getStringExtra("info"));
        major.setText(intent.getStringExtra("mainMajor"));
        subject.setText(intent.getStringExtra("subject"));
        derection.setText(intent.getStringExtra("derections"));
    }

}

