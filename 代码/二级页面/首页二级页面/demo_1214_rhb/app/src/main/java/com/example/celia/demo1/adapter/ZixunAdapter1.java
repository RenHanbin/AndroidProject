package com.example.celia.demo1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.Article;
import com.example.celia.demo1.showImgAsyncTask.ShowImgAsyncTask;

import java.util.List;

public class ZixunAdapter1 extends BaseAdapter {

    private Context context;
    private int itemLayout;
    private List<Article> newsList;

    public ZixunAdapter1(Context context ,int itemLayout,List<Article> newsList){
        this.context=context;
        this.itemLayout=itemLayout;
        this.newsList=newsList;
    }
    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(itemLayout,null);
        }
        TextView newsInfo=convertView.findViewById(R.id.zixun2_newInfo);
        TextView newsCount=convertView.findViewById(R.id.tv_newCount);
        ImageView newsImg=convertView.findViewById(R.id.iv_newImg);
        Article news=newsList.get(position);
        newsInfo.setText(news.getArticleTitle());
        newsCount.setText("时间："+news.getArticleTime());
        String murl=news.getArticleImg();
        ShowImgAsyncTask showImg=new ShowImgAsyncTask(murl,newsImg);
        showImg.execute();
        return convertView;
    }
}
