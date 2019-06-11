package com.example.celia.demo1.my;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.celia.demo1.ContextData;
import com.example.celia.demo1.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.celia.demo1.bean.UserBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MySetUpThree extends AppCompatActivity{
    private EditText change;
    private UserBean user;
    private String password;
    private int userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my3_setup_password);
        final ContextData data= (ContextData) getApplication();
        userId=data.getUserId();
        initData();
        ImageView error=findViewById(R.id.error);
        error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password=change.getText().toString();
                Intent intent=getIntent();
                intent.putExtra("password",password);
                setResult(2,intent);
                finish();
            }
        });

        ImageView right=findViewById(R.id.right);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password=change.getText().toString();
                GetMajorListAsyncTask66 asyncTask1=new GetMajorListAsyncTask66(MySetUpThree.this);
                asyncTask1.execute();
                Intent intent=getIntent();
                intent.putExtra("password",password);
                setResult(2,intent);
                finish();
            }
        });
    }

    class GetMajorListAsyncTask6 extends AsyncTask {
        private Context mContext6;

        public GetMajorListAsyncTask6(Context mContext6) {
            this.mContext6 = mContext6;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            String path=getResources().getString(R.string.url_path);

            String urlStr = path+"SetUpUserServlet?remark=setUpUser&userId="+userId;
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                String str = reader.readLine();
                Log.e("test", str);
                JSONObject object1 = new JSONObject(str);
                user=new UserBean();
                user.setUserPassword(object1.getString("userPassword"));
                Log.e("test", user.toString());
                return user;
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
        protected void onPostExecute(Object o) {
            Log.e("test","已经进行到异步类的显示阶段");

            change=findViewById(R.id.et_change);
            change.setText(user.getUserPassword());
        }
    }

    class GetMajorListAsyncTask66 extends AsyncTask {
        private Context mContext66;

        public GetMajorListAsyncTask66(Context mContext66) {
            this.mContext66 = mContext66;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            String path=getResources().getString(R.string.url_path);

            String urlStr = path+"SetUpUserServlet?remark=setUpUserPassword&userId="+userId+"&userPassword="+password;
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                String str = reader.readLine();
                Log.e("test", str);
                JSONObject object1 = new JSONObject(str);
                user=new UserBean();
                user.setUserPassword(object1.getString("userPassword"));
                Log.e("test", user.toString());
                return user;
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
        protected void onPostExecute(Object o) {
            Log.e("test","已经进行到异步类的显示阶段");

            change=findViewById(R.id.et_change);
            change.setText(user.getUserPassword());

        }
    }

    private void initData() {
        GetMajorListAsyncTask6 asyncTask=new GetMajorListAsyncTask6(MySetUpThree.this);
        asyncTask.execute();
    }

}
