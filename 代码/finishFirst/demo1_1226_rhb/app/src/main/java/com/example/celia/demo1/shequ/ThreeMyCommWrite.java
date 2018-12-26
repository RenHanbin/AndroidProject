package com.example.celia.demo1.shequ;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.celia.demo1.ContextData;
import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.Answer;

import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ThreeMyCommWrite extends AppCompatActivity {

    /*
     * 写问题回答的具体页面
     * */

    private TextView submit;//发布的tv
    private TextView questionTitle;//问题的title，由上一级跳转页面传来
    private EditText answerContent;//回答的内容
    private String answer;
    private int userId;
    private boolean flag=true;
    private int questionId;//回答的问题id，由上一级跳转页面传来

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_three_comm_write);

        submit=findViewById(R.id.tv_submit);
        answerContent=findViewById(R.id.answerContent);
        final ContextData data= (ContextData) getApplication();
        userId=data.getUserId();
        Log.e("userId",userId+"");

        questionTitle=findViewById(R.id.tv_questionTitle);
        Intent intent = getIntent();
        questionTitle.setText(intent.getStringExtra("questionTitle"));
        questionId=intent.getIntExtra("questionId",1);

        //返回我的评论二级界面
        ImageView error=findViewById(R.id.error);
        error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer=answerContent.getText().toString();
                SubmitAnswerAsyncTask asyncTask=new SubmitAnswerAsyncTask(ThreeMyCommWrite.this);
                asyncTask.execute();
            }
        });


    }
    //获取answer
    private class answerAsyncTask extends AsyncTask<String,Void,Answer>{

        private Context mContext;

        protected answerAsyncTask(Context context){
            mContext=context;
        }
        @Override
        protected Answer doInBackground(String... strings) {
            try {
                String path=getResources().getString(R.string.url_path);
                URL url=new URL(path+"AnswerServlet?remark=Answer&questionId="+ questionId
                        +"&userId="+userId);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType","utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream=connection.getInputStream();
                //字节流转字符流
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//转换流
                BufferedReader reader=new BufferedReader(inputStreamReader);//字符流
                String str=reader.readLine();
                Log.e("test",str);
                JSONObject object = new JSONObject(str);
                Answer answer = new Answer();
                answer.setAnswerId(object.getInt("answerId"));
                answer.setQuestionId(object.getInt("questionId"));
                answer.setUserId(object.getInt("userId"));
                answer.setAnswerContent(object.getString("answerContent"));
                answer.setAnswerTime(object.getString("answerTime"));
                answer.setAnswerUserName(object.getString("answerUserName"));
                answer.setAnswerUserImg(object.getString("answerUserImg"));
                return answer;
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
        protected void onPostExecute(Answer answer) {
            Intent intent=new Intent();
            intent.setClass(mContext,ShequQuestion.class);
            intent.putExtra("questionId",questionId);
            intent.putExtra("userId",userId);
            intent.putExtra("answerId",answer.getAnswerId());
            intent.putExtra("answerContent",answer.getAnswerContent());
            intent.putExtra("answerTime",answer.getAnswerTime());
            intent.putExtra("answerUserName",answer.getAnswerUserName());
            intent.putExtra("answerUserImg",answer.getAnswerUserImg());
            setResult(2,intent);
            finish();
        }
    }

    //发表回答的异步类
    private class SubmitAnswerAsyncTask extends AsyncTask<String,Void,Boolean>{

        private String success;
        private String falseStr;
        private Boolean b=false;
        private Context mContext;

        protected SubmitAnswerAsyncTask(Context context){
            mContext=context;
        }
        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                String path=getResources().getString(R.string.url_path);
                URL url=new URL(path+"AnswerServlet?remark=addAnswer&questionId="+ questionId
                        +"&userId="+userId+"&answerContent="+answer);
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
                //发布成功
                Toast.makeText(mContext,"发布成功",Toast.LENGTH_LONG).show();
                //发布成功之后跳转
                answerAsyncTask task = new answerAsyncTask(ThreeMyCommWrite.this);
                task.execute();
                //startActivity(intent);

            }else{
                //发布失败
                Toast.makeText(mContext,"发布失败",Toast.LENGTH_LONG).show();
            }
        }
    }
}
