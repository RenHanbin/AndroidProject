package com.example.celia.demo1.shequ;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.celia.demo1.ContextData;
import com.example.celia.demo1.bean.Answer;
import com.example.celia.demo1.bean.Article;
import com.example.celia.demo1.bean.Question;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.celia.demo1.R;
import com.example.celia.demo1.my.MyTopic;
import com.example.celia.demo1.showImgAsyncTask.ShowImgAsyncTask;


public class ShequQuestion extends AppCompatActivity {
    private int questionId;
    private int followedUserId;
    private Question question;
    private String shoucangStr;
    private ListView listView;
    private List<Answer> answerList;
    private CustomAdapter2 adapter2;
    //收藏
    private ImageView ivshoucang;
    private TextView tvshoucang;
    private Boolean flag=false;//收藏的标志位
    //关注
    private ImageView ivguanzhu;
    private TextView tvguanzhu;
    private Boolean flag1=false;//关注的标志位

    private int userId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.shequ_question);
        final ContextData data= (ContextData) getApplication();
        userId=data.getUserId();
        Log.e("userId",userId+"");

        final Intent intent = getIntent();
        questionId= intent.getIntExtra("questionId",1);

        initData1();


        listView=findViewById(R.id.lv_list_shequ_question);
        initData2();
        //返回社区
        ImageView Ireturn=findViewById(R.id.iv_return);
        Ireturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent();
                intent1.setClass(getApplicationContext(), MyTopic.class);
                startActivity(intent1);
            }
        });
        //搜索框
        ImageView search=findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShequQuestion.this,"搜索内容",Toast.LENGTH_SHORT).show();
            }
        });
        //写回答
        ImageView writeQuestion=findViewById(R.id.write_question);
        writeQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShequQuestion.this,"写回答",Toast.LENGTH_SHORT).show();
            }
        });

        //判断是否收藏过
        IfCollectionedAsyncTask asyncTasked=new IfCollectionedAsyncTask();
        asyncTasked.execute();

        //收藏
        ivshoucang=findViewById(R.id.iv_shoucang);
        tvshoucang=findViewById(R.id.tv_shoucang);
