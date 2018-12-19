package bean;

import java.util.Date;

public class AnswerBean {
	private int answerId;
	private String answerContent;
	private Date answerTime;
	private int questionId;
	private int userId;
	
	public AnswerBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AnswerBean(int answerId, String answerContent, Date answerTime, int questionId, int userId) {
		super();
		this.answerId = answerId;
		this.answerContent = answerContent;
		this.answerTime = answerTime;
		this.questionId = questionId;
		this.userId = userId;
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
		return "AnswerBean [answerId=" + answerId + ", answerContent=" + answerContent + ", answerTime=" + answerTime
				+ ", questionId=" + questionId + ", userId=" + userId + "]";
	}
}
