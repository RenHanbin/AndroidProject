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

public class MyScho extends AppCompatActivity {
    private ListView listScho;
    private List<Map<String,Object>> datalist;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_school);

        listScho= findViewById(R.id.lv_school);
        initData();
        CustomAdapter adapter=new CustomAdapter(this,R.layout.my_school_list,datalist);
        listScho.setAdapter(adapter);

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
            ImageView imageView1 = convertView.findViewById(R.id.iv_scho);
            TextView textView1 = convertView.findViewById(R.id.tv_school_name);
            TextView textView2 = convertView.findViewById(R.id.tv_level);
            TextView textView3 = convertView.findViewById(R.id.tv_grade);
            Map<String, Object> map = datalist.get(position);
            imageView1.setImageResource((int) map.get("school"));
            textView1.setText((String) map.get("name"));
            textView2.setText((String) map.get("level"));
            textView3.setText((String) map.get("grade"));
            return convertView;
        }
    }
    private void initData() {
        int[] icon = {R.drawable.p1,R.drawable.p2,R.drawable.p3};
        String[] name1 = {"清华大学", "北京大学", "厦门大学"};
        String[] name2 = {"本科","本科","本科"};
        String[] name3 = {"2018年最低录取分数线","2018年最低录取分数线", "2018年最低录取分数线"};
        datalist = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("school", icon[i]);
            map.put("name", name1[i]);
            map.put("level", name2[i]);
            map.put("grade", name3[i]);
            datalist.add(map);
        }
    }
}
