package com.example.celia.demo1.index_two;

import android.content.Context;
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

import com.example.celia.demo1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexLearnMajor extends AppCompatActivity {
    private List<Map<String,Object>> data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.index_learn_major);

        LinearLayout return1=findViewById(R.id.ll_return1);
        return1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initData();
        CustomAdapter adapter=new CustomAdapter(this,R.layout.index_learn_listview,data);
        ListView listView=findViewById(R.id.lv_list_learn_major);
        listView.setAdapter(adapter);
    }

    public class CustomAdapter extends BaseAdapter {

        private Context context;
        private int itemLayoutId;
        private List<Map<String,Object>> data;

        public CustomAdapter(Context context, int itemLayoutId, List<Map<String, Object>> data) {
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
            TextView major=convertView.findViewById(R.id.tv_major_type);
            ImageView imageView=convertView.findViewById(R.id.iv_return3);
            Map<String,Object> map=data.get(position);
            major.setText((String)map.get("major"));
            imageView.setImageResource((int)map.get("image"));
            return convertView;
        }
    }

    private void initData() {
        int[] icon={R.drawable.return3};
        String [] major={"计算机科学与技术","数学与应用数学","信息与计算科学","物理学",
                "应用物理学","化学","应用化学","生物科学",
                "生物技术","天文学"};
        data=new ArrayList<Map<String,Object>>();
        for(int i=0;i<10;++i){
            Map<String,Object> map=new HashMap<>();
            map.put("major",major[i]);
            map.put("image",icon[0]);
            data.add(map);
        }
    }
}
