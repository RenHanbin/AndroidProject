package com.example.celia.demo1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.celia.demo1.adapter.ZiXunTalkAdapter;
import com.example.celia.demo1.adapter.ZixunAdapter1;
import com.example.celia.demo1.bean.Article;
import com.example.celia.demo1.bean.Writer;
import com.example.celia.demo1.zixun.ZiXun2;
import com.example.celia.demo1.zixun.ZiXun2Sister;
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

public class FragmentTab3 extends Fragment {
    private ListView newsListView;
    private GridView talkGridView;
    private List<Article> newsList;
    private List<Article> talkList;
    private ZixunAdapter1 adapter;
    private ZiXunTalkAdapter adapter2;

    /*
     * 资讯——一级页面
     *
     * */
    //创建View时调用
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_page3,container,false);
        //View view = inflater.inflate(R.layout.fragment_page3,container,false);//根据布局文件产生布局控件
        //false 不放到第二个参数中，true 放到第二个参数中
        super.onCreate(savedInstanceState);
        //专栏圆角效果
        TextView text1=view.findViewById(R.id.tv_zhuanlan);
        text1.setBackgroundResource(R.drawable.corner_view);

        //显示高考新资讯
        newsListView= view.findViewById(R.id.zixun_index_news);
        initNewsData();
       //显示学长学姐有话说
        talkGridView=view.findViewById(R.id.zixun_index_talk);
        initTalkData();

        /*
         * 进入高考新资讯二级页面
         * */
        TextView tvgaokao=view.findViewById(R.id.tv_gaokao);
        tvgaokao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("test","点击了高考新资讯");
                Intent intent = new Intent();
                intent.setClass(getActivity().getApplicationContext(),ZiXun2.class);
                startActivity(intent);
            }
        });

        /*
         * 进入学长学姐有话说二级页面
         * */
        TextView tvsister=view.findViewById(R.id.tv_sister);
        tvsister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("test","点击了学长学姐有话说");
                Intent intent = new Intent();
                intent.setClass(getActivity().getApplicationContext(),ZiXun2Sister.class);
                startActivity(intent);
            }
        });


        final ImageView imdianzan=(ImageView)view.findViewById(R.id.iv_dianzan);
        imdianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == imdianzan)
                    imdianzan.setImageDrawable(getResources().getDrawable(R.drawable.dianzan2));
            }
        });
//               final ImageView imdianzan2=(ImageView)view.findViewById(R.id.iv_dianzan2);
//        imdianzan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(v == imdianzan2)
//                    imdianzan2.setImageDrawable(getResources().getDrawable(R.drawable.dianzan2));
//            }
//        });
//        final ImageView imdianzan3=(ImageView)view.findViewById(R.id.iv_dianzan3);
//        imdianzan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(v == imdianzan3)
//                    imdianzan3.setImageDrawable(getResources().getDrawable(R.drawable.dianzan2));
//            }
//        });



        return view;
    }

    //创建显示高考新资讯的异步类
    class GetIndexNewsListAsyncTask extends AsyncTask<String,Void,List<Article>> {
        private Context context;
        private ListView listView;

        public GetIndexNewsListAsyncTask(Context ziXun, ListView listView1) {
            context=ziXun;
            listView=listView1;
        }

        @Override
        protected List<Article> doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"ZiXunServlet?remark=getIndexNewsList";
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
                Log.e("test", newsList.toString());
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
            adapter=new ZixunAdapter1(context, R.layout.zixun_index_news,result);
            newsListView.setAdapter(adapter);
        }
    }
    //创建高考新资讯内容
    private void initNewsData() {
        GetIndexNewsListAsyncTask asyncTask1=new GetIndexNewsListAsyncTask(getActivity().getApplicationContext(),newsListView);
        asyncTask1.execute();
    }

    //创建显示学长学姐有话说的异步类
    class GetIndexTalkListAsyncTask extends AsyncTask<String,Void,List<Article>>{
        private Context context1;
        private GridView gridView;

        public GetIndexTalkListAsyncTask(Context ziXun, GridView gridView1) {
            context1=ziXun;
            gridView=gridView1;
        }

        @Override
        protected List<Article> doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"ZiXunServlet?remark=getIndexTalkList";
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
            adapter2=new ZiXunTalkAdapter(context1, R.layout.zixun_index_talk,result);
            gridView.setAdapter(adapter2);
        }
    }
    //创建学长学姐有话说内容
    private void initTalkData() {
        GetIndexTalkListAsyncTask asyncTask2=new GetIndexTalkListAsyncTask(getActivity().getApplicationContext(),talkGridView);
        asyncTask2.execute();
    }



    //当view创建完成
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
