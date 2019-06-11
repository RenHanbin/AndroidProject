package net.onest.go2school.entity;

import java.util.Date;

public class Article {
	private int articleId;
	private String articleTitle;
	private String articleContent;
	private Date articleTime;
	private String articleImg;
	private Writer writer;
	private int articleType;
	private User user;
	
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Article(int articleId, String articleTitle, String articleContent, Date articleTime, String articleImg,
			Writer writer, int articleType, User user) {
		super();
		this.articleId = articleId;
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
		this.articleTime = articleTime;
		this.articleImg = articleImg;
		this.writer = writer;
		this.articleType = articleType;
		this.user = user;
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

	public Date getArticleTime() {
		return articleTime;
	}

	public void setArticleTime(Date articleTime) {
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

	public int getArticleType() {
		return articleType;
	}

	public void setArticleType(int articleType) {
		this.articleType = articleType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", articleTitle=" + articleTitle + ", articleContent="
				+ articleContent + ", articleTime=" + articleTime + ", articleImg=" + articleImg + ", writer=" + writer
				+ ", articleType=" + articleType + ", user=" + user + "]";
	}
	
	
}
