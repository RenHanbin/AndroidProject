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
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.celia.demo1.MainActivity;
import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.School;
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

public class PaimingSchool extends AppCompatActivity {
    private ImageView iv_return;
    private ListView listView;
    private List<School> datalist;
    private DAdapter adapter;
    private TextView search;
    private EditText comment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.paiming_school);

        //返回按钮
        iv_return = findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PaimingSchool.this, TabHostActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
        //list
        listView = findViewById(R.id.lv_list);
        initData();

        //搜索
        comment = findViewById(R.id.et_name);
        search = findViewById(R.id.tv_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comment.getText()==null){
                    initData();
                }
                GetListAsyncTask asyncTask1 = new GetListAsyncTask(PaimingSchool.this,listView);
                asyncTask1.execute();
            }
        });
    }

    private void initData() {
        GetSchoolListAsyncTask asyncTask=new GetSchoolListAsyncTask(PaimingSchool.this,listView);
        asyncTask.execute();
    }
    private class DAdapter extends BaseAdapter {

        private Context context;
        private int itemLayoutID;
        private List<School> datalist;
        public DAdapter(Context context,
                        int itemLayoutID,
                        List<School>datalist){
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
            TextView school = convertView.findViewById(R.id.tv_school);
            TextView area = convertView.findViewById(R.id.tv_area);
            School schools = datalist.get(position);
            paiming.setText((position+1)+"");
            school.setText(schools.getSchoolName());
            area.setText(schools.getCityName());
            return convertView;
        }
    }

    //创建获取数据的异步任务1
    class GetSchoolListAsyncTask extends AsyncTask<String,Void,List<School>> {

        private Context mContext;
        private ListView listView;

        public GetSchoolListAsyncTask(Context mContext,ListView listView){
            this.mContext=mContext;
            this.listView=listView;
        }
        @Override
        protected List<School> doInBackground(String... strings) {
            //String urlStr = "http://10.7.88.251:8080/go2school/SchoolServlet?schoolMark=getSchoolPaiList";
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"SchoolServlet?schoolMark=getSchoolPaiList";
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream = connection.getInputStream();
                //字节流转字符流
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                String str = reader.readLine();
                //解析jsonarray
                JSONArray array = new JSONArray(str);
                datalist=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    School school = new School();
                    school.setSchoolName(object1.getString("schoolName"));
                    school.setCityName(object1.getString("cityName"));
                    datalist.add(school);
                }
                Log.e("test", datalist.toString());
                return datalist;
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
        protected void onPostExecute(List<School> result){
            Log.e("test","已经进行到异步类的显示阶段");
            adapter = new DAdapter(mContext,R.layout.paiming_school_layout,result);
            listView.setAdapter(adapter);
        }

    }

    //创建获取数据的异步任务2
    class GetListAsyncTask extends AsyncTask<String,Void,List<School>> {

        private Context mContext;
        private ListView listView;

        public GetListAsyncTask(Context mContext,ListView listView){
            this.mContext=mContext;
            this.listView=listView;
        }
        @Override
        protected List<School> doInBackground(String... strings) {

            String name = String.valueOf(comment.getText());
            /*String urlStr = "http://10.7.88.251:8080/go2school/SchoolServlet?schoolMark=getSchoolPaiListByName&name="
                    +name;*/
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"SchoolServlet?schoolMark=getSchoolPaiListByName&name="
                    +name;
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream = connection.getInputStream();
                //字节流转字符流
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                String str = reader.readLine();
                //解析jsonarray
                JSONArray array = new JSONArray(str);
                datalist=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    School school = new School();
                    school.setSchoolName(object1.getString("schoolName"));
                    school.setCityName(object1.getString("cityName"));
                    datalist.add(school);
                }
                Log.e("test", datalist.toString());
                return datalist;
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
        protected void onPostExecute(List<School> result){
            Log.e("test","已经进行到异步类的显示阶段");
            adapter = new DAdapter(mContext,R.layout.paiming_school_layout,result);
            listView.setAdapter(adapter);
        }

    }
}
