package bean;

public class AnswerBean {
	private int answerId;
	private String answerContent;
	private String answerTime;
	private int questionId;
	private UserBean user;
	private int userId;
	public AnswerBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AnswerBean(int answerId, String answerContent, String answerTime, int questionId, UserBean user,
			int userId) {
		super();
		this.answerId = answerId;
		this.answerContent = answerContent;
		this.answerTime = answerTime;
		this.questionId = questionId;
		this.user = user;
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
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
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
				+ ", questionId=" + questionId + ", user=" + user + ", userId=" + userId + "]";
	}
	
}
