package com.example.celia.demo1.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.celia.demo1.R;
import android.view.Window;
import android.widget.ImageView;



public class ThreeMyCommWrite extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_three_comm_write);
        //返回我的评论二级界面
        ImageView error=findViewById(R.id.error);
        error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
