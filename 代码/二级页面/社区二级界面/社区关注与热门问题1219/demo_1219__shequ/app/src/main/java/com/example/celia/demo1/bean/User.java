package com.example.celia.demo1.bean;


public class User {

    private int userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userTel;
    private String userSex;
    private String userImage;
    private String userBalance;
    private int userFans;
    private int userAtten;

    public User(){
        userSex="男";
        userEmail=null;
    }

    public User(int userId, String userName, String userPassword, String userEmail, String userTel, String userSex, String userImage, String userBalance, int userFans, int userAtten) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userTel = userTel;
        this.userSex = userSex;
        this.userImage = userImage;
        this.userBalance = userBalance;
        this.userFans = userFans;
        this.userAtten = userAtten;
    }

    public int getUserFans() {
        return userFans;
    }

    public void setUserFans(int userFans) {
        this.userFans = userFans;
    }

    public int getUserAtten() {
        return userAtten;
    }

    public void setUserAtten(int userAtten) {
        this.userAtten = userAtten;
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

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(String userBalance) {
        this.userBalance = userBalance;
    }

    @Override
    public String toString() {
        return "UserBean [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword
                + ", userEmail=" + userEmail + ", userTel=" + userTel + ", userSex=" + userSex + ", userImage="
                + userImage + ", userBalance=" + userBalance + "]";
    }
}


