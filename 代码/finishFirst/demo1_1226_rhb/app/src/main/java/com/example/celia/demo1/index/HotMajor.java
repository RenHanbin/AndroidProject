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
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.Major;
import com.example.celia.demo1.zixun.TabHostActivity;

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


public class HotMajor extends AppCompatActivity {
    private ListView majorListView;
    private List<Major> majorList;
    private ListViewAdapter adapter;
    private TextView search1;
    private EditText inputMajor;
    private List strList;
    private String inputStr;
    private Map<String,TextView> tvMap2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.zhuanye_hot);
        majorListView =findViewById(R.id.hotMajor_item);

        //获取数据，从数据库中提取
        initData();
        //创建adapter

        //搜索
        search1=findViewById(R.id.iv_search);
        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到输入的专业
                inputMajor=findViewById(R.id.et_inputMajor);
                inputStr=inputMajor.getText().toString();
                Log.e("test:专业",inputStr);
                //提供省份和学生类别
                strList=new ArrayList<>();
                strList.add(inputStr);
                TextView majorName=findViewById(R.id.tv_name);
                TextView majorType=findViewById(R.id.tv_majorType);
                TextView num1=findViewById(R.id.tv_num1);
                TextView num2=findViewById(R.id.tv_num2);
                //创建返回map
                tvMap2= new HashMap<>();
                tvMap2.put("majorName",majorName);
                tvMap2.put("majorType",majorType);
                tvMap2.put("num1",num1);
                tvMap2.put("num2",num2);
                GetLineAsyncTask lineAsyncTask=new GetLineAsyncTask(HotMajor.this, majorListView,tvMap2);
                lineAsyncTask.execute();
            }
        });

        //三级页面传输数据
        majorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("majorId",majorList.get(position).getMajorId());
                intent.putExtra("majorName",majorList.get(position).getMajorName());
                intent.putExtra("majorInf",majorList.get(position).getMajorIntroduce());
                intent.putExtra("majorSubject",majorList.get(position).getMajorSubject());
                intent.putExtra("majorTypeName",majorList.get(position).getMajorTypeName());
                intent.putExtra("majorWork",majorList.get(position).getMajorWork());
                intent.setClass(HotMajor.this,Index3Major.class);
                startActivity(intent);
            }
        });

        //点击“<”,返回上级页面
        ImageView Ireturn = findViewById(R.id.iv_return);
        Ireturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HotMajor.this, TabHostActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
    }

    //创建搜索异步任务类
    class GetLineAsyncTask extends AsyncTask<String, Void, List<Major>>{
        private Context gContext;
        private  ListView listView;
        private Map<String,TextView> tvMap2;
        protected GetLineAsyncTask(Context gContext,ListView listView,Map<String,TextView> tvList2) {
            this.gContext = gContext;
            this.listView = listView;
            this.tvMap2 = tvList2;
        }
        @Override
        protected List<Major> doInBackground(String... strings) {//192.168.1.106
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"MajorServlet?remark=getMajorHotSubList&majorName="+inputStr;
            URL url = null;
            try {
                url = new URL(urlStr);
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
                majorList = new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Major major = new Major();
                    major.setMajorId(object1.getInt("majorId"));
                    major.setMajorName(object1.getString("majorName"));
                    major.setMajorTypeName(object1.getString("majorTypeName"));
                    major.setMajorWant(object1.getInt("majorWant"));
                    major.setMajorNeed(object1.getInt("majorNeed"));
                    major.setMajorIntroduce(object1.getString("majorInf"));
                    major.setMajorSubject(object1.getString("majorSubject"));
                    major.setMajorWork(object1.getString("majorWork"));
                    Log.e("mmmm:",object1.getInt("majorNeed")+"");
                    Log.e("test11",major.toString());
                    majorList.add(major);
                }
                Log.e("test1111", majorList.toString());
                return majorList;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(List<Major> majorList) {
            Log.e("test", tvMap2.size()+"");
            Log.e("test",majorList.toString());
            for (int i=0;i<majorList.size();++i){
                tvMap2.get("majorName").setText(majorList.get(i).getMajorName());
                tvMap2.get("majorType").setText(majorList.get(i).getMajorTypeName());
                tvMap2.get("num1").setText(majorList.get(i).getMajorWant()+"");
                tvMap2.get("num2").setText(majorList.get(i).getMajorNeed()+"");
            }
            adapter = new ListViewAdapter(gContext, R.layout.listview_item,majorList);
            listView.setAdapter(adapter);
            adapter.notifyDataSetInvalidated();
        }
    }

    //创建总的获取数据的adapter
    class ListViewAdapter extends BaseAdapter {

        private Context context;
        private int itemLayout;
        private List<Major> majorList;

        public ListViewAdapter(Context context,
                               int itemLayout,
                               List<Major> list) {
            this.context = context;
            this.itemLayout = itemLayout;
            this.majorList = list;
        }

        @Override
        public int getCount() {
            return majorList.size();
        }

        @Override
        public Object getItem(int position) {
            return majorList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(itemLayout, null);
            }
            TextView major = convertView.findViewById(R.id.tv_name);
            TextView majorType = convertView.findViewById(R.id.tv_majorType);
            TextView majorWant = convertView.findViewById(R.id.tv_num1);
            TextView majorNeed = convertView.findViewById(R.id.tv_num2);
            Major major1 = majorList.get(position);
            Log.e("test", major1.getMajorName());
            major.setText(major1.getMajorName());
            majorType.setText("（"+major1.getMajorTypeName()+")");
            majorWant.setText(String.valueOf(major1.getMajorWant()));
            majorNeed.setText(String.valueOf(major1.getMajorNeed()));
            return convertView;
        }
    }



    //创建获取数据的异步任务
    class GetMajorListAsyncTask extends AsyncTask<String, Void, List<Major>> {

        private Context hContext;
        private ListView listView;
        public GetMajorListAsyncTask(Context hContext, ListView listView) {
            this.hContext = hContext;
            this.listView = listView;
        }

        @Override
        protected List<Major> doInBackground(String... strings) {// 10.7.88.218
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"MajorServlet?remark=getMajorHotList";
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream = connection.getInputStream();
                //字节流转字符流
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                String str = reader.readLine();
                Log.e("test123", str);
                //解析jsonarray
                JSONArray array = new JSONArray(str);
                majorList = new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Major major = new Major();
                    major.setMajorId(object1.getInt("majorId"));
                    major.setMajorName(object1.getString("majorName"));
                    major.setMajorTypeName(object1.getString("majorTypeName"));
                    major.setMajorWant(object1.getInt("majorWant"));
                    major.setMajorNeed(object1.getInt("majorNeed"));
                    Log.e("test",object1.getString("majorNeed"));
                    major.setMajorIntroduce(object1.getString("majorIntroduce"));
                    major.setMajorSubject(object1.getString("majorSubject"));
                    major.setMajorWork(object1.getString("majorWork"));
                    majorList.add(major);
                    Log.e("major",major.toString());
                }
                Log.e("test111", majorList.toString());
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
        protected void onPostExecute(List<Major> majorList) {
            Log.e("test", "已经进行到异步类的显示阶段");
            Log.e("test", majorList.toString());
            adapter = new ListViewAdapter(hContext, R.layout.listview_item, majorList);
            listView.setAdapter(adapter);
        }
    }

    //从数据库当中获取data
    public void initData() {
        GetMajorListAsyncTask asyncTask = new GetMajorListAsyncTask(HotMajor.this, majorListView);
        asyncTask.execute();
    }

}