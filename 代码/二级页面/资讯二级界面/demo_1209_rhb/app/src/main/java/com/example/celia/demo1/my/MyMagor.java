package com.example.celia.demo1.my;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyMagor extends AppCompatActivity {
    private ListView listMagor;
    private List<Map<String,Object>> datalist;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_major);

        listMagor= findViewById(R.id.lv_magor);
        initData();
        CustomAdapter adapter=new CustomAdapter(this,R.layout.my_magor_list,datalist);
        listMagor.setAdapter(adapter);

        //点击“<”，返回上一级界面我的my
        ImageView Ireturn=findViewById(R.id.iv_return);
        Ireturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
            TextView textView1 = convertView.findViewById(R.id.tv_magor_name);
            TextView textView2 = convertView.findViewById(R.id.tv_big_magor_name);
            Map<String, Object> map = datalist.get(position);
            textView1.setText((String) map.get("name"));
            textView2.setText((String) map.get("bname"));
            return convertView;
        }
    }
    private void initData() {
        String[] name1 = {"计算机科学与技术","计算机科学与技术","计算机科学与技术","计算机科学与技术","计算机科学与技术"};
        String[] name2 = {"计算机类", "计算机类","计算机类","计算机类","计算机类"};
        datalist = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", name1[i]);
            map.put("bname", name2[i]);
            datalist.add(map);
        }
    }
}
