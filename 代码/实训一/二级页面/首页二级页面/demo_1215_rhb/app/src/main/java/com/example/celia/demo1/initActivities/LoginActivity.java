package com.example.celia.demo1.initActivities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celia.demo1.ContextData;
import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.UserBean;
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

public class LoginActivity extends AppCompatActivity {

    private EditText userNameEt;
    private EditText userPasswordEt;
    private String userName;
    private String userPassword;
    public UserBean user;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        userNameEt=findViewById(R.id.et_userName);
        userPasswordEt=findViewById(R.id.et_userPwd);
        //点击登录按钮的时候发生的操作
        Button loginButton=findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                //获取用户输入的用户名和密码
                userName=userNameEt.getText().toString();
                userPassword=userPasswordEt.getText().toString();
                Log.e("test:userName",userName);
                Log.e("test:userPassword",userPassword);
                if("".equals(userName)||"".equals(userPassword)){
                    Toast.makeText(getApplicationContext(),"用户名或者密码为空！",Toast.LENGTH_LONG).show();
                    return;
                }
                LoginTest loginTest=new LoginTest(LoginActivity.this);
                loginTest.execute();

            }
        });
        TextView registerText=findViewById(R.id.tv_register);
        TextView findPasswordText=findViewById(R.id.tv_findPassword);
        //跳转到注册界面
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToRegister=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intentToRegister);
            }
        });
        //跳转到找回密码界面
        findPasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTofindPwd=new Intent(LoginActivity.this,FindPasswordActivity.class);
                startActivity(intentTofindPwd);
            }
        });




    }
    //采用异步任务
    private class LoginTest extends AsyncTask<String,Void,UserBean> {

        private Context mContext;

        protected LoginTest(Context context){
            mContext=context;
        }
        @Override
        protected UserBean doInBackground(String... strings) {
            try {
                String path=getResources().getString(R.string.url_path);
                URL url=new URL(path+"LoginTestServlet?userName="+ userName
                        +"&userPassword="+userPassword);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType","utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream=connection.getInputStream();
                //字节流转字符流
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//转换流
                BufferedReader reader=new BufferedReader(inputStreamReader);//字符流
                String str=reader.readLine();
                Log.e("test",str);
                if(!"".equals(str)){
                    //用户登录成功
                    Log.e("test","用户登录成功");
                    JSONObject object=new JSONObject(str);
                    String userStr=object.get("user").toString();
                    user=new Gson().fromJson(userStr,UserBean.class);
                    return user;
                    //Toast.makeText(getApplicationContext(),"登陆成功",Toast.LENGTH_LONG).show();
                }else{
                    Log.e("test","用户登录不成功");
                    return null;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return user;
        }

        @Override
        protected void onPostExecute(UserBean result) {
            if(result!=null){
                Toast.makeText(mContext,"登陆成功",Toast.LENGTH_LONG).show();
                //把登录的user信息传给我的
                final ContextData data= (ContextData) getApplication();
                data.setUserId(user.getUserId());
                Intent intentToMy=new Intent();
                intentToMy.setClass(LoginActivity.this, TabHostActivity.class);
                startActivity(intentToMy);
            }else{
                Toast.makeText(mContext,"登陆失败，请重新登录",Toast.LENGTH_LONG).show();
            }

        }
    }

}
