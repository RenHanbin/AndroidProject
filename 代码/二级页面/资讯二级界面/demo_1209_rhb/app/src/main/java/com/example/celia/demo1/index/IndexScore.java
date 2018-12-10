package com.example.celia.demo1.index;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.celia.demo1.MainActivity;
import com.example.celia.demo1.R;

import java.util.ArrayList;
import java.util.List;

public class IndexScore extends AppCompatActivity {
    private ImageView iv_return;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.index_score);

        //返回首页index
        iv_return = findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(IndexScore.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
        //搜索城市
        ImageView search=findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(IndexScore.this,"搜索省份",Toast.LENGTH_SHORT).show();
            }
        });

        //下拉列表
        List<String> list = new ArrayList<String>();
        list.add("普通生");
        list.add("体育生");
        list.add("艺术生");
        Spinner sp = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_textsize,R.id.weekofday,list);

        sp.setAdapter(adapter);
    }
}
