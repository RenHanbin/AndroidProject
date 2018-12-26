package com.example.celia.demo1.zixun;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celia.demo1.ContextData;
import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.Writer;
import com.example.celia.demo1.my.MyTopic;
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

public class ZiXun3 extends AppCompatActivity {
    private int articleId;
    private Boolean flag=false;
    private ImageView collection;
    private TextView text;
    private int userId;
    private String page;//上一级跳转页面的标志位
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.zixun3);
        final ContextData data= (ContextData) getApplication();
        userId=data.getUserId();
        Log.e("userId",userId+"");
        Intent intent=getIntent();
        articleId=intent.getIntExtra("articleId",0);
        //TextView articleTitle=findViewById(R.id.tv_articleTitle);
        TextView articleTitle1=findViewById(R.id.tv_article_title);
        TextView articleTime=findViewById(R.id.tv_articleTime);
        TextView articleContent=findViewById(R.id.tv_articleContent);
        ImageView writerImg=findViewById(R.id.writerImg);
        TextView writerName=findViewById(R.id.tv_writerName);
        text=findViewById(R.id.tv_collection_text);
        page=intent.getStringExtra("page");
        //articleTitle.setText(intent.getStringExtra("articleTitle"));
        articleTitle1.setText(intent.getStringExtra("articleTitle"));
        articleContent.setText(intent.getStringExtra("articleContent"));
        articleTime.setText(intent.getStringExtra("articleTime"));
        Writer writer= (Writer) intent.getSerializableExtra("writer");
        writerName.setText(writer.getWriterName());
        ShowImgAsyncTask showImg=new ShowImgAsyncTask(writer.getWriterImg(),writerImg);
        showImg.execute();
        //点击返回按钮返回
        LinearLayout linearLayout=findViewById(R.id.ll_return1);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("MyTopic".equals(page)){
                    Intent backIntent=new Intent();
                    backIntent.setClass(ZiXun3.this, MyTopic.class);
                    startActivity(backIntent);
                    finish();
                }else{
                    finish();
                }

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
                    text.setText("已收藏");
                    flag=true;
                    //向数据库中加入收藏的文章
                    AddCollectionAsyncTask asyncTask=new AddCollectionAsyncTask(ZiXun3.this);
                    asyncTask.execute();
                }else{
                    collection.setImageResource(R.drawable.dianzan);
                    text.setText("添加收藏");
                    flag=false;
                    //取消收藏
                    DeleteCollectionAsyncTask deleteAsyncTask=new DeleteCollectionAsyncTask(ZiXun3.this);
                    deleteAsyncTask.execute();
                }
            }
        });
    }

    //判断是否收藏过的异步类
    private class IfCollectionedAsyncTask extends AsyncTask<String,Void,Boolean> {

        private String success;
        private String falseStr;
        private Boolean b=false;
        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                String path=getResources().getString(R.string.url_path);
                URL url=new URL(path+"CollectionArticleServlet?remark=ifExistCollectionArticle&articleId="+ articleId
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
                text.setText("已收藏");
            }else{
                //未收藏
                flag=false;

            }
        }
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
                URL url=new URL(path+"CollectionArticleServlet?remark=addArticleCollection&articleId="+ articleId
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
                Toast.makeText(mContext,"收藏成功",Toast.LENGTH_SHORT).show();
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
                URL url=new URL(path+"CollectionArticleServlet?remark=deleteArticleCollection&articleId="+ articleId
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
                Toast.makeText(mContext,"取消收藏成功",Toast.LENGTH_SHORT).show();
            }else{
                //注册失败
                Toast.makeText(mContext,"取消收藏失败",Toast.LENGTH_LONG).show();
            }
        }
    }
}
