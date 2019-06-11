package com.example.celia.demo1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FragmentTab2 extends Fragment {
    private ListView listGuan;
    private ListView listQuest;
    private List<Map<String,Object>> datalist;
    private List<Map<String,Object>> datalist2;

    TabHost tabHost;

    //创建View时调用
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page2,container,false);//根据布局文件产生布局控件
        //false 不放到第二个参数中，true 放到第二个参数中
        //1.获取TabHost控件
        tabHost=view.findViewById(android.R.id.tabhost);
        //2.TabHost初始化
        tabHost.setup();
        //3.添加选项卡
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("关注")
                .setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("热门问题")
                .setContent(R.id.tab2));
        updateTab(tabHost);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                updateTab(tabHost);
            }
        });
        listGuan= view.findViewById(R.id.lv_guanzhu);
        listQuest=view.findViewById(R.id.lv_questions);
        initData();
        initData2();
        CustomAdapter adapter=new CustomAdapter(getActivity().getApplicationContext(),R.layout.guanzhu,datalist);
        listGuan.setAdapter(adapter);
        CustomAdapter2 adapter2=new CustomAdapter2(getActivity().getApplicationContext(),R.layout.questions,datalist2);
        listQuest.setAdapter(adapter2);
        TextView Search = view.findViewById(R.id.iv_search);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询
                Toast.makeText(getActivity(),"搜索",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


    //当view创建完成
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /*适配器*/
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
            ImageView imageView1 = convertView.findViewById(R.id.iv_head);
            ImageView imageView2 = convertView.findViewById(R.id.iv_red_heart);
            TextView textView1 = convertView.findViewById(R.id.tv_name);
            TextView textView2 = convertView.findViewById(R.id.tv_data);
            TextView textView3 = convertView.findViewById(R.id.tv_header);
            TextView textView4 = convertView.findViewById(R.id.tv_nei);
            Map<String, Object> map = datalist.get(position);
            imageView1.setImageResource((int) map.get("head"));
            imageView2.setImageResource((int) map.get("heart"));
            textView1.setText((String) map.get("name"));
            textView2.setText((String) map.get("data"));
            textView3.setText((String) map.get("header"));
            textView4.setText((String) map.get("nei"));
            return convertView;
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
            ImageView imageView1 = convertView.findViewById(R.id.iv_quest);
            TextView textView1 = convertView.findViewById(R.id.tv_quest);
            TextView textView2 = convertView.findViewById(R.id.tv_num1);
            TextView textView3 = convertView.findViewById(R.id.tv_num2);
            Map<String, Object> map = datalist.get(position);
            imageView1.setImageResource((int) map.get("quest"));
            textView1.setText((String) map.get("questv"));
            textView2.setText((String) map.get("num1"));
            textView3.setText((String) map.get("num2"));
            return convertView;
        }
    }
    /*初始化数据*/
    private void initData() {
        int[] icon = {R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p2,R.drawable.p3};
        int[] icon1 = {R.drawable.dianzan,R.drawable.dianzan,R.drawable.dianzan,R.drawable.dianzan,R.drawable.dianzan};
        String[] name1 = {"王美", "小青", "在中","小青", "在中"};
        String[] name2 = {"09-21 09:24","09-21 09:24","09-21 09:24","09-21 09:24","09-21 09:24"};
        String[] name3 = {"不可摸数-HDU 1999","实习手札|我与360", "实习手札|我与360","不可摸数-HDU 1999","实习手札|我与360"};
        datalist = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("head", icon[i]);
            map.put("heart", icon1[i]);
            map.put("name", name1[i]);
            map.put("data", name2[i]);
            map.put("header", name3[i]);
            map.put("nei", name3[i]);
            datalist.add(map);
        }
    }
    private void initData2() {
        int[] icon = {R.drawable.p1,R.drawable.p1,R.drawable.p1,R.drawable.p1,R.drawable.p1};
        String[] name1 = {"不可摸数-HDU 1999","实习手札|我与360","不可摸数-HDU 1999","实习手札|我与360","实习手札|我与360"};
        String[] name2 = {"1234","1234","1234","1234","1234"};
        String[] name3 = {"234", "345", "345", "345", "345"};
        datalist2 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("quest", icon[i]);
            map.put("questv", name1[i]);
            map.put("num1", name2[i]);
            map.put("num2", name3[i]);
            datalist2.add(map);
        }
    }
   /* tab颜色*/
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
