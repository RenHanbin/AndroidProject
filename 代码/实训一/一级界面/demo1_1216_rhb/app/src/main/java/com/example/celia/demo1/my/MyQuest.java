package com.example.celia.demo1.my;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.celia.demo1.R;
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

public class MyQuest extends AppCompatActivity {
    private ListView listQuest;
    private List<Question> datalist;
    private ListViewAdapter adapter;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_quest);

        listQuest= findViewById(R.id.lv_my_quest);
        initData();
        //点击“<”，返回上一级界面我的my
        ImageView Ireturn=findViewById(R.id.iv_return);
        Ireturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //跳转三级页面
        listQuest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent();
                    intent.setClass(MyQuest.this,Index3MyQuestItem.class);
                    intent.putExtra("questionId",datalist.get(position).getQuestionId());
                    intent.putExtra("questionTitle",datalist.get(position).getQuestionTitle());
                    Log.e("questionTitle",datalist.get(position).getQuestionTitle());
                    intent.putExtra("questionDiscribe",datalist.get(position).getQuestionDiscribe());
                    intent.putExtra("attenNum",datalist.get(position).getUserCount()+"");
                    intent.putExtra("commNum",datalist.get(position).getCommNum()+"");
                    Log.e("intent11",intent.toString());
                    startActivity(intent);
            }
        });

    }
    public class ListViewAdapter extends BaseAdapter {

        private Context context;
        private int itemLayoutID;
        private List<Question> datalist;
        public ListViewAdapter(Context context,
                               int itemLayoutID,
                               List<Question> datalist){
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
            Log.e("aa","getView1");
            TextView questTitle = convertView.findViewById(R.id.tv_title1);
            TextView questTime = convertView.findViewById(R.id.tv_time);
            Question quest = datalist.get(position);
                Log.e("test", quest.getQuestionTitle());
            questTitle.setText(quest.getQuestionTitle());
            questTime.setText(quest.getQuestionTime());
            Log.e("aa","getView2");
            return convertView;
        }
    }

    //创建获取数据的异步任务类
    class GetMyQuestListAsyncTask extends AsyncTask<String,Void,List<Question>>{
        private Context qContext;
        private ListView listView;
        public GetMyQuestListAsyncTask(Context qContext,ListView listView){
            this.qContext = qContext;
            this.listView = listView;
        }
        @Override
        protected List<Question> doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"MyQuestionServlet?remark=getMyQuestionList";
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
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Question quest = new Question();
                    quest.setQuestionId(object1.getInt("questionId"));
                    quest.setQuestionTitle(object1.getString("questionTitle"));
                    quest.setQuestionTime(object1.getString("questionTime"));
                    quest.setQuestionDiscribe(object1.getString("questionDiscribe"));
                    quest.setUserCount(object1.getInt("attenNum"));
                    quest.setCommNum(object1.getInt("commNum"));
                   datalist.add(quest);
                }
                Log.e("test3", datalist.toString());
                return datalist;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(List<Question> result){
            adapter = new ListViewAdapter(qContext,R.layout.my_quest_list,result);
            listView.setAdapter(adapter);
        }
    }
    public void initData(){
        GetMyQuestListAsyncTask asyncTask = new GetMyQuestListAsyncTask(MyQuest.this,listQuest);
        asyncTask.execute();
    }
}
