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

public class ZiXunTalkAdapter extends BaseAdapter {
    private Context context;
    private int itemLayout;
    private List<Article> talkList;

    public ZiXunTalkAdapter(Context context,int itemLayout,List<Article> talkList){
        this.context=context;
        this.talkList=talkList;
        this.itemLayout=itemLayout;
    }
    @Override
    public int getCount() {
        return talkList.size();
    }

    @Override
    public Object getItem(int position) {
        return talkList.get(position);
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
        TextView talkTitle=convertView.findViewById(R.id.zixun2_talk_text);
        TextView writerName=convertView.findViewById(R.id.zixun2_talk_writerName);
        ImageView talkImg=convertView.findViewById(R.id.zixun2_talk_img);
        ImageView writerImg=convertView.findViewById(R.id.zixun2_talk_writerImg);
        Article talk=talkList.get(position);
        talkTitle.setText(talk.getArticleTitle());
        writerName.setText(talk.getWriter().getWriterName());
        //异步类显示图片
        String murl1=talk.getArticleImg();
        String murl2=talk.getWriter().getWriterImg();
        ShowImgAsyncTask showImg=new ShowImgAsyncTask(murl1,talkImg);
        ShowImgAsyncTask showImg2=new ShowImgAsyncTask(murl2,writerImg);
        showImg.execute();
        showImg2.execute();
        return convertView;
    }
}
