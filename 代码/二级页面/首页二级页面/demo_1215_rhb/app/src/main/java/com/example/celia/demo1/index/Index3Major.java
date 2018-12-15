package com.example.celia.demo1.index;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celia.demo1.ContextData;
import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.UserBean;
import com.example.celia.demo1.initActivities.LoginActivity;
import com.example.celia.demo1.initActivities.RegisterActivity;
import com.example.celia.demo1.zixun.TabHostActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Index3Major extends AppCompatActivity {

    private Boolean flag=false;
    private ImageView collection;
    private int majorId;
    private int userId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index3_major);
        final ContextData data= (ContextData) getApplication();
        userId=data.getUserId();
        Log.e("userId",userId+"");
        //显示内容
        Intent intent=getIntent();
        TextView majorName=findViewById(R.id.tv_majorName);
        TextView majorName1=findViewById(R.id.tv_major_name);
        TextView majorType=findViewById(R.id.tv_main_major);
        TextView majorInf=findViewById(R.id.tv_info);
        TextView majorSubject=findViewById(R.id.tv_subjects);
        TextView majorWork=findViewById(R.id.tv_derection);
        majorId=intent.getIntExtra("majorId",0);
        majorName.setText(intent.getStringExtra("majorName"));
        majorName1.setText(intent.getStringExtra("majorName"));
        majorInf.setText(intent.getStringExtra("majorInf"));
        majorSubject.setText(intent.getStringExtra("majorSubject"));
        majorType.setText(intent.getStringExtra("majorTypeName"));
        majorWork.setText(intent.getStringExtra("majorWork"));

        //点击返回按钮返回
        LinearLayout linearLayout=findViewById(R.id.ll_return1);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //收藏
        collection=findViewById(R.id.iv_collection);
        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==false){
                    collection.setImageResource(R.drawable.dianzan2);
                    flag=true;
                    //向数据库中加入收藏的专业
                    AddCollectionAsyncTask asyncTask=new AddCollectionAsyncTask(Index3Major.this);
                    asyncTask.execute();
                }else{
                    collection.setImageResource(R.drawable.dianzan);
                    flag=false;
                    //取消收藏专业
                    DeleteCollectionAsyncTask deleteAsyncTask=new DeleteCollectionAsyncTask(Index3Major.this);
                    deleteAsyncTask.execute();
                }
            }
        });

    }

    //添加收藏专业的异步类
    private class AddCollectionAsyncTask extends AsyncTask<String,Void,Boolean>{

        private String success;
        private String falseStr;
        private Boolean b=false;
        private Context mContext;
        protected AddCollectionAsyncTask(Context context){
            mContext=context;
        }
        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                String path=getResources().getString(R.string.url_path);
                URL url=new URL(path+"CollectionMajorServlet?remark=addMajorCollection&majorId="+ majorId
                        +"&userId="+userId);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType","utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream=connection.getInputStream();
                //字节流转字符流
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//转换流
                BufferedReader reader=new BufferedReader(inputStreamReader);//字符流
                String str=reader.readLine();
                Log.e("test",str);
                JSONObject object=new JSONObject(str);
                if(object.getString("success")!=null){
                    success=object.getString("success");
                    Log.e("test",success);
                    b=true;
                    return b;
                }else if(object.getString("false")!=null){
                    falseStr=object.getString("false");
                    Log.e("test",falseStr);
                    b=false;
                    return b;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
          return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean){
                //收藏成功
                Toast.makeText(mContext,"收藏成功",Toast.LENGTH_LONG).show();
            }else{
                //注册失败
                Toast.makeText(mContext,"收藏失败",Toast.LENGTH_LONG).show();
            }
        }
    }

    //取消收藏专业

    private class DeleteCollectionAsyncTask extends AsyncTask<String,Void,Boolean>{

        private String success;
        private String falseStr;
        private Boolean b=false;
        private Context mContext;

        protected DeleteCollectionAsyncTask(Context context){
            mContext=context;
        }
        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                String path=getResources().getString(R.string.url_path);
                URL url=new URL(path+"CollectionMajorServlet?remark=deleteMajorCollection&majorId="+ majorId
                        +"&userId="+userId);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType","utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream=connection.getInputStream();
                //字节流转字符流
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//转换流
                BufferedReader reader=new BufferedReader(inputStreamReader);//字符流
                String str=reader.readLine();
                Log.e("test",str);
                JSONObject object=new JSONObject(str);
                if(object.getString("success")!=null){
                    success=object.getString("success");
                    Log.e("test",success);
                    b=true;
                    return b;
                }else if(object.getString("false")!=null){
                    falseStr=object.getString("false");
                    Log.e("test",falseStr);
                    b=false;
                    return b;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }


        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean){
                //收藏成功
                Toast.makeText(mContext,"取消收藏成功",Toast.LENGTH_LONG).show();
            }else{
                //注册失败
                Toast.makeText(mContext,"取消收藏失败",Toast.LENGTH_LONG).show();
            }
        }
    }



}
