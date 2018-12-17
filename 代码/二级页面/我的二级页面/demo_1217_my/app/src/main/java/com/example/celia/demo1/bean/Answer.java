package com.example.celia.demo1.bean;

import java.util.Date;

public class Answer {
    private int answerId;
    private String answerContent;
    private Date answerTime;
    private int questionId;
    private int userId;

    public Answer(int answerId, String answerContent, Date answerTime, int questionId, int userId) {
        this.answerId = answerId;
        this.answerContent = answerContent;
        this.answerTime = answerTime;
        this.questionId = questionId;
        this.userId = userId;
    }

    public Answer() {
        super();
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", answerContent='" + answerContent + '\'' +
                ", answerTime=" + answerTime +
                ", questionId=" + questionId +
                ", userId=" + userId +
                '}';
    }
}
