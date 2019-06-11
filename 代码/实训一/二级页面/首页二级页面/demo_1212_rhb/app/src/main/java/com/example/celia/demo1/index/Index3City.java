package com.example.celia.demo1.index;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.CityType;
import com.example.celia.demo1.bean.Province;
import com.example.celia.demo1.showImgAsyncTask.ShowImgAsyncTask;

public class Index3City extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index3_city);
        Intent reciveIntent=getIntent();
        ImageView cityImg=findViewById(R.id.iv_cityImg);
        TextView cityName=findViewById(R.id.tv_cityName);
        TextView cityType=findViewById(R.id.tv_cityType);
        TextView cityTitle=findViewById(R.id.tv_cityTitle);
        TextView provinceName=findViewById(R.id.tv_provinceName);
        TextView cityContent=findViewById(R.id.tv_cityContent);
        TextView citySalary=findViewById(R.id.tv_citySalary);
        TextView cityGdp=findViewById(R.id.tv_cityGdp);
        cityName.setText(reciveIntent.getStringExtra("cityName"));
        cityTitle.setText(reciveIntent.getStringExtra("cityName"));
        cityContent.setText(reciveIntent.getStringExtra("cityContent"));
        cityGdp.setText(reciveIntent.getDoubleExtra("cityGdp",100)+"元/月");
        citySalary.setText(reciveIntent.getDoubleExtra("citySalary",100)+"元/月");
        Province province= (Province) reciveIntent.getSerializableExtra("province");
        provinceName.setText(province.getProvinceName());
        CityType cityType1= (CityType) reciveIntent.getSerializableExtra("cityType");
        cityType.setText(cityType1.getCityTypeName());
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
