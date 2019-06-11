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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.celia.demo1.MainActivity;
import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.Major;

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
import java.util.List;


public class HotMajor extends AppCompatActivity {
    private ListView majorListView;
    private List<Major> majorList;
    private ListViewAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.zhuanye_hot);
        majorListView=findViewById(R.id.hotMajor_item);
        //准备数据
        initData();
        //点击返回
        ImageView ivreturn=findViewById(R.id.iv_return);
        ivreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(HotMajor.this,MainActivity.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }
        });



    }

    //创建数据
    private void initData() {
        GetMajorListAsyncTask asyncTask = new GetMajorListAsyncTask(HotMajor.this, majorListView);
        asyncTask.execute();
    }


    //创建adapter
    class ListViewAdapter extends BaseAdapter {

        private Context context;
        private int itemLayout;
        private List<Major> majorList;

        public ListViewAdapter(Context context,
                               int itemLayout,
                               List<Major> list) {
            this.context = context;
            this.itemLayout = itemLayout;
            this.majorList = list;
        }

        @Override
        public int getCount() {
            return majorList.size();
        }

        @Override
        public Object getItem(int position) {
            return majorList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(itemLayout, null);
            }
            TextView major = convertView.findViewById(R.id.tv_name);
            TextView majorWant = convertView.findViewById(R.id.tv_num1);
            TextView majorNeed = convertView.findViewById(R.id.tv_num2);
            Major major1 = majorList.get(position);
            Log.e("test", major1.getMajorName());
            major.setText(major1.getMajorName());
            majorWant.setText(String.valueOf(major1.getMajorWant()));
            majorNeed.setText(String.valueOf(major1.getMajorNeed()));
            return convertView;
        }
    }

    //创建获取数据的异步任务
    class GetMajorListAsyncTask extends AsyncTask<String, Void, List<Major>> {

        private Context hContext;
        private ListView listView;

        public GetMajorListAsyncTask(Context hContext, ListView listView) {
            this.hContext = hContext;
            this.listView = listView;
        }

        @Override
        protected List<Major> doInBackground(String... strings) {// 10.7.88.218
            String path = getResources().getString(R.string.url_path);
            String urlStr = path + "MajorServlet?remark=getMajorHotList";
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
                majorList = new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Major major = new Major();
                    major.setMajorId(object1.getInt("majorId"));
                    major.setMajorName(object1.getString("majorName"));
                    major.setMajorTypeName(object1.getString("majorTypeName"));
                    major.setMajorWant(object1.getInt("majorWant"));
                    major.setMajorNeed(object1.getInt("majorNeed"));
                    majorList.add(major);
                }
                Log.e("test", majorList.toString());
                return majorList;
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
        protected void onPostExecute(List<Major> result) {
            Log.e("test", "已经进行到异步类的显示阶段");
            adapter = new ListViewAdapter(hContext, R.layout.listview_item, result);
            listView.setAdapter(adapter);
        }
    }
}