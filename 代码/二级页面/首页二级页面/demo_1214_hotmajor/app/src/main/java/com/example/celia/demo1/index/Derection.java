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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.celia.demo1.MainActivity;
import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.School;
import com.example.celia.demo1.bean.Work;
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

public class Derection extends AppCompatActivity{
    private ImageView iv_return;
    private Spinner spinner;
    private ListView listView;
    private List<School> datalist;
    private List<String> worklist;
    private ArrayAdapter<String> adapter;
    private DAdapter Dadapter;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.derection);

        //返回
        iv_return = findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Derection.this, TabHostActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });

        //下拉列表
        spinner = findViewById(R.id.spinner1);
        GetWorkAsyncTask asyncTask = new GetWorkAsyncTask(Derection.this);
        asyncTask.execute();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textView = view.findViewById(R.id.tv_spinner);
                GetWorkListAsyncTask asyncTask1 = new GetWorkListAsyncTask(Derection.this,listView);
                asyncTask1.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //列表
        listView = findViewById(R.id.lv_list);
        initData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(Derection.this,Index_school.class);
                intent.putExtra("schoolImg",datalist.get(position).getSchoolImg());
                intent.putExtra("schoolName",datalist.get(position).getSchoolName());
                intent.putExtra("schoolNum",datalist.get(position).getSchoolNum());
                intent.putExtra("schoolContent",datalist.get(position).getSchoolContent());
                intent.putExtra("schoolBestMajor",datalist.get(position).getSchoolBestMajor());
                intent.putExtra("schoolType",datalist.get(position).getSchoolTypeName());
                startActivity(intent);
            }
        });

    }

    private void initData() {
        GetListAsyncTask asyncTask=new GetListAsyncTask(Derection.this,listView);
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
            TextView work = convertView.findViewById(R.id.tv_work);
            TextView school = convertView.findViewById(R.id.tv_school);
            TextView area = convertView.findViewById(R.id.tv_area);
            School schools = datalist.get(position);
            work.setText(schools.getSalary()+" %");
            school.setText(schools.getSchoolName());
            area.setText(schools.getCityName());
            return convertView;
        }
    }

    //下拉列表
    class GetWorkAsyncTask extends AsyncTask<String,Void,List<String>>{

        private Context mContext;

        public GetWorkAsyncTask(Context mContext){
            this.mContext=mContext;
        }
        @Override
        protected List<String> doInBackground(String... strings) {
            String path = getResources().getString(R.string.url_path);
            String urlStr = path+ "WorkServlet?workMark=getWorkList";
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
                worklist = new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Work work = new Work();
                    work.setWorkId(object1.getInt("workId"));
                    work.setWorkName(object1.getString("workName"));
                    worklist.add(work.getWorkName());
                }
                Log.e("test", worklist.toString());
                return worklist;
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
        protected void onPostExecute(List<String> strings) {
            Log.e("test","已经进行到异步类的显示阶段");
            adapter=new ArrayAdapter<String>(mContext,R.layout.spinner_item, strings);
            spinner.setAdapter(adapter);
        }
    }

    //数据
    class GetListAsyncTask extends AsyncTask<String,Void,List<School>> {

        private Context mContext;
        private ListView listView;

        public GetListAsyncTask(Context mContext,ListView listView){
            this.mContext=mContext;
            this.listView=listView;
        }
        @Override
        protected List<School> doInBackground(String... strings) {

            String path = getResources().getString(R.string.url_path);
            String urlStr = path+"SchoolServlet?schoolMark=getSchoolWorkList";
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
                    school.setSchoolId(object1.getInt("schoolId"));
                    school.setSchoolName(object1.getString("schoolName"));
                    school.setSchoolRank(object1.getInt("schoolRank"));
                    school.setCityId(object1.getInt("cityId"));
                    school.setSchoolImg(object1.getString("schoolImg"));
                    school.setSchoolContent(object1.getString("schoolContent"));
                    school.setSchoolNum(object1.getString("schoolNum"));
                    school.setSchoolTypeId(object1.getInt("schoolTypeId"));
                    school.setSchoolTypeName(object1.getString("schoolTypeName"));
                    school.setSchoolBestMajor(object1.getString("schoolMajor"));
                    school.setCityName(object1.getString("cityName"));
                    school.setSalary(object1.getString("salary"));
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
            Dadapter = new DAdapter(mContext,R.layout.derection_layout,result);
            listView.setAdapter(Dadapter);
        }

    }

    //筛选
    class GetWorkListAsyncTask extends AsyncTask<String,Void,List<School>> {

        private Context mContext;
        private ListView listView;

        public GetWorkListAsyncTask(Context mContext,ListView listView){
            this.mContext=mContext;
            this.listView=listView;
        }
        @Override
        protected List<School> doInBackground(String... strings) {
            String workName = (String) textView.getText();
            Log.e("workName",workName);
            String path = getResources().getString(R.string.url_path);
            String urlStr = path+"SchoolServlet?schoolMark=getSchoolWorkListByWorkId&workName="+workName;
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
                    school.setSchoolId(object1.getInt("schoolId"));
                    school.setSchoolName(object1.getString("schoolName"));
                    school.setSchoolRank(object1.getInt("schoolRank"));
                    school.setCityId(object1.getInt("cityId"));
                    school.setSchoolImg(object1.getString("schoolImg"));
                    school.setSchoolContent(object1.getString("schoolContent"));
                    school.setSchoolNum(object1.getString("schoolNum"));
                    school.setSchoolTypeId(object1.getInt("schoolTypeId"));
                    school.setSchoolTypeName(object1.getString("schoolTypeName"));
                    school.setSchoolBestMajor(object1.getString("schoolMajor"));
                    school.setCityName(object1.getString("cityName"));
                    school.setSalary(object1.getString("salary"));
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
            Dadapter = new DAdapter(mContext,R.layout.derection_layout,result);
            listView.setAdapter(Dadapter);
        }

    }
}
