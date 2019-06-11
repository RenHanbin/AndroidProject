package com.example.celia.demo1.initActivities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.celia.demo1.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {
    private String userName;
    private String userPwd;
    private String userTel;
    private String userEmial;
    private EditText userNameEt;
    private EditText userPwdEt;
    private EditText userTelEt;
    private EditText userEmailEt;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register_layout);
        userNameEt=findViewById(R.id.et_userName);
        userPwdEt=findViewById(R.id.et_userPwd);
        userTelEt=findViewById(R.id.et_userTel);
        userEmailEt=findViewById(R.id.et_userEmail);
        Button register=findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName=userNameEt.getText().toString();
                userPwd=userPwdEt.getText().toString();
                userTel=userTelEt.getText().toString();
                userEmial=userEmailEt.getText().toString();
                Log.e("test",userPwd);
                RegisterAsyncTask registerAsyncTask=new RegisterAsyncTask(RegisterActivity.this);
                registerAsyncTask.execute();
            }
        });
        //点击返回按钮返回
        LinearLayout linearLayout=findViewById(R.id.ll_return1);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    //注册异步类
    private class RegisterAsyncTask extends AsyncTask<String,Void,Boolean> {
        private Context mContext;
        private String success;
        private String falseStr;
        private String false2;
        private Boolean b;
        protected RegisterAsyncTask(Context context){
            mContext=context;
        }
        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                String path=getResources().getString(R.string.url_path);
                URL url=new URL(path+"RegisterServlet?userName="+ userName
                        +"&userPwd="+userPwd+"&userTel="+userTel+"&userEmail="+userEmial);
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
                }else if(object.getString("false2")!=null){
                    false2=object.getString("false2");
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
                //注册成功
                Toast.makeText(mContext,"注册成功",Toast.LENGTH_SHORT).show();
                Intent intentToLogin=new Intent();
                intentToLogin.setClass(RegisterActivity.this,LoginActivity.class);
                startActivity(intentToLogin);
            }else{
                //注册失败
                if(!"".equals(false2)){
                    Toast.makeText(mContext,"注册失败！该手机号已注册！",Toast.LENGTH_LONG).show();
                }else if(!"".equals(falseStr)){
                    Toast.makeText(mContext,"注册失败",Toast.LENGTH_LONG).show();
                }

            }
        }
    }
}
