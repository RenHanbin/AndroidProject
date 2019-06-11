package com.example.celia.demo1.my;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.celia.demo1.ContextData;
import com.example.celia.demo1.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;


import com.example.celia.demo1.bean.Article;
import com.example.celia.demo1.bean.Question;
import com.example.celia.demo1.bean.Writer;
import com.example.celia.demo1.shequ.ShequQuestion;
import com.example.celia.demo1.showImgAsyncTask.ShowImgAsyncTask;
import com.example.celia.demo1.zixun.TabHostActivity;
import com.example.celia.demo1.zixun.ZiXun3;
import com.google.gson.Gson;

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
import java.util.List;

public class MyTopic extends AppCompatActivity {
    private ListView listEssay;
    private ListView listTquest;
    private List<Article> articleList3;
    private CustomAdapter1 adapter1;
    private List<Question> questionList4;
    private CustomAdapter2 adapter2;
    private int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_topic);
        final ContextData data= (ContextData) getApplication();
        userId=data.getUserId();
        Log.e("userId",userId+"");

        //1.获取TabHost控件
        final TabHost tabHost=findViewById(android.R.id.tabhost);
        //2.TabHost初始化
        tabHost.setup();
        //3.添加选项卡
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("收藏文章")
                .setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("收藏问题")
                .setContent(R.id.tab2));

        tabHost.setOnClickListener(new AdapterView.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           updateTab(tabHost);
                                       }
                                   }
        );
        //收藏文章
        listEssay= findViewById(R.id.lv_topic_essay);
        initData1();
        //跳转到文章的具体显示页面zixun3
        listEssay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(getApplicationContext(), ZiXun3.class);
                intent.putExtra("articleId",articleList3.get(position).getArticleId() );
                intent.putExtra("articleTitle",articleList3.get(position).getArticleTitle());
                intent.putExtra("articleTime",articleList3.get(position).getArticleTime());
                intent.putExtra("writer",articleList3.get(position).getWriter());
                intent.putExtra("articleContent",articleList3.get(position).getArticleContent());
                intent.putExtra("position",position);
                intent.putExtra("page","MyTopic");
                startActivity(intent);
            }
        });


        listTquest=findViewById(R.id.lv_topic_questions);
        initData2();

        listTquest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent();
                intent.setClass(getApplicationContext(), ShequQuestion.class);
                intent.putExtra("questionId",questionList4.get(position).getQuestionId());
                startActivity(intent);
            }
        });



        //点击“<”，返回上一级界面我的my
        ImageView Ireturn=findViewById(R.id.iv_return);
        Ireturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MyTopic.this,TabHostActivity.class);
                intent.putExtra("bigMake",1);
                startActivity(intent);
                finish();
            }
        });
    }
    private void updateTab(final TabHost tabHost) {
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            View view = tabHost.getTabWidget().getChildAt(i);
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(18);
            tv.setTypeface(Typeface.SERIF, 1); // 设置字体微风格
            if (tabHost.getCurrentTab() == i) {//选中
                tv.setTextColor(Color.parseColor("#3ac9bf"));
            } else {//不选中
                tv.setTextColor(Color.parseColor("#a3a3a3"));
            }
        }
    }

    class GetMajorListAsyncTask3 extends AsyncTask<String, Void, List<Article>> {
        private Context mContext3;
        private ListView listView3;

        public GetMajorListAsyncTask3(Context mContext3, ListView listView3) {
            this.mContext3 = mContext3;
            this.listView3 = listView3;
        }

        @Override
        protected List<Article> doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"CollectionArticleServlet?remark=collectionArticleList&userId="+userId;
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
                articleList3 = new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Article article=new Article();
//                  article.setArticleId(object1.getInt("articleId"));
                    article.setArticleId(object1.getInt("articleId"));
                    article.setArticleTitle(object1.getString("articleTitle"));
                    article.setArticleImg(object1.getString("articleImg"));
                    article.setArticleTime(object1.getString("articleTime"));
                    article.setArticleContent(object1.getString("articleContent"));
                    String writerStr=object1.get("articleWriter").toString();
                    Writer writer=new Gson().fromJson(writerStr,Writer.class);
                    /**/
                    article.setWriter(writer);
                    article.setCommentCount(object1.getInt("commentCount"));
                    articleList3.add(article);
                }
                Log.e("test", articleList3.toString());
                return articleList3;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(List<Article> result) {
            Log.e("test", "已经进行到异步类的显示阶段");
            adapter1=new CustomAdapter1(mContext3,R.layout.my_topic_essay,result);
            listEssay.setAdapter(adapter1);
            adapter1.notifyDataSetInvalidated();
        }
    }

    public class CustomAdapter1 extends BaseAdapter {

        private Context context;
        private int itemLayoutID;
        private List<Article> articleList3;

        public CustomAdapter1(Context context, int itemLayoutID, List<Article> articleList3) {
            this.context = context;
            this.itemLayoutID = itemLayoutID;
            this.articleList3 = articleList3;
        }

        @Override
        public int getCount() {
            return articleList3.size();
        }

        @Override
        public Object getItem(int position) {
            return articleList3.get(position);
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
            ImageView imageView1 = convertView.findViewById(R.id.iv_topic_image);
            TextView textView1 = convertView.findViewById(R.id.tv_topic_title);
            TextView textView2 = convertView.findViewById(R.id.tv_enum1);
            Article article1=articleList3.get(position);
            Log.e("test", article1.getArticleTitle());
            String articleImgUrl=article1.getArticleImg();
            new ShowImgAsyncTask(articleImgUrl, imageView1).execute();
            textView1.setText(article1.getArticleTitle());
            textView2.setText(String.valueOf(article1.getCommentCount()));
            return convertView;
        }
    }
    private void initData1() {
        GetMajorListAsyncTask3 asyncTask=new GetMajorListAsyncTask3(MyTopic.this,listEssay);
        asyncTask.execute();
    }


    class GetMajorListAsyncTask4 extends AsyncTask<String, Void, List<Question>> {
        private Context mContext4;
        private ListView listView4;

        public GetMajorListAsyncTask4(Context mContext4, ListView listView4) {
            this.mContext4 = mContext4;
            this.listView4 = listView4;
        }

        @Override
        protected List<Question> doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"CollectionQuestionServlet?remark=collectionQuestionList&userId="+userId;
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
                questionList4 = new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Question question=new Question();
                    question.setQuestionId(object1.getInt("questionId"));
                    question.setQuestionTitle(object1.getString("questionTitle"));
                    question.setQuestionUserName(object1.getString("userName"));
                    question.setQuestionUserImg(object1.getString("userImg"));
                    question.setUserCount(object1.getInt("userCount"));
                    questionList4.add(question);
                }
                Log.e("test", questionList4.toString());
                return questionList4;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(List<Question> result) {
            Log.e("test", "已经进行到异步类的显示阶段");
            adapter2=new CustomAdapter2(mContext4,R.layout.my_topic_questions,result);
            listTquest.setAdapter(adapter2);
            adapter2.notifyDataSetInvalidated();
        }
    }


    public class CustomAdapter2 extends BaseAdapter {
        private Context context;
        private int itemLayoutID;
        private List<Question> questionList4;

        public CustomAdapter2(Context context, int itemLayoutID, List<Question> questionList4) {
            this.context = context;
            this.itemLayoutID = itemLayoutID;
            this.questionList4 = questionList4;
        }

        @Override
        public int getCount() {
            return questionList4.size();
        }

        @Override
        public Object getItem(int position) {
            return questionList4.get(position);
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
            ImageView imageView1 = convertView.findViewById(R.id.iv_topic_head);
            TextView textView1 = convertView.findViewById(R.id.tv_topic_qtitle);
            TextView textView2 = convertView.findViewById(R.id.tv_topic_name);
            TextView textView3 = convertView.findViewById(R.id.tv_topic_qnum);

            Question question1=questionList4.get(position);
            Log.e("test", question1.getQuestionTitle());
            String questionImgUrl=question1.getQuestionUserImg();
            new ShowImgAsyncTask(questionImgUrl, imageView1).execute();
            textView1.setText(question1.getQuestionTitle());
            textView2.setText(question1.getQuestionUserName());
            textView3.setText(String.valueOf(question1.getUserCount()));
            return convertView;
        }
    }
    private void initData2() {
        GetMajorListAsyncTask4 asyncTask=new GetMajorListAsyncTask4(MyTopic.this,listTquest);
        asyncTask.execute();
    }
}
