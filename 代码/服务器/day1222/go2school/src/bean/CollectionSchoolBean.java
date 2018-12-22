package bean;

public class CollectionSchoolBean {
	private int schoolId;
	private int userId;
	public CollectionSchoolBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CollectionSchoolBean(int schoolId, int userId) {
		super();
		this.schoolId = schoolId;
		this.userId = userId;
	}
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
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
		result = prime * result + schoolId;
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
		CollectionSchoolBean other = (CollectionSchoolBean) obj;
		if (schoolId != other.schoolId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CollectionSchoolBean [schoolId=" + schoolId + ", userId=" + userId + "]";
	}
	
}
