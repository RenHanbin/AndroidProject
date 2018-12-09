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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.celia.demo1.MainActivity;
import com.example.celia.demo1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexLearn extends AppCompatActivity {
    private ImageView iv_return;

    private List<Map<String,Object>> data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.index_learn);

        //返回
        iv_return = findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(IndexLearn.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });

        initData();
        CustomAdapter adapter=new CustomAdapter(this,R.layout.index_learn_listview,data);
        ListView listView=findViewById(R.id.lv_list_learn);
        listView.setAdapter(adapter);
        //点击列表中的每一项跳转到三级界面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(IndexLearn.this,IndexLearnMajor.class);
                startActivity(intent);
            }
        });
    }

    public class CustomAdapter extends BaseAdapter{

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
            TextView majorType=convertView.findViewById(R.id.tv_major_type);
            ImageView imageView=convertView.findViewById(R.id.iv_return3);
            Map<String,Object> map=data.get(position);
            imageView.setImageResource((int)map.get("image"));
            majorType.setText((String)map.get("text"));
           return convertView;
        }
    }

    private void initData() {
        int[] icon={R.drawable.return3};
        String [] marjorType={"哲学","经济学","法学","教育学",
                "文学","历史学","理学","工学",
                "农学","医学","军事学","管理学","艺术学"};
        data=new ArrayList<Map<String,Object>>();
        for(int i=0;i<13;++i){
            Map<String,Object> map=new HashMap<>();
            map.put("text",marjorType[i]);
            map.put("image",icon[0]);
            data.add(map);
        }
    }
}
