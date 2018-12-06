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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.celia.demo1.MainActivity;
import com.example.celia.demo1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Select extends AppCompatActivity {
    private ImageView iv_return;
    private Spinner spinner1;
    private Spinner spinner2;
    private ListView listView;
    private List<Map<String,String>> datalist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.select);

        //返回
        iv_return = findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Select.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
        //下拉列表
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        String[] data1 ={"2018","2017","2016"};
        String[] data2 ={"北京","上海","天津"};
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,R.layout.spinner_item, data1);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,R.layout.spinner_item, data2);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        //列表
        listView = findViewById(R.id.lv_list1);
        initData();
        DAdapter adapter = new DAdapter(this,R.layout.select_layout,datalist);
        listView.setAdapter(adapter);
    }

    private void initData() {
        String[] school = {"清华大学","浙江大学","北京大学","上海交通大学","复旦大学","南京大学","武汉大学","四川大学","中山大学","华中科技大学"};
        String[] min = {"698","650","699","689","685","659","645","620","642","638"};
        String[] num={"13","1631","15","11","17","63","157","227","99","220"};
        String[] minPaiming = {"69","4678","53","210","348","2823","5921","16176","6922","8319"};
        datalist = new ArrayList<Map<String,String>>();
        for (int i = 0; i < school.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("school", school[i]);
            map.put("min", min[i]);
            map.put("num", num[i]);
            map.put("minPaiming", minPaiming[i]);
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
            TextView school = convertView.findViewById(R.id.tv_school_name);
            TextView num = convertView.findViewById(R.id.tv_num);
            TextView min = convertView.findViewById(R.id.tv_min_score);
            TextView minPaiming = convertView.findViewById(R.id.tv_min_paiming);
            Map<String, String> map = datalist.get(position);
            school.setText((String)map.get("school"));
            num.setText((String)map.get("num"));
            min.setText((String)map.get("min"));
            minPaiming.setText((String)map.get("minPaiming"));
            return convertView;
        }
    }
}
