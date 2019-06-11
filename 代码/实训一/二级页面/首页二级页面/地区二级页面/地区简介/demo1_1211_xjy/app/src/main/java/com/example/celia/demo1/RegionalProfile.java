package com.example.celia.demo1;

import android.bluetooth.BluetoothClass;
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

import com.example.celia.demo1.bean.CityBean;
import com.example.celia.demo1.bean.PositionBean;
import com.example.celia.demo1.bean.ProfileBean;
import com.example.celia.demo1.bean.ProvinceBean;

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

public class RegionalProfile extends AppCompatActivity {
    private Spinner position;
    private Spinner province;
    private Spinner city;
    private ListView profileListView;
    private String[] mItems;
    private List<String> positionList;
    private List<String> provinceList;
    private List<String> cityList;
    ArrayAdapter<String> adapter2;
    ArrayAdapter<String> adapter3;
    ArrayAdapter<String> adapter4;
    private ListViewAdapter1 adapter;
    private List<ProfileBean> profileList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.regionalprofile);
        position = findViewById(R.id.sp1);
        province = findViewById(R.id.sp2);
        city = findViewById(R.id.sp3);
        profileListView = findViewById(R.id.regional);
        //获取数据，从数据库中提取
        initData();
        initData2();
        //下拉列表
        position = findViewById(R.id.sp1);
        province = findViewById(R.id.sp2);
        city = findViewById(R.id.sp3);
        ImageView ivreturn=findViewById(R.id.iv_return);
        ivreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(
                        RegionalProfile.this,MainActivity.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }
        });


       //根据选择的地区显示省份
        position.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GetProvinceAsyncTask asyncTask2=new GetProvinceAsyncTask(RegionalProfile.this);
                asyncTask2.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //根据选择的省份显示城市
        province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GetCityAsyncTask asyncTask3=new GetCityAsyncTask(RegionalProfile.this);
                asyncTask3.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //创建adapter
    private class ListViewAdapter1 extends BaseAdapter{

        private Context context;
        private int itemLayout;
        private List<ProfileBean> profileList;

        public ListViewAdapter1(Context context,
                               int itemLayout,
                               List<ProfileBean> list){
            this.context=context;
            this.itemLayout=itemLayout;
            this.profileList=list;
        }
        @Override
        public int getCount() {
            return profileList.size();
        }

        @Override
        public Object getItem(int position) {
            return profileList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                LayoutInflater inflater=LayoutInflater.from(context);
                convertView=inflater.inflate(itemLayout,null);
            }
            TextView college=convertView.findViewById(R.id.college);
            TextView level=convertView.findViewById(R.id.level);
            TextView bestmajor=convertView.findViewById(R.id.position);
            ProfileBean profile=profileList.get(position);
            Log.e("test",profile.getSchoolName());
            college.setText(profile.getSchoolName());
            level.setText(""+profile.getSchoolRank());
            bestmajor.setText(""+profile.getSchoolBestMajor());
            return convertView;
        }
    }

    //获取列表的异步任务类
    class GetProfileListAsyncTask extends AsyncTask<String,Void,List<ProfileBean>>{

        private Context mContext;
        private ListView listView;

        public GetProfileListAsyncTask(Context mContext,ListView listView){
            this.mContext=mContext;
            this.listView=listView;
        }
        @Override
        protected List<ProfileBean> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr = path+"ProfileServlet?remark=getProfileList";
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
               profileList=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    ProfileBean profile = new ProfileBean();
                    profile.setSchoolName(object1.getString("schoolName"));
                    profile.setSchoolRank(object1.getInt("schoolRank"));
                    profile.setSchoolBestMajor(object1.getString("schoolBestMajor"));
                    profileList.add(profile);
                }
                Log.e("test", profileList.toString());
                return profileList;
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
        protected void onPostExecute(List<ProfileBean> result){
            Log.e("test","已经进行到异步类的显示阶段");
            adapter=new ListViewAdapter1(mContext,R.layout.regional_list_item,result);
            listView.setAdapter(adapter);
        }

    }


        //从数据库当中获取data
    public void initData() {
        GetPositionAsyncTask asyncTask=new GetPositionAsyncTask(RegionalProfile.this);
        asyncTask.execute();

    }
    public void initData2(){
        GetProfileListAsyncTask asyncTask2=new GetProfileListAsyncTask(RegionalProfile.this,profileListView);
        asyncTask2.execute();
    }

    //获取Position下拉列表的异步任务类
    class GetPositionAsyncTask extends AsyncTask<String, Void, List<String>> {
        private Context mContext;

        public GetPositionAsyncTask(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected List<String> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr =  path+"PositionServlet?remark=getPositionList";
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
                positionList = new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    PositionBean position = new PositionBean();
                    position.setPositionId(object1.getInt("positionId"));
                    position.setPositionName(object1.getString("positionName"));
                    positionList.add(position.getPositionName());
                }
                Log.e("test", positionList.toString());
                return positionList;
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
            adapter2 = new ArrayAdapter<String>(mContext, R.layout.spinner_item, result);
            position.setAdapter(adapter2);
        }
    }

    //获取Province下拉列表的异步任务类
    class GetProvinceAsyncTask extends AsyncTask<String, Void, List<String>> {
        private Context mContext;

        public GetProvinceAsyncTask(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected List<String> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
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
                    ProvinceBean province = new ProvinceBean();
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
            province.setAdapter(adapter3);
        }
    }

    //获取城市下拉列表的异步任务类
    class GetCityAsyncTask extends AsyncTask<String, Void, List<String>> {

        private Context mContext;

        public GetCityAsyncTask(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected List<String> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr = path + "CityServlet?remark=getCityList";
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
                cityList = new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    CityBean city = new CityBean();
                    city.setCityName(object1.getString("cityName"));
                    cityList.add(city.getCityName());
                }
                Log.e("test", cityList.toString());
                return cityList;
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
            adapter4 = new ArrayAdapter<String>(mContext, R.layout.spinner_item, result);
            city.setAdapter(adapter4);
        }
    }
}
