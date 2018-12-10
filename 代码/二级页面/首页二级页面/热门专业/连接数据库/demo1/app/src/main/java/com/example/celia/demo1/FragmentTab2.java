package com.example.celia.demo1;

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.celia.demo1.shequ.QuestActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FragmentTab2 extends Fragment {
    private ViewPager viewPager;
    private TabLayout myTab;


    //创建View时调用
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page2,container,false);//根据布局文件产生布局控件
        //false 不放到第二个参数中，true 放到第二个参数中
        myTab = view.findViewById(R.id.Tab);
        viewPager = view.findViewById(R.id.vp_pager);

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
        myTab.addTab(myTab.newTab().setText("关注"));
        myTab.addTab(myTab.newTab().setText("热门问题"));

        myTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(getActivity(),tab.getText().toString(),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }




    /*public class CustomAdapter extends BaseAdapter {

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
    }*/
}
