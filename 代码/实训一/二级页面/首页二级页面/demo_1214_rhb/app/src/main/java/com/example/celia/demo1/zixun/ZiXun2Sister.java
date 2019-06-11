package com.example.celia.demo1.zixun;

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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.celia.demo1.R;
import com.example.celia.demo1.adapter.ZiXunTalkAdapter;
import com.example.celia.demo1.bean.Article;
import com.example.celia.demo1.bean.Writer;
import com.example.celia.demo1.showImgAsyncTask.ShowImgAsyncTask;
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

public class ZiXun2Sister extends AppCompatActivity {

    private List<Article> talkList;
    private GridView gridView;
    private ZiXunTalkAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zixun2_talk);
        gridView=findViewById(R.id.zixun2_talk_gridView);
        initData();
        //当点击进入三级页面时
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(ZiXun2Sister.this,ZiXun3.class);
                intent.putExtra("articleTitle",talkList.get(position).getArticleTitle());
                intent.putExtra("articleTime",talkList.get(position).getArticleTime());
                intent.putExtra("writer",talkList.get(position).getWriter());
                intent.putExtra("articleContent",talkList.get(position).getArticleContent());
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
    class GetTalkListAsyncTask extends AsyncTask<String,Void,List<Article>>{
        private Context context;
        private GridView gridView;

        public GetTalkListAsyncTask(Context ziXun2, GridView gridView1) {
            context=ziXun2;
            gridView=gridView1;
        }

        @Override
        protected List<Article> doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"ZiXunServlet?remark=getTalkList";
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream = connection.getInputStream();
                //字节流转字符流
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                String str = reader.readLine();
                Log.e("test", str);
                //解析jsonarray
                JSONArray array = new JSONArray(str);
                talkList=new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Article article2 = new Article();
                    article2.setArticleId(object1.getInt("articleId"));
                    article2.setArticleTitle(object1.getString("articleTitle"));
                    article2.setArticleContent(object1.getString("articleContent"));
                    article2.setArticleTime(object1.getString("articleTime"));
                    article2.setArticleImg(object1.getString("articleImg"));
                    String writerStr=object1.get("articleWriter").toString();
                    Log.e("test",writerStr);
                    Writer writer=new Gson().fromJson(writerStr,Writer.class);
                    /**/
                    article2.setWriter(writer);
                    talkList.add(article2);
                }
                Log.e("test", talkList.toString());
                return talkList;
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
            adapter=new ZiXunTalkAdapter(context, R.layout.zixun2_talk_item,result);
            gridView.setAdapter(adapter);
        }
    }

    //创建填充数据
    private void initData() {
        GetTalkListAsyncTask asyncTask1=new GetTalkListAsyncTask(ZiXun2Sister.this,gridView);
        asyncTask1.execute();
    }
}
