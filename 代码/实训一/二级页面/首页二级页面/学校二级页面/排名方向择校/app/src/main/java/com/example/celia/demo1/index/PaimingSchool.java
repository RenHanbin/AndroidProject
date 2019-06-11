package com.example.celia.demo1.index;

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

import com.example.celia.demo1.MainActivity;
import com.example.celia.demo1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaimingSchool extends AppCompatActivity {
    private ImageView iv_return;
    private ListView listView;
    private List<Map<String,String>> datalist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.paiming_school);

        //返回按钮
        iv_return = findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PaimingSchool.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
        //list
        listView = findViewById(R.id.lv_list);
        initData();
        DAdapter adapter = new DAdapter(this,R.layout.paiming_school_layout,datalist);
        listView.setAdapter(adapter);
    }

    private void initData() {
        String[] paiming = {"1","2", "3","4","5","6","7", "8","9","10"};
        String[] school = {"清华大学","浙江大学","北京大学","上海交通大学","复旦大学","南京大学","武汉大学","四川大学","中山大学","华中科技大学"};
        String[] area = {"北京","浙江·杭州","北京","上海","上海","江苏·南京","湖北·武汉","四川·成都","广东·广州","湖北·武汉"};
        datalist = new ArrayList<Map<String,String>>();
        for (int i = 0; i < paiming.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("paiming", paiming[i]);
            map.put("school", school[i]);
            map.put("area",area[i]);
            datalist.add(map);
        }
    }
    private class DAdapter extends BaseAdapter {

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
            TextView school = convertView.findViewById(R.id.tv_school);
            TextView area = convertView.findViewById(R.id.tv_area);
            Map<String, String> map = datalist.get(position);
            paiming.setText((String)map.get("paiming"));
            school.setText((String)map.get("school"));
            area.setText((String)map.get("area"));
            return convertView;
        }
    }
}
