package com.example.celia.demo1.index;

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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.CityBean;
import com.example.celia.demo1.bean.PositionBean;
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
    private ListView collegeListView;
    private String[] mItems;
    private List<String> positionList;
    private List<String> provinceList;
    private List<String> cityList;
    ArrayAdapter<String> adapter2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.regionalprofile);
        position = findViewById(R.id.sp1);
        province = findViewById(R.id.sp2);
        city = findViewById(R.id.sp3);
        collegeListView = findViewById(R.id.regional);
        //获取数据，从数据库中提取
        initData();
        //下拉列表
        position = findViewById(R.id.sp1);
        province = findViewById(R.id.sp2);
        city = findViewById(R.id.sp3);
    }

    //创建adapter

    //从数据库当中获取data
    public void initData() {
        GetPositionAsyncTask asyncTask = new GetPositionAsyncTask(RegionalProfile.this);
        asyncTask.execute();
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
                    province.setProvinceId(object1.getInt("positionId"));
                    province.setProvinceName(object1.getString("positionName"));
                    positionList.add(province.getProvinceName());
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
            adapter2 = new ArrayAdapter<String>(mContext, R.layout.spinner_item, result);
            position.setAdapter(adapter2);
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
                    city.setCityId(object1.getInt("cityId"));
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
            adapter2 = new ArrayAdapter<String>(mContext, R.layout.spinner_item, result);
            position.setAdapter(adapter2);
        }
    }
}
