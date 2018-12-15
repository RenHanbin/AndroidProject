package com.example.celia.demo1.index;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.celia.demo1.MainActivity;
import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.Province;
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

public class Select extends AppCompatActivity {
    private ImageView iv_return;
    private Spinner spinner1;
    private Spinner spinner2;
    //数据
    private ListView listView;
    private List<School> datalist;
    private DAdapter adapter;
    //省份
    private List<String> provinceList;
    private ArrayAdapter<String> adapter3;
    //查询名次
    private EditText score;
    private TextView rank;
    private String year;
    private String province;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.select);

        //返回
        iv_return = findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Select.this, TabHostActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
        //下拉列表
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        String[] data1 ={"2018","2017","2016"};
        final ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,R.layout.spinner_item, data1);
        spinner1.setAdapter(adapter1);
        GetProvinceAsyncTask provinceAsyncTask = new GetProvinceAsyncTask(Select.this);
        provinceAsyncTask.execute();

        //列表
        listView = findViewById(R.id.lv_list1);
        initData();

        //估分排名
        score = findViewById(R.id.et_score);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = view.findViewById(R.id.tv_spinner);
                year  = (String) textView.getText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = view.findViewById(R.id.tv_spinner);
                province = (String) textView.getText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        rank = findViewById(R.id.tv_mingci);
        score.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(score.getText()!=null){
                    GetRankAsyncTask rankAsyncTask = new GetRankAsyncTask(Select.this);
                    rankAsyncTask.execute();
                }
            }
        });

        //单选
        RadioGroup group = findViewById(R.id.rg);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int paiming = Integer.parseInt((String) rank.getText());
                switch (checkedId){
                    case R.id.rb1:{
                        GetSchoolAsyncTask schoolAsyncTask = new GetSchoolAsyncTask(Select.this,listView);
                        schoolAsyncTask.execute();
                        break;
                    }
                    case R.id.rb2:{
                        GetSchoolAsyncTask2 schoolAsyncTask2 = new GetSchoolAsyncTask2(Select.this,listView);
                        schoolAsyncTask2.execute();
                        break;
                    }
                    case R.id.rb3:{
                        GetSchoolAsyncTask3 schoolAsyncTask3 = new GetSchoolAsyncTask3(Select.this,listView);
                        schoolAsyncTask3.execute();
                        break;
                    }
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(Select.this,Index_school.class);
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
        GetSchoolAllAsyncTask allAsyncTask = new GetSchoolAllAsyncTask(Select.this,listView);
        allAsyncTask.execute();
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
            TextView school = convertView.findViewById(R.id.tv_school_name);
            TextView num = convertView.findViewById(R.id.tv_num);
            TextView min = convertView.findViewById(R.id.tv_min_score);
            TextView minPaiming = convertView.findViewById(R.id.tv_min_paiming);
            School schools = datalist.get(position);
            school.setText(schools.getSchoolName());
            num.setText(schools.getNeed());
            min.setText(schools.getLow_grade());
            String lowrank = String.valueOf(schools.getLow_rank());
            minPaiming.setText(lowrank);
            return convertView;
        }
    }

    //创建获取数据的异步任务1
    class GetSchoolAllAsyncTask extends AsyncTask<String,Void,List<School>> {

        private Context mContext;
        private ListView listView;

        public GetSchoolAllAsyncTask(Context mContext,ListView listView){
            this.mContext=mContext;
            this.listView=listView;
        }
        @Override
        protected List<School> doInBackground(String... strings) {
            String path = getResources().getString(R.string.url_path);
            String urlStr = path+ "SchoolServlet?schoolMark=getSchoolAll";
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
                    school.setNeed(object1.getString("need"));
                    school.setLow_grade(object1.getString("lowGrade"));
                    school.setLow_rank(object1.getInt("lowRank"));
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
            adapter = new DAdapter(mContext,R.layout.select_layout,result);
            listView.setAdapter(adapter);
        }

    }
    //创建获取数据的异步任务2
    class GetSchoolAsyncTask extends AsyncTask<String,Void,List<School>> {

        private Context mContext;
        private ListView listView;

        public GetSchoolAsyncTask(Context mContext,ListView listView){
            this.mContext=mContext;
            this.listView=listView;
        }
        @Override
        protected List<School> doInBackground(String... strings) {
            String paiming = (String) rank.getText();
            String path = getResources().getString(R.string.url_path);
            String urlStr = path+ "SchoolServlet?schoolMark=getSchool&paiming="+paiming;
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
                    school.setNeed(object1.getString("need"));
                    school.setLow_grade(object1.getString("lowGrade"));
                    school.setLow_rank(object1.getInt("lowRank"));
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
            adapter = new DAdapter(mContext,R.layout.select_layout,result);
            listView.setAdapter(adapter);
        }

    }
    //创建获取数据的异步任务3
    class GetSchoolAsyncTask2 extends AsyncTask<String,Void,List<School>> {

        private Context mContext;
        private ListView listView;

        public GetSchoolAsyncTask2(Context mContext,ListView listView){
            this.mContext=mContext;
            this.listView=listView;
        }
        @Override
        protected List<School> doInBackground(String... strings) {
            String paiming = (String) rank.getText();
            String path = getResources().getString(R.string.url_path);
            String urlStr = path+ "SchoolServlet?schoolMark=getSchool2&paiming="+paiming;
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
                    school.setNeed(object1.getString("need"));
                    school.setLow_grade(object1.getString("lowGrade"));
                    school.setLow_rank(object1.getInt("lowRank"));
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
            adapter = new DAdapter(mContext,R.layout.select_layout,result);
            listView.setAdapter(adapter);
        }

    }
    //创建获取数据的异步任务4
    class GetSchoolAsyncTask3 extends AsyncTask<String,Void,List<School>> {

        private Context mContext;
        private ListView listView;

        public GetSchoolAsyncTask3(Context mContext,ListView listView){
            this.mContext=mContext;
            this.listView=listView;
        }
        @Override
        protected List<School> doInBackground(String... strings) {
            String paiming = (String) rank.getText();
            String path = getResources().getString(R.string.url_path);
            String urlStr = path+ "SchoolServlet?schoolMark=getSchool3&paiming="+paiming;
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
                    school.setNeed(object1.getString("need"));
                    school.setLow_grade(object1.getString("lowGrade"));
                    school.setLow_rank(object1.getInt("lowRank"));
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
            adapter = new DAdapter(mContext,R.layout.select_layout,result);
            listView.setAdapter(adapter);
        }

    }
    //地区
    class GetProvinceAsyncTask extends AsyncTask<String, Void, List<String>> {
        private Context mContext;

        public GetProvinceAsyncTask(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected List<String> doInBackground(String... strings) {
            String path = getResources().getString(R.string.url_path);
            String urlStr = path + "ProvinceServlet?remark=getProvinceList";
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
                provinceList = new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(object1.getString("provinceName"));
                    provinceList.add(province.getProvinceName());
                }
                Log.e("test", provinceList.toString());
                return provinceList;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(List<String> result) {
            Log.e("test", "已经进行到异步类的显示阶段");
            adapter3 = new ArrayAdapter<String>(mContext, R.layout.spinner_item, result);
            spinner2.setAdapter(adapter3);
        }
    }
    //名次
    class GetRankAsyncTask extends AsyncTask<String,Void,String> {

        private Context mContext;

        public GetRankAsyncTask(Context mContext){
            this.mContext=mContext;
        }
        @Override
        protected String doInBackground(String... strings) {
            String grade = String.valueOf(score.getText());
            String path = getResources().getString(R.string.url_path);
            String urlStr = path+ "SchoolServlet?schoolMark=getRank&grade="+grade+"&year="+year+"&province="+province;
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream = connection.getInputStream();
                //字节流转字符流
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                String str = reader.readLine();
                //解析jsonobject
                JSONObject object1 = new JSONObject(str);
                String ranks = object1.getString("rank");
                return ranks;
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
        protected void onPostExecute(String result){
            Log.e("test","已经进行到异步类的显示阶段");
            rank.setText(result);
        }

    }

}
