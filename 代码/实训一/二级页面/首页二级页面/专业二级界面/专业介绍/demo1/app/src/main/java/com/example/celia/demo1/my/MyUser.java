package com.example.celia.demo1.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.celia.demo1.R;

public class MyUser extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_user);

        //点击“×”，返回上一级界面我的my
        ImageView error=findViewById(R.id.error);
        error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击“√”,保存修改后的数据并返回上一级界面我的my
        ImageView right=findViewById(R.id.right);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyUser.this,"保存修改信息",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
