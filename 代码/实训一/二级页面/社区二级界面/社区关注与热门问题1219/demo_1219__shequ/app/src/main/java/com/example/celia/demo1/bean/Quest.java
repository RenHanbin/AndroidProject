package com.example.celia.demo1.bean;


public class Quest {
    private int questionId;
    private String questionTitle;
    private String questionDiscribe;
    private String questionTime;
    private String questionImg;
    private int userId;
    private int attenNum;
    private int commNum;
    private String userImg;
    private String userName;

    public Quest() {
        super();
    }

    public Quest(int questionId, String questionTitle, String questionDiscribe, String questionTime, String questionImg, int userId, int attenNum, int commNum, String userImg, String userName) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.questionDiscribe = questionDiscribe;
        this.questionTime = questionTime;
        this.questionImg = questionImg;
        this.userId = userId;
        this.attenNum = attenNum;
        this.commNum = commNum;
        this.userImg = userImg;
        this.userName = userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQuestionImg() {
        return questionImg;
    }

    public void setQuestionImg(String questionImg) {
        this.questionImg = questionImg;
    }

    public int getAttenNum() {
        return attenNum;
    }

    public void setAttenNum(int attenNum) {
        this.attenNum = attenNum;
    }

    public int getCommNum() {
        return commNum;
    }

    public void setCommNum(int commNum) {
        this.commNum = commNum;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionDiscribe() {
        return questionDiscribe;
    }

    public void setQuestionDiscribe(String questionDiscribe) {
        this.questionDiscribe = questionDiscribe;
    }

    public String getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(String questionTime) {
        this.questionTime = questionTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Quest{" +
                "questionId=" + questionId +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionDiscribe='" + questionDiscribe + '\'' +
                ", questionTime='" + questionTime + '\'' +
                ", questionImg='" + questionImg + '\'' +
                ", userId=" + userId +
                ", attenNum=" + attenNum +
                ", commNum=" + commNum +
                ", userImg='" + userImg + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}