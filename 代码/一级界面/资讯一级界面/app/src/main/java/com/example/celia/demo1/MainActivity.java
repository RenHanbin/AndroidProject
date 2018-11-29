package com.example.celia.demo1;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost tabHost;// 声明FragmentTabhost控件
    private int[] tabHostIconNormal = {R.drawable.home_normal,R.drawable.comunicate_normal,R.drawable.msg_normal,R.drawable.my_normal};
    private String[] tabHostText={"首页","社区","资讯","我的"};
    private Class[] fragmentArr = {FragmentTab1.class,FragmentTab2.class,FragmentTab3.class,FragmentTab4.class};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //初始化FragmentTabHost
        initTabHost();

    }

    private void initTabHost() {
        tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup(this,getSupportFragmentManager(),android.R.id.tabhost);
        for(int i=0;i<fragmentArr.length;i++){
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabHostText[i]).setIndicator(getTabHostView(i));
            tabHost.addTab(tabSpec,fragmentArr[i],null);
        }
    }
    private View getTabHostView(int index){
        View view = getLayoutInflater().inflate(R.layout.fragment_tab,null);
        TextView textView = view.findViewById(R.id.tv_text);
        ImageView imageView = view.findViewById(R.id.iv_image);
        textView.setText(tabHostText[index]);
        imageView.setImageResource(tabHostIconNormal[index]);
        return view;
    }
}
