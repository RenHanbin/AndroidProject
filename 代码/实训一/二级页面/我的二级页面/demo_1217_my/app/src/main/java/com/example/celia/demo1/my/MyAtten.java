package com.example.celia.demo1.my;

import android.content.Context;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.Atten;
import com.example.celia.demo1.showImgAsyncTask.ShowImgAsyncTask;

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
import java.util.List;

public class MyAtten extends AppCompatActivity {
    private ListView listAtten;
    private List<Atten> datalist;
    private ListViewAdapter adapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_attention);

        listAtten= findViewById(R.id.lv_attention);
        initData();

        //点击“<”，返回上一级界面我的my
        ImageView Ireturn=findViewById(R.id.iv_return);
        Ireturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listAtten.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("test","initData1");
                initData();
                initData1(position);
            }
        });

    }
    public class ListViewAdapter extends BaseAdapter {

        private Context context;
        private int itemLayoutID;
        private List<Atten> datalist;
        public ListViewAdapter(Context context,
                             int itemLayoutID,
                             List<Atten> datalist){
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
            ImageView imageView1 = convertView.findViewById(R.id.iv_headd);
            TextView textView1 = convertView.findViewById(R.id.tv_name);
            Atten atten = datalist.get(position);
            new ShowImgAsyncTask(atten.getFollowedUserImage(),imageView1).execute();
            textView1.setText((String) atten.getFollowedUserName());
            return convertView;
        }
    }

    //获取关注列表异步类
    class GetMyAttenListAsyncTask extends AsyncTask<String,Void,List<Atten>> {
        private Context aContext;
        private ListView listView;
        public GetMyAttenListAsyncTask(Context aContext,ListView listView){
            this.aContext = aContext;
            this.listView = listView;
        }
        @Override
        protected List<Atten> doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"MyFollowServlet?remark=getMyFollowList&userId=1";
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream = connection.getInputStream();
                //字节流转字符流
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                String str = reader.readLine();
                Log.e("test1", str);
                //解析jsonarray
                JSONArray array = new JSONArray(str);
                datalist = new ArrayList<>();
                Log.e("test",array.length()+"");
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Atten atten = new Atten();
                    atten.setFollowUserId(object1.getInt("followId"));
                    atten.setFollowedUserId(object1.getInt("folloewdUserId"));
                    atten.setFollowedUserImage(object1.getString("followedUserHead"));
                    atten.setFollowedUserName(object1.getString("followedUserName"));
                    datalist.add(atten);
                }
                Log.e("test3", datalist.toString());
                return datalist;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(List<Atten> datalist){
            adapter = new ListViewAdapter(aContext,R.layout.my_attention_list,datalist);
            listView.setAdapter(adapter);
        }
    }


    //取消关注异步类
    class DeleteMyAttenListAsyncTask extends AsyncTask<String,Void,List<Atten>> {
        private Context aContext;
        private ListView listView;
        private int position;
        public DeleteMyAttenListAsyncTask(Context aContext,ListView listView,int position){
            this.aContext = aContext;
            this.listView = listView;
            this.position = position;
        }
        @Override

        protected List<Atten> doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"MyFollowServlet?remark=deleteMyFollowList&followedUserId="+datalist.get(position).getFollowedUserId()+"&userId="+datalist.get(position).getFollowUserId();
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
                Log.e("test1", str);
                //解析jsonarray
                JSONArray array = new JSONArray(str);
                datalist = new ArrayList<>();
                Log.e("test",array.length()+"");
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Atten atten = new Atten();
                    atten.setFollowUserId(object1.getInt("followId"));
                    atten.setFollowedUserId(object1.getInt("folloewdUserId"));
                    atten.setFollowedUserImage(object1.getString("followedUserHead"));
                    atten.setFollowedUserName(object1.getString("followedUserName"));
                    datalist.add(atten);
                    Log.e("test","取消关注成功");
                }
                Log.e("test3", datalist.toString());
                return datalist;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Atten> datalist) {
            adapter = new ListViewAdapter(aContext,R.layout.my_attention_list,datalist);
            listView.setAdapter(adapter);
        }
    }




    private void initData() {
        GetMyAttenListAsyncTask asyncTask = new GetMyAttenListAsyncTask(MyAtten.this,listAtten);
        asyncTask.execute();
    }

    private void initData1(int position) {
        DeleteMyAttenListAsyncTask asyncTask = new DeleteMyAttenListAsyncTask(MyAtten.this,listAtten,position);
        asyncTask.execute();
    }

}
