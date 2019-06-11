package com.example.celia.demo1.my;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.celia.demo1.ContextData;
import com.example.celia.demo1.R;
import com.example.celia.demo1.bean.UserBean;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.security.auth.login.LoginException;

public class MyUser extends AppCompatActivity {
    private UserBean user;
    private int userId;
    private ImageView myUserImage;
    private EditText myUserName;
    private EditText myUserSex;
    private EditText myUserPhone;
    private EditText myUserEmail;
    private String name;
    private Bitmap head;// 头像Bitmap
    String intentImg="";
    Bundle b;
    String img="";
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_user);
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        final ContextData data = (ContextData) getApplication();
        userId = data.getUserId();
        myUserName = findViewById(R.id.my_user_name);
        myUserSex = findViewById(R.id.my_user_sex);
        myUserPhone= findViewById(R.id.my_user_phone);
        myUserEmail = findViewById(R.id.my_user_email);
        myUserImage = findViewById(R.id.my_user_image);
        initDate();
        //点击“×”，返回上一级界面我的my
        ImageView IvError=findViewById(R.id.iv_error);
        IvError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //点击“√”，完成信息的修改
        ImageView IvRight = findViewById(R.id.iv_right);
        IvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建异步任务类，把数据传过去
                name = myUserName.getText().toString();
                String sex= myUserSex.getText().toString();
                String phone = myUserPhone.getText().toString();
                String email = myUserEmail.getText().toString();
                if(img.equals("")){
                    ChangeMyUerAsyncTask asyncTask = new ChangeMyUerAsyncTask(MyUser.this,name,sex,phone,email);
                    asyncTask.execute();
                }else{
                    ChangeAsyncTask asyncTask = new ChangeAsyncTask(MyUser.this,name,sex,phone,email);
                    asyncTask.execute();
                }
            }
        });
        myUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTypeDialog();
            }
        });
    }

    private void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        final View view = getLayoutInflater().inflate(R.layout.dialog_select_photo, null);
        TextView tv_select_gallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = (TextView) view.findViewById(R.id.tv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                dialog.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {// 调用照相机
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, 2);// 采用ForResult打开
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    private void initDate() {
        Intent intent = getIntent();
        myUserName.setText(intent.getStringExtra("userName"));
        myUserSex.setText(intent.getStringExtra("userSex"));
        myUserPhone.setText(intent.getStringExtra("userTel"));
        myUserEmail.setText(intent.getStringExtra("userEmail"));
        intentImg = intent.getStringExtra("userImg");
        Log.e("test2",intentImg);
        String s = intentImg.replace(" ", "+");
        Log.e("test",s);
        Bitmap bitmap = stringToBitmap(s);
        myUserImage.setImageBitmap(bitmap);
    }


    //修改资料异步任务类
    public class ChangeMyUerAsyncTask extends AsyncTask {
        private Context mContext;
        private String name;
        private String sex;
        private String phone;
        private String email;
        //构造函数，接收更新的视图控件
        public ChangeMyUerAsyncTask(Context mContext,String name,String sex,String phone,String email){
            this.mContext=mContext;
            this.name=name;
            this.sex=sex;
            this.phone=phone;
            this.email=email;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"UserServlet?remark=changeUser&name="+name+"&sex="+sex+"&phone="+phone+"&email="+email+"&userId="+userId+"&img="+intentImg;
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
                user.setUserName(object.getString("userName"));
                user.setUserSex(object.getString("userSex"));
                user.setUserTel(object.getString("userTel"));
                user.setUserEmail(object.getString("userEmail"));
                user.setUserImg(intentImg);
                myUserName.setText(user.getUserName());
                myUserSex.setText(user.getUserSex());
                myUserPhone.setText(user.getUserTel());
                myUserEmail.setText(user.getUserEmail());
                return user;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                Log.e("aaa",e.toString());
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return user;
        }
        protected void onPostExecute(Object o) {
            Intent intent = new Intent();
            intent.putExtra("userName",user.getUserName());
            intent.putExtra("userSex",user.getUserSex());
            intent.putExtra("userTel",user.getUserTel());
            intent.putExtra("userEmail",user.getUserEmail());
            if(b!=null) {
                intent.putExtras(b);
            }else{
                if(!user.getUserImg().equals("")){
                    intent.putExtra("userImg",user.getUserImg());
                }else{
                    intent.putExtra("userImg",intentImg);
                }
            }
            setResult(2,intent);
            finish();
        }
    }

    //修改资料异步任务类2
    public class ChangeAsyncTask extends AsyncTask {
        private Context mContext;
        private String name;
        private String sex;
        private String phone;
        private String email;
        //构造函数，接收更新的视图控件
        public ChangeAsyncTask(Context mContext,String name,String sex,String phone,String email){
            this.mContext=mContext;
            this.name=name;
            this.sex=sex;
            this.phone=phone;
            this.email=email;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            String path=getResources().getString(R.string.url_path);
            String urlStr = path+"UserServlet?remark=change&name="+name+"&sex="+sex+"&phone="+phone+"&email="+email+"&userId="+userId+"&img="+img;
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
                user.setUserName(object.getString("userName"));
                user.setUserImg(object.getString("userImage"));
                user.setUserSex(object.getString("userSex"));
                user.setUserTel(object.getString("userTel"));
                user.setUserEmail(object.getString("userEmail"));
                myUserName.setText(user.getUserName());
                myUserSex.setText(user.getUserSex());
                myUserPhone.setText(user.getUserTel());
                myUserEmail.setText(user.getUserEmail());
                return user;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                Log.e("aaa",e.toString());
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return user;
        }
        protected void onPostExecute(Object o) {
            Intent intent = new Intent();
            intent.putExtra("userName",user.getUserName());
            intent.putExtra("userSex",user.getUserSex());
            intent.putExtra("userTel",user.getUserTel());
            intent.putExtra("userEmail",user.getUserEmail());
            if(b!=null) {
                intent.putExtras(b);
            }else{
                if(!user.getUserImg().equals("")){
                    intent.putExtra("userImg",user.getUserImg());
                }else{
                    intent.putExtra("userImg",intentImg);
                }

            }
            setResult(2,intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        myUserImage.setImageBitmap(head);// 用ImageView显示出来
                        //上传
                        contorlImage(head);
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //imageview取出图片转换为base64格式
    public String getImgBase64(ImageView imageView){
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
        byte[] bb = bos.toByteArray();
        String image = Base64.encodeToString(bb, Base64.DEFAULT);
        return image;
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

    //bitmap转byte数组
    public void contorlImage(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        head.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        int options = 100;
        while (stream.toByteArray().length / 1024>1) { //循环判断如果压缩后图片是否大于1kb,大于继续压缩
            stream.reset();//重置baos即清空baos
            head.compress(Bitmap.CompressFormat.JPEG, options, stream);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
            Log.i("Compress",stream.toByteArray().length+"");
        }
        byte[] bytes = stream.toByteArray();
        img = Base64.encodeToString(bytes, Base64.NO_WRAP);
        Log.e("img",img.length()+"");
        Log.e("img",img);
        b = new Bundle();
        b.putByteArray("bitmap", bytes);
    }


    /**加密*/
    private static String encryptBase64_android(String string){

        return Base64.encodeToString(string.getBytes(), Base64.DEFAULT);
    }
    /**解密*/
    private static String decoderBase64_android(String string){

        return new String(Base64.decode(string.getBytes(), Base64.DEFAULT));
    }

    /**
 * 调用系统的裁剪功能
 *
 * @param uri
 */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }



}