//        shoucangStr= String.valueOf(tvshoucang.getText());
//
//        ivshoucang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if("已收藏".equals(shoucangStr)){
//                    tvshoucang.setText("未收藏");
//                    ivshoucang.setImageResource(R.drawable.weishoucang);
//                    DeleteCollectionAsyncTask delete=new DeleteCollectionAsyncTask(ShequQuestion.this);
//                    delete.execute();
//                }else if("未收藏".equals(shoucangStr)){
//                    tvshoucang.setText("已收藏");
//                    ivshoucang.setImageResource(R.drawable.yishoucang);
//                    AddCollectionAsyncTask add=new AddCollectionAsyncTask(ShequQuestion.this);
//                    add.execute();
//                }
//                shoucangStr= String.valueOf(tvshoucang.getText());
//            }
//        });

        ivshoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag==false){
                    tvshoucang.setText("已收藏");
                    ivshoucang.setImageResource(R.drawable.yishoucang);
                    flag=true;
                    AddCollectionAsyncTask add=new AddCollectionAsyncTask(ShequQuestion.this);
                    add.execute();
                }else{
                    tvshoucang.setText("未收藏");
                    ivshoucang.setImageResource(R.drawable.weishoucang);
                    flag=false;
                    DeleteCollectionAsyncTask delete=new DeleteCollectionAsyncTask(ShequQuestion.this);
                    delete.execute();

                }
                //shoucangStr= String.valueOf(tvshoucang.getText());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }


    //判断是否关注过的异步类
    private class IfFollowAsyncTask extends AsyncTask<String,Void,Boolean>{

        private String success;
        private String falseStr;
        private Boolean b=false;
        private int followedUserId1;
        private ImageView ivguanzhu1;
        private TextView tvguanzhu1;


        public IfFollowAsyncTask(int followedUserId1, ImageView ivguanzhu1, TextView tvguanzhu1) {
            this.followedUserId1 = followedUserId1;
            this.ivguanzhu1 = ivguanzhu1;
            this.tvguanzhu1 = tvguanzhu1;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr=path+"FollowServlet?remark=ifExistHuanZhu&followedUserId="+followedUserId1+"&userId="+userId;

            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType","utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream=connection.getInputStream();
                //字节流转字符流
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//转换流
                BufferedReader reader=new BufferedReader(inputStreamReader);//字符流
                String str=reader.readLine();
                Log.e("test==s",str);
//                JSONObject object=new JSONObject(str);
//                if(object.getString("success")!=null){
                if(str.indexOf("success")!=-1) {
//                    success = object.getString("success");
//                    Log.e("test", success);
                    b = true;
//                }else if(object.getString("false")!=null){
                }else{
//                    falseStr=object.getString("false");
//                    Log.e("test",falseStr);
                    b=false;
                }
                return b;
            } catch (MalformedURLException e) {
                Log.e("test",e.toString());
                e.printStackTrace();
            } catch (IOException e) {
                Log.e("test", e.toString());
                e.printStackTrace();
            }
//            } catch (JSONException e) {
//                Log.e("test",e.toString());
//                e.printStackTrace();
//            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean){
                //已经关注过
                flag1=true;
                tvguanzhu1.setText("已关注");
                ivguanzhu1.setImageResource(R.drawable.yishoucang);

            }else{
                //未关注过
                flag1=false;
                tvguanzhu1.setText("未关注");
                ivguanzhu1.setImageResource(R.drawable.weishoucang);
            }
        }
    }


    //删除关注
    private class DeleteFollowAsyncTask extends AsyncTask<String,Void,Boolean>{

        private Context mContext;
        protected DeleteFollowAsyncTask(Context context){
            mContext=context;
        }
        @Override
        protected Boolean doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr=path+"FollowServlet?remark=FollowDelete&followedUserId="+followedUserId+"&userId="+userId;
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                String str = reader.readLine();
                Log.e("test", str);
                JSONObject object=new JSONObject(str);
                if(object.getString("success")!=null){
                    Log.e("test",object.getString("success"));
                    return true;
                }else if(object.getString("fail")!=null){
                    Log.e("test",object.getString("false"));
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
                //取消关注成功
                Toast.makeText(mContext,"取消关注成功",Toast.LENGTH_LONG).show();
            }else{
                //取消关注失败
                Toast.makeText(mContext,"取消关注失败",Toast.LENGTH_LONG).show();
            }
        }
    }



    //添加关注
    private class AddFollowAsyncTask extends AsyncTask<String,Void,Boolean>{

        private Context mContext;
        protected AddFollowAsyncTask(Context context){
            mContext=context;
        }
        @Override
        protected Boolean doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr=path+"FollowServlet?remark=FollowInsert&followedUserId="+followedUserId+"&userId="+userId;
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                String str = reader.readLine();
                Log.e("test", str);
                JSONObject object=new JSONObject(str);
                if(object.getString("success")!=null){
                    Log.e("test",object.getString("success"));
                    return true;
                }else if(object.getString("fail")!=null){
                    Log.e("test",object.getString("false"));
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
                //添加关注成功
                Toast.makeText(mContext,"添加关注成功",Toast.LENGTH_LONG).show();
            }else{
                //添加关注失败
                Toast.makeText(mContext,"添加关注失败",Toast.LENGTH_LONG).show();
            }
        }
    }

    //判断是否收藏过的异步类
    private class IfCollectionedAsyncTask extends AsyncTask<String,Void,Boolean>{

        private String success;
        private String falseStr;
        private Boolean b=false;
        @Override
        protected Boolean doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr=path+"CollectionQuestionServlet?remark=ifExistCollectionQuestion&questionId="+ questionId+"&userId="+userId;

            try {
                URL url = new URL(urlStr);

//                String path=getResources().getString(R.string.url_path);
//                URL url=new URL(path+"CollectionQuestionServlet?remark=ifExistCollectionQuestion&questionId="+ questionId
//                        +"&userId="+userId);
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
                tvshoucang.setText("已收藏");
                ivshoucang.setImageResource(R.drawable.yishoucang);

            }else{
                //未收藏
                flag=false;
                tvshoucang.setText("未收藏");
                ivshoucang.setImageResource(R.drawable.weishoucang);
            }
        }
    }
    //删除收藏
    private class DeleteCollectionAsyncTask extends AsyncTask<String,Void,Boolean>{

        private Context mContext;
        protected DeleteCollectionAsyncTask(Context context){
            mContext=context;
        }
        @Override
        protected Boolean doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr=path+"CollectionQuestionServlet?remark=collectionQuestionListDelete&questionId="+questionId+"&userId="+userId;
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                String str = reader.readLine();
                Log.e("test", str);

                return true;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean){
                //取消收藏成功
                Toast.makeText(mContext,"取消收藏成功",Toast.LENGTH_LONG).show();
            }else{
                //取消收藏失败
                Toast.makeText(mContext,"取消收藏失败",Toast.LENGTH_LONG).show();
            }
        }
    }

    //添加收藏
    private class AddCollectionAsyncTask extends AsyncTask<String,Void,Boolean>{

        private Context mContext;
        protected AddCollectionAsyncTask(Context context){
            mContext=context;
        }
        @Override
        protected Boolean doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr=path+"CollectionQuestionServlet?remark=collectionQuestionListInsert&questionId="+questionId+"&userId="+userId;
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                String str = reader.readLine();
                Log.e("test", str);
                return true;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean){
                //收藏成功
                Toast.makeText(mContext,"收藏成功",Toast.LENGTH_LONG).show();
            }else{
                //收藏失败
                Toast.makeText(mContext,"收藏失败",Toast.LENGTH_LONG).show();
            }
        }
    }



    class GetMajorListAsyncTask2 extends AsyncTask<String, Void, List<Answer>> {
        private Context mContext2;
        private ListView listView2;

        public GetMajorListAsyncTask2(Context mContext2, ListView listView2) {
            this.mContext2 = mContext2;
            this.listView2 = listView2;
        }

        @Override
        protected List<Answer> doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"CollectionQuestionServlet?remark=collectionQuestionListThreeAnswer&questionId="+questionId+"&userId="+userId;
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                String str = reader.readLine();
                Log.e("test", str);
                //解析jsonarray
                JSONArray array = new JSONArray(str);
                answerList = new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Article article=new Article();
                    Answer answer=new Answer();
                    answer.setUserId(object1.getInt("followedUserId"));
                    answer.setAnswerTime(object1.getString("answerTime"));
                    answer.setAnswerContent(object1.getString("answerContent"));
                    answer.setAnswerUserName(object1.getString("answerUserName"));
                    answer.setAnswerUserImg(object1.getString("answerUserImg"));
                    answerList.add(answer);
                }
                Log.e("test", answerList.toString());
                return answerList;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(List<Answer> result) {
            Log.e("test", "已经进行到异步类的显示阶段");
            adapter2=new CustomAdapter2(mContext2,R.layout.shequ_question_listview,result);
            listView.setAdapter(adapter2);
        }
    }

    public class CustomAdapter2 extends BaseAdapter {

        private Context context;
        private int itemLayoutID;
        private List<Answer> answerList;

        public CustomAdapter2(Context context, int itemLayoutID, List<Answer> answerList) {
            this.context = context;
            this.itemLayoutID = itemLayoutID;
            this.answerList = answerList;
        }

        @Override
        public int getCount() {
            return answerList.size();
        }

        @Override
        public Object getItem(int position) {
            return answerList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                LayoutInflater inflater=LayoutInflater.from(context);
                convertView=inflater.inflate(itemLayoutID,null);
            }
            ImageView imageView1 = convertView.findViewById(R.id.shequ_question_image);
            TextView textView1 = convertView.findViewById(R.id.tv_shequ_question_name);
            TextView textView2 = convertView.findViewById(R.id.tv_shequ_question_time);
            TextView textView3 = convertView.findViewById(R.id.tv_shequ_question_content);
            ivguanzhu=convertView.findViewById(R.id.iv_guanzhu);
            tvguanzhu=convertView.findViewById(R.id.tv_guanzhu);
            Answer answer1=answerList.get(position);
            int followedUserId1=answer1.getUserId();
            //判断是否关注过
            IfFollowAsyncTask asyncTasked=new IfFollowAsyncTask(followedUserId1,ivguanzhu,tvguanzhu);
            asyncTasked.execute();

            followedUserId=answerList.get(position).getUserId();
            ivguanzhu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(flag1==false){
                        tvguanzhu.setText("已关注");
                        ivguanzhu.setImageResource(R.drawable.yishoucang);
                        flag1=true;
                        AddFollowAsyncTask add=new AddFollowAsyncTask(ShequQuestion.this);
                        add.execute();
                    }else{
                        tvguanzhu.setText("未关注");
                        ivguanzhu.setImageResource(R.drawable.weishoucang);
                        flag1=false;
                        DeleteFollowAsyncTask delete=new DeleteFollowAsyncTask(ShequQuestion.this);
                        delete.execute();

                    }
                    //shoucangStr= String.valueOf(tvshoucang.getText());
                }
            });



            String answerUserImgUrl=answer1.getAnswerUserImg();
            new ShowImgAsyncTask(answerUserImgUrl, imageView1).execute();
            textView1.setText(answer1.getAnswerUserName());
            textView2.setText(answer1.getAnswerTime());
            textView3.setText(answer1.getAnswerContent());
            return convertView;
        }
    }

    private void initData2() {
        GetMajorListAsyncTask2 asyncTask=new GetMajorListAsyncTask2(ShequQuestion.this,listView);
        asyncTask.execute();
    }

    class GetMajorListAsyncTask1 extends AsyncTask {
        private Context mContext;

        public GetMajorListAsyncTask1(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"CollectionQuestionServlet?remark=collectionQuestionListThree&questionId="+questionId+"&userId="+userId;
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
                question=new Question();
                question.setQuestionTitle(object1.getString("questionTitle"));

                Log.e("test", question.toString());
                return question;
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
            TextView questionTitle=findViewById(R.id.tv_shequ_question_title);
            questionTitle.setText(question.getQuestionTitle());

        }


    }

    private void initData1(){
        GetMajorListAsyncTask1 asyncTask=new GetMajorListAsyncTask1(ShequQuestion.this);
        asyncTask.execute();
    }
}
