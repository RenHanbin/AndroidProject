package com.example.celia.demo1.my;

import android.content.Context;
import android.content.Intent;
import com.example.celia.demo1.R;
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


public class MyComm extends AppCompatActivity {
    private ListView listComm;
    private List<Map<String,Object>> datalist;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_comment);

        listComm= findViewById(R.id.lv_my_comments);
        initData();
        CustomAdapter adapter=new CustomAdapter(this,R.layout.my_comments,datalist);
        listComm.setAdapter(adapter);

        //点击“<”，返回上一级界面我的my
        ImageView Ireturn=findViewById(R.id.iv_return);
        Ireturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //
        /*ImageView myCommImageWrite=findViewById(R.id.iv_my_comm_image_write);
        myCommImageWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getApplicationContext(),ThreeMyCommWrite.class);
                startActivity(intent);
            }
        });*/
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
            TextView textView1 = convertView.findViewById(R.id.tv_title);
            TextView textView2 = convertView.findViewById(R.id.tv_comment);
            TextView textView3 = convertView.findViewById(R.id.tv_time);
            Map<String, Object> map = datalist.get(position);
            textView1.setText((String) map.get("title"));
            textView2.setText((String) map.get("comment"));
            textView3.setText((String) map.get("time"));
            return convertView;
        }
    }
    private void initData() {
        String[] name1 = {"不可摸数-HDU 1999","实习手札|我与360","不可摸数-HDU 1999","实习手札|我与360","实习手札|我与360"};
        String[] name2 = {"内容1","内容2","内容3","内容4","内容5"};
        String[] name3 = {"01:12:01", "20:12:01", "23:02:02", "00:50:50", "03:09:05"};
        datalist = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", name1[i]);
            map.put("comment", name2[i]);
            map.put("time", name3[i]);
            datalist.add(map);
        }
    }
}
