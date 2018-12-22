package bean;

public class CollectionMajorBean {
	private int majorId;
	private int userId;
	
	public CollectionMajorBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CollectionMajorBean(int majorId, int userId) {
		super();
		this.majorId = majorId;
		this.userId = userId;
	}

	public int getMajorId() {
		return majorId;
	}
	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + majorId;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CollectionMajorBean other = (CollectionMajorBean) obj;
		if (majorId != other.majorId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CollectionMajor [majorId=" + majorId + ", userId=" + userId + "]";
	}
	
}
