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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celia.demo1.MainActivity;
import com.example.celia.demo1.R;
import com.example.celia.demo1.my.MyUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexJobs extends AppCompatActivity {
    private ImageView iv_return;
    private List<Map<String,Object>> data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.index_jobs);


        //返回首页index
        iv_return = findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(IndexJobs.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
        //搜索城市
        ImageView search=findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(IndexJobs.this,"搜索城市",Toast.LENGTH_SHORT).show();
            }
        });

        initData();
        CustomAdapter adapter=new CustomAdapter(this,R.layout.index_jobs_listview,data);
        ListView listView=findViewById(R.id.lv_list_jobs);
        listView.setAdapter(adapter);
    }


    public class CustomAdapter extends BaseAdapter {
        private Context context;
        private int itemLayoutId;
        private List<Map<String,Object>> data;

        public CustomAdapter(Context context,
                             int itemLayoutId,
                             List<Map<String, Object>> data) {
            this.context = context;
            this.itemLayoutId = itemLayoutId;
            this.data = data;
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
            if (convertView==null){
                LayoutInflater inflater=LayoutInflater.from(context);
                convertView=inflater.inflate(itemLayoutId,null);
            }
            TextView city=convertView.findViewById(R.id.city);
            TextView salary=convertView.findViewById(R.id.salary);
            Map<String,Object> map=data.get(position);
            city.setText((String)map.get("city"));
            salary.setText(String.valueOf(map.get("salary")));
            return convertView;
        }
    }

    private void initData() {//salary
        String [] citys={"北京","上海","深圳","廊坊","石家庄"};
        int [] salarys={10000,7000,12000,5000,4000};
        data=new ArrayList<Map<String,Object>>();
        for (int i=0;i<5;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("city",citys[i]);
            map.put("salary", salarys[i]);
            data.add(map);
        }
    }
}
