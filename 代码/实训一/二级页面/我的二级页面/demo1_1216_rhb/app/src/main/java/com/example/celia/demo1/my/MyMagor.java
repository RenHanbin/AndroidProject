package com.example.celia.demo1.my;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.celia.demo1.ContextData;
import com.example.celia.demo1.bean.Major;

import android.support.annotation.Nullable;
import com.example.celia.demo1.R;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


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


public class MyMagor extends AppCompatActivity {
    private ListView listMagor;
    private List<Major> majorList1;
    private CustomAdapter adapter;
    private int userId;



    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_major);
        final ContextData data= (ContextData) getApplication();
        userId=data.getUserId();
        Log.e("userId=",userId+"");
        listMagor= findViewById(R.id.lv_magor);
        initData();
        listMagor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                initData();
                initData1(position);
                Log.e("test", String.valueOf(position));
            }
        });





        //点击“<”，返回上一级界面我的my
        ImageView Ireturn=findViewById(R.id.iv_return);
        Ireturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public class CustomAdapter extends BaseAdapter {

        private Context context;
        private int itemLayoutID;
        private List<Major> majorList1;

        public CustomAdapter(Context context, int itemLayoutID, List<Major> majorList1) {
            this.context = context;
            this.itemLayoutID = itemLayoutID;
            this.majorList1 = majorList1;
        }

        @Override
        public int getCount() {
            return majorList1.size();
        }

        @Override
        public Object getItem(int position) {
            return majorList1.get(position);
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
            TextView textView1 = convertView.findViewById(R.id.tv_magor_name);
            TextView textView2 = convertView.findViewById(R.id.tv_big_magor_name);

            Major major1=majorList1.get(position);
            Log.e("test",major1.getMajorName());
            textView1.setText(major1.getMajorName());
            textView2.setText(major1.getMajorTypeName());
            return convertView;
        }
    }

    class GetMajorListAsyncTask1 extends AsyncTask<String,Void,List<Major>> {
        private Context mContext1;
        private ListView listView1;

        public GetMajorListAsyncTask1(Context mContext1, ListView listView1) {
            this.mContext1 = mContext1;
            this.listView1 = listView1;
        }

        @Override
        protected List<Major> doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr=path+"CollectionMajorServlet?remark=collectionMajorListSelect&userId="+userId;
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                String str = reader.readLine();
                Log.e("test", str);
                //解析jsonarray
                JSONArray array = new JSONArray(str);
                majorList1=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Major major = new Major();
                    major.setMajorId(object1.getInt("majorId"));
                    major.setMajorName(object1.getString("majorName"));
                    major.setMajorTypeName(object1.getString("majorTypeName"));
                    majorList1.add(major);
                }
                Log.e("test", majorList1.toString());
                return majorList1;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(List<Major> result){
            Log.e("test","已经进行到异步类的显示阶段1");
            adapter=new CustomAdapter(mContext1,R.layout.my_magor_list,result);
            listMagor.setAdapter(adapter);
        }
    }
    //
    class GetMajorListAsyncTask11 extends AsyncTask<String,Void,List<Major>> {
        private Context mContext1;
        private ListView listView1;
        private int posit;

        public GetMajorListAsyncTask11(Context mContext1, ListView listView1, int posit) {
            this.mContext1 = mContext1;
            this.listView1 = listView1;
            this.posit = posit;
        }

        @Override
        protected List<Major> doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);

            String urlStr=path+"CollectionMajorServlet?remark=collectionMajorListDelete&userId="+userId+"&majorId1="+majorList1.get(posit).getMajorId();
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                String str = reader.readLine();
                Log.e("test", str);
                //解析jsonarray
                JSONArray array = new JSONArray(str);
                majorList1=null;
                majorList1=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Major major = new Major();
                    major.setMajorName(object1.getString("majorName"));
                    major.setMajorTypeName(object1.getString("majorTypeName"));
                    majorList1.add(major);
                }
                Log.e("test", majorList1.toString());
                return majorList1;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(List<Major> result){
            Log.e("test","已经进行到异步类的显示阶段2");
            adapter=new CustomAdapter(mContext1,R.layout.my_magor_list,result);
            listMagor.setAdapter(adapter);

        }
    }

    private void initData() {
        GetMajorListAsyncTask1 asyncTask=new GetMajorListAsyncTask1(MyMagor.this,listMagor);
        asyncTask.execute();

    }
    private void initData1(int position){
        GetMajorListAsyncTask11 asyncTask=new GetMajorListAsyncTask11(MyMagor.this,listMagor,position);
        asyncTask.execute();
    }

}
