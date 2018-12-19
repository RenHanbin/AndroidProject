package bean;

public class FollowBean {
	private int followUserId;
	private UserBean followedUser;
	public FollowBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FollowBean(int followUserId, UserBean followedUser) {
		super();
		this.followUserId = followUserId;
		this.followedUser = followedUser;
	}
	public int getFollowUserId() {
		return followUserId;
	}
	public void setFollowUserId(int followUserId) {
		this.followUserId = followUserId;
	}
	public UserBean getFollowedUser() {
		return followedUser;
	}
	public void setFollowedUser(UserBean followedUser) {
		this.followedUser = followedUser;
	}
	@Override
	public String toString() {
		return "FollowBean [followUserId=" + followUserId + ", followedUser=" + followedUser + "]";
	}
	
}
