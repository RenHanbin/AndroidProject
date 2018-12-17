package com.example.celia.demo1.my;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.User;
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

public class MyUser extends AppCompatActivity {
    private User user;
    private ImageView myUserImage;
    private EditText myUserName;
    private EditText myUserSex;
    private EditText myUserPhone;
    private EditText myUserEmail;
    private String name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_user);
        initDate();
        //点击“×”，返回上一级界面我的my
        ImageView IvError=findViewById(R.id.iv_error);
        IvError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //点击“√”，完成信息的修改
        ImageView IvRight = findViewById(R.id.iv_right);
        IvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建异步任务类，把数据传过去
                name = myUserName.getText().toString();
                String sex= myUserSex.getText().toString();
                String phone = myUserPhone.getText().toString();
                String email = myUserEmail.getText().toString();
                ChangeMyUerAsyncTask asyncTask = new ChangeMyUerAsyncTask(MyUser.this,name,sex,phone,email);
                asyncTask.execute();

                finish();
            }
        });
    }

    private void initDate() {
        GetMyUerAsyncTask asyncTask = new GetMyUerAsyncTask(MyUser.this);
        asyncTask.execute();
    }

    public class GetMyUerAsyncTask extends AsyncTask {
        private Context mContext;
        //构造函数，接收更新的视图控件
        public GetMyUerAsyncTask(Context mContext){
            this.mContext=mContext;
        }
        @Override
        protected Object doInBackground(Object[] objects) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"UserServlet?remark=getUser";
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //设置请求参数，因为传过去的东西有中文，避免乱码
                connection.setRequestProperty("contentType", "UTF-8");
                //通过流取数据
                InputStream in = connection.getInputStream();
                //返回数据为中文,字节流与字符流转换
                InputStreamReader inputStreamReader = new InputStreamReader(in);//得到字符流
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String res = reader.readLine();//读一行

                //解析JSON格式字符串
                JSONObject object = new JSONObject(res);
                //从对象中取数据，放入user对象
                user = new User();
                user.setUserId(object.getInt("userId"));
                myUserImage = findViewById(R.id.my_user_image);
                user.setUserImage(object.getString("userImage"));
                new ShowImgAsyncTask(user.getUserImage(),myUserImage).execute();
                user.setUserName(object.getString("userName"));
                user.setUserSex(object.getString("userSex"));
                user.setUserTel(object.getString("userTel"));
                user.setUserEmail(object.getString("userEmail"));
                return user;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Object o) {
            myUserName = findViewById(R.id.my_user_name);
            myUserSex = findViewById(R.id.my_user_sex);
            myUserPhone= findViewById(R.id.my_user_phone);
            myUserEmail = findViewById(R.id.my_user_email);
            myUserName.setText(user.getUserName());
            myUserSex.setText(user.getUserSex());
            myUserPhone.setText(user.getUserTel());
            myUserEmail.setText(user.getUserEmail());
            Log.e("MyUser", user.toString());
        }
    }


    //修改资料异步任务类
    public class ChangeMyUerAsyncTask extends AsyncTask {
        private Context mContext;
        private String name;
        private String sex;
        private String phone;
        private String email;
        //构造函数，接收更新的视图控件
        public ChangeMyUerAsyncTask(Context mContext,String name,String sex,String phone,String email){
            this.mContext=mContext;
            this.name=name;
            this.sex=sex;
            this.phone=phone;
            this.email=email;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"UserServlet?remark=changeUser&name="+name+"&sex="+sex+"&phone="+phone+"&email="+email;
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //设置请求参数，因为传过去的东西有中文，避免乱码
                connection.setRequestProperty("contentType", "UTF-8");
                //通过流取数据
                InputStream in = connection.getInputStream();
                //返回数据为中文,字节流与字符流转换
                InputStreamReader inputStreamReader = new InputStreamReader(in);//得到字符流
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String res = reader.readLine();//读一行

                //解析JSON格式字符串
                JSONObject object = new JSONObject(res);
                //从对象中取数据，放入user对象
                user = new User();
                user.setUserId(object.getInt("userId"));
                myUserImage = findViewById(R.id.my_user_image);
                user.setUserImage(object.getString("userImage"));
                new ShowImgAsyncTask(user.getUserImage(),myUserImage).execute();
                user.setUserName(object.getString("userName"));
                user.setUserSex(object.getString("userSex"));
                user.setUserTel(object.getString("userTel"));
                user.setUserEmail(object.getString("userEmail"));
                return user;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Object o) {
            myUserName = findViewById(R.id.my_user_name);
            myUserSex = findViewById(R.id.my_user_sex);
            myUserPhone= findViewById(R.id.my_user_phone);
            myUserEmail = findViewById(R.id.my_user_email);
            myUserName.setText(user.getUserName());
            myUserSex.setText(user.getUserSex());
            myUserPhone.setText(user.getUserTel());
            myUserEmail.setText(user.getUserEmail());
//            Intent intent = getIntent();
//            intent.putExtra("userName",name);
//            Log.e("test:userName",name);
//            setResult(2,intent);
            Log.e("MyUser", user.toString());
        }
    }

}
