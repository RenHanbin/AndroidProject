package cn.edu.hebtu.software.shequ;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView listGuan;
    private ImageView imageHeart;
    private List<Map<String,Object>> datalist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnGuan1 = findViewById(R.id.btn_guanzhu1);
        Button btnQuest1 = findViewById(R.id.btn_quest1);
        ImageView imageSearch = findViewById(R.id.iv_search);
        imageHeart = findViewById(R.id.iv_red_heart);



        listGuan=findViewById(R.id.lv_guanzhu);
        initData();
        CustomAdapter adapter=new CustomAdapter(this,R.layout.guanzhu,datalist);
        listGuan.setAdapter(adapter);

        btnGuan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();//创建意图
                intent.setClass(MainActivity.this,MainActivity.class);//跳转到哪个activity
                startActivity(intent);
            }
        });
        btnQuest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();//创建意图
                intent.setClass(MainActivity.this,QuestActivity.class);//跳转到哪个activity
                startActivity(intent);
            }
        });
        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询
                Toast toast= Toast.makeText(MainActivity.this,"短时提醒Toast",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        });
//        imageHeart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imageHeart.setImageResource(R.drawable.greyheart);
//            }
//        });
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
            ImageView imageView1 = convertView.findViewById(R.id.iv_head);
            ImageView imageView2 = convertView.findViewById(R.id.iv_red_heart);
            TextView textView1 = convertView.findViewById(R.id.tv_name);
            TextView textView2 = convertView.findViewById(R.id.tv_data);
            TextView textView3 = convertView.findViewById(R.id.tv_header);
            TextView textView4 = convertView.findViewById(R.id.tv_nei);
            Map<String, Object> map = datalist.get(position);
            imageView1.setImageResource((int) map.get("head"));
            imageView2.setImageResource((int) map.get("heart"));
            textView1.setText((String) map.get("name"));
            textView2.setText((String) map.get("data"));
            textView3.setText((String) map.get("header"));
            textView4.setText((String) map.get("nei"));
            return convertView;
        }
    }
    private void initData() {
        int[] icon = {R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p2,R.drawable.p3};
        int[] icon1 = {R.drawable.heart,R.drawable.heart,R.drawable.heart,R.drawable.heart,R.drawable.heart};
        String[] name1 = {"王美", "小青", "在中","小青", "在中"};
        String[] name2 = {"09-21 09:24","09-21 09:24","09-21 09:24","09-21 09:24","09-21 09:24"};
        String[] name3 = {"不可摸数-HDU 1999","实习手札|我与360", "实习手札|我与360","不可摸数-HDU 1999","实习手札|我与360"};
        datalist = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("head", icon[i]);
            map.put("heart", icon1[i]);
            map.put("name", name1[i]);
            map.put("data", name2[i]);
            map.put("header", name3[i]);
            map.put("nei", name3[i]);
            datalist.add(map);
        }
    }
}
