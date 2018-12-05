package com.example.celia.demo1;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegionalEconomy extends AppCompatActivity {
    List<Map<String,Object>> listdata;
    DAdapter adapter;
    ListView listView;
    Boolean isPause = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.regionaleconomy);

        ImageView ivreturn=findViewById(R.id.iv_return);
        ivreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(
                        RegionalEconomy.this,MainActivity.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }
        });

        //准备数据
        listdata = initData();
        //创建Adapter
        adapter = new DAdapter(this, R.layout.economy_list_item, listdata);
        //绑定适配器
        listView = findViewById(R.id.economy);
        listView.setAdapter(adapter);

    }
    private List<Map<String, Object>>initData(){
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("city", "北京");
        map1.put("gdp", "10000");
        map1.put("amount", "200");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("city", "北京");
        map2.put("gdp", "10000");
        map2.put("amount", "200");
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("city", "北京");
        map3.put("gdp", "10000");
        map3.put("amount", "200");
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("city", "北京");
        map4.put("gdp", "10000");
        map4.put("amount", "200");
        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("city", "北京");
        map5.put("gdp", "10000");
        map5.put("amount", "200");
        Map<String, Object> map6 = new HashMap<String, Object>();
        map6.put("city", "北京");
        map6.put("gdp", "10000");
        map6.put("amount", "200");
        Map<String, Object> map7 = new HashMap<String, Object>();
        map7.put("city", "北京");
        map7.put("gdp", "10000");
        map7.put("amount", "200");
        Map<String, Object> map8 = new HashMap<String, Object>();
        map8.put("city", "北京");
        map8.put("gdp", "10000");
        map8.put("amount", "200");
        Map<String, Object> map9 = new HashMap<String, Object>();
        map9.put("city", "北京");
        map9.put("gdp", "10000");
        map9.put("amount", "200");
        Map<String, Object> map10 = new HashMap<String, Object>();
        map10.put("city", "北京");
        map10.put("gdp", "10000");
        map10.put("amount", "200");
        Map<String, Object> map11 = new HashMap<String, Object>();
        map11.put("city", "北京");
        map11.put("gdp", "10000");
        map11.put("amount", "200");

        listdata = new ArrayList<>();
        listdata.add(map1);listdata.add(map2);
        listdata.add(map3);listdata.add(map4);
        listdata.add(map5);listdata.add(map6);
        listdata.add(map7);listdata.add(map8);
        listdata.add(map9);listdata.add(map10);
        listdata.add(map11);
        return listdata;
    }

    //创建自定义适配器
    private  class DAdapter extends BaseAdapter {
        private Context context;
        private int itemLayoutId;
        private List<Map<String,Object>> data;
        public DAdapter(Context context,int itemLayoutId,List<Map<String,Object>> data){
            this.context=context;
            this.itemLayoutId=itemLayoutId;
            this.data=data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(itemLayoutId,null);
            }
            TextView name= convertView.findViewById(R.id.city);
            TextView num1 = convertView.findViewById(R.id.gdp);
            TextView num2 = convertView.findViewById(R.id.amount);
            Map<String,Object> map = data.get(position);
            name.setText((String) map.get("city"));
            num1.setText((String) map.get("gdp"));
            num2.setText((String) map.get("amount"));
            return convertView;
        }

        public void setListdata(List<Map<String,Object>> data) {
            this.data = data;
        }


    }
}

