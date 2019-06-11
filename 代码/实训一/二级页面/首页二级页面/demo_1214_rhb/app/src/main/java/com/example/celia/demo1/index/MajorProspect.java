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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.celia.demo1.MainActivity;
import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.Major;
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
import java.util.List;

public class MajorProspect extends AppCompatActivity{
    private ListView majorListView;
    private ImageView iv_return;
    private List<Major> majorList;
    private ListViewAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.majorprospect_layout);
        //返回按钮
        iv_return = findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MajorProspect.this, TabHostActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
        majorListView=findViewById(R.id.lv_majorProspect);
        //获取数据，从数据库中提取
        initData();
        //创建adapter

        //进入三级页面
        majorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(MajorProspect.this,Index3Major.class);
                intent.putExtra("majorId",majorList.get(position).getMajorId());
                intent.putExtra("majorName",majorList.get(position).getMajorName());
                intent.putExtra("majorInf",majorList.get(position).getMajorIntroduce());
                intent.putExtra("majorSubject",majorList.get(position).getMajorSubject());
                intent.putExtra("majorTypeName",majorList.get(position).getMajorTypeName());
                intent.putExtra("majorWork",majorList.get(position).getMajorWork());
                startActivity(intent);

            }
        });
        //收藏专业


    }

    //创建adapter
    private class ListViewAdapter extends BaseAdapter{

        private Context context;
        private int itemLayout;
        private List<Major> majorList;

        public ListViewAdapter(Context context,
                               int itemLayout,
                               List<Major> list){
            this.context=context;
            this.itemLayout=itemLayout;
            this.majorList=list;
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
            if(convertView==null){
                LayoutInflater inflater=LayoutInflater.from(context);
                convertView=inflater.inflate(itemLayout,null);
            }
            TextView major=convertView.findViewById(R.id.tv_major);
            TextView majorType=convertView.findViewById(R.id.tv_majorType);
            TextView workPercent=convertView.findViewById(R.id.tv_workPercent);
            TextView salary=convertView.findViewById(R.id.tv_salary);
            Major major1=majorList.get(position);
            Log.e("test",major1.getMajorName());
            major.setText(major1.getMajorName());
            majorType.setText("（"+major1.getMajorTypeName()+"）");
            workPercent.setText(String.valueOf(major1.getMajorWorkPercent())+"%");
            salary.setText(String.valueOf(major1.getMajorSalary())+"元/月");
            return convertView;
        }
    }

    //创建获取数据的异步任务
    class GetMajorListAsyncTask extends AsyncTask<String,Void,List<Major>>{

        private Context mContext;
        private ListView listView;

        protected GetMajorListAsyncTask(Context mContext,ListView listView){
            this.mContext=mContext;
            this.listView=listView;
        }
        @Override
        protected List<Major> doInBackground(String... strings) {
            /*String urlStr = "http://10.7.88.251:8080/go2school/MajorServlet?remark=getMajorList";*/
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"MajorServlet?remark=getMajorList";

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
                majorList=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Major major = new Major();
                    major.setMajorId(object1.getInt("majorId"));
                    major.setMajorName(object1.getString("majorName"));
                    major.setMajorTypeName(object1.getString("majorTypeName"));
                    major.setMajorWorkPercent(object1.getDouble("majorWorkPercent"));
                    major.setMajorSalary(object1.getInt("majorSalary"));
                    major.setMajorIntroduce(object1.getString("majorIntroduce"));
                    major.setMajorSubject(object1.getString("majorSubject"));
                    major.setMajorWork(object1.getString("majorWork"));
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
        protected void onPostExecute(List<Major> result){
            Log.e("test","已经进行到异步类的显示阶段");
            adapter=new ListViewAdapter(mContext,R.layout.majorsprospectitem_layout,result);
            listView.setAdapter(adapter);
        }

    }

    //从数据库当中获取data
    public void initData() {
        GetMajorListAsyncTask asyncTask=new GetMajorListAsyncTask(MajorProspect.this,majorListView);
        asyncTask.execute();
    }






}
