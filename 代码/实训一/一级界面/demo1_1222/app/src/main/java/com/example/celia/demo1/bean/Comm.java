package com.example.celia.demo1.bean;

import java.util.Date;

public class Comm {
    private int commentId;
    private String commentContent;
    private String commentTime;
    private String answerContent;
    private int userId;
    private String articleTitle;

    public Comm() {
        super();
    }

    public Comm(int commentId, String commentContent, String commentTime, String answerContent, int userId, String articleTitle) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
        this.answerContent = answerContent;
        this.userId = userId;
        this.articleTitle = articleTitle;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    @Override
    public String toString() {
        return "Comm{" +
                "commentId=" + commentId +
                ", commentContent='" + commentContent + '\'' +
                ", commentTime='" + commentTime + '\'' +
                ", answerContent='" + answerContent + '\'' +
                ", userId=" + userId +
                ", articleTitle='" + articleTitle + '\'' +
                '}';
    }
}
