package com.example.celia.demo1.index;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celia.demo1.MainActivity;
import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.City;
import com.example.celia.demo1.bean.CityType;
import com.example.celia.demo1.bean.Province;
import com.example.celia.demo1.bean.ProvinceStudent;
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

public class IndexScore extends AppCompatActivity {
    private ImageView iv_return;
    List<String> list;//下拉列表的选项list
    ImageView search;
    EditText inputProvince;//搜索的省份
    String studentTypeName;//学生类别名
    private List<String> strList;
    private Map<String,TextView> tvMap2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.index_score);
        //获取下拉列表选中值
        list = new ArrayList<String>();
        list.add("普通生");
        list.add("体育生");
        list.add("艺术生");
        Spinner sp = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_textsize,R.id.weekofday,list);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                studentTypeName=list.get(position);
                Log.e("test",studentTypeName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //返回首页index
        iv_return = findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(IndexScore.this, TabHostActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
        //搜索省份
        search=findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到输入的省份
                inputProvince=findViewById(R.id.et_inputProvince);
                String inputStr=inputProvince.getText().toString();
                Log.e("test:省份：",inputStr);
                //提供省份和学生类别
                strList=new ArrayList<>();
                strList.add(inputStr);
                strList.add(studentTypeName);
                TextView proName=findViewById(R.id.provinceName);
                TextView first=findViewById(R.id.firstLine);
                TextView second=findViewById(R.id.secondLine);
                TextView third=findViewById(R.id.thirdLine);
                TextView other=findViewById(R.id.otherLine);
                //创建返回map
                tvMap2= new HashMap<>();
                tvMap2.put("proName",proName);
                tvMap2.put("first",first);
                tvMap2.put("second",second);
                tvMap2.put("third",third);
                tvMap2.put("other",other);
                GetLineAsyncTask lineAsyncTask=new GetLineAsyncTask(strList,tvMap2);
                lineAsyncTask.execute();
                Toast.makeText(IndexScore.this,"搜索省份",Toast.LENGTH_SHORT).show();
            }
        });


    }

    //创建异步类
    private class GetLineAsyncTask extends AsyncTask {

        private List<String> list1;
        private Map<String,TextView> tvMap2;
        private ProvinceStudent provinceStudent;
        protected GetLineAsyncTask(List<String> list1,Map<String,TextView> tvList2) {
            this.list1 = list1;
            this.tvMap2 = tvList2;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"ProvinceServlet?remark=getProvinceLineList&studentType="+studentTypeName+
                    "&provinceName="+list1.get(0);

            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream = connection.getInputStream();
                //字节流转字符流
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                String str = reader.readLine();
                Log.e("test:获得str", str);
                //解析json
                JSONObject object1 = null;
                try {
                    object1 = new JSONObject(str);
                    provinceStudent=new ProvinceStudent();
                    provinceStudent.setFirstGradeLine(object1.getInt("firstLine"));
                    provinceStudent.setSecondGradeLine(object1.getInt("secondLine"));
                    provinceStudent.setThirdGradeLine(object1.getInt("thirdLine"));
                    provinceStudent.setOtherGradeLine(object1.getInt("otherLine"));
                    provinceStudent.setProvinceId(object1.getInt("provinceId"));
                    provinceStudent.setStudentTypeId(object1.getInt("studentTypeId"));
                    Log.e("test",provinceStudent.toString());
                    return provinceStudent;
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            Log.e("test:tvMap",tvMap2.size()+"");
            tvMap2.get("first").setText(provinceStudent.getFirstGradeLine()+"");
            tvMap2.get("second").setText(provinceStudent.getSecondGradeLine()+"");
            tvMap2.get("third").setText(provinceStudent.getThirdGradeLine()+"");
            tvMap2.get("other").setText(provinceStudent.getOtherGradeLine()+"");
            tvMap2.get("proName").setText(list1.get(0));
        }
    }
}
