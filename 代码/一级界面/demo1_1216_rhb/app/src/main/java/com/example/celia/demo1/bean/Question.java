package com.example.celia.demo1.bean;

import java.util.Date;

public class Question {
    private int questionId;
    private String questionImg;
    private String questionTitle;
    private String questionDiscribe;
    private String questionTime;
    private int userId;
    private UserBean questionUser;//创建此问题的用户
    private int userCount;//关注此问题的人数
    private int commNum; //评论此问题的人数
    private String questionUserName;
    private String questionUserImg;

    public Question(int questionId, String questionImg, String questionTitle, String questionDiscribe, String questionTime, int userId, UserBean questionUser, int userCount, int commNum, String questionUserName, String questionUserImg) {
        this.questionId = questionId;
        this.questionImg = questionImg;
        this.questionTitle = questionTitle;
        this.questionDiscribe = questionDiscribe;
        this.questionTime = questionTime;
        this.userId = userId;
        this.questionUser = questionUser;
        this.userCount = userCount;
        this.commNum = commNum;
        this.questionUserName = questionUserName;
        this.questionUserImg = questionUserImg;
    }

    public Question() {
        super();
    }

    public String getQuestionUserName() {
        return questionUserName;
    }

    public void setQuestionUserName(String questionUserName) {
        this.questionUserName = questionUserName;
    }

    public String getQuestionUserImg() {
        return questionUserImg;
    }

    public void setQuestionUserImg(String questionUserImg) {
        this.questionUserImg = questionUserImg;
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

    public int getCommNum() {
        return commNum;
    }

    public void setCommNum(int commNum) {
        this.commNum = commNum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserBean getQuestionUser() {
        return questionUser;
    }

    public void setQuestionUser(UserBean questionUser) {
        this.questionUser = questionUser;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public String getQuestionImg() {
        return questionImg;
    }

    public void setQuestionImg(String questionImg) {
        this.questionImg = questionImg;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", questionImg='" + questionImg + '\'' +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionDiscribe='" + questionDiscribe + '\'' +
                ", questionTime='" + questionTime + '\'' +
                ", userId=" + userId +
                ", questionUser=" + questionUser +
                ", userCount=" + userCount +
                ", commNum=" + commNum +
                ", questionUserName='" + questionUserName + '\'' +
                ", questionUserImg='" + questionUserImg + '\'' +
                '}';
    }
}
