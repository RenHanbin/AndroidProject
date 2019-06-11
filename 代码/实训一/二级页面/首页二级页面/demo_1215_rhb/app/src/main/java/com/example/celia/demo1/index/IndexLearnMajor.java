package com.example.celia.demo1.index;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.celia.demo1.MainActivity;
import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.Major;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexLearnMajor extends AppCompatActivity {
    private ImageView iv_return;
    private List<Map<String,Object>> data;
    private List<Major> majorList;
    private String majorName;
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;
    CustomAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.index_learn_major);

        //点击返回按钮返回
        LinearLayout linearLayout=findViewById(R.id.ll_return1);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        majorName = intent.getStringExtra("majorName");
        listView=findViewById(R.id.lv_list_learn_major);
        GetMajorListAsyncTask listAsyncTask = new GetMajorListAsyncTask(IndexLearnMajor.this);
        listAsyncTask.execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(IndexLearnMajor.this,Index3Major.class);
                intent.putExtra("majorId",majorList.get(position).getMajorId());
                intent.putExtra("majorName",majorList.get(position).getMajorName());
                intent.putExtra("majorInf",majorList.get(position).getMajorIntroduce());
                intent.putExtra("majorSubject",majorList.get(position).getMajorSubject());
                intent.putExtra("majorTypeName",majorList.get(position).getMajorTypeName());
                intent.putExtra("majorWork",majorList.get(position).getMajorWork());
                startActivity(intent);
            }
        });
    }



    //创建获取数据的异步任务
    class GetMajorListAsyncTask extends AsyncTask<String,Void,List<Major>>{

        private Context mContext;

        public GetMajorListAsyncTask(Context mContext){
            this.mContext=mContext;
        }
        @Override
        protected List<Major> doInBackground(String... strings) {
            String path = getResources().getString(R.string.url_path);
            String urlStr = path+"MajorServlet?remark=getMajorListByName&majorName="+majorName;

            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream = connection.getInputStream();
                //字节流转字符流
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                String str = reader.readLine();
                Log.e("test", str);
                //解析jsonarray
                JSONArray array = new JSONArray(str);
                majorList=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Major major = new Major();
                    major.setMajorId(object1.getInt("majorId"));
                    major.setMajorName(object1.getString("majorName"));
                    major.setMajorTypeName(object1.getString("majorTypeName"));
                    major.setMajorWorkPercent(object1.getDouble("majorWorkPercent"));
                    major.setMajorSalary(object1.getInt("majorSalary"));
                    major.setMajorIntroduce(object1.getString("majorIntroduce"));
                    major.setMajorSubject(object1.getString("majorSubject"));
                    major.setMajorWork(object1.getString("majorWork"));
                    majorList.add(major);
                }
                Log.e("test", majorList.toString());
                return majorList;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(List<Major> strings) {
            Log.e("test","已经进行到异步类的显示阶段");
            String [] marjorName=new String[strings.size()];
            for (int i = 0;i<strings.size();i++){
                marjorName[i]=strings.get(i).getMajorName();
            }
            int[] icon={R.drawable.return3};
            data=new ArrayList<Map<String,Object>>();
            for(int i=0;i<strings.size();++i){
                Map<String,Object> map=new HashMap<>();
                map.put("text",marjorName[i]);
                map.put("image",icon[0]);
                data.add(map);
            }
            adapter=new CustomAdapter(mContext,R.layout.index_learn_listview,data);
            listView.setAdapter(adapter);
        }

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
}
