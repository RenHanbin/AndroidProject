package com.example.celia.demo1.index;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
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
import java.util.List;

public class paiming extends AppCompatActivity {
    private TabHost tabHost;
    private List<Major> majorLearnList;
    private List<Major> majorLearnList2;
    private List<Major> majorLearnList3;
    private DAdapter adapter;
    private ListView learn;
    private ListView out;
    private ListView work;
    private String index = "tab1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.paiming);

        //tab 标签设置
        tabHost=findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("考研率")
                .setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("出国")
                .setContent(R.id.tab2));
        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator("就业")
                .setContent(R.id.tab3));
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                index = tabId;
                updateTab(tabHost);
            }
        });
        updateTab(tabHost);
        //数据导入
        learn = findViewById(R.id.lv_learn);
        out = findViewById(R.id.lv_out);
        work = findViewById(R.id.lv_work);
        initData();
        initData2();
        initData3();
        ImageView iv_return = findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(paiming.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
    }
    /*初始化数据*/
    private void initData() {
        GetMajorLearnListAsyncTask asyncTask=new GetMajorLearnListAsyncTask(paiming.this,learn);
        asyncTask.execute();
    }
    private void initData2() {
        GetMajorLearnListAsyncTask2 asyncTask2=new GetMajorLearnListAsyncTask2(paiming.this,out);
        asyncTask2.execute();
    }
    private void initData3() {
        GetMajorLearnListAsyncTask3 asyncTask3=new GetMajorLearnListAsyncTask3(paiming.this,work);
        asyncTask3.execute();
    }

    private class DAdapter extends BaseAdapter{

        private Context context;
        private int itemLayoutID;
        private List<Major> datalist;
        public DAdapter(Context context,
                        int itemLayoutID,
                        List<Major> datalist){
            this.context = context;
            this.itemLayoutID = itemLayoutID;
            this.datalist = datalist;
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
            if(convertView == null){
                LayoutInflater inflater=LayoutInflater.from(context);
                convertView=inflater.inflate(itemLayoutID,null);
            }
            TextView paiming = convertView.findViewById(R.id.tv_paiming);
            TextView subject = convertView.findViewById(R.id.tv_subject);
            if(index.equals("tab1")){
                Major major1=majorLearnList.get(position);
                subject.setText(major1.getMajorName());
            }else if(index.equals("tab2")){
                Major major2=majorLearnList2.get(position);
                subject.setText(major2.getMajorName());
            }else{
                Major major3=majorLearnList3.get(position);
                subject.setText(major3.getMajorName());
            }
            paiming.setText((position+1)+"");
            return convertView;
        }
    }

    /*tab颜色*/
    private void updateTab(final TabHost tabHost) {
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            View view = tabHost.getTabWidget().getChildAt(i);
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(18);
            tv.setTypeface(Typeface.SERIF, 1); // 设置字体微风格
            if (tabHost.getCurrentTab() == i) {//选中
                tv.setTextColor(Color.parseColor("#3ac9bf"));
            } else {//不选中
                tv.setTextColor(Color.parseColor("#a3a3a3"));
            }
        }
    }

    //创建获取数据的异步任务1
    class GetMajorLearnListAsyncTask extends AsyncTask<String,Void,List<Major>> {

        private Context mContext;
        private ListView listView;

        public GetMajorLearnListAsyncTask(Context mContext,ListView listView){
            this.mContext=mContext;
            this.listView=listView;
        }
        @Override
        protected List<Major> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr = path+"MajorServlet?remark=getMajorLearnList";
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
                majorLearnList=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Major major = new Major();
                    major.setMajorId(object1.getInt("majorId"));
                    major.setMajorName(object1.getString("majorName"));
                    major.setMajorWorkPercent(object1.getDouble("majorWorkPercent"));
                    major.setMajorStudy(object1.getDouble("majorStudy"));
                    major.setMajorGo(object1.getDouble("majorGo"));
                    majorLearnList.add(major);
                }
                Log.e("test", majorLearnList.toString());
                return majorLearnList;
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
        protected void onPostExecute(List<Major> result){
            Log.e("test","已经进行到异步类的显示阶段");
            adapter = new DAdapter(mContext,R.layout.paiming_layout,result);
            learn.setAdapter(adapter);
        }

    }
    //创建获取数据的异步任务2
    class GetMajorLearnListAsyncTask2 extends AsyncTask<String,Void,List<Major>> {

        private Context mContext;
        private ListView listView;

        public GetMajorLearnListAsyncTask2(Context mContext,ListView listView){
            this.mContext=mContext;
            this.listView=listView;
        }
        @Override
        protected List<Major> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr = path+"MajorServlet?remark=getMajorOutList";
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
                majorLearnList2=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Major major = new Major();
                    major.setMajorId(object1.getInt("majorId"));
                    major.setMajorName(object1.getString("majorName"));
                    major.setMajorWorkPercent(object1.getDouble("majorWorkPercent"));
                    major.setMajorStudy(object1.getDouble("majorStudy"));
                    major.setMajorGo(object1.getDouble("majorGo"));
                    majorLearnList2.add(major);
                }
                Log.e("test", majorLearnList2.toString());
                return majorLearnList2;
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
        protected void onPostExecute(List<Major> result){
            Log.e("test","已经进行到异步类的显示阶段2");
            adapter = new DAdapter(mContext,R.layout.paiming_layout,result);
            out.setAdapter(adapter);
        }

    }
    //创建获取数据的异步任务3
    class GetMajorLearnListAsyncTask3 extends AsyncTask<String,Void,List<Major>> {

        private Context mContext;
        private ListView listView;

        public GetMajorLearnListAsyncTask3(Context mContext,ListView listView){
            this.mContext=mContext;
            this.listView=listView;
        }
        @Override
        protected List<Major> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr = path+"MajorServlet?remark=getMajorWorkList";
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
                majorLearnList3=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Major major = new Major();
                    major.setMajorId(object1.getInt("majorId"));
                    major.setMajorName(object1.getString("majorName"));
                    major.setMajorWorkPercent(object1.getDouble("majorWorkPercent"));
                    major.setMajorStudy(object1.getDouble("majorStudy"));
                    major.setMajorGo(object1.getDouble("majorGo"));
                    majorLearnList3.add(major);
                }
                Log.e("test", majorLearnList3.toString());
                return majorLearnList3;
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
        protected void onPostExecute(List<Major> result){
            Log.e("test","已经进行到异步类的显示阶段3");
            adapter = new DAdapter(mContext,R.layout.paiming_layout,result);
            work.setAdapter(adapter);
        }

    }
}
