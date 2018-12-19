package com.example.celia.demo1.my;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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


public class ThreeMyQuestWrite extends AppCompatActivity {
    private String questionTitle;
    private String questionDiscribe;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_three_quest_write);
        //返回我的二级界面
        ImageView error=findViewById(R.id.error);
        error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //发布问题
        TextView fabuQuestion=findViewById(R.id.fabu_question);
        fabuQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etquestionTitle=findViewById(R.id.question_title);
                EditText etquestionDiscribe=findViewById(R.id.question_content);
                questionTitle= String.valueOf(etquestionTitle.getText());
                Log.e("test",questionTitle);
                questionDiscribe= String.valueOf(etquestionDiscribe.getText());
                Log.e("test",questionDiscribe);
                AddQuestionAsyncTask addQuestionAsyncTask=new AddQuestionAsyncTask(ThreeMyQuestWrite.this);
                addQuestionAsyncTask.execute();
            }
        });
    }

    private class AddQuestionAsyncTask extends AsyncTask<String,Void,Boolean> {

        private Context mContext;
        protected AddQuestionAsyncTask(Context context){
            mContext=context;
        }
        @Override
        protected Boolean doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"QuestionServlet?remark=addQuestion&userId=1&questionTitle="+questionTitle+"&questionDiscribe="+questionDiscribe;
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
                if(object1.getString("success")!=null){
                    return true;
                }else{
                    return false;
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
                Toast.makeText(mContext,"添加成功",Toast.LENGTH_LONG).show();
            }else{
                //注册失败
                Toast.makeText(mContext,"添加失败",Toast.LENGTH_LONG).show();
            }
        }
    }

}
