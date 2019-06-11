package com.example.celia.demo1.initActivities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.UserBean;
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
import java.util.List;

public class FindPasswordActivity extends AppCompatActivity {
    Button btnFindPwd;
    String userTel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.findpassword_layout);
        //给用户发送短信
        btnFindPwd=findViewById(R.id.btn_sendMessage);
        btnFindPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户输入的电话号码
                EditText userTelEt=findViewById(R.id.et_userTel);
                userTel=userTelEt.getText().toString();
                Log.e("test",userTel);
                GetPasswordAsyncTask getPasswordAsyncTask=new GetPasswordAsyncTask(FindPasswordActivity.this);
                getPasswordAsyncTask.execute();

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

    //获得密码的异步类
    private class GetPasswordAsyncTask extends AsyncTask<String,Void,String>{

        private Context mContext;
        protected GetPasswordAsyncTask(Context context){
            mContext=context;
        }
        @Override
        protected String doInBackground(String... strings) {
            try {
                String path=getResources().getString(R.string.url_path);
                URL url=new URL(path+"FindPasswordServlet?userTel="+
                        userTel);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType","utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream=connection.getInputStream();
                //字节流转字符流
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//转换流
                BufferedReader reader=new BufferedReader(inputStreamReader);//字符流
                String str=reader.readLine();
                Log.e("test",str);
                if(!"".equals(str)){
                    //找回密码成功
                    JSONObject object=new JSONObject(str);
                    String userPassword=object.getString("userPwd");
                    Log.e("test",userPassword);
                    return userPassword;
                    //Toast.makeText(getApplicationContext(),"登陆成功",Toast.LENGTH_LONG).show();
                }else{
                    //找回密码失败
                    return null;
                }

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
        protected void onPostExecute(String messagePwd) {
            //通过数据库查询获得该用户对应的密码
            SmsManager smsManager=SmsManager.getDefault();
            List<String> list=smsManager.divideMessage("您的密码为："+messagePwd);
            for (String text:list){
                smsManager.sendTextMessage(userTel,null,text,null,null);

            }
        }
    }
}
