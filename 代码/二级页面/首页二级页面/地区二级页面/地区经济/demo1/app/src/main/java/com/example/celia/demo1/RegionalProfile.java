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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegionalProfile extends AppCompatActivity {
    List<Map<String,Object>> listdata;
    DAdapter adapter;
    ListView listView;
    Boolean isPause = false;
    private Spinner spinner;
    private List data_list;
    private ArrayAdapter arr_adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.regionalprofile);

        ImageView ivreturn=findViewById(R.id.iv_return);
        ivreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(RegionalProfile.this,MainActivity.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }
        });


        spinner = (Spinner) findViewById(R.id.sp1);
        //数据
        data_list = new ArrayList<String>();
        data_list.add("北部");
        data_list.add("南部");
        data_list.add("东部");
        data_list.add("西部");
        //适配器
        arr_adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 加载适配器
        spinner.setAdapter(arr_adapter);



        spinner = (Spinner) findViewById(R.id.sp2);
        //数据
        data_list = new ArrayList<String>();
        data_list.add("河北省");
        data_list.add("黑龙江省");
        data_list.add("吉林省");
        data_list.add("辽宁省");
        //适配器
        arr_adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 加载适配器
        spinner.setAdapter(arr_adapter);




        spinner = (Spinner) findViewById(R.id.sp3);
        //数据
        data_list = new ArrayList<String>();
        data_list.add("石家庄");
        data_list.add("廊坊");
        data_list.add("沧州");
        data_list.add("张家口");
        //适配器
        arr_adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 加载适配器
        spinner.setAdapter(arr_adapter);

        //准备数据
        listdata = initData();
        //创建Adapter
        adapter = new DAdapter(this, R.layout.regional_list_item, listdata);
        //绑定适配器
        listView = findViewById(R.id.regional);
        listView.setAdapter(adapter);
    }

    private List<Map<String, Object>> initData(){
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("college", "清华大学");
        map1.put("level", "211");
        map1.put("position", "土木工程");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("college", "清华大学");
        map2.put("level", "211");
        map2.put("position", "土木工程");
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("college", "清华大学");
        map3.put("level", "211");
        map3.put("position", "土木工程");
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("college", "清华大学");
        map4.put("level", "211");
        map4.put("position", "土木工程");
        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("college", "清华大学");
        map5.put("level", "211");
        map5.put("position", "土木工程");
        Map<String, Object> map6 = new HashMap<String, Object>();
        map6.put("college", "清华大学");
        map6.put("level", "211");
        map6.put("position", "土木工程");
        Map<String, Object> map7 = new HashMap<String, Object>();
        map7.put("college", "清华大学");
        map7.put("level", "211");
        map7.put("position", "土木工程");
        Map<String, Object> map8 = new HashMap<String, Object>();
        map8.put("college", "清华大学");
        map8.put("level", "211");
        map8.put("position", "土木工程");
        Map<String, Object> map9 = new HashMap<String, Object>();
        map9.put("college", "清华大学");
        map9.put("level", "211");
        map9.put("position", "土木工程");
        Map<String, Object> map10 = new HashMap<String, Object>();
        map10.put("college", "清华大学");
        map10.put("level", "211");
        map10.put("position", "土木工程");


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
            TextView college= convertView.findViewById(R.id.college);
            TextView collegelevel = convertView.findViewById(R.id.level);
            TextView collegeposition = convertView.findViewById(R.id.position);
            Map<String,Object> map = data.get(position);
            college.setText((String) map.get("college"));
            collegelevel.setText((String) map.get("level"));
            collegeposition.setText((String) map.get("position"));
            return convertView;
        }

        public void setListdata(List<Map<String,Object>> data) {
            this.data = data;
        }


    }

}
