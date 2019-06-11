package com.example.celia.demo1.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.celia.demo1.R;
import com.example.celia.demo1.shequ.ShequQuestion;

public class Index3MyQuestItem extends AppCompatActivity {
    private String questionId;
    private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_three_quest_item);

        intent= getIntent();
        Log.e("intent22",intent.toString());
        TextView questionTitle = findViewById(R.id.tv_question_title);
        TextView questionDiscribe = findViewById(R.id.tv_question_discribe);
        TextView attenNum = findViewById(R.id.tv_attenNum);
        TextView commNum = findViewById(R.id.tv_commNum);
        questionId=intent.getStringExtra("questionId");
        questionTitle.setText(intent.getStringExtra("questionTitle"));
        questionDiscribe.setText(intent.getStringExtra("questionDiscribe"));
        attenNum.setText(intent.getStringExtra("attenNum"));
        commNum.setText(intent.getStringExtra("commNum"));

        LinearLayout questionNext=findViewById(R.id.ll_question_next);
        questionNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(Index3MyQuestItem.this,ShequQuestion.class);
                startActivity(intent);
            }
        });

        //返回上一级
        ImageView IvReturn = findViewById(R.id.iv_return);
        IvReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
