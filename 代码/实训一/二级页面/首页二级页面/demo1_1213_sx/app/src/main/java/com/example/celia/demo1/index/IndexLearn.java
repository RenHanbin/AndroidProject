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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.celia.demo1.MainActivity;
import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.Major;
import com.example.celia.demo1.bean.MajorType;

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

public class IndexLearn extends AppCompatActivity {
    private ImageView iv_return;
    private CheckBox chinese;
    private CheckBox math;
    private CheckBox english;
    private CheckBox history;
    private CheckBox geography;
    private CheckBox politics;
    private CheckBox physics;
    private CheckBox chemistry;
    private CheckBox biology;
    private CheckBox art;
    private CheckBox pe;
    private CheckBox zhiji;
    private List<MajorType> dataList;
    private List<MajorType> dataList1;
    private List<MajorType> dataList2;
    private List<MajorType> dataList3;
    private List<MajorType> dataList4;
    private List<MajorType> dataList5;
    private List<MajorType> dataList6;
    private List<MajorType> dataList7;
    private List<MajorType> dataList8;
    private List<MajorType> dataList9;
    private List<MajorType> dataList10;
    private List<MajorType> dataList11;
    private List<MajorType> dataList12;
    private List<Map<String,Object>> data;
    ListView listView;
    CustomAdapter adapter;
    String content;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.index_learn);

        //返回
        iv_return = findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(IndexLearn.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });

        GetMajorTypeListAsyncTask asyncTask = new GetMajorTypeListAsyncTask(IndexLearn.this);
        asyncTask.execute();

        listView=findViewById(R.id.lv_list_learn);

        //点击列表中的每一项跳转到三级界面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String majorName = (String) data.get(position).get("text");
                Intent intent=new Intent();
                intent.setClass(IndexLearn.this,IndexLearnMajor.class);
                intent.putExtra("majorName",majorName);
                startActivity(intent);
            }
        });
        //科目
        chinese = findViewById(R.id.chinese);
        math = findViewById(R.id.math);
        english = findViewById(R.id.english);
        history = findViewById(R.id.history);
        geography = findViewById(R.id.geography);
        politics = findViewById(R.id.politics);
        physics = findViewById(R.id.physics);
        chemistry = findViewById(R.id.chemistry);
        biology = findViewById(R.id.biology);
        art = findViewById(R.id.art);
        pe = findViewById(R.id.pe);
        zhiji = findViewById(R.id.zhiji);

        CheckListener listener = new CheckListener();
        chinese.setOnCheckedChangeListener(listener);
        math.setOnCheckedChangeListener(listener);
        english.setOnCheckedChangeListener(listener);
        history.setOnCheckedChangeListener(listener);
        geography.setOnCheckedChangeListener(listener);
        politics.setOnCheckedChangeListener(listener);
        physics.setOnCheckedChangeListener(listener);
        chemistry.setOnCheckedChangeListener(listener);
        biology.setOnCheckedChangeListener(listener);
        art.setOnCheckedChangeListener(listener);
        pe.setOnCheckedChangeListener(listener);
        zhiji.setOnCheckedChangeListener(listener);

    }

    public class CustomAdapter extends BaseAdapter{

        private Context context;
        private int itemLayoutId;
        private List<Map<String,Object>> data;

        public CustomAdapter(Context context,
                             int itemLayoutId,
                             List<Map<String, Object>> data) {
            this.context = context;
            this.itemLayoutId = itemLayoutId;
            this.data = data;
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
           if (convertView==null){
               LayoutInflater inflater=LayoutInflater.from(context);
               convertView=inflater.inflate(itemLayoutId,null);
           }
            TextView majorType=convertView.findViewById(R.id.tv_major_type);
            ImageView imageView=convertView.findViewById(R.id.iv_return3);
            Map<String,Object> map=data.get(position);
            imageView.setImageResource((int)map.get("image"));
            majorType.setText((String)map.get("text"));
           return convertView;
        }
    }

    private class CheckListener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                content = buttonView.getText().toString();
                Log.e("text",content);
                switch (buttonView.getId()){
                    case R.id.chinese:{
                        Log.e("text","选择");
                        GetMajorTypeListByNameAsyncTask nameAsyncTask = new GetMajorTypeListByNameAsyncTask(IndexLearn.this);
                        nameAsyncTask.execute();
                        break;
                    }
                    case R.id.math:{
                        GetMajorTypeListByNameAsyncTask2 nameAsyncTask = new GetMajorTypeListByNameAsyncTask2(IndexLearn.this);
                        nameAsyncTask.execute();
                        break;
                    }
                    case R.id.english:{
                        GetMajorTypeListByNameAsyncTask3 nameAsyncTask = new GetMajorTypeListByNameAsyncTask3(IndexLearn.this);
                        nameAsyncTask.execute();
                        break;
                    }
                    case R.id.history:{
                        GetMajorTypeListByNameAsyncTask4 nameAsyncTask = new GetMajorTypeListByNameAsyncTask4(IndexLearn.this);
                        nameAsyncTask.execute();
                        break;
                    }
                    case R.id.geography:{
                        GetMajorTypeListByNameAsyncTask5 nameAsyncTask = new GetMajorTypeListByNameAsyncTask5(IndexLearn.this);
                        nameAsyncTask.execute();
                        break;
                    }
                    case R.id.politics:{
                        GetMajorTypeListByNameAsyncTask6 nameAsyncTask = new GetMajorTypeListByNameAsyncTask6(IndexLearn.this);
                        nameAsyncTask.execute();
                        break;
                    }
                    case R.id.physics:{
                        GetMajorTypeListByNameAsyncTask7 nameAsyncTask = new GetMajorTypeListByNameAsyncTask7(IndexLearn.this);
                        nameAsyncTask.execute();
                        break;
                    }
                    case R.id.chemistry:{
                        GetMajorTypeListByNameAsyncTask8 nameAsyncTask = new GetMajorTypeListByNameAsyncTask8(IndexLearn.this);
                        nameAsyncTask.execute();
                        break;
                    }
                    case R.id.biology:{
                        GetMajorTypeListByNameAsyncTask9 nameAsyncTask = new GetMajorTypeListByNameAsyncTask9(IndexLearn.this);
                        nameAsyncTask.execute();
                        break;
                    }
                    case R.id.art:{
                        GetMajorTypeListByNameAsyncTask10 nameAsyncTask = new GetMajorTypeListByNameAsyncTask10(IndexLearn.this);
                        nameAsyncTask.execute();
                        break;
                    }
                    case R.id.pe:{
                        GetMajorTypeListByNameAsyncTask11 nameAsyncTask = new GetMajorTypeListByNameAsyncTask11(IndexLearn.this);
                        nameAsyncTask.execute();
                        break;
                    }
                    case R.id.zhiji:{
                        GetMajorTypeListByNameAsyncTask12 nameAsyncTask = new GetMajorTypeListByNameAsyncTask12(IndexLearn.this);
                        nameAsyncTask.execute();
                        break;
                    }
                }
            }else {
                switch (buttonView.getId()){
                    case R.id.chinese:{
                        if(dataList3!=null)
                            dataList3.removeAll(dataList1);
                        if(dataList2!=null)
                            dataList2.removeAll(dataList1);
                        if(dataList4!=null)
                            dataList4.removeAll(dataList1);
                        if(dataList5!=null)
                            dataList5.removeAll(dataList1);
                        if(dataList6!=null)
                            dataList6.removeAll(dataList1);
                        if(dataList7!=null)
                            dataList7.removeAll(dataList1);
                        if(dataList8!=null)
                            dataList8.removeAll(dataList1);
                        if(dataList9!=null)
                            dataList9.removeAll(dataList1);
                        if(dataList10!=null)
                            dataList10.removeAll(dataList1);
                        if(dataList11!=null)
                            dataList11.removeAll(dataList1);
                        if(dataList12!=null)
                            dataList12.removeAll(dataList1);
                        dataList1 = null;
                        break;
                    }
                    case R.id.math:{
                        if(dataList1!=null)
                            dataList1.removeAll(dataList2);
                        if(dataList3!=null)
                            dataList3.removeAll(dataList2);
                        if(dataList4!=null)
                            dataList4.removeAll(dataList2);
                        if(dataList5!=null)
                            dataList5.removeAll(dataList2);
                        if(dataList6!=null)
                            dataList6.removeAll(dataList2);
                        if(dataList7!=null)
                            dataList7.removeAll(dataList2);
                        if(dataList8!=null)
                            dataList8.removeAll(dataList2);
                        if(dataList9!=null)
                            dataList9.removeAll(dataList2);
                        if(dataList10!=null)
                            dataList10.removeAll(dataList2);
                        if(dataList11!=null)
                            dataList11.removeAll(dataList2);
                        if(dataList12!=null)
                            dataList12.removeAll(dataList2);
                        dataList2 = null;
                        break;
                    }
                    case R.id.english:{
                        if(dataList1!=null)
                            dataList1.removeAll(dataList3);
                        if(dataList2!=null)
                            dataList2.removeAll(dataList3);
                        if(dataList4!=null)
                            dataList4.removeAll(dataList3);
                        if(dataList5!=null)
                            dataList5.removeAll(dataList3);
                        if(dataList6!=null)
                            dataList6.removeAll(dataList3);
                        if(dataList7!=null)
                            dataList7.removeAll(dataList3);
                        if(dataList8!=null)
                            dataList8.removeAll(dataList3);
                        if(dataList9!=null)
                            dataList9.removeAll(dataList3);
                        if(dataList10!=null)
                            dataList10.removeAll(dataList3);
                        if(dataList11!=null)
                            dataList11.removeAll(dataList3);
                        if(dataList12!=null)
                            dataList12.removeAll(dataList3);
                        dataList3 = null;
                        break;
                    }
                    case R.id.history:{
                        if(dataList1!=null)
                            dataList1.removeAll(dataList4);
                        if(dataList2!=null)
                            dataList2.removeAll(dataList4);
                        if(dataList3!=null)
                            dataList3.removeAll(dataList4);
                        if(dataList5!=null)
                            dataList5.removeAll(dataList4);
                        if(dataList6!=null)
                            dataList6.removeAll(dataList4);
                        if(dataList7!=null)
                            dataList7.removeAll(dataList4);
                        if(dataList8!=null)
                            dataList8.removeAll(dataList4);
                        if(dataList9!=null)
                            dataList9.removeAll(dataList4);
                        if(dataList10!=null)
                            dataList10.removeAll(dataList4);
                        if(dataList11!=null)
                            dataList11.removeAll(dataList4);
                        if(dataList12!=null)
                            dataList12.removeAll(dataList4);
                        dataList4 = null;
                        break;
                    }
                    case R.id.geography:{
                        if(dataList1!=null)
                            dataList1.removeAll(dataList5);
                        if(dataList2!=null)
                            dataList2.removeAll(dataList5);
                        if(dataList3!=null)
                            dataList3.removeAll(dataList5);
                        if(dataList4!=null)
                            dataList4.removeAll(dataList5);
                        if(dataList6!=null)
                            dataList6.removeAll(dataList5);
                        if(dataList7!=null)
                            dataList7.removeAll(dataList5);
                        if(dataList8!=null)
                            dataList8.removeAll(dataList5);
                        if(dataList9!=null)
                            dataList9.removeAll(dataList5);
                        if(dataList10!=null)
                            dataList10.removeAll(dataList5);
                        if(dataList11!=null)
                            dataList11.removeAll(dataList5);
                        if(dataList12!=null)
                            dataList12.removeAll(dataList5);
                        dataList5 = null;
                        break;
                    }
                    case R.id.politics:{
                        if(dataList1!=null)
                            dataList1.removeAll(dataList6);
                        if(dataList2!=null)
                            dataList2.removeAll(dataList6);
                        if(dataList3!=null)
                            dataList3.removeAll(dataList6);
                        if(dataList4!=null)
                            dataList4.removeAll(dataList6);
                        if(dataList5!=null)
                            dataList5.removeAll(dataList6);
                        if(dataList7!=null)
                            dataList7.removeAll(dataList6);
                        if(dataList8!=null)
                            dataList8.removeAll(dataList6);
                        if(dataList9!=null)
                            dataList9.removeAll(dataList6);
                        if(dataList10!=null)
                            dataList10.removeAll(dataList6);
                        if(dataList11!=null)
                            dataList11.removeAll(dataList6);
                        if(dataList12!=null)
                            dataList12.removeAll(dataList6);
                        dataList6 = null;
                        break;
                    }
                    case R.id.physics:{
                        if(dataList1!=null)
                            dataList1.removeAll(dataList7);
                        if(dataList2!=null)
                            dataList2.removeAll(dataList7);
                        if(dataList3!=null)
                            dataList3.removeAll(dataList7);
                        if(dataList4!=null)
                            dataList4.removeAll(dataList7);
                        if(dataList5!=null)
                            dataList5.removeAll(dataList7);
                        if(dataList6!=null)
                            dataList6.removeAll(dataList7);
                        if(dataList8!=null)
                            dataList8.removeAll(dataList7);
                        if(dataList9!=null)
                            dataList9.removeAll(dataList7);
                        if(dataList10!=null)
                            dataList10.removeAll(dataList7);
                        if(dataList11!=null)
                            dataList11.removeAll(dataList7);
                        if(dataList12!=null)
                            dataList12.removeAll(dataList7);
                        dataList7 = null;
                        break;
                    }
                    case R.id.chemistry:{
                        if(dataList1!=null)
                            dataList1.removeAll(dataList8);
                        if(dataList2!=null)
                            dataList2.removeAll(dataList8);
                        if(dataList3!=null)
                            dataList3.removeAll(dataList8);
                        if(dataList4!=null)
                            dataList4.removeAll(dataList8);
                        if(dataList5!=null)
                            dataList5.removeAll(dataList8);
                        if(dataList6!=null)
                            dataList6.removeAll(dataList8);
                        if(dataList7!=null)
                            dataList7.removeAll(dataList8);
                        if(dataList9!=null)
                            dataList9.removeAll(dataList8);
                        if(dataList10!=null)
                            dataList10.removeAll(dataList8);
                        if(dataList11!=null)
                            dataList11.removeAll(dataList8);
                        if(dataList12!=null)
                            dataList12.removeAll(dataList8);
                        dataList8 = null;
                        break;
                    }
                    case R.id.biology:{
                        if(dataList1!=null)
                            dataList1.removeAll(dataList9);
                        if(dataList2!=null)
                            dataList2.removeAll(dataList9);
                        if(dataList3!=null)
                            dataList3.removeAll(dataList9);
                        if(dataList4!=null)
                            dataList4.removeAll(dataList9);
                        if(dataList5!=null)
                            dataList5.removeAll(dataList9);
                        if(dataList6!=null)
                            dataList6.removeAll(dataList9);
                        if(dataList7!=null)
                            dataList7.removeAll(dataList9);
                        if(dataList8!=null)
                            dataList8.removeAll(dataList9);
                        if(dataList10!=null)
                            dataList10.removeAll(dataList9);
                        if(dataList11!=null)
                            dataList11.removeAll(dataList9);
                        if(dataList12!=null)
                            dataList12.removeAll(dataList9);
                        dataList9 = null;
                        break;
                    }
                    case R.id.art:{
                        if(dataList1!=null)
                            dataList1.removeAll(dataList10);
                        if(dataList2!=null)
                            dataList2.removeAll(dataList10);
                        if(dataList3!=null)
                            dataList3.removeAll(dataList10);
                        if(dataList4!=null)
                            dataList4.removeAll(dataList10);
                        if(dataList5!=null)
                            dataList5.removeAll(dataList10);
                        if(dataList6!=null)
                            dataList6.removeAll(dataList10);
                        if(dataList7!=null)
                            dataList7.removeAll(dataList10);
                        if(dataList8!=null)
                            dataList8.removeAll(dataList10);
                        if(dataList9!=null)
                            dataList9.removeAll(dataList10);
                        if(dataList11!=null)
                            dataList11.removeAll(dataList10);
                        if(dataList12!=null)
                            dataList12.removeAll(dataList10);
                        dataList10 = null;
                        break;
                    }
                    case R.id.pe:{
                        if(dataList1!=null)
                            dataList1.removeAll(dataList11);
                        if(dataList2!=null)
                            dataList2.removeAll(dataList11);
                        if(dataList3!=null)
                            dataList3.removeAll(dataList11);
                        if(dataList4!=null)
                            dataList4.removeAll(dataList11);
                        if(dataList5!=null)
                            dataList5.removeAll(dataList11);
                        if(dataList6!=null)
                            dataList6.removeAll(dataList11);
                        if(dataList7!=null)
                            dataList7.removeAll(dataList11);
                        if(dataList8!=null)
                            dataList8.removeAll(dataList11);
                        if(dataList9!=null)
                            dataList9.removeAll(dataList11);
                        if(dataList10!=null)
                            dataList10.removeAll(dataList11);
                        if(dataList12!=null)
                            dataList12.removeAll(dataList11);
                        dataList11 = null;
                        break;
                    }
                    case R.id.zhiji:{
                        if(dataList1!=null)
                            dataList1.removeAll(dataList12);
                        if(dataList2!=null)
                            dataList2.removeAll(dataList12);
                        if(dataList3!=null)
                            dataList3.removeAll(dataList12);
                        if(dataList4!=null)
                            dataList4.removeAll(dataList12);
                        if(dataList5!=null)
                            dataList5.removeAll(dataList12);
                        if(dataList6!=null)
                            dataList6.removeAll(dataList12);
                        if(dataList7!=null)
                            dataList7.removeAll(dataList12);
                        if(dataList8!=null)
                            dataList8.removeAll(dataList12);
                        if(dataList9!=null)
                            dataList9.removeAll(dataList12);
                        if(dataList10!=null)
                            dataList10.removeAll(dataList12);
                        if(dataList11!=null)
                            dataList11.removeAll(dataList12);
                        dataList12 = null;
                        break;
                    }
                }
            }
        }
    }

    class GetMajorTypeListAsyncTask extends AsyncTask<String,Void,List<MajorType>> {

        private Context mContext;


        public GetMajorTypeListAsyncTask(Context mContext){
            this.mContext=mContext;

        }
        @Override
        protected List<MajorType> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr = path+"MajorServlet?remark=getMajorTypeList";

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
                dataList=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    MajorType majorType = new MajorType();
                    majorType.setMajorTypeId(object1.getInt("majorTypeId"));
                    majorType.setMajorTypeName(object1.getString("majorTypeName"));
                    dataList.add(majorType);
                }
                Log.e("test", dataList.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return dataList;
        }
        @Override
        protected void onPostExecute(List<MajorType> strings) {
            Log.e("test","已经进行到异步类的显示阶段");
            String [] marjorType=new String[strings.size()];
            for (int i = 0;i<strings.size();i++){
                marjorType[i]=strings.get(i).getMajorTypeName();
            }
            int[] icon={R.drawable.return3};
            data=new ArrayList<Map<String,Object>>();
            for(int i=0;i<strings.size();++i){
                Map<String,Object> map=new HashMap<>();
                map.put("text",marjorType[i]);
                map.put("image",icon[0]);
                data.add(map);
            }
            adapter=new CustomAdapter(mContext,R.layout.index_learn_listview,data);
            listView.setAdapter(adapter);
        }

    }
    //筛选
    class GetMajorTypeListByNameAsyncTask extends AsyncTask<String,Void,List<MajorType>> {

        private Context mContext;


        public GetMajorTypeListByNameAsyncTask(Context mContext){
            this.mContext=mContext;

        }
        @Override
        protected List<MajorType> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr = path+"MajorServlet?remark=getMajorTypeListByName&content="+content;
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
                dataList1=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    MajorType majorType = new MajorType();
                    majorType.setMajorTypeId(object1.getInt("majorTypeId"));
                    majorType.setMajorTypeName(object1.getString("majorTypeName"));
                    dataList1.add(majorType);
                }
                Log.e("test", dataList1.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(dataList2!=null){
                dataList2.removeAll(dataList1);
                dataList1.addAll(dataList2);
            }
            if(dataList3!=null){
                dataList3.removeAll(dataList1);
                dataList1.addAll(dataList3);
            }
            if(dataList4!=null){
                dataList4.removeAll(dataList1);
                dataList1.addAll(dataList4);
            }
            if(dataList5!=null){
                dataList5.removeAll(dataList1);
                dataList1.addAll(dataList5);
            }
            if(dataList6!=null){
                dataList6.removeAll(dataList1);
                dataList1.addAll(dataList6);
            }
            if(dataList7!=null){
                dataList7.removeAll(dataList1);
                dataList1.addAll(dataList7);
            }
            if(dataList8!=null){
                dataList8.removeAll(dataList1);
                dataList1.addAll(dataList8);
            }
            if(dataList9!=null){
                dataList9.removeAll(dataList1);
                dataList1.addAll(dataList9);
            }
            if(dataList10!=null){
                dataList10.removeAll(dataList1);
                dataList1.addAll(dataList10);
            }
            if(dataList11!=null){
                dataList11.removeAll(dataList1);
                dataList1.addAll(dataList11);
            }
            if(dataList12!=null){
                dataList12.removeAll(dataList1);
                dataList1.addAll(dataList12);
            }

            return dataList1;
        }
        @Override
        protected void onPostExecute(List<MajorType> strings) {
            Log.e("test","已经进行到异步类的显示阶段");
            String [] marjorType=new String[strings.size()];
            for (int i = 0;i<strings.size();i++){
                marjorType[i]=strings.get(i).getMajorTypeName();
            }
            int[] icon={R.drawable.return3};
            data=new ArrayList<Map<String,Object>>();
            for(int i=0;i<strings.size();++i){
                Map<String,Object> map=new HashMap<>();
                map.put("text",marjorType[i]);
                map.put("image",icon[0]);
                data.add(map);
            }
            adapter=new CustomAdapter(mContext,R.layout.index_learn_listview,data);
            listView.setAdapter(adapter);
        }

    }
    class GetMajorTypeListByNameAsyncTask2 extends AsyncTask<String,Void,List<MajorType>> {

        private Context mContext;


        public GetMajorTypeListByNameAsyncTask2(Context mContext){
            this.mContext=mContext;

        }
        @Override
        protected List<MajorType> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr = path+"MajorServlet?remark=getMajorTypeListByName&content="+content;
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
                dataList2=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    MajorType majorType = new MajorType();
                    majorType.setMajorTypeId(object1.getInt("majorTypeId"));
                    majorType.setMajorTypeName(object1.getString("majorTypeName"));
                    dataList2.add(majorType);
                }
                Log.e("test", dataList2.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(dataList1!=null){
                dataList1.removeAll(dataList2);
                dataList2.addAll(dataList1);
            }
            if(dataList3!=null){
                dataList3.removeAll(dataList2);
                dataList2.addAll(dataList3);
            }
            if(dataList4!=null){
                dataList4.removeAll(dataList2);
                dataList2.addAll(dataList4);
            }
            if(dataList5!=null){
                dataList5.removeAll(dataList2);
                dataList2.addAll(dataList5);
            }
            if(dataList6!=null){
                dataList6.removeAll(dataList2);
                dataList2.addAll(dataList6);
            }
            if(dataList7!=null){
                dataList7.removeAll(dataList2);
                dataList2.addAll(dataList7);
            }
            if(dataList8!=null){
                dataList8.removeAll(dataList2);
                dataList2.addAll(dataList8);
            }
            if(dataList9!=null){
                dataList9.removeAll(dataList2);
                dataList2.addAll(dataList9);
            }
            if(dataList10!=null){
                dataList10.removeAll(dataList2);
                dataList2.addAll(dataList10);
            }
            if(dataList11!=null){
                dataList11.removeAll(dataList2);
                dataList2.addAll(dataList11);
            }
            if(dataList12!=null){
                dataList12.removeAll(dataList2);
                dataList2.addAll(dataList12);
            }
            return dataList2;
        }
        @Override
        protected void onPostExecute(List<MajorType> strings) {
            Log.e("test","已经进行到异步类的显示阶段");
            String [] marjorType=new String[strings.size()];
            for (int i = 0;i<strings.size();i++){
                marjorType[i]=strings.get(i).getMajorTypeName();
            }
            int[] icon={R.drawable.return3};
            data=new ArrayList<Map<String,Object>>();
            for(int i=0;i<strings.size();++i){
                Map<String,Object> map=new HashMap<>();
                map.put("text",marjorType[i]);
                map.put("image",icon[0]);
                data.add(map);
            }
            adapter=new CustomAdapter(mContext,R.layout.index_learn_listview,data);
            listView.setAdapter(adapter);
        }

    }
    class GetMajorTypeListByNameAsyncTask3 extends AsyncTask<String,Void,List<MajorType>> {

        private Context mContext;


        public GetMajorTypeListByNameAsyncTask3(Context mContext){
            this.mContext=mContext;

        }
        @Override
        protected List<MajorType> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr = path+"MajorServlet?remark=getMajorTypeListByName&content="+content;
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
                dataList3=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    MajorType majorType = new MajorType();
                    majorType.setMajorTypeId(object1.getInt("majorTypeId"));
                    majorType.setMajorTypeName(object1.getString("majorTypeName"));
                    dataList3.add(majorType);
                }
                Log.e("test", dataList3.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(dataList1!=null){
                dataList1.removeAll(dataList3);
                dataList3.addAll(dataList1);

            }
            if(dataList2!=null){
                dataList2.removeAll(dataList3);
                dataList3.addAll(dataList2);
            }
            if(dataList4!=null){
                dataList4.removeAll(dataList3);
                dataList3.addAll(dataList4);
            }
            if(dataList5!=null){
                dataList5.removeAll(dataList3);
                dataList3.addAll(dataList5);
            }
            if(dataList6!=null){
                dataList6.removeAll(dataList3);
                dataList3.addAll(dataList6);
            }
            if(dataList7!=null){
                dataList7.removeAll(dataList3);
                dataList3.addAll(dataList7);
            }
            if(dataList8!=null){
                dataList8.removeAll(dataList3);
                dataList3.addAll(dataList8);
            }
            if(dataList9!=null){
                dataList9.removeAll(dataList3);
                dataList3.addAll(dataList9);
            }
            if(dataList10!=null){
                dataList10.removeAll(dataList3);
                dataList3.addAll(dataList10);
            }
            if(dataList11!=null){
                dataList11.removeAll(dataList3);
                dataList3.addAll(dataList11);
            }
            if(dataList12!=null){
                dataList12.removeAll(dataList3);
                dataList3.addAll(dataList12);
            }
            return dataList3;
        }
        @Override
        protected void onPostExecute(List<MajorType> strings) {
            Log.e("test","已经进行到异步类的显示阶段");
            String [] marjorType=new String[strings.size()];
            for (int i = 0;i<strings.size();i++){
                marjorType[i]=strings.get(i).getMajorTypeName();
            }
            int[] icon={R.drawable.return3};
            data=new ArrayList<Map<String,Object>>();
            for(int i=0;i<strings.size();++i){
                Map<String,Object> map=new HashMap<>();
                map.put("text",marjorType[i]);
                map.put("image",icon[0]);
                data.add(map);
            }
            adapter=new CustomAdapter(mContext,R.layout.index_learn_listview,data);
            listView.setAdapter(adapter);
        }

    }
    class GetMajorTypeListByNameAsyncTask4 extends AsyncTask<String,Void,List<MajorType>> {

        private Context mContext;


        public GetMajorTypeListByNameAsyncTask4(Context mContext){
            this.mContext=mContext;

        }
        @Override
        protected List<MajorType> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr = path+"MajorServlet?remark=getMajorTypeListByName&content="+content;
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
                dataList4=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    MajorType majorType = new MajorType();
                    majorType.setMajorTypeId(object1.getInt("majorTypeId"));
                    majorType.setMajorTypeName(object1.getString("majorTypeName"));
                    dataList4.add(majorType);
                }
                Log.e("test", dataList4.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(dataList1!=null){
                dataList1.removeAll(dataList4);
                dataList4.addAll(dataList1);
            }
            if(dataList3!=null){
                dataList3.removeAll(dataList4);
                dataList4.addAll(dataList3);
            }
            if(dataList2!=null){
                dataList2.removeAll(dataList4);
                dataList4.addAll(dataList2);
            }
            if(dataList5!=null){
                dataList5.removeAll(dataList4);
                dataList4.addAll(dataList5);
            }
            if(dataList6!=null){
                dataList6.removeAll(dataList4);
                dataList4.addAll(dataList6);
            }
            if(dataList7!=null){
                dataList7.removeAll(dataList4);
                dataList4.addAll(dataList7);
            }
            if(dataList8!=null){
                dataList8.removeAll(dataList4);
                dataList4.addAll(dataList8);
            }
            if(dataList9!=null){
                dataList9.removeAll(dataList4);
                dataList4.addAll(dataList9);
            }
            if(dataList10!=null){
                dataList10.removeAll(dataList4);
                dataList4.addAll(dataList10);
            }
            if(dataList11!=null){
                dataList11.removeAll(dataList4);
                dataList4.addAll(dataList11);
            }
            if(dataList12!=null){
                dataList12.removeAll(dataList4);
                dataList4.addAll(dataList12);
            }
            return dataList4;
        }
        @Override
        protected void onPostExecute(List<MajorType> strings) {
            Log.e("test","已经进行到异步类的显示阶段");
            String [] marjorType=new String[strings.size()];
            for (int i = 0;i<strings.size();i++){
                marjorType[i]=strings.get(i).getMajorTypeName();
            }
            int[] icon={R.drawable.return3};
            data=new ArrayList<Map<String,Object>>();
            for(int i=0;i<strings.size();++i){
                Map<String,Object> map=new HashMap<>();
                map.put("text",marjorType[i]);
                map.put("image",icon[0]);
                data.add(map);
            }
            adapter=new CustomAdapter(mContext,R.layout.index_learn_listview,data);
            listView.setAdapter(adapter);
        }

    }
    class GetMajorTypeListByNameAsyncTask5 extends AsyncTask<String,Void,List<MajorType>> {

        private Context mContext;


        public GetMajorTypeListByNameAsyncTask5(Context mContext){
            this.mContext=mContext;

        }
        @Override
        protected List<MajorType> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr = path+"MajorServlet?remark=getMajorTypeListByName&content="+content;
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
                dataList5=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    MajorType majorType = new MajorType();
                    majorType.setMajorTypeId(object1.getInt("majorTypeId"));
                    majorType.setMajorTypeName(object1.getString("majorTypeName"));
                    dataList5.add(majorType);
                }
                Log.e("test", dataList5.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(dataList1!=null){
                dataList1.removeAll(dataList5);
                dataList5.addAll(dataList1);
            }
            if(dataList3!=null){
                dataList3.removeAll(dataList5);
                dataList5.addAll(dataList3);
            }
            if(dataList2!=null){
                dataList2.removeAll(dataList5);
                dataList5.addAll(dataList2);
            }
            if(dataList4!=null){
                dataList4.removeAll(dataList5);
                dataList5.addAll(dataList4);
            }
            if(dataList6!=null){
                dataList6.removeAll(dataList5);
                dataList5.addAll(dataList6);
            }
            if(dataList7!=null){
                dataList7.removeAll(dataList5);
                dataList5.addAll(dataList7);
            }
            if(dataList8!=null){
                dataList8.removeAll(dataList5);
                dataList5.addAll(dataList8);
            }
            if(dataList9!=null){
                dataList9.removeAll(dataList5);
                dataList5.addAll(dataList9);
            }
            if(dataList10!=null){
                dataList10.removeAll(dataList5);
                dataList5.addAll(dataList10);
            }
            if(dataList11!=null){
                dataList11.removeAll(dataList5);
                dataList5.addAll(dataList11);
            }
            if(dataList12!=null){
                dataList12.removeAll(dataList5);
                dataList5.addAll(dataList12);
            }
            return dataList5;
        }
        @Override
        protected void onPostExecute(List<MajorType> strings) {
            Log.e("test","已经进行到异步类的显示阶段");
            String [] marjorType=new String[strings.size()];
            for (int i = 0;i<strings.size();i++){
                marjorType[i]=strings.get(i).getMajorTypeName();
            }
            int[] icon={R.drawable.return3};
            data=new ArrayList<Map<String,Object>>();
            for(int i=0;i<strings.size();++i){
                Map<String,Object> map=new HashMap<>();
                map.put("text",marjorType[i]);
                map.put("image",icon[0]);
                data.add(map);
            }
            adapter=new CustomAdapter(mContext,R.layout.index_learn_listview,data);
            listView.setAdapter(adapter);
        }
    }
    class GetMajorTypeListByNameAsyncTask6 extends AsyncTask<String,Void,List<MajorType>> {

        private Context mContext;


        public GetMajorTypeListByNameAsyncTask6(Context mContext){
            this.mContext=mContext;

        }
        @Override
        protected List<MajorType> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr = path+"MajorServlet?remark=getMajorTypeListByName&content="+content;
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
                dataList6=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    MajorType majorType = new MajorType();
                    majorType.setMajorTypeId(object1.getInt("majorTypeId"));
                    majorType.setMajorTypeName(object1.getString("majorTypeName"));
                    dataList6.add(majorType);
                }
                Log.e("test", dataList6.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(dataList1!=null){
                dataList1.removeAll(dataList6);
                dataList6.addAll(dataList1);
            }
            if(dataList3!=null){
                dataList3.removeAll(dataList6);
                dataList6.addAll(dataList3);
            }
            if(dataList2!=null){
                dataList2.removeAll(dataList6);
                dataList6.addAll(dataList2);
            }
            if(dataList5!=null){
                dataList5.removeAll(dataList6);
                dataList6.addAll(dataList5);
            }
            if(dataList4!=null){
                dataList4.removeAll(dataList6);
                dataList6.addAll(dataList4);
            }
            if(dataList7!=null){
                dataList7.removeAll(dataList6);
                dataList6.addAll(dataList7);
            }
            if(dataList8!=null){
                dataList8.removeAll(dataList6);
                dataList6.addAll(dataList8);
            }
            if(dataList9!=null){
                dataList9.removeAll(dataList6);
                dataList6.addAll(dataList9);
            }
            if(dataList10!=null){
                dataList10.removeAll(dataList6);
                dataList6.addAll(dataList10);
            }
            if(dataList11!=null){
                dataList11.removeAll(dataList6);
                dataList6.addAll(dataList11);
            }
            if(dataList12!=null){
                dataList12.removeAll(dataList6);
                dataList6.addAll(dataList12);
            }
            return dataList6;
        }
        @Override
        protected void onPostExecute(List<MajorType> strings) {
            Log.e("test","已经进行到异步类的显示阶段");
            String [] marjorType=new String[strings.size()];
            for (int i = 0;i<strings.size();i++){
                marjorType[i]=strings.get(i).getMajorTypeName();
            }
            int[] icon={R.drawable.return3};
            data=new ArrayList<Map<String,Object>>();
            for(int i=0;i<strings.size();++i){
                Map<String,Object> map=new HashMap<>();
                map.put("text",marjorType[i]);
                map.put("image",icon[0]);
                data.add(map);
            }
            adapter=new CustomAdapter(mContext,R.layout.index_learn_listview,data);
            listView.setAdapter(adapter);
        }

    }
    class GetMajorTypeListByNameAsyncTask7 extends AsyncTask<String,Void,List<MajorType>> {

        private Context mContext;


        public GetMajorTypeListByNameAsyncTask7(Context mContext){
            this.mContext=mContext;

        }
        @Override
        protected List<MajorType> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr = path+"MajorServlet?remark=getMajorTypeListByName&content="+content;
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
                dataList7=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    MajorType majorType = new MajorType();
                    majorType.setMajorTypeId(object1.getInt("majorTypeId"));
                    majorType.setMajorTypeName(object1.getString("majorTypeName"));
                    dataList7.add(majorType);
                }
                Log.e("test", dataList7.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(dataList1!=null){
                    dataList1.removeAll(dataList7);
                    dataList7.addAll(dataList1);
            }
            if(dataList3!=null){
                dataList3.removeAll(dataList7);
                dataList7.addAll(dataList3);
            }
            if(dataList2!=null){
                dataList2.removeAll(dataList7);
                dataList7.addAll(dataList2);
            }
            if(dataList5!=null){
                dataList5.removeAll(dataList7);
                dataList7.addAll(dataList5);
            }
            if(dataList6!=null){
                dataList6.removeAll(dataList7);
                dataList7.addAll(dataList6);
            }
            if(dataList4!=null){
                dataList4.removeAll(dataList7);
                dataList7.addAll(dataList4);
            }
            if(dataList8!=null){
                dataList8.removeAll(dataList7);
                dataList7.addAll(dataList8);
            }
            if(dataList9!=null){
                dataList9.removeAll(dataList7);
                dataList7.addAll(dataList9);
            }
            if(dataList10!=null){
                dataList10.removeAll(dataList7);
                dataList7.addAll(dataList10);
            }
            if(dataList11!=null){
                dataList11.removeAll(dataList7);
                dataList7.addAll(dataList11);
            }
            if(dataList12!=null){
                dataList12.removeAll(dataList7);
                dataList7.addAll(dataList12);
            }
            return dataList7;
        }
        @Override
        protected void onPostExecute(List<MajorType> strings) {
            Log.e("test","已经进行到异步类的显示阶段");
            String [] marjorType=new String[strings.size()];
            for (int i = 0;i<strings.size();i++){
                marjorType[i]=strings.get(i).getMajorTypeName();
            }
            int[] icon={R.drawable.return3};
            data=new ArrayList<Map<String,Object>>();
            for(int i=0;i<strings.size();++i){
                Map<String,Object> map=new HashMap<>();
                map.put("text",marjorType[i]);
                map.put("image",icon[0]);
                data.add(map);
            }
            adapter=new CustomAdapter(mContext,R.layout.index_learn_listview,data);
            listView.setAdapter(adapter);
        }

    }
    class GetMajorTypeListByNameAsyncTask8 extends AsyncTask<String,Void,List<MajorType>> {

        private Context mContext;


        public GetMajorTypeListByNameAsyncTask8(Context mContext){
            this.mContext=mContext;

        }
        @Override
        protected List<MajorType> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr = path+"MajorServlet?remark=getMajorTypeListByName&content="+content;
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
                dataList8=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    MajorType majorType = new MajorType();
                    majorType.setMajorTypeId(object1.getInt("majorTypeId"));
                    majorType.setMajorTypeName(object1.getString("majorTypeName"));
                    dataList8.add(majorType);
                }
                Log.e("test", dataList8.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(dataList1!=null){
                dataList1.removeAll(dataList8);
                dataList8.addAll(dataList1);
            }
            if(dataList3!=null){
                dataList3.removeAll(dataList8);
                dataList8.addAll(dataList3);
            }
            if(dataList2!=null){
                dataList2.removeAll(dataList8);
                dataList8.addAll(dataList2);
            }
            if(dataList5!=null){
                dataList5.removeAll(dataList8);
                dataList8.addAll(dataList5);
            }
            if(dataList6!=null){
                dataList6.removeAll(dataList8);
                dataList8.addAll(dataList6);
            }
            if(dataList4!=null){
                dataList4.removeAll(dataList8);
                dataList8.addAll(dataList4);
            }
            if(dataList7!=null){
                dataList7.removeAll(dataList8);
                dataList8.addAll(dataList7);
            }
            if(dataList9!=null){
                dataList9.removeAll(dataList8);
                dataList8.addAll(dataList9);
            }
            if(dataList10!=null){
                dataList10.removeAll(dataList8);
                dataList8.addAll(dataList10);
            }
            if(dataList11!=null){
                dataList11.removeAll(dataList8);
                dataList8.addAll(dataList11);
            }
            if(dataList12!=null){
                dataList12.removeAll(dataList8);
                dataList8.addAll(dataList12);
            }
            return dataList8;
        }
        @Override
        protected void onPostExecute(List<MajorType> strings) {
            Log.e("test","已经进行到异步类的显示阶段");
            String [] marjorType=new String[strings.size()];
            for (int i = 0;i<strings.size();i++){
                marjorType[i]=strings.get(i).getMajorTypeName();
            }
            int[] icon={R.drawable.return3};
            data=new ArrayList<Map<String,Object>>();
            for(int i=0;i<strings.size();++i){
                Map<String,Object> map=new HashMap<>();
                map.put("text",marjorType[i]);
                map.put("image",icon[0]);
                data.add(map);
            }
            adapter=new CustomAdapter(mContext,R.layout.index_learn_listview,data);
            listView.setAdapter(adapter);
        }

    }
    class GetMajorTypeListByNameAsyncTask9 extends AsyncTask<String,Void,List<MajorType>> {

        private Context mContext;


        public GetMajorTypeListByNameAsyncTask9(Context mContext){
            this.mContext=mContext;

        }
        @Override
        protected List<MajorType> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr = path+"MajorServlet?remark=getMajorTypeListByName&content="+content;
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
                dataList9=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    MajorType majorType = new MajorType();
                    majorType.setMajorTypeId(object1.getInt("majorTypeId"));
                    majorType.setMajorTypeName(object1.getString("majorTypeName"));
                    dataList9.add(majorType);
                }
                Log.e("test", dataList9.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(dataList2!=null){
                dataList2.removeAll(dataList9);
                dataList9.addAll(dataList2);
            }
            if(dataList3!=null){
                dataList3.removeAll(dataList9);
                dataList9.addAll(dataList3);
            }
            if(dataList4!=null){
                dataList4.removeAll(dataList9);
                dataList9.addAll(dataList4);
            }
            if(dataList5!=null){
                dataList5.removeAll(dataList9);
                dataList9.addAll(dataList5);
            }
            if(dataList6!=null){
                dataList6.removeAll(dataList9);
                dataList9.addAll(dataList6);
            }
            if(dataList7!=null){
                dataList7.removeAll(dataList9);
                dataList9.addAll(dataList7);
            }
            if(dataList8!=null){
                dataList8.removeAll(dataList9);
                dataList9.addAll(dataList8);
            }
            if(dataList1!=null){
                dataList1.removeAll(dataList9);
                dataList9.addAll(dataList1);
            }
            if(dataList10!=null){
                dataList10.removeAll(dataList9);
                dataList9.addAll(dataList10);
            }
            if(dataList11!=null){
                dataList11.removeAll(dataList9);
                dataList9.addAll(dataList11);
            }
            if(dataList12!=null){
                dataList12.removeAll(dataList9);
                dataList9.addAll(dataList12);
            }
            return dataList9;
        }
        @Override
        protected void onPostExecute(List<MajorType> strings) {
            Log.e("test","已经进行到异步类的显示阶段");
            String [] marjorType=new String[strings.size()];
            for (int i = 0;i<strings.size();i++){
                marjorType[i]=strings.get(i).getMajorTypeName();
            }
            int[] icon={R.drawable.return3};
            data=new ArrayList<Map<String,Object>>();
            for(int i=0;i<strings.size();++i){
                Map<String,Object> map=new HashMap<>();
                map.put("text",marjorType[i]);
                map.put("image",icon[0]);
                data.add(map);
            }
            adapter=new CustomAdapter(mContext,R.layout.index_learn_listview,data);
            listView.setAdapter(adapter);
        }

    }
    class GetMajorTypeListByNameAsyncTask10 extends AsyncTask<String,Void,List<MajorType>> {

        private Context mContext;


        public GetMajorTypeListByNameAsyncTask10(Context mContext){
            this.mContext=mContext;

        }
        @Override
        protected List<MajorType> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr = path+"MajorServlet?remark=getMajorTypeListByName&content="+content;
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
                dataList10=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    MajorType majorType = new MajorType();
                    majorType.setMajorTypeId(object1.getInt("majorTypeId"));
                    majorType.setMajorTypeName(object1.getString("majorTypeName"));
                    dataList10.add(majorType);
                }
                Log.e("test", dataList10.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(dataList2!=null){
                dataList2.removeAll(dataList10);
                dataList10.addAll(dataList2);
            }
            if(dataList3!=null){
                dataList3.removeAll(dataList10);
                dataList10.addAll(dataList3);
            }
            if(dataList4!=null){
                dataList4.removeAll(dataList10);
                dataList10.addAll(dataList4);
            }
            if(dataList5!=null){
                dataList5.removeAll(dataList10);
                dataList10.addAll(dataList5);
            }
            if(dataList6!=null){
                dataList6.removeAll(dataList10);
                dataList10.addAll(dataList6);
            }
            if(dataList7!=null){
                dataList7.removeAll(dataList10);
                dataList10.addAll(dataList7);
            }
            if(dataList8!=null){
                dataList8.removeAll(dataList10);
                dataList10.addAll(dataList8);
            }
            if(dataList9!=null){
                dataList9.removeAll(dataList10);
                dataList10.addAll(dataList9);
            }
            if(dataList1!=null){
                dataList1.removeAll(dataList10);
                dataList10.addAll(dataList1);
            }
            if(dataList11!=null){
                dataList11.removeAll(dataList10);
                dataList10.addAll(dataList11);
            }
            if(dataList12!=null){
                dataList12.removeAll(dataList10);
                dataList10.addAll(dataList12);
            }
            return dataList10;
        }
        @Override
        protected void onPostExecute(List<MajorType> strings) {
            Log.e("test","已经进行到异步类的显示阶段");
            String [] marjorType=new String[strings.size()];
            for (int i = 0;i<strings.size();i++){
                marjorType[i]=strings.get(i).getMajorTypeName();
            }
            int[] icon={R.drawable.return3};
            data=new ArrayList<Map<String,Object>>();
            for(int i=0;i<strings.size();++i){
                Map<String,Object> map=new HashMap<>();
                map.put("text",marjorType[i]);
                map.put("image",icon[0]);
                data.add(map);
            }
            adapter=new CustomAdapter(mContext,R.layout.index_learn_listview,data);
            listView.setAdapter(adapter);
        }

    }
    class GetMajorTypeListByNameAsyncTask11 extends AsyncTask<String,Void,List<MajorType>> {

        private Context mContext;


        public GetMajorTypeListByNameAsyncTask11(Context mContext){
            this.mContext=mContext;

        }
        @Override
        protected List<MajorType> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr = path+"MajorServlet?remark=getMajorTypeListByName&content="+content;
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
                dataList11=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    MajorType majorType = new MajorType();
                    majorType.setMajorTypeId(object1.getInt("majorTypeId"));
                    majorType.setMajorTypeName(object1.getString("majorTypeName"));
                    dataList11.add(majorType);
                }
                Log.e("test", dataList11.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(dataList2!=null){
                dataList2.removeAll(dataList11);
                dataList11.addAll(dataList2);
            }
            if(dataList3!=null){
                dataList3.removeAll(dataList11);
                dataList11.addAll(dataList3);
            }
            if(dataList4!=null){
                dataList4.removeAll(dataList11);
                dataList11.addAll(dataList4);
            }
            if(dataList5!=null){
                dataList5.removeAll(dataList11);
                dataList11.addAll(dataList5);
            }
            if(dataList6!=null){
                dataList6.removeAll(dataList11);
                dataList11.addAll(dataList6);
            }
            if(dataList7!=null){
                dataList7.removeAll(dataList11);
                dataList11.addAll(dataList7);
            }
            if(dataList8!=null){
                dataList8.removeAll(dataList11);
                dataList11.addAll(dataList8);
            }
            if(dataList9!=null){
                dataList9.removeAll(dataList11);
                dataList11.addAll(dataList9);
            }
            if(dataList10!=null){
                dataList10.removeAll(dataList11);
                dataList11.addAll(dataList10);
            }
            if(dataList1!=null){
                dataList1.removeAll(dataList11);
                dataList11.addAll(dataList1);
            }
            if(dataList12!=null){
                dataList12.removeAll(dataList11);
                dataList11.addAll(dataList12);
            }
            return dataList11;
        }
        @Override
        protected void onPostExecute(List<MajorType> strings) {
            Log.e("test","已经进行到异步类的显示阶段");
            String [] marjorType=new String[strings.size()];
            for (int i = 0;i<strings.size();i++){
                marjorType[i]=strings.get(i).getMajorTypeName();
            }
            int[] icon={R.drawable.return3};
            data=new ArrayList<Map<String,Object>>();
            for(int i=0;i<strings.size();++i){
                Map<String,Object> map=new HashMap<>();
                map.put("text",marjorType[i]);
                map.put("image",icon[0]);
                data.add(map);
            }
            adapter=new CustomAdapter(mContext,R.layout.index_learn_listview,data);
            listView.setAdapter(adapter);
        }

    }
    class GetMajorTypeListByNameAsyncTask12 extends AsyncTask<String,Void,List<MajorType>> {

        private Context mContext;


        public GetMajorTypeListByNameAsyncTask12(Context mContext){
            this.mContext=mContext;

        }
        @Override
        protected List<MajorType> doInBackground(String... strings) {
            String path = getResources().getString(R.string.app_url);
            String urlStr = path+"MajorServlet?remark=getMajorTypeListByName&content="+content;
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
                dataList12=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    MajorType majorType = new MajorType();
                    majorType.setMajorTypeId(object1.getInt("majorTypeId"));
                    majorType.setMajorTypeName(object1.getString("majorTypeName"));
                    dataList12.add(majorType);
                }
                Log.e("test", dataList12.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(dataList2!=null){
                dataList2.removeAll(dataList12);
                dataList12.addAll(dataList2);
            }
            if(dataList3!=null){
                dataList3.removeAll(dataList12);
                dataList12.addAll(dataList3);
            }
            if(dataList4!=null){
                dataList4.removeAll(dataList12);
                dataList12.addAll(dataList4);
            }
            if(dataList5!=null){
                dataList5.removeAll(dataList12);
                dataList12.addAll(dataList5);
            }
            if(dataList6!=null){
                dataList6.removeAll(dataList12);
                dataList12.addAll(dataList6);
            }
            if(dataList7!=null){
                dataList7.removeAll(dataList12);
                dataList12.addAll(dataList7);
            }
            if(dataList8!=null){
                dataList8.removeAll(dataList12);
                dataList12.addAll(dataList8);
            }
            if(dataList9!=null){
                dataList9.removeAll(dataList12);
                dataList12.addAll(dataList9);
            }
            if(dataList10!=null){
                dataList10.removeAll(dataList12);
                dataList12.addAll(dataList10);
            }
            if(dataList11!=null){
                dataList11.removeAll(dataList12);
                dataList12.addAll(dataList11);
            }
            if(dataList1!=null){
                dataList1.removeAll(dataList12);
                dataList12.addAll(dataList1);
            }
            return dataList12;
        }
        @Override
        protected void onPostExecute(List<MajorType> strings) {
            Log.e("test","已经进行到异步类的显示阶段");
            String [] marjorType=new String[strings.size()];
            for (int i = 0;i<strings.size();i++){
                marjorType[i]=strings.get(i).getMajorTypeName();
            }
            int[] icon={R.drawable.return3};
            data=new ArrayList<Map<String,Object>>();
            for(int i=0;i<strings.size();++i){
                Map<String,Object> map=new HashMap<>();
                map.put("text",marjorType[i]);
                map.put("image",icon[0]);
                data.add(map);
            }
            adapter=new CustomAdapter(mContext,R.layout.index_learn_listview,data);
            listView.setAdapter(adapter);
        }

    }
}
