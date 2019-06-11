package com.example.celia.demo1.bean;

public class Article {
    private int articleId;
    private String articleTitle;
    private String articleContent;
    private String articleTime;
    private String articleImg;
    private Writer writer;
    private int commentCount;
    private String articleWriterName;
    private String articleWriterImg;

    public Article(int articleId, String articleTitle, String articleContent, String articleTime, String articleImg, Writer writer, int commentCount, String articleWriterName, String articleWriterImg) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleTime = articleTime;
        this.articleImg = articleImg;
        this.writer = writer;
        this.commentCount = commentCount;
        this.articleWriterName = articleWriterName;
        this.articleWriterImg = articleWriterImg;
    }

    public String getArticleWriterName() {
        return articleWriterName;
    }

    public void setArticleWriterName(String articleWriterName) {
        this.articleWriterName = articleWriterName;
    }

    public String getArticleWriterImg() {
        return articleWriterImg;
    }

    public void setArticleWriterImg(String articleWriterImg) {
        this.articleWriterImg = articleWriterImg;
    }

    public Article() {
        super();
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleTime() {
        return articleTime;
    }

    public void setArticleTime(String articleTime) {
        this.articleTime = articleTime;
    }

    public String getArticleImg() {
        return articleImg;
    }

    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public String toString() {
        return "{" +
                "articleId=" + articleId +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", articleTime='" + articleTime + '\'' +
                ", articleImg='" + articleImg + '\'' +
                ", writer=" + writer +
                ", commentCount=" + commentCount +
                ", articleWriterName='" + articleWriterName + '\'' +
                ", articleWriterImg='" + articleWriterImg + '\'' +
                '}';
    }
}
