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

public class UniversityIntroduce extends AppCompatActivity {

    List<Map<String,Object>> listdata;
    DAdapter adapter;
    ListView listView;
    Boolean isPause = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.university);


        ImageView ivreturn=findViewById(R.id.iv_return);
        ivreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(UniversityIntroduce.this,MainActivity.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }
        });

        //准备数据
        listdata = initData();
        //创建Adapter
        adapter = new DAdapter(this, R.layout.listview_item2, listdata);
        //绑定适配器
        listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }
    private List<Map<String, Object>> initData(){
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("name", "河北师范大学");
        map1.put("num", "034434");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("name", "河北师范大学");
        map2.put("num", "034434");
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("name", "河北师范大学");
        map3.put("num", "034434");
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("name", "河北师范大学");
        map4.put("num", "034434");
        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("name", "河北师范大学");
        map5.put("num", "034434");
        Map<String, Object> map6 = new HashMap<String, Object>();
        map6.put("name", "河北师范大学");
        map6.put("num", "034434");
        Map<String, Object> map7 = new HashMap<String, Object>();
        map7.put("name", "河北师范大学");
        map7.put("num", "034434");
        Map<String, Object> map8 = new HashMap<String, Object>();
        map8.put("name", "河北师范大学");
        map8.put("num", "034434");
        Map<String, Object> map9 = new HashMap<String, Object>();
        map9.put("name", "河北师范大学");
        map9.put("num", "034434");
        Map<String, Object> map10 = new HashMap<String, Object>();
        map10.put("name", "河北师范大学");
        map10.put("num", "034434");

        listdata = new ArrayList<>();
        listdata.add(map1);listdata.add(map2);
        listdata.add(map3);listdata.add(map4);
        listdata.add(map5);listdata.add(map6);
        listdata.add(map7);listdata.add(map8);
        listdata.add(map9);listdata.add(map10);
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
            TextView universityname= convertView.findViewById(R.id.tv_universityname);
            TextView universitynum = convertView.findViewById(R.id.tv_universitynum);
            Map<String,Object> map = data.get(position);
            universityname.setText((String) map.get("name"));
            universitynum.setText((String) map.get("num"));
            return convertView;
        }

        public void setListdata(List<Map<String,Object>> data) {
            this.data = data;
        }


    }
}
