package com.example.celia.demo1.my;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import com.example.celia.demo1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTopic extends AppCompatActivity {
    private ListView listEssay;
    private ListView listTquest;
    private List<Map<String,Object>> datalist1;
    private List<Map<String,Object>> datalist2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_topic);

        //1.获取TabHost控件
        final TabHost tabHost=findViewById(android.R.id.tabhost);
        //2.TabHost初始化
        tabHost.setup();
        //3.添加选项卡
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("收藏文章")
                .setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("收藏问题")
                .setContent(R.id.tab2));

        tabHost.setOnClickListener(new AdapterView.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           updateTab(tabHost);
                                       }
                                   }
        );
        listEssay= findViewById(R.id.lv_topic_essay);
        initData();
        CustomAdapter adapter=new CustomAdapter(this,R.layout.my_topic_essay,datalist1);
        listEssay.setAdapter(adapter);

        listTquest=findViewById(R.id.lv_topic_questions);
        initData2();
        CustomAdapter2 adapter2= new CustomAdapter2(this,R.layout.my_topic_questions,datalist2);
        listTquest.setAdapter(adapter2);

        //点击“<”，返回上一级界面我的my
        ImageView Ireturn=findViewById(R.id.iv_return);
        Ireturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
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
    public class CustomAdapter extends BaseAdapter {

        private Context context;
        private int itemLayoutID;
        private List<Map<String,Object>> datalist;
        public CustomAdapter(Context context,
                             int itemLayoutID,
                             List<Map<String,Object>>datalist){
            this.context=context;
            this.itemLayoutID=itemLayoutID;
            this.datalist=datalist;
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
            if(convertView==null){
                LayoutInflater inflater=LayoutInflater.from(context);
                convertView=inflater.inflate(itemLayoutID,null);
            }
            ImageView imageView1 = convertView.findViewById(R.id.iv_topic_image);
            TextView textView1 = convertView.findViewById(R.id.tv_topic_title);
            TextView textView2 = convertView.findViewById(R.id.tv_enum1);
            TextView textView3 = convertView.findViewById(R.id.tv_enum2);
            Map<String, Object> map = datalist.get(position);
            imageView1.setImageResource((int) map.get("image"));
            textView1.setText((String) map.get("etitle"));
            textView2.setText((String) map.get("enum1"));
            textView3.setText((String) map.get("enum2"));
            return convertView;
        }
    }
    private void initData() {
        int[] icon = {R.drawable.p1,R.drawable.p2,R.drawable.p3};
        String[] name1 = {"#设计小课堂#超级话题","#设计小课堂#超级话题","#设计小课堂#超级话题"};
        String[] name2 = {"324","324","324"};
        String[] name3 = {"325","324","358"};
        datalist1 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("etitle", name1[i]);
            map.put("enum1", name2[i]);
            map.put("enum2", name3[i]);
            datalist1.add(map);
        }
    }

    public class CustomAdapter2 extends BaseAdapter {
        private Context context;
        private int itemLayoutID;
        private List<Map<String,Object>> datalist;
        public CustomAdapter2(Context context,
                             int itemLayoutID,
                             List<Map<String,Object>>datalist){
            this.context=context;
            this.itemLayoutID=itemLayoutID;
            this.datalist=datalist;
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
            if(convertView==null){
                LayoutInflater inflater=LayoutInflater.from(context);
                convertView=inflater.inflate(itemLayoutID,null);
            }
            ImageView imageView1 = convertView.findViewById(R.id.iv_topic_head);
            TextView textView1 = convertView.findViewById(R.id.tv_topic_qtitle);
            TextView textView2 = convertView.findViewById(R.id.tv_topic_name);
            TextView textView3 = convertView.findViewById(R.id.tv_topic_qnum);
            Map<String, Object> map = datalist.get(position);
            imageView1.setImageResource((int) map.get("head"));
            textView1.setText((String) map.get("qtitle"));
            textView2.setText((String) map.get("name"));
            textView3.setText((String) map.get("qnum"));
            return convertView;
        }
    }
    private void initData2() {
        int[] icon = {R.drawable.p1,R.drawable.p2,R.drawable.p3};
        String[] name1 = {"LOGO要这样排，你的PPT不用愁。。。","LOGO要这样排，你的PPT不用愁。。。","LOGO要这样排，你的PPT不用愁。。。"};
        String[] name2 = {"張三三","王强","小狼狗"};
        String[] name3 = {"321","345", "345"};
        datalist2 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("head", icon[i]);
            map.put("qtitle", name1[i]);
            map.put("name", name2[i]);
            map.put("qnum", name3[i]);
            datalist2.add(map);
        }
    }
}
