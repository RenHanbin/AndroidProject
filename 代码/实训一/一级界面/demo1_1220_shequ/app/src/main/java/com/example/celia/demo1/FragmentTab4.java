package com.example.celia.demo1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.celia.demo1.bean.UserBean;
import com.example.celia.demo1.my.MyAtten;
import com.example.celia.demo1.my.MyComm;
import com.example.celia.demo1.my.MyMagor;
import com.example.celia.demo1.my.MyQuest;
import com.example.celia.demo1.my.MyScho;
import com.example.celia.demo1.my.MySetUp;
import com.example.celia.demo1.my.MyTopic;
import com.example.celia.demo1.my.MyUser;
import com.example.celia.demo1.my.MyWallet;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class FragmentTab4 extends Fragment{
    private UserBean user;
    private int userId;
    private ImageView myUserImage;
    private TextView myUserName;
    private TextView myUserFans;
    private TextView myUserAtten;
    //创建View时调用
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page4,container,false);//根据布局文件产生布局控件

        final ContextData data = (ContextData) getContext().getApplicationContext();
        userId = data.getUserId();
        myUserImage = view.findViewById(R.id.iv_user);
        myUserName = view.findViewById(R.id.tv_user_name);
        myUserFans = view.findViewById(R.id.tv_fan_number);
        myUserAtten = view.findViewById(R.id.tv_follow_number);
        //头像。用户名，粉丝添加
        initdate();

        //false 不放到第二个参数中，true 放到第二个参数中
        //用户头像
        ImageView ivUser=view.findViewById(R.id.iv_user);
        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),MyUser.class);
                intent.putExtra("userName",myUserName.getText());
                intent.putExtra("userSex",user.getUserSex());
                intent.putExtra("userTel",user.getUserTel());
                intent.putExtra("userEmail",user.getUserEmail());
                intent.putExtra("userImg",user.getUserImg());
                startActivityForResult(intent,1);
            }
        });
        //问题
        LinearLayout llQuestion=view.findViewById(R.id.ll_question);
        llQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(), MyQuest.class);
                startActivity(intent);
            }
        });
        //评论
        LinearLayout llComment=view.findViewById(R.id.ll_comment);
        llComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),MyComm.class);
                startActivity(intent);
            }
        });
        //专业
        LinearLayout llMagor=view.findViewById(R.id.ll_major);
        llMagor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),MyMagor.class);
                startActivity(intent);
            }
        });
        //学校
        LinearLayout llScho=view.findViewById(R.id.ll_university);
        llScho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),MyScho.class);
                startActivity(intent);
            }
        });
        //关注
        LinearLayout llAtten=view.findViewById(R.id.ll_follow);
        llAtten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),MyAtten.class);
                startActivity(intent);
            }
        });
        //话题
        LinearLayout llTopic=view.findViewById(R.id.ll_topic);
        llTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),MyTopic.class);
                startActivity(intent);
            }
        });
        //钱包
        LinearLayout llWallet=view.findViewById(R.id.ll_wallet);
        llWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),MyWallet.class);
                startActivity(intent);
            }
        });
        //设置
        LinearLayout llSetUp=view.findViewById(R.id.ll_setup);
        llSetUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity().getApplicationContext(),MySetUp.class);
                startActivity(intent);

            }
        });
        return view;

    }


    // 带返回值
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("Fragment","RequestCode"+requestCode+" ResultCode"+ resultCode);
        if (requestCode == 1 && resultCode == 2){
            Log.e("Fragment","信息");
            user.setUserName(data.getStringExtra("userName"));
            user.setUserSex(data.getStringExtra("userSex"));
            user.setUserTel(data.getStringExtra("userTel"));
            user.setUserEmail(data.getStringExtra("userEmail"));
            Bundle b=data.getExtras();
            if(b!=null){
                byte[] bytes=b.getByteArray("bitmap");
                if(bytes!=null) {
                    Bitmap bmp= BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    myUserImage.setImageBitmap(bmp);
                }
            }
            Log.e("Fragment",user.getUserName());
            myUserName.setText(user.getUserName());
        }

    }

    private void initdate() {
        GetMyUerAsyncTask asyncTask = new GetMyUerAsyncTask(FragmentTab4.this);
        asyncTask.execute();
    }

    //当view创建完成
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    //获取用户信息异步类
    public class GetMyUerAsyncTask extends AsyncTask {
        private Fragment fragmentTab4;
        //构造函数，接收更新的视图控件
        public GetMyUerAsyncTask(Fragment fragmentTab4){
            this.fragmentTab4=fragmentTab4;
        }
        @Override
        protected Object doInBackground(Object[] objects) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"UserServlet?remark=getUser&userId="+userId;
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //设置请求参数，因为传过去的东西有中文，避免乱码
                connection.setRequestProperty("contentType", "UTF-8");
                //通过流取数据
                InputStream in = connection.getInputStream();
                //返回数据为中文,字节流与字符流转换
                InputStreamReader inputStreamReader = new InputStreamReader(in);//得到字符流
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String res = reader.readLine();//读一行

                //解析JSON格式字符串
                JSONObject object = new JSONObject(res);
                //从对象中取数据，放入user对象
                user = new UserBean();
                user.setUserId(object.getInt("userId"));
                user.setUserPassword(object.getString("userPassword"));
                user.setUserImg(object.getString("userImage"));
                user.setUserName(object.getString("userName"));
                user.setUserFans(object.getInt("fansNum"));
                user.setUserAtten(object.getInt("attenNum"));
                user.setUserSex(object.getString("userSex"));
                user.setUserTel(object.getString("userTel"));
                user.setUserEmail(object.getString("userEmail"));
                return user;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Object o) {
            myUserName.setText(user.getUserName());
            myUserFans.setText(user.getUserFans()+"");
            myUserAtten.setText(user.getUserAtten()+"");
//            Log.e("test",user.getUserImg());
            String strings = user.getUserImg();
            String s = strings.replace(" ", "+");
            Log.e("test",s);
            Bitmap bitmap = stringToBitmap(s);
            myUserImage.setImageBitmap(bitmap);
            Log.e("MyUser", user.toString());
        }
    }
    public static Bitmap stringToBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray = Base64.decode(string, Base64.NO_WRAP);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}