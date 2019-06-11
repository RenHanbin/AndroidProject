package com.example.celia.demo1.zixun;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.celia.demo1.R;
import com.example.celia.demo1.adapter.ZixunAdapter1;
import com.example.celia.demo1.bean.Article;
import com.example.celia.demo1.bean.Writer;
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

public class ZiXun2 extends AppCompatActivity {

    private ListView zixunListView1;
    private List<Article> newsList;
    private ZixunAdapter1 adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.zixun2);
        zixunListView1=findViewById(R.id.zixun2_newInfo_listview);
        initData();
        //当点击进入三级页面时
        zixunListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(ZiXun2.this,ZiXun3.class);
                intent.putExtra("articleId",newsList.get(position).getArticleId());
                intent.putExtra("articleTitle",newsList.get(position).getArticleTitle());
                intent.putExtra("articleTime",newsList.get(position).getArticleTime());
                intent.putExtra("writer",newsList.get(position).getWriter());
                intent.putExtra("articleContent",newsList.get(position).getArticleContent());
                intent.putExtra("position",position);
                startActivity(intent);
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


    //创建异步类
    class GetNewsListAsyncTask extends AsyncTask<String,Void,List<Article>>{
        private Context context;
        private ListView listView;

        public GetNewsListAsyncTask(Context ziXun2, ListView listView1) {
            context=ziXun2;
            listView=listView1;
        }

        @Override
        protected List<Article> doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"ZiXunServlet?remark=getNewsList";
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream = connection.getInputStream();
                //字节流转字符流
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                String str = reader.readLine();
                //解析jsonarray
                JSONArray array = new JSONArray(str);
                newsList=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Article article = new Article();
                    article.setArticleId(object1.getInt("articleId"));
                    article.setArticleTitle(object1.getString("articleTitle"));
                    article.setArticleContent(object1.getString("articleContent"));
                    article.setArticleTime(object1.getString("articleTime"));
                    article.setArticleImg(object1.getString("articleImg"));
                    String writerStr=object1.get("articleWriter").toString();
                    Writer writer=new Gson().fromJson(writerStr,Writer.class);
                    /**/
                    article.setWriter(writer);
                    newsList.add(article);
                }
                return newsList;
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
        protected void onPostExecute(List<Article> result){
            Log.e("test","已经进行到异步类的显示阶段");
            adapter=new ZixunAdapter1(context, R.layout.zixun2_newinfo_item_layout,result);
            zixunListView1.setAdapter(adapter);
        }
    }
    //获取数据
    private void initData() {
        GetNewsListAsyncTask newsListAsyncTask=new GetNewsListAsyncTask(ZiXun2.this,zixunListView1);
        newsListAsyncTask.execute();
    }



}
