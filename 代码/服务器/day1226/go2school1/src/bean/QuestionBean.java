package bean;

import java.util.Date;

public class QuestionBean {
	private int questionId;
	private String questionTitle;
	private String questionDiscribe;
	private Date questionTime;
	private int userId;
	private UserBean questionUser;//创建此问题的用户
	private int userCount;//关注此问题的人数
	private String questionImg;
	public QuestionBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	public QuestionBean(int questionId, String questionTitle, String questionDiscribe, Date questionTime, int userId,
			UserBean questionUser, int userCount, String questionImg) {
		super();
		this.questionId = questionId;
		this.questionTitle = questionTitle;
		this.questionDiscribe = questionDiscribe;
		this.questionTime = questionTime;
		this.userId = userId;
		this.questionUser = questionUser;
		this.userCount = userCount;
		this.questionImg = questionImg;
	}

	public String getQuestionImg() {
		return questionImg;
	}

	public void setQuestionImg(String questionImg) {
		this.questionImg = questionImg;
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
		return "QuestionBean [questionId=" + questionId + ", questionTitle=" + questionTitle + ", questionDiscribe="
				+ questionDiscribe + ", questionTime=" + questionTime + ", userId=" + userId + ", questionUser="
				+ questionUser + ", userCount=" + userCount + ", questionImg=" + questionImg + "]";
	}
	 
	
	
}
