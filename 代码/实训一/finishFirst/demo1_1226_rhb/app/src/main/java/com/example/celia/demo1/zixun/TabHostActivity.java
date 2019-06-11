package com.example.celia.demo1.zixun;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.celia.demo1.FragmentTab1;
import com.example.celia.demo1.FragmentTab2;
import com.example.celia.demo1.FragmentTab3;
import com.example.celia.demo1.FragmentTab4;
import com.example.celia.demo1.R;

public class TabHostActivity extends AppCompatActivity {
    private FragmentTabHost tabHost;// 声明FragmentTabhost控件
    private int[] tabHostIconNormal = {R.drawable.home_normal,R.drawable.comunicate_normal,R.drawable.msg_normal,R.drawable.my_normal};
    private int[] tabHostIcon = {R.drawable.home,R.drawable.comunicate,R.drawable.msg,R.drawable.my};
    private String[] tabHostText={"首页","社区","资讯","我的"};
    private Class[] fragmentArr = {FragmentTab1.class, FragmentTab2.class,FragmentTab3.class,FragmentTab4.class};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);



            //初始化FragmentTabHost
        initTabHost();

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        if (id == 3) {
            tabHost.setCurrentTab(3);
        }
        updateTab(tabHost);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for (int i = 0; i < fragmentArr.length; i++) {//颜色全部重置
                    ((TextView) tabHost.getTabWidget().getChildTabViewAt(i).findViewById(R.id.tv_text)).setTextColor(Color.parseColor("#a3a3a3"));
                    ((ImageView) tabHost.getTabWidget().getChildTabViewAt(i).findViewById(R.id.iv_image)).setImageResource(tabHostIconNormal[i]);
                }
                updateTab(tabHost);
            }
        });

    }


    private void updateTab(TabHost tabHost) {
        int curr = tabHost.getCurrentTab();
        View view = tabHost.getTabWidget().getChildAt(curr);
        TextView textView = view.findViewById(R.id.tv_text);
        ImageView imageView = view.findViewById(R.id.iv_image);
        imageView.setImageResource(tabHostIcon[curr]);
        textView.setTextColor(Color.parseColor("#3ac9bf"));
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
