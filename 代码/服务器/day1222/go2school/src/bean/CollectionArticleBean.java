package bean;

public class CollectionArticleBean {

	private int userId;
	private int articleId;
	public CollectionArticleBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CollectionArticleBean(int userId, int articleId) {
		super();
		this.userId = userId;
		this.articleId = articleId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	@Override
	public String toString() {
		return "CollectionArticleBean [userId=" + userId + ", articleId=" + articleId + "]";
	}
}
