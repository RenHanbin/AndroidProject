package bean;

import java.util.Date;

public class QuestionBean {
	private int questionId;
	private String questionTitle;
	private String questionDiscribe;
	private Date questionTime;
	private int userId;
	
	public QuestionBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public QuestionBean(int questionId, String questionTitle, String questionDiscribe, Date questionTime, int userId,
			int attenNum, int commNum) {
		super();
		this.questionId = questionId;
		this.questionTitle = questionTitle;
		this.questionDiscribe = questionDiscribe;
		this.questionTime = questionTime;
		this.userId = userId;
//		this.attenNum = attenNum;
//		this.commNum = commNum;
	}

//	public int getAttenNum() {
//		return attenNum;
//	}
//	public void setAttenNum(int attenNum) {
//		this.attenNum = attenNum;
//	}
//	public int getCommNum() {
//		return commNum;
//	}
//	public void setCommNum(int commNum) {
//		this.commNum = commNum;
//	}
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
	@Override
	public String toString() {
		return "QuestionBean [questionId=" + questionId + ", questionTitle=" + questionTitle + ", questionDiscribe="
				+ questionDiscribe + ", questionTime=" + questionTime + ", userId=" + userId + "]";
	}
	
	
}
