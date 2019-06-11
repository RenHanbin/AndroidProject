package com.example.celia.demo1.my;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.celia.demo1.ContextData;
import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.UserBean;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class MySetUp extends AppCompatActivity {
    private UserBean user;
    private int userId;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_setup);
        final ContextData data= (ContextData) getApplication();
        userId=data.getUserId();
        initData();
        //点击“<”，返回上一级界面我的my
        ImageView Ireturn=findViewById(R.id.iv_return);
        Ireturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    class GetMajorListAsyncTask5 extends AsyncTask {
        private Context mContext5;

        public GetMajorListAsyncTask5(Context mContext5) {
            this.mContext5 = mContext5;
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
                user.setUserTel(object1.getString("userTel"));
                user.setUserEmail(object1.getString("userEmail"));
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
            ImageView imageView=findViewById(R.id.my_setup_password);
            TextView textView1=findViewById(R.id.setup_user_tel);
            TextView textView2=findViewById(R.id.setup_user_email);
            TextView textView3=findViewById(R.id.setup_user_password);
            textView1.setText(user.getUserTel());
            textView2.setText(user.getUserEmail());
            textView3.setText(user.getUserPassword());

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    intent.setClass(getApplicationContext(),MySetUpThree.class);
                    startActivityForResult(intent,1);
                }
            });

        }
    }

    protected void onActivityResult(int requestCode,//与上面的requestCode的数值相同，
                                    int resultCode, //被关闭的跳转Activity的方法 setResult(2,intent);的数值
                                    @Nullable Intent data) {
        String str=data.getStringExtra("password");
        TextView textView3=findViewById(R.id.setup_user_password);
        textView3.setText(str);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initData() {
        GetMajorListAsyncTask5 asyncTask=new GetMajorListAsyncTask5(MySetUp.this);
        asyncTask.execute();
    }
}
