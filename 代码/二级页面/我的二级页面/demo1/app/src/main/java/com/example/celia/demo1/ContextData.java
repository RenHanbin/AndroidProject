package com.example.celia.demo1;

import android.app.Application;

public class ContextData extends Application {
    private int userId;
    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId=userId;
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
