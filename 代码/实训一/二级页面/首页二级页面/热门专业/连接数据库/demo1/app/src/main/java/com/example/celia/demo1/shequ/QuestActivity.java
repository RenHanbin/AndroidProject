package com.example.celia.demo1.shequ;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.celia.demo1.FragmentTab2;
import com.example.celia.demo1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestActivity extends AppCompatActivity {
    private ListView listQuest;
    private List<Map<String,Object>> datalist;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.quest_layout);
        listQuest=findViewById(R.id.lv_questions);
        initData();
        CustomAdapter adapter=new CustomAdapter(this,R.layout.questions,datalist);
        listQuest.setAdapter(adapter);



    }
    public void questList(){
        listQuest=findViewById(R.id.lv_questions);
        initData();
        CustomAdapter adapter=new CustomAdapter(this,R.layout.questions,datalist);
        listQuest.setAdapter(adapter);
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
            ImageView imageView1 = convertView.findViewById(R.id.iv_quest);
            TextView textView1 = convertView.findViewById(R.id.tv_quest);
            TextView textView2 = convertView.findViewById(R.id.tv_num1);
            TextView textView3 = convertView.findViewById(R.id.tv_num2);
            Map<String, Object> map = datalist.get(position);
            imageView1.setImageResource((int) map.get("quest"));
            textView1.setText((String) map.get("questv"));
            textView2.setText((String) map.get("num1"));
            textView3.setText((String) map.get("num2"));
            return convertView;
        }
    }
    private void initData() {
        int[] icon = {R.drawable.p1,R.drawable.p1,R.drawable.p1,R.drawable.p1,R.drawable.p1};
        String[] name1 = {"不可摸数-HDU 1999","实习手札|我与360","不可摸数-HDU 1999","实习手札|我与360","实习手札|我与360"};
        String[] name2 = {"1234","1234","1234","1234","1234"};
        String[] name3 = {"234", "345", "345", "345", "345"};
        datalist = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("quest", icon[i]);
            map.put("questv", name1[i]);
            map.put("num1", name2[i]);
            map.put("num2", name3[i]);
            datalist.add(map);
        }
    }
}
