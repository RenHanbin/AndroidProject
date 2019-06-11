package net.onest.go2school.entity;

public class Answer {

	private int answerId;
	private String answerContent;
	private String answerTime;
	private int questionId;
	private User user;//回答所属的用户
	private String answerUserName;
    private String answerUserImg;
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
	public String getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getAnswerUserName() {
		return answerUserName;
	}
	public void setAnswerUserName(String answerUserName) {
		this.answerUserName = answerUserName;
	}
	public String getAnswerUserImg() {
		return answerUserImg;
	}
	public void setAnswerUserImg(String answerUserImg) {
		this.answerUserImg = answerUserImg;
	}
	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Answer(int answerId, String answerContent, String answerTime, int questionId, User user, int userId,
			String answerUserName, String answerUserImg) {
		super();
		this.answerId = answerId;
		this.answerContent = answerContent;
		this.answerTime = answerTime;
		this.questionId = questionId;
		this.user = user;
		this.answerUserName = answerUserName;
		this.answerUserImg = answerUserImg;
	}
	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", answerContent=" + answerContent + ", answerTime=" + answerTime
				+ ", questionId=" + questionId + ", user=" + user + ", answerUserName="
				+ answerUserName + ", answerUserImg=" + answerUserImg + "]";
	}
    
}
