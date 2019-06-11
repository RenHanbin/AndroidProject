package com.example.celia.demo1.bean;

import java.util.Date;

public class Question {
    private int questionId;
    private String questionTitle;
    private String questionDiscribe;
    private Date questionTime;
    private int userId;
    private UserBean questionUser;//创建此问题的用户
    private int userCount;//关注此问题的人数
    private String questionUserName;
    private String questionUserImg;

    public Question(int questionId, String questionTitle, String questionDiscribe, Date questionTime, int userId, UserBean questionUser, int userCount, String questionUserName, String questionUserImg) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.questionDiscribe = questionDiscribe;
        this.questionTime = questionTime;
        this.userId = userId;
        this.questionUser = questionUser;
        this.userCount = userCount;
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

    public Date getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(Date questionTime) {
        this.questionTime = questionTime;
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

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionDiscribe='" + questionDiscribe + '\'' +
                ", questionTime=" + questionTime +
                ", userId=" + userId +
                ", questionUser=" + questionUser +
                ", userCount=" + userCount +
                ", questionUserName='" + questionUserName + '\'' +
                ", questionUserImg='" + questionUserImg + '\'' +
                '}';
    }
}
