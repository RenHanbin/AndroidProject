package bean;

import java.util.Date;

public class CommentBean {
	private int commentId;
	private String commentContent;
	private Date commentTime;
	private int answerId;
	private AnswerBean answer;//回答的内容 anser.answerContent
	private int userId;
	private Article article;//文章的题目article.articleTitle
	private int articleId;
	public CommentBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentBean(int commentId, String commentContent, Date commentTime, int answerId, AnswerBean answer,
			int userId, Article article, int articleId) {
		super();
		this.commentId = commentId;
		this.commentContent = commentContent;
		this.commentTime = commentTime;
		this.answerId = answerId;
		this.answer = answer;
		this.userId = userId;
		this.article = article;
		this.articleId = articleId;
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
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public AnswerBean getAnswer() {
		return answer;
	}
	public void setAnswer(AnswerBean answer) {
		this.answer = answer;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	@Override
	public String toString() {
		return "CommentBean [commentId=" + commentId + ", commentContent=" + commentContent + ", commentTime="
				+ commentTime + ", answerId=" + answerId + ", answer=" + answer + ", userId=" + userId + ", article="
				+ article + ", articleId=" + articleId + "]";
	}
	
	
}
