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
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celia.demo1.MainActivity;
import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.City;
import com.example.celia.demo1.bean.CityType;
import com.example.celia.demo1.bean.Major;
import com.example.celia.demo1.bean.Position;
import com.example.celia.demo1.bean.Province;
import com.example.celia.demo1.bean.Writer;
import com.example.celia.demo1.my.MyUser;
import com.example.celia.demo1.zixun.TabHostActivity;
import com.google.gson.Gson;

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

public class IndexJobs extends AppCompatActivity {
    private ImageView iv_return;
    private List<City> cityJobsList;
    ListView listView;
    String cityName;//查询的城市名称
    RadioGroup radioGroup;
    String cityType;//查询的城市类别
    EditText etCityName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.index_jobs);
        //根据数据库显示城市
        listView=findViewById(R.id.lv_list_jobs);
        initData();

        //返回首页index
        iv_return = findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(IndexJobs.this, TabHostActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });

        //搜索城市类别
        radioGroup=findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadio=findViewById(radioGroup.getCheckedRadioButtonId());
                cityType=checkedRadio.getText().toString();
                Log.e("test:cityType",cityType);
                SearchCityByTypeAsyncTask searchCityByTypeAsyncTask=new SearchCityByTypeAsyncTask(IndexJobs.this,listView);
                searchCityByTypeAsyncTask.execute();
            }
        });
        //搜索城市
        etCityName=findViewById(R.id.et_cityName);
        ImageView search=findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cityName=etCityName.getText().toString();
                SearchCityJobsAsyncTask searchCityJobsAsyncTask=new SearchCityJobsAsyncTask(IndexJobs.this,listView);
                searchCityJobsAsyncTask.execute();
                //Toast.makeText(IndexJobs.this,"搜索城市",Toast.LENGTH_SHORT).show();
            }
        });

        etCityName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                cityName=etCityName.getText().toString();
                SearchCityJobsAsyncTask searchCityJobsAsyncTask=new SearchCityJobsAsyncTask(IndexJobs.this,listView);
                searchCityJobsAsyncTask.execute();
            }
        });

        //进入三级页面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentToDetail=new Intent(IndexJobs.this,Index3City.class);
                Log.e("test",cityJobsList.get(position).getCitySalary()+"");
                Log.e("test",cityJobsList.get(position).getCityGdp()+"");
                intentToDetail.putExtra("cityImg",cityJobsList.get(position).getCityImg());
                intentToDetail.putExtra("cityName",cityJobsList.get(position).getCityName());
                intentToDetail.putExtra("citySalary",cityJobsList.get(position).getCitySalary());
                intentToDetail.putExtra("cityGdp",cityJobsList.get(position).getCityGdp());
                intentToDetail.putExtra("cityContent",cityJobsList.get(position).getCityContent());
                intentToDetail.putExtra("province",cityJobsList.get(position).getProvince());
                intentToDetail.putExtra("cityType",cityJobsList.get(position).getCityType());
                startActivity(intentToDetail);
            }
        });


    }



    //创建异步类
    private class CityJobsAsyncTask extends AsyncTask<String,Void,List<City>>{

        private Context mContext;
        private ListView listView;

            protected CityJobsAsyncTask(Context mContext,ListView listView) {
            this.mContext = mContext;
            this.listView = listView;
        }
            @Override
            protected List<City> doInBackground(String... strings) {
                String path=getResources().getString(R.string.url_path);
                String urlStr = path+"CityServlet?remark=getCityJobsList";

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
                    cityJobsList=new ArrayList<>();
                    for (int i = 0; i < array.length(); ++i) {
                        JSONObject object1 = array.getJSONObject(i);
                        City city = new City();
                        city.setCityId(object1.getInt("cityId"));
                        city.setCityName(object1.getString("cityName"));
                        city.setCityImg(object1.getString("cityImg"));
                        city.setCityGdp(object1.getDouble("cityGdp"));
                        city.setCityContent(object1.getString("cityContent"));
                        city.setCitySalary(object1.getDouble("citySalary"));
                        String cityTypeStr=object1.get("cityType").toString();
                        CityType cityType=new Gson().fromJson(cityTypeStr,CityType.class);
                        city.setCityType(cityType);
                        String provinceStr=object1.get("province").toString();
                        Province province=new Gson().fromJson(provinceStr,Province.class);
                        city.setProvince(province);
                        /**/
                        cityJobsList.add(city);
                    }
                    Log.e("test", cityJobsList.toString());
                    return cityJobsList;
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
            protected void onPostExecute(List<City> result){
            Log.e("test","已经进行到异步类的显示阶段");
            CustomAdapter adapter=new CustomAdapter(mContext,R.layout.index_jobs_listview,cityJobsList);
            listView.setAdapter(adapter);
        }
        }

    //创建adapter
    public class CustomAdapter extends BaseAdapter {
        private Context context;
        private int itemLayoutId;
        private List<City> cityList;

        public CustomAdapter(Context context,
                             int itemLayoutId,
                             List<City> data) {
            this.context = context;
            this.itemLayoutId = itemLayoutId;
            cityList = data;
        }

        @Override
        public int getCount() {
            return cityList.size();
        }

        @Override
        public Object getItem(int position) {
            return cityList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                LayoutInflater inflater=LayoutInflater.from(context);
                convertView=inflater.inflate(itemLayoutId,null);
            }
            TextView cityName=convertView.findViewById(R.id.city_name);
            TextView citySalary=convertView.findViewById(R.id.city_salary);
            City city=cityList.get(position);
            cityName.setText(city.getCityName());
            citySalary.setText(String.valueOf(city.getCitySalary()));
            return convertView;
        }
    }

    //构造数据
    private void initData() {//salary
        CityJobsAsyncTask cityJobsAsyncTask=new CityJobsAsyncTask(IndexJobs.this,listView);
        cityJobsAsyncTask.execute();
    }

    //根据城市类别显示城市的异步类
    private class SearchCityByTypeAsyncTask extends AsyncTask<String,Void,List<City>>{

        private Context mContext;
        private ListView listView;

        protected SearchCityByTypeAsyncTask(Context mContext,ListView listView) {
            this.mContext = mContext;
            this.listView = listView;
        }
        @Override
        protected List<City> doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"CityServlet?remark=getSearchCityListByType&cityTypeName="+cityType;

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
                cityJobsList=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    City city = new City();
                    city.setCityId(object1.getInt("cityId"));
                    city.setCityName(object1.getString("cityName"));
                    city.setCityGdp(object1.getDouble("cityGdp"));
                    city.setCityImg(object1.getString("cityImg"));
                    city.setCityContent(object1.getString("cityContent"));
                    city.setCitySalary(object1.getDouble("citySalary"));
                    String cityTypeStr=object1.get("cityType").toString();
                    CityType cityType=new Gson().fromJson(cityTypeStr,CityType.class);
                    city.setCityType(cityType);
                    String provinceStr=object1.get("province").toString();
                    Province province=new Gson().fromJson(provinceStr,Province.class);
                    city.setProvince(province);
                    /**/
                    cityJobsList.add(city);
                }
                Log.e("test", cityJobsList.toString());
                return cityJobsList;
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
        protected void onPostExecute(List<City> result){
            Log.e("test","已经进行到异步类的显示阶段");
            CustomAdapter adapter=new CustomAdapter(mContext,R.layout.index_jobs_listview,cityJobsList);
            listView.setAdapter(adapter);
            adapter.notifyDataSetInvalidated();
        }
    }
    //创建根据城市名称显示城市的异步类
    private class SearchCityJobsAsyncTask extends AsyncTask<String,Void,List<City>>{

        private Context mContext;
        private ListView listView;

        protected SearchCityJobsAsyncTask(Context mContext,ListView listView) {
            this.mContext = mContext;
            this.listView = listView;
        }
        @Override
        protected List<City> doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"CityServlet?remark=getSearchCityJobsList&cityName="+cityName;

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
                cityJobsList=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    City city = new City();
                    city.setCityId(object1.getInt("cityId"));
                    city.setCityName(object1.getString("cityName"));
                    city.setCityGdp(object1.getDouble("cityGdp"));
                    city.setCitySalary(object1.getDouble("citySalary"));
                    city.setCityImg(object1.getString("cityImg"));
                    city.setCityContent(object1.getString("cityContent"));
                    String cityTypeStr=object1.get("cityType").toString();
                    CityType cityType=new Gson().fromJson(cityTypeStr,CityType.class);
                    city.setCityType(cityType);
                    String provinceStr=object1.get("province").toString();
                    Province province=new Gson().fromJson(provinceStr,Province.class);
                    city.setProvince(province);
                    /**/
                    cityJobsList.add(city);
                }
                Log.e("test", cityJobsList.toString());
                return cityJobsList;
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
        protected void onPostExecute(List<City> result){
            Log.e("test","已经进行到异步类的显示阶段");
            CustomAdapter adapter=new CustomAdapter(mContext,R.layout.index_jobs_listview,cityJobsList);
            listView.setAdapter(adapter);
            adapter.notifyDataSetInvalidated();
        }
    }
}
