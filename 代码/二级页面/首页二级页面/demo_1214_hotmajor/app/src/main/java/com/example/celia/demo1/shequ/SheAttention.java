package com.example.celia.demo1.shequ;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.celia.demo1.R;
import com.example.celia.demo1.my.MyAtten;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SheAttention extends AppCompatActivity {
    private ListView listSatten;
    private List<Map<String,Object>> datalist;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shequ_guanzhu);

        listSatten= findViewById(R.id.lv_shequ_guanzhu);
        initData();
        CustomAdapter adapter=new CustomAdapter(this,R.layout.shequ_guanzhu_list,datalist);
        listSatten.setAdapter(adapter);

        //点击“<”，返回上一级界面我的my
        ImageView Ireturn=findViewById(R.id.iv_return);
        Ireturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击某一项跳转到社区关注二级
//                Intent intent = new Intent();
//                intent.setClass(SheAttention.this, SheAttention.class);
//                startActivity(intent);
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
            ImageView imageView1 = convertView.findViewById(R.id.iv_shequ_head);
            TextView textView1 = convertView.findViewById(R.id.tv_shequ_name);
            TextView textView2 = convertView.findViewById(R.id.tv_shequ_time);
            TextView textView3 = convertView.findViewById(R.id.tv_shequ_guanzhu_nei);
            Map<String, Object> map = datalist.get(position);
            imageView1.setImageResource((int) map.get("head"));
            textView1.setText((String) map.get("name"));
            textView1.setText((String) map.get("time"));
            textView1.setText((String) map.get("nei"));
            return convertView;
        }
    }
    private void initData() {
        int[] icon = {R.drawable.p1,R.drawable.p2,R.drawable.p3};
        String[] name1 = {"张晓晓", "刘晓晓", "王哈哈"};
        String[] name2 = {"张晓晓", "刘晓晓", "王哈哈"};
        String[] name3 = {"张晓晓", "刘晓晓", "王哈哈"};
        datalist = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("head", icon[i]);
            map.put("name", name1[i]);
            map.put("time", name2[i]);
            map.put("nei", name3[i]);
            datalist.add(map);
        }
    }
}
