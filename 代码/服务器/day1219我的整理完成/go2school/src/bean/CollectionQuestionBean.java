package bean;

public class CollectionQuestionBean {
	private int userId;
	private int qusetionId;
	public CollectionQuestionBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CollectionQuestionBean(int userId, int qusetionId) {
		super();
		this.userId = userId;
		this.qusetionId = qusetionId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getQusetionId() {
		return qusetionId;
	}
	public void setQusetionId(int qusetionId) {
		this.qusetionId = qusetionId;
	}
	@Override
	public String toString() {
		return "CollectionQuestionBean [userId=" + userId + ", qusetionId=" + qusetionId + "]";
	}
	
}
