package com.example.celia.demo1.shequ;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celia.demo1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShequQuestion extends AppCompatActivity {
    private List<Map<String,Object>> data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.shequ_question);


        //返回社区
        ImageView Ireturn=findViewById(R.id.iv_return);
        Ireturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //搜索框
        ImageView search=findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShequQuestion.this,"搜索内容",Toast.LENGTH_SHORT).show();
            }
        });
        //写回答
        ImageView writeQuestion=findViewById(R.id.write_question);
        writeQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShequQuestion.this,"写回答",Toast.LENGTH_SHORT).show();
            }
        });
        //取消收藏
        Button cancelShouCang=findViewById(R.id.cancel_shoucang);
        cancelShouCang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShequQuestion.this,"取消收藏",Toast.LENGTH_SHORT).show();
            }
        });


        initData();
        CustomAdapter adapter=new CustomAdapter(this,R.layout.shequ_question_listview,data);
        ListView listView=findViewById(R.id.lv_list_shequ_question);
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
            return  data.get(position);
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
            ImageView image=convertView.findViewById(R.id.shequ_question_image);
            TextView name=convertView.findViewById(R.id.tv_shequ_question_name);
            TextView time=convertView.findViewById(R.id.tv_shequ_question_time);
            TextView content=convertView.findViewById(R.id.tv_shequ_question_content);
            Map<String,Object> map=data.get(position);
            image.setImageResource((int)map.get("image"));
            name.setText((String)map.get("name"));
            time.setText((String)map.get("time"));
            content.setText((String)map.get("content"));
            return convertView;
        }
    }

    private void initData() {
        int[] icon={R.drawable.user};
        String[] names={"张三","李四","王五","赵六","刘琦"};
        String[] times={"12-32 20:20","12-32 20:20","12-32 20:20","12-32 20:20","12-32 20:20"};
        String[] contents={"改革开放是决定当代中国命运的关键抉择，是当代中国发展进步的活力之源，是党和人民事业大踏步赶上时代的重要法宝，是坚持和发展中国特色社会主义、实现中华民族伟大复兴的必由之路。",
        "改革开放是决定当代中国命运的关键抉择，是当代中国发展进步的活力之源，是党和人民事业大踏步赶上时代的重要法宝，是坚持和发展中国特色社会主义、实现中华民族伟大复兴的必由之路。",
        "改革开放是决定当代中国命运的关键抉择，是当代中国发展进步的活力之源，是党和人民事业大踏步赶上时代的重要法宝，是坚持和发展中国特色社会主义、实现中华民族伟大复兴的必由之路。",
        "改革开放是决定当代中国命运的关键抉择，是当代中国发展进步的活力之源，是党和人民事业大踏步赶上时代的重要法宝，是坚持和发展中国特色社会主义、实现中华民族伟大复兴的必由之路。",
        "改革开放是决定当代中国命运的关键抉择，是当代中国发展进步的活力之源，是党和人民事业大踏步赶上时代的重要法宝，是坚持和发展中国特色社会主义、实现中华民族伟大复兴的必由之路。"};
        data=new ArrayList<Map<String,Object>>();
        for (int i=0;i<5;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("name",names[i]);
            map.put("time",times[i]);
            map.put("content",contents[i]);
            map.put("image",icon[0]);
            data.add(map);
        }
    }
}
