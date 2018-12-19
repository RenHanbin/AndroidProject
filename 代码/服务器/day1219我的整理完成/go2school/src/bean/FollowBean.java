package bean;

public class FollowBean {
	private int followUserId;
	private UserBean followedUser;
	private int followedUserId;
	public FollowBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	public FollowBean(int followUserId, UserBean followedUser, int followedUserId) {
		super();
		this.followUserId = followUserId;
		this.followedUser = followedUser;
		this.followedUserId = followedUserId;
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
	
	
	
	public int getFollowedUserId() {
		return followedUserId;
	}

	public void setFollowedUserId(int followedUserId) {
		this.followedUserId = followedUserId;
	}

	@Override
	public String toString() {
		return "FollowBean [followUserId=" + followUserId + ", followedUser=" + followedUser + ", followedUserId="
				+ followedUserId + "]";
	}

 
}
