package com.example.celia.demo1.my;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.Quest;
import com.example.celia.demo1.bean.User;

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
import java.util.List;

public class MyWallet extends AppCompatActivity {
    private TextView textView;
    private User user;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_wallet);

        initDate();

        //点击“<”，返回上一级界面我的my
        ImageView Ireturn=findViewById(R.id.iv_return);
        Ireturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initDate() {
        ShowUserBalanceAsyncTask asyncTask = new ShowUserBalanceAsyncTask(MyWallet.this);
        asyncTask.execute();
    }

    class ShowUserBalanceAsyncTask extends AsyncTask{
        private Context wContext;
        public ShowUserBalanceAsyncTask(Context wContext){
            this.wContext = wContext;
        }
        @Override
        protected Object doInBackground(Object[] objects) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"UserServlet?remark=getUserBalance";
            try {
                URL url = new URL(urlStr);
                Log.e("ttst1","asdfghsfdgfg");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                Log.e("ttst2","asdfghsfdgfg");
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                Log.e("ttst3","asdfghsfdgfg");
                InputStream inputStream = connection.getInputStream();
                //字节流转字符流
                Log.e("ttst4","asdfghsfdgfg");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                Log.e("ttst5","asdfghsfdgfg");
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                Log.e("ttst6","asdfghsfdgfg");
                String str = reader.readLine();
                Log.e("test1", str);
                //解析jsonarray
                JSONObject object = new JSONObject(str);
                user = new User();
                user.setUserId(object.getInt("userId"));
                user.setUserBalance(object.getString("userBalance"));
                Log.e("test",user+"");
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
        protected void onPostExecute(Object o){
            textView=findViewById(R.id.tv_my_wallet);
            textView.setText(user.getUserBalance());
            Log.e("test",user.getUserBalance());
        }
    }
}
