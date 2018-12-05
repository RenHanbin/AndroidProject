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

public class HotMajorActivity extends AppCompatActivity{
    List<Map<String,Object>> listdata;
    DAdapter adapter;
    ListView listView;
    Boolean isPause = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.zhuanye3);

        ImageView ivreturn=findViewById(R.id.iv_return);
        ivreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(HotMajorActivity.this,MainActivity.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }
        });


        //准备数据
        listdata = initData();
        //创建Adapter
        adapter = new DAdapter(this, R.layout.listview_item, listdata);
        //绑定适配器
        listView = findViewById(R.id.list_item);
        listView.setAdapter(adapter);
        }
    private List<Map<String, Object>>initData(){
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("name", "软件工程");
        map1.put("num1", "1234人");
        map1.put("num2", "12344人");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("name", "软件工程");
        map2.put("num1", "1234人");
        map2.put("num2", "12344人");
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("name", "软件工程");
        map3.put("num1", "1234人");
        map3.put("num2", "12344人");
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("name", "软件工程");
        map4.put("num1", "1234人");
        map4.put("num2", "12344人");
        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("name", "软件工程");
        map5.put("num1", "1234人");
        map5.put("num2", "12344人");
        Map<String, Object> map6 = new HashMap<String, Object>();
        map6.put("name", "软件工程");
        map6.put("num1", "1234人");
        map6.put("num2", "12344人");
        Map<String, Object> map7 = new HashMap<String, Object>();
        map7.put("name", "软件工程");
        map7.put("num1", "1234人");
        map7.put("num2", "12344人");
        listdata = new ArrayList<>();
        listdata.add(map1);listdata.add(map2);
        listdata.add(map3);listdata.add(map4);
        listdata.add(map5);listdata.add(map6);
        listdata.add(map7);
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
                TextView name= convertView.findViewById(R.id.tv_name);
                TextView num1 = convertView.findViewById(R.id.tv_num1);
                TextView num2 = convertView.findViewById(R.id.tv_num2);
                Map<String,Object> map = data.get(position);
                name.setText((String) map.get("name"));
                num1.setText((String) map.get("num1"));
                num2.setText((String) map.get("num2"));
                return convertView;
            }

            public void setListdata(List<Map<String,Object>> data) {
                this.data = data;
            }


    }
}
