package com.example.celia.demo1.index;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.celia.demo1.FragmentTab1;
import com.example.celia.demo1.MainActivity;
import com.example.celia.demo1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class paiming extends AppCompatActivity {
    private TabHost tabHost;

    private List<Map<String,String>> datalist;
    private List<Map<String,String>> datalist2;
    private List<Map<String,String>> datalist3;
    private ListView learn;
    private ListView out;
    private ListView work;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.paiming);

        //tab 标签设置
        tabHost=findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("考研率")
                .setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("出国")
                .setContent(R.id.tab2));
        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator("就业")
                .setContent(R.id.tab3));
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                updateTab(tabHost);
            }
        });
        updateTab(tabHost);
        //数据导入
        learn = findViewById(R.id.lv_learn);
        out = findViewById(R.id.lv_out);
        work = findViewById(R.id.lv_work);
        initData();
        initData2();
        initData3();
        DAdapter adapter = new DAdapter(this,R.layout.paiming_layout,datalist);
        learn.setAdapter(adapter);
        DAdapter adapter2 = new DAdapter(this,R.layout.paiming_layout,datalist2);
        out.setAdapter(adapter2);
        DAdapter adapter3 = new DAdapter(this,R.layout.paiming_layout,datalist3);
        work.setAdapter(adapter3);

        ImageView iv_return = findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(paiming.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
    }
    /*初始化数据*/
    private void initData() {
        String[] paiming = {"1","2", "3","4","5","6","7", "8","9","10"};
        String[] subject = {"临床医学","工程力学", "口腔医学","预防医学","农学","微电子科学与工程","生物技术", "药学","高分子材料与工程","机械工程"};
        datalist = new ArrayList<Map<String, String>>();
        for (int i = 0; i < paiming.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("paiming", paiming[i]);
            map.put("subject", subject[i]);
            datalist.add(map);
        }
    }
    private void initData2() {
        String[] paiming = {"1","2", "3","4","5","6","7", "8","9","10"};
        String[] subject = {"法语","朝鲜语", "俄语","传播学","金融学","日语","建筑学", "国际商务","为电子工程与科学","通信工程"};
        datalist2 = new ArrayList<Map<String, String>>();
        for (int i = 0; i < paiming.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("paiming", paiming[i]);
            map.put("subject", subject[i]);
            datalist2.add(map);
        }
    }
    private void initData3() {
        String[] paiming = {"1","2", "3","4","5","6","7", "8","9","10"};
        String[] subject = {"物流管理","电气自动化及其自动化", "软件工程","地理信息科学","人力资源管理","护理学","地理科学", "预防医学","高分子材料与工程","信息管理与信息系统"};
        datalist3 = new ArrayList<Map<String, String>>();
        for (int i = 0; i < paiming.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("paiming", paiming[i]);
            map.put("subject", subject[i]);
            datalist3.add(map);
        }
    }

    private class DAdapter extends BaseAdapter{

        private Context context;
        private int itemLayoutID;
        private List<Map<String,String>> datalist;
        public DAdapter(Context context,
                 int itemLayoutID,
                 List<Map<String,String>>datalist){
            this.context = context;
            this.itemLayoutID = itemLayoutID;
            this.datalist = datalist;
        }

        @Override
        public int getCount() {
            return datalist.size();
        }

        @Override
        public Object getItem(int position) {
            return datalist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                LayoutInflater inflater=LayoutInflater.from(context);
                convertView=inflater.inflate(itemLayoutID,null);
            }
            TextView paiming = convertView.findViewById(R.id.tv_paiming);
            TextView subject = convertView.findViewById(R.id.tv_subject);
            Map<String, String> map = datalist.get(position);
            paiming.setText((String)map.get("paiming"));
            subject.setText((String)map.get("subject"));
            return convertView;
        }
    }

    /*tab颜色*/
    private void updateTab(final TabHost tabHost) {
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            View view = tabHost.getTabWidget().getChildAt(i);
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(18);
            tv.setTypeface(Typeface.SERIF, 1); // 设置字体微风格
            if (tabHost.getCurrentTab() == i) {//选中
                tv.setTextColor(Color.parseColor("#3ac9bf"));
            } else {//不选中
                tv.setTextColor(Color.parseColor("#a3a3a3"));
            }
        }
    }

}
