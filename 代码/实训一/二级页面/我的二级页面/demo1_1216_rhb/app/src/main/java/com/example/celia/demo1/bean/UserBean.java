package com.example.celia.demo1.bean;

import java.io.Serializable;

public class UserBean implements Serializable{
    private int userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userTel;
    private String userSex;
    private String userImg;
    private double userBalance;

    public UserBean(){
        userSex="男";
        userEmail=null;
        userBalance=0.00;
    }

    public UserBean(int userId,String userImg,double userBalance, String userName, String userPassword, String userEmail, String userTel, String userSex) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userTel = userTel;
        this.userSex = userSex;
        this.userBalance=userBalance;
        this.userImg=userImg;
    }

    public double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(double userBalance) {
        this.userBalance = userBalance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    @Override
    public String toString() {
        return "{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userImg='" + userImg + '\'' +
                ", userBalance=" + userBalance +
                '}';
    }
}
