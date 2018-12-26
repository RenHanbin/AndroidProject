package com.example.celia.demo1.my;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.celia.demo1.ContextData;
import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.School;
import com.example.celia.demo1.showImgAsyncTask.ShowImgAsyncTask;

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



public class MyScho extends AppCompatActivity {
    private ListView listScho;
    private List<School> schoolList2;
    private CustomAdapter adapter;
    private int userId;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_school);
        final ContextData data= (ContextData) getApplication();
        userId=data.getUserId();
        Log.e("userId=",userId+"");
        listScho = findViewById(R.id.lv_school);
        initData();
        listScho.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                initData();
                initData1(position);
            }
        });

        //点击“<”，返回上一级界面我的my
        ImageView Ireturn = findViewById(R.id.iv_return);
        Ireturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    class GetMajorListAsyncTask2 extends AsyncTask<String, Void, List<School>> {
        private Context mContext2;
        private ListView listView2;

        public GetMajorListAsyncTask2(Context mContext2, ListView listView2) {
            this.mContext2 = mContext2;
            this.listView2 = listView2;
        }

        @Override
        protected List<School> doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);

            String urlStr = path+"CollectionSchoolServlet?remark=collectionSchoolListSelect&userId="+userId;
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
                schoolList2 = new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    School school = new School();
                    school.setSchoolId(object1.getInt("schoolId"));
                    school.setSchoolName(object1.getString("schoolName"));
                    school.setSchoolRank(object1.getInt("schoolRank"));
                    school.setSchoolTypeName(object1.getString("schoolTypeName"));
                    school.setSchoolImg(object1.getString("schoolImg"));
                    schoolList2.add(school);
                }
                Log.e("test", schoolList2.toString());
                return schoolList2;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(List<School> result) {
            Log.e("test", "已经进行到异步类的显示阶段");
            adapter = new CustomAdapter(mContext2, R.layout.my_school_list, result);
            listView2.setAdapter(adapter);
        }
    }


    public class CustomAdapter extends BaseAdapter {

        private Context context;
        private int itemLayoutID;
        private List<School> schoolList2;

        public CustomAdapter(Context context, int itemLayoutID, List<School> schoolList2) {
            this.context = context;
            this.itemLayoutID = itemLayoutID;
            this.schoolList2 = schoolList2;
        }

        @Override
        public int getCount() {
            return schoolList2.size();
        }

        @Override
        public Object getItem(int position) {
            return schoolList2.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(itemLayoutID, null);
            }
            ImageView imageView1 = convertView.findViewById(R.id.iv_scho);
            TextView textView1 = convertView.findViewById(R.id.tv_school_name);
            TextView textView2 = convertView.findViewById(R.id.tv_level);
            TextView textView3 = convertView.findViewById(R.id.tv_grade);
            School school = schoolList2.get(position);
            Log.e("test", school.getSchoolName());
            String schoolImagUrl = school.getSchoolImg();
            new ShowImgAsyncTask(schoolImagUrl, imageView1).execute();
            textView1.setText(school.getSchoolName());
            textView2.setText(school.getSchoolTypeName());
            textView3.setText("大学排名："+String.valueOf(school.getSchoolRank()));
            return convertView;
        }
    }

    class GetMajorListAsyncTask22 extends AsyncTask<String, Void, List<School>> {
        private Context mContext2;
        private ListView listView2;
        private int posit;

        public GetMajorListAsyncTask22(Context mContext2, ListView listView2, int posit) {
            this.mContext2 = mContext2;
            this.listView2 = listView2;
            this.posit = posit;
        }

        @Override
        protected List<School> doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);

            String urlStr = path+"CollectionSchoolServlet?remark=collectionSchoolListDelete&userId="+userId+"&schoolId1="+schoolList2.get(posit).getSchoolId();
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
                schoolList2 = new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    School school = new School();
                    school.setSchoolName(object1.getString("schoolName"));
                    school.setSchoolRank(object1.getInt("schoolRank"));
                    school.setSchoolTypeName(object1.getString("schoolTypeName"));
                    school.setSchoolImg(object1.getString("schoolImg"));
                    schoolList2.add(school);
                }
                Log.e("test", schoolList2.toString());
                return schoolList2;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(List<School> result) {
            Log.e("test", "已经进行到异步类的显示阶段");
            adapter = new CustomAdapter(mContext2, R.layout.my_school_list, result);
            listView2.setAdapter(adapter);
        }
    }

    private void initData() {
        GetMajorListAsyncTask2 asyncTask=new GetMajorListAsyncTask2(MyScho.this,listScho);
        asyncTask.execute();
    }
    private void initData1(int position) {
        GetMajorListAsyncTask22 asyncTask=new GetMajorListAsyncTask22(MyScho.this,listScho,position);
        asyncTask.execute();
    }
}