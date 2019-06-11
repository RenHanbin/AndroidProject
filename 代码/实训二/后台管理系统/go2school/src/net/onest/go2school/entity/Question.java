package net.onest.go2school.entity;

import java.util.Date;

public class Question {
	
	private int questionId;
	private String questionTitle;
	private String questionDiscribe;
	private Date questionTime;
	private User questionUser;//创建此问题的用户
	private String questionImg;
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
	public User getQuestionUser() {
		return questionUser;
	}
	public void setQuestionUser(User questionUser) {
		this.questionUser = questionUser;
	}
	public String getQuestionImg() {
		return questionImg;
	}
	public void setQuestionImg(String questionImg) {
		this.questionImg = questionImg;
	}
	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", questionTitle=" + questionTitle + ", questionDiscribe="
				+ questionDiscribe + ", questionTime=" + questionTime + ", questionUser=" + questionUser
				+ ", questionImg=" + questionImg + "]";
	}
	public Question(int questionId, String questionTitle, String questionDiscribe, Date questionTime, User questionUser,
			String questionImg) {
		super();
		this.questionId = questionId;
		this.questionTitle = questionTitle;
		this.questionDiscribe = questionDiscribe;
		this.questionTime = questionTime;
		this.questionUser = questionUser;
		this.questionImg = questionImg;
	}
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
