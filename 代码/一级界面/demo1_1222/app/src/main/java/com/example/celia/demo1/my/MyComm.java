package com.example.celia.demo1.my;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.celia.demo1.ContextData;
import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.Article;
import com.example.celia.demo1.bean.Answer;
import com.example.celia.demo1.bean.Comm;

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

import javax.net.ssl.HttpsURLConnection;

public class MyComm extends AppCompatActivity {
    private ListView listComm;
    private List<Comm> datalist;
    private ListViewAdapter adapter;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_comment);

        listComm= findViewById(R.id.lv_my_comments);
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
    public class ListViewAdapter extends BaseAdapter {

        private Context context;
        private int itemLayoutID;
        private List<Comm> datalist;
        public ListViewAdapter(Context context,
                             int itemLayoutID,
                             List<Comm>datalist){
            this.context=context;
            this.itemLayoutID=itemLayoutID;
            this.datalist=datalist;
        }
        @Override
        public int getCount() {
            return datalist.size();
        }

        @Override
        public Object getItem(int position) {
            return datalist.get(position);
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
            TextView commTitle = convertView.findViewById(R.id.tv_title);
            TextView comment = convertView.findViewById(R.id.tv_comment);
            TextView commTime = convertView.findViewById(R.id.tv_time);
            Comm comm = datalist.get(position);
            //如何判断到底是answerContent还是articleTitle
            commTitle.setText(comm.getArticleTitle());
            comment.setText(comm.getCommentContent());
            commTime.setText(comm.getCommentTime());
            return convertView;
        }
    }

    //创建异步任务类
    class GetMyCommListAsyncTask extends AsyncTask<String,Void,List<Comm>>{
        private Context cContext;
        private ListView listView;
        public GetMyCommListAsyncTask(Context cContext,ListView listView){
            this.cContext = cContext;
            this.listView = listView;
        }

        @Override
        protected List<Comm> doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"MyCommentServlet?remark=getMyCommentList";
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream = connection.getInputStream();
                //字节流转字符流
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                String str = reader.readLine();
                Log.e("test1", str);
                //解析jsonarray
                JSONArray array = new JSONArray(str);
                datalist = new ArrayList<>();
                for(int i = 0;i < array.length(); ++i){
                    JSONObject object1 = array.getJSONObject(i);
                    Comm comm = new Comm();
                    comm.setCommentId(object1.getInt("commentId"));
                    comm.setCommentContent(object1.getString("commentContent"));
                    comm.setCommentTime(object1.getString("commentTime"));
//                    comm.setAnswerContent(object1.getString("answerContent"));
                    comm.setArticleTitle(object1.getString("articleTitle"));
                    datalist.add(comm);
                    Log.e("test1","datalisttththf");
                }
                Log.e("test1",datalist.toString());
                return datalist;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(List<Comm> result) {
            Log.e("testtsfdssd", "异步类显示");
            adapter = new ListViewAdapter(cContext,R.layout.my_comments,result);
            listView.setAdapter(adapter);
        }
    }

    private void initData() {
        GetMyCommListAsyncTask asyncTask = new GetMyCommListAsyncTask(MyComm.this,listComm);
        asyncTask.execute();
    }
}
