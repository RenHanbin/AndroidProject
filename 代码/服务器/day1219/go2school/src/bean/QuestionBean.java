package bean;

import java.util.Date;

public class QuestionBean {
	private int questionId;
	private String questionTitle;
	private String questionDescribe;
	private String questionTime;
	private int userId;
	private String questionImg;
	private int userCount; //关注问题人数
	private UserBean questionUser;//创建问题用户
	
	public QuestionBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public QuestionBean(int questionId, String questionTitle, String questionDescribe, String questionTime, int userId,
			String questionImg, int userCount, UserBean questionUser) {
		super();
		this.questionId = questionId;
		this.questionTitle = questionTitle;
		this.questionDescribe = questionDescribe;
		this.questionTime = questionTime;
		this.userId = userId;
		this.questionImg = questionImg;
		this.userCount = userCount;
		this.questionUser = questionUser;
	}


	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	public UserBean getQuestionUser() {
		return questionUser;
	}
	public void setQuestionUser(UserBean questionUser) {
		this.questionUser = questionUser;
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
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getQuestionDescribe() {
		return questionDescribe;
	}


	public void setQuestionDescribe(String questionDescribe) {
		this.questionDescribe = questionDescribe;
	}


	public String getQuestionTime() {
		return questionTime;
	}


	public void setQuestionTime(String questionTime) {
		this.questionTime = questionTime;
	}


	@Override
	public String toString() {
		return "QuestionBean [questionId=" + questionId + ", questionTitle=" + questionTitle + ", questionDescribe="
				+ questionDescribe + ", questionTime=" + questionTime + ", userId=" + userId + ", questionImg="
				+ questionImg + ", userCount=" + userCount + ", questionUser=" + questionUser + "]";
	}
	
	
}
