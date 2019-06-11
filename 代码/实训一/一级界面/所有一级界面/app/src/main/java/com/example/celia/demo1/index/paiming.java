package com.example.celia.demo1.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.celia.demo1.R;

public class paiming extends AppCompatActivity {
    private FragmentTabHost tabHost;// 声明FragmentTabhost控件
    private String[] tabHostText={"考研率","出国","就业率"};
    private Class[] fragmentArr = {Paiming_learn.class,Paiming_out.class,Paiming_work.class};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.paiming);
        initTabHost();
    }

    private void initTabHost() {
        tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup(this,getSupportFragmentManager(),android.R.id.tabhost);
        for(int i=0;i<fragmentArr.length;i++){
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabHostText[i]).setIndicator(tabHostText[i]);
            tabHost.addTab(tabSpec,fragmentArr[i],null);
        }
    }
}
