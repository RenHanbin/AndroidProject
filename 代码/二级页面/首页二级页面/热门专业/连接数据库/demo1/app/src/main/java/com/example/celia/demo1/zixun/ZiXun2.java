package com.example.celia.demo1.zixun;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.Major;

import java.util.List;

public class ZiXun2 extends AppCompatActivity {

    private ListView zixunListView1;
    private List<Major> majorList;
    private ZixunAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zixun2);
        zixunListView1=findViewById(R.id.zixun2_newInfo_listview);
        initData();
    }

    //获取数据
    private void initData() {
    }

    //创建自定义adapter
    private class ZixunAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }

}
