package com.example.celia.demo1.index;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.celia.demo1.R;
import com.example.celia.demo1.showImgAsyncTask.ShowImgAsyncTask;


public class Index_city extends AppCompatActivity {

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.index3_city);
            Intent reciveIntent=getIntent();

            TextView cityName=findViewById(R.id.tv_cityName);
            TextView cityType=findViewById(R.id.tv_cityType);
            TextView cityTitle=findViewById(R.id.tv_cityTitle);
            TextView provinceName=findViewById(R.id.tv_provinceName);
            TextView cityContent=findViewById(R.id.tv_cityContent);
            TextView citySalary=findViewById(R.id.tv_citySalary);
            ImageView cityImg=findViewById(R.id.iv_cityImg);
            TextView cityGdp=findViewById(R.id.tv_cityGdp);
            cityName.setText(reciveIntent.getStringExtra("cityName"));
            cityTitle.setText(reciveIntent.getStringExtra("cityTitle"));
            cityContent.setText(reciveIntent.getStringExtra("cityContent"));
            cityGdp.setText(reciveIntent.getDoubleExtra("cityGdp",100)+"元/月");
            citySalary.setText(reciveIntent.getDoubleExtra("citySalary",100)+"元/月");
            provinceName.setText(reciveIntent.getStringExtra("province"));
            cityType.setText(reciveIntent.getStringExtra("cityType"));
            ShowImgAsyncTask showImgAsyncTask=new ShowImgAsyncTask(reciveIntent.getStringExtra("cityImg"),cityImg);
            showImgAsyncTask.execute();
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
