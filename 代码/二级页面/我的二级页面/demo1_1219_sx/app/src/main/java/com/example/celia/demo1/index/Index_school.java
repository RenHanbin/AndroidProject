package com.example.celia.demo1.index;

import android.content.Context;
import android.content.Intent;

import com.example.celia.demo1.ContextData;
import com.example.celia.demo1.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celia.demo1.showImgAsyncTask.ShowImgAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Index_school extends AppCompatActivity{

    private int userId;
    private int schoolId;
    private Boolean flag=false;
    private ImageView collection;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.index3_school);
        final ContextData data= (ContextData) getApplication();
        userId=data.getUserId();
        Log.e("userId",userId+"");
        Intent intent = getIntent();
        schoolId=intent.getIntExtra("schoolId",0);
        ImageView img = findViewById(R.id.iv_school_img);
        TextView name = findViewById(R.id.tv_school_name);
        TextView num = findViewById(R.id.tv_school_num);
        TextView content = findViewById(R.id.tv_school_content);
        TextView major = findViewById(R.id.tv_school_major);
        ShowImgAsyncTask showImg=new ShowImgAsyncTask(intent.getStringExtra("schoolImg"),img);
        showImg.execute();
        TextView type = findViewById(R.id.tv_type);
        type.setText(intent.getStringExtra("schoolType"));
        name.setText(intent.getStringExtra("schoolName"));
        num.setText(intent.getStringExtra("schoolNum"));
        content.setText(intent.getStringExtra("schoolContent"));
        major.setText(intent.getStringExtra("schoolBestMajor"));

        //点击返回按钮返回
        LinearLayout linearLayout=findViewById(R.id.ll_return1);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        //判断是否收藏过
        IfCollectionedAsyncTask asyncTasked=new IfCollectionedAsyncTask();
        asyncTasked.execute();
        //收藏
        collection=findViewById(R.id.iv_collection);
        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==false){
                    collection.setImageResource(R.drawable.dianzan2);
                    flag=true;
                    //向数据库中加入收藏的专业
                    AddSchoolCollectionAsyncTask asyncTask1=new AddSchoolCollectionAsyncTask(Index_school.this);
                    asyncTask1.execute();
                }else{
                    collection.setImageResource(R.drawable.dianzan);
                    flag=false;
                    //取消收藏专业
                    DeleteSchoolCollectionAsyncTask asyncTask1=new DeleteSchoolCollectionAsyncTask(Index_school.this);
                    asyncTask1.execute();
                }
            }
        });




    }


    //判断是否收藏过的异步类
    private class IfCollectionedAsyncTask extends AsyncTask<String,Void,Boolean>{

        private String success;
        private String falseStr;
        private Boolean b=false;
        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                String path=getResources().getString(R.string.url_path);
                URL url=new URL(path+"CollectionSchoolServlet?remark=ifExistCollectionSchool&schoolId="+ schoolId
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
                //已经收藏过
                flag=true;
                collection.setImageResource(R.drawable.dianzan2);
            }else{
                //未收藏
                flag=false;

            }
        }
    }
    //收藏学校的异步类

    private class AddSchoolCollectionAsyncTask extends AsyncTask<String,Void,Boolean>{

        private String success;
        private String falseStr;
        private Boolean b=false;
        private Context mContext;

        protected AddSchoolCollectionAsyncTask(Context context){
            mContext=context;
        }
        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                String path=getResources().getString(R.string.url_path);
                URL url=new URL(path+"CollectionSchoolServlet?remark=addSchoolCollection&schoolId="+ schoolId
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

    //取消收藏学校的异步类

    private class DeleteSchoolCollectionAsyncTask extends AsyncTask<String,Void,Boolean>{

        private String success;
        private String falseStr;
        private Boolean b=false;
        private Context mContext;

        protected DeleteSchoolCollectionAsyncTask (Context context){
            mContext=context;
        }
        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                String path=getResources().getString(R.string.url_path);
                URL url=new URL(path+"CollectionSchoolServlet?remark=deleteSchoolCollection&schoolId="+ schoolId
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
