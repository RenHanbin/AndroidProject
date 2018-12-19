package com.example.celia.demo1.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.example.celia.demo1.R;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyQuest extends AppCompatActivity {
    private ListView listQuest;
    private List<Map<String,Object>> datalist;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_quest);

        listQuest= findViewById(R.id.lv_my_quest);
        initData();
        CustomAdapter adapter=new CustomAdapter(this,R.layout.my_quest_list,datalist);
        listQuest.setAdapter(adapter);

        //点击“<”，返回上一级界面我的my
        ImageView Ireturn=findViewById(R.id.iv_return);
        Ireturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击我的问题二级界面的“去提问”时，跳转到问题的三级提问界面
        ImageView myQuestImageWrite=findViewById(R.id.iv_my_quest_image_write);
        myQuestImageWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getApplicationContext(),ThreeMyQuestWrite.class);
                startActivity(intent);
            }
        });
        //点击我的问题二级界面的“列表项”时，跳转到问题的三级列表项设置界面
        listQuest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(getApplicationContext(),ThreeMyQuestItem.class);
                startActivity(intent);
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
            TextView textView1 = convertView.findViewById(R.id.tv_title1);
            TextView textView2 = convertView.findViewById(R.id.tv_time);
            Map<String, Object> map = datalist.get(position);
            textView1.setText((String) map.get("title"));
            textView2.setText((String) map.get("time"));
            return convertView;
        }
    }
    private void initData() {
        String[] name1 = {"用户分享内容到社区","用户分享内容到社区","用户分享内容到社区","用户分享内容到社区","用户分享内容到社区"};
        String[] name2 = {"01:12:01", "20:12:01", "23:02:02", "00:50:50", "03:09:05"};
        datalist = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", name1[i]);
            map.put("time", name2[i]);
            datalist.add(map);
        }
    }
}
