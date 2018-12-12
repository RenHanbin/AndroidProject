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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.celia.demo1.MainActivity;
import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.School;

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

public class UniversityIntroduce extends AppCompatActivity {

    private List<School> datalist;
    private DAdapter adapter;
    private ListView listView;
    private Boolean isPause = false;
    private String typeName;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView11;
    TextView textView12;
    TextView textView13;
    TextView textView14;
    TextView textView15;
    TextView textView16;
    TextView textView17;
    TextView textView18;
    TextView textView19;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.university);


        ImageView ivreturn=findViewById(R.id.iv_return);
        ivreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(UniversityIntroduce.this,MainActivity.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }
        });
        listView = findViewById(R.id.listview);
        //准备数据
        initData();
        //获取类别
        textView1 = findViewById(R.id.tv_985);
        textView2 = findViewById(R.id.tv_211);
        textView3 = findViewById(R.id.tv_double);
        textView4 = findViewById(R.id.tv_1);
        textView5 = findViewById(R.id.tv_2);
        textView6 = findViewById(R.id.tv_3);
        textView11 = findViewById(R.id.tv_all);
        textView12 = findViewById(R.id.tv_medical);
        textView13 = findViewById(R.id.tv_law);
        textView14 = findViewById(R.id.tv_pe);
        textView15 = findViewById(R.id.tv_art);
        textView16 = findViewById(R.id.tv_nation);
        textView17 = findViewById(R.id.tv_army);
        textView18 = findViewById(R.id.tv_teach);
        textView19 = findViewById(R.id.tv_language);

        TypeListener listener = new TypeListener();
        textView1.setOnClickListener(listener);
        textView2.setOnClickListener(listener);
        textView3.setOnClickListener(listener);
        textView4.setOnClickListener(listener);
        textView5.setOnClickListener(listener);
        textView6.setOnClickListener(listener);
        textView11.setOnClickListener(listener);
        textView12.setOnClickListener(listener);
        textView13.setOnClickListener(listener);
        textView14.setOnClickListener(listener);
        textView15.setOnClickListener(listener);
        textView16.setOnClickListener(listener);
        textView17.setOnClickListener(listener);
        textView18.setOnClickListener(listener);
        textView19.setOnClickListener(listener);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(UniversityIntroduce.this,Index_school.class);
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
        GetSchoolNumAsyncTask asyncTask=new GetSchoolNumAsyncTask(UniversityIntroduce.this,listView);
        asyncTask.execute();
    }


    //创建自定义适配器
    private  class DAdapter extends BaseAdapter {
        private Context context;
        private int itemLayoutId;
        private List<School> data;
        public DAdapter(Context context,int itemLayoutId,List<School> data){
            this.context=context;
            this.itemLayoutId=itemLayoutId;
            this.data=data;
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
            if(convertView==null){
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(itemLayoutId,null);
            }
            TextView universityname= convertView.findViewById(R.id.tv_universityname);
            TextView universitynum = convertView.findViewById(R.id.tv_universitynum);
            School school = data.get(position);
            universityname.setText(school.getSchoolName());
            universitynum.setText(school.getSchoolNum());
            return convertView;
        }


    }

    //创建获取数据的异步任务1
    class GetSchoolNumAsyncTask extends AsyncTask<String,Void,List<School>> {

        private Context mContext;
        private ListView listView;

        public GetSchoolNumAsyncTask(Context mContext,ListView listView){
            this.mContext=mContext;
            this.listView=listView;
        }
        @Override
        protected List<School> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr = path+ "SchoolServlet?schoolMark=getSchoolNum";
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
            adapter = new DAdapter(mContext, R.layout.listview_item2, result);
            listView.setAdapter(adapter);
        }

    }
    //创建获取数据的异步任务2
    class GetSchoolNumByTypeNameAsyncTask extends AsyncTask<String,Void,List<School>> {

        private Context mContext;
        private ListView listView;

        public GetSchoolNumByTypeNameAsyncTask(Context mContext,ListView listView){
            this.mContext=mContext;
            this.listView=listView;
        }
        @Override
        protected List<School> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr = path+ "SchoolServlet?schoolMark=getSchoolNumByTypeName&typeName="+typeName;
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
            adapter = new DAdapter(mContext, R.layout.listview_item2, result);
            listView.setAdapter(adapter);
        }

    }

    //监听器
    private class TypeListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_985:{
                    typeName = (String) textView1.getText();
                    break;
                }
                case R.id.tv_211:{
                    typeName = (String) textView2.getText();
                    break;
                }
                case R.id.tv_double:{
                    typeName = (String) textView3.getText();
                    break;
                }
                case R.id.tv_1:{
                    typeName = (String) textView4.getText();
                    break;
                }
                case R.id.tv_2:{
                    typeName = (String) textView5.getText();
                    break;
                }
                case R.id.tv_3:{
                    typeName = (String) textView6.getText();
                    break;
                }
                case R.id.tv_all:{
                    typeName = (String) textView11.getText();
                    break;
                }
                case R.id.tv_medical:{
                    typeName = (String) textView12.getText();
                    break;
                }
                case R.id.tv_law:{
                    typeName = (String) textView13.getText();
                    break;
                }
                case R.id.tv_pe:{
                    typeName = (String) textView14.getText();
                    break;
                }
                case R.id.tv_art:{
                    typeName = (String) textView15.getText();
                    break;
                }
                case R.id.tv_nation:{
                    typeName = (String) textView16.getText();
                    break;
                }
                case R.id.tv_army:{
                    typeName = (String) textView17.getText();
                    break;
                }
                case R.id.tv_teach:{
                    typeName = (String) textView18.getText();
                    break;
                }
                case R.id.tv_language:{
                    typeName = (String) textView19.getText();
                    break;
                }

            }
            GetSchoolNumByTypeNameAsyncTask asyncTask2 = new GetSchoolNumByTypeNameAsyncTask(UniversityIntroduce.this,listView);
            asyncTask2.execute();
        }
    }
}
