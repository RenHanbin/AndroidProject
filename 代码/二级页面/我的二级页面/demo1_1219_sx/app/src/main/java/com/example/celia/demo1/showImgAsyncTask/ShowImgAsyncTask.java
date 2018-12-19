package com.example.celia.demo1.showImgAsyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ShowImgAsyncTask extends AsyncTask {
    private String murl;
    private ImageView imageView;
    private Bitmap bitmap;

    public ShowImgAsyncTask(String url,ImageView imageView){
        murl=url;
        this.imageView=imageView;
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            //创建URL对象
            URL url=new URL(murl);
            //创建URL连接对象
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            //设置连接的请求方式
            connection.setRequestMethod("GET");
            //设置连接超时时间
            connection.setConnectTimeout(8000);
            //设置数据传输超时时间
            connection.setReadTimeout(8000);
            //获取服务器返回数据的输入流数据
            InputStream in=connection.getInputStream();
            //将输入流转换成bitmap，解码
            bitmap= BitmapFactory.decodeStream(in);
            //关闭流
            in.close();
            //断开连接
            connection.disconnect();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        imageView.setImageBitmap(bitmap);
    }
}
