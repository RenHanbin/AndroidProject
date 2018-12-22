package com.example.celia.demo1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


import com.example.celia.demo1.bean.Question;
import com.example.celia.demo1.shequ.ShequQuestion;
import com.example.celia.demo1.showImgAsyncTask.ShowImgAsyncTask;

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


public class FragmentTab2 extends Fragment {
    private int userId;
    private ListView listGuan;
    private ListView listQuest;
    private List<Question> datalist1;
    private List<Question> datalist2;
    private CustomAdapter adapter1;
    private CustomAdapter2 adapter2;
    private ImageView userImg1;
    private ImageView dianZan1;
    private boolean b=false;
    private TextView userName1;
    private TextView questTime1;
    private TextView questTitle1;
    private TextView questDes1;
    private ImageView questImg2;
    private TextView questTitle2;
    private TextView questAttenNum2;

    TabHost tabHost;

    //创建View时调用
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page2,container,false);//根据布局文件产生布局控件
        //false 不放到第二个参数中，true 放到第二个参数中
        //1.获取TabHost控件
        tabHost=view.findViewById(android.R.id.tabhost);
        //2.TabHost初始化
        tabHost.setup();
        //3.添加选项卡
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("关注")
                .setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("热门问题")
                .setContent(R.id.tab2));
        updateTab(tabHost);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                updateTab(tabHost);
            }
        });

        final ContextData data = (ContextData) getContext().getApplicationContext();
        userId=data.getUserId();
        Log.e("userId",userId+"");

        listGuan= view.findViewById(R.id.lv_guanzhu);
        listQuest=view.findViewById(R.id.lv_questions);
        initData1();
        initData2();
        TextView Search = view.findViewById(R.id.iv_search);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询
                Toast.makeText(getActivity(),"搜索",Toast.LENGTH_SHORT).show();
            }
        });

        //跳转关注二级页面
        listGuan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ShequQuestion.class);
                intent.putExtra("page","guanzhu");
                intent.putExtra("questionId",datalist1.get(position).getQuestionId());
                startActivity(intent);
            }
        });

        //点击热点问题list其中的一项，跳转
        listQuest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ShequQuestion.class);
                intent.putExtra("page","questions");
                intent.putExtra("questionId",datalist2.get(position).getQuestionId());
                startActivity(intent);
            }
        });

        return view;
    }

    //当view创建完成
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /*适配器*/
    //关注adapter
    public class CustomAdapter extends BaseAdapter {
        private Context context;
        private int itemLayoutId;
        private List<Question> datalist1;
        public CustomAdapter(Context context,
                             int itemLayoutId,
                             List<Question>datalist1){
            this.context=context;
            this.itemLayoutId=itemLayoutId;
            this.datalist1=datalist1;
        }
        @Override
        public int getCount() {
            return datalist1.size();
        }

        @Override
        public Object getItem(int position) {
            return datalist1.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                LayoutInflater inflater=LayoutInflater.from(context);
                convertView=inflater.inflate(itemLayoutId,null);
            }
            userImg1 = convertView.findViewById(R.id.iv_head);
            dianZan1 = convertView.findViewById(R.id.iv_grey_heart);
            userName1 = convertView.findViewById(R.id.tv_name);
            questTime1 = convertView.findViewById(R.id.tv_data);
            questTitle1 = convertView.findViewById(R.id.tv_header);
            questDes1 = convertView.findViewById(R.id.tv_nei);
            Question quest= datalist1.get(position);
            new ShowImgAsyncTask(quest.getQuestionUserImg(),userImg1).execute();
            dianZan1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(b==false){
                        dianZan1.setImageResource(R.drawable.dianzan2);
                        b=true;
                    }else{
                        dianZan1.setImageResource(R.drawable.dianzan);
                        b=false;
                    }
                }
            });

            userName1.setText(quest.getQuestionUserName());
            questTime1.setText(quest.getQuestionTime());
            questTitle1.setText(quest.getQuestionTitle());
            questDes1.setText(quest.getQuestionDiscribe());
            return convertView;
        }
    }
    //热门问题adapter
    public class CustomAdapter2 extends BaseAdapter {
        private Context context;
        private int itemLayoutId;
        private List<Question> datalist2;
        public CustomAdapter2(Context context,
                              int itemLayoutId,
                              List<Question> datalist2){
            this.context=context;
            this.itemLayoutId=itemLayoutId;
            this.datalist2=datalist2;
        }
        @Override
        public int getCount() {
            return datalist2.size();
        }

        @Override
        public Object getItem(int position) {
            return datalist2.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                LayoutInflater inflater=LayoutInflater.from(context);
                convertView=inflater.inflate(itemLayoutId,null);
            }
            questImg2 = convertView.findViewById(R.id.iv_quest_image);
            questAttenNum2 = convertView.findViewById(R.id.tv_anum);
            questTitle2 = convertView.findViewById(R.id.tv_quest);
            Question quest = datalist2.get(position);
            new ShowImgAsyncTask(quest.getQuestionImg(),questImg2).execute();
            questAttenNum2.setText(quest.getUserCount()+"");
            questTitle2.setText(quest.getQuestionTitle());
            return convertView;
        }
    }

    /*初始化数据*/
    private void initData1() {
        GetAttenListAsyncTask asyncTask = new GetAttenListAsyncTask(FragmentTab2.this,listGuan);
        asyncTask.execute();
    }
    public void initData2(){
        GetQuestListAsyncTask asyncTask = new GetQuestListAsyncTask(FragmentTab2.this,listQuest);
        asyncTask.execute();
    }


    //关注异步任务类
    class GetAttenListAsyncTask extends AsyncTask<String,Void,List<Question>> {
        private Fragment fragmentTab2;
        private ListView listView;
        public GetAttenListAsyncTask(Fragment fragmentTab2,ListView listView){
            this.fragmentTab2 = fragmentTab2;
            this.listView = listView;
        }
        @Override
        protected List<Question> doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"MyQuestionServlet?remark=getAllAttenList&userId="+userId;
            URL url = null;
            try {
                url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("contentType", "utf-8");//解决给服务器端传输的乱码问题
                InputStream inputStream = connection.getInputStream();
                //字节流转字符流
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);//字符流
                String str = reader.readLine();
                //解析jsonarray
                JSONArray array = new JSONArray(str);
                datalist1 = new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Question quest = new Question();
                    quest.setQuestionId(object1.getInt("questionId"));
                    quest.setQuestionUserImg(object1.getString("userImg"));
                    quest.setQuestionUserName(object1.getString("userName"));
                    quest.setQuestionTime(object1.getString("questionTime"));
                    quest.setQuestionTitle(object1.getString("questionTitle"));
                    quest.setQuestionDiscribe(object1.getString("questionDescribe"));
                    datalist1.add(quest);
                }
                return datalist1;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(List<Question> datalist){
            adapter1 = new CustomAdapter(getContext(), R.layout.guanzhu,datalist1);
            listView.setAdapter(adapter1);
        }
    }

        //热门问题异步任务类
    class GetQuestListAsyncTask extends AsyncTask<String,Void,List<Question>> {
        private Fragment fragmentTab2;
        private ListView listView;
        public GetQuestListAsyncTask(Fragment fragmentTab2,ListView listView){
            this.fragmentTab2 = fragmentTab2;
            this.listView = listView;
        }
        @Override
        protected List<Question> doInBackground(String... strings) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"MyQuestionServlet?remark=getAllQuestionList&userId="+userId;
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
                datalist2 = new ArrayList<>();
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject object1 = array.getJSONObject(i);
                    Question quest = new Question();
                    quest.setQuestionId(object1.getInt("questionId"));
                    quest.setQuestionImg(object1.getString("questionImg"));
                    quest.setQuestionTitle(object1.getString("questionTitle"));
                    quest.setUserCount(object1.getInt("attenNum"));
                    datalist2.add(quest);
                }
                return datalist2;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(List<Question> datalist2){
            adapter2 = new CustomAdapter2(getContext(), R.layout.questions,datalist2);
            listView.setAdapter(adapter2);
        }
    }


   /* tab颜色*/
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

}
