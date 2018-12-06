package bean;

public class SchoolTypeBean {
	private int schoolTypeId;
	private String schoolTypeName;
	public int getSchoolTypeId() {
		return schoolTypeId;
	}
	public void setSchoolTypeId(int schoolTypeId) {
		this.schoolTypeId = schoolTypeId;
	}
	public String getSchoolTypeName() {
		return schoolTypeName;
	}
	public void setSchoolTypeName(String schoolTypeName) {
		this.schoolTypeName = schoolTypeName;
	}
	public SchoolTypeBean(int schoolTypeId, String schoolTypeName) {
		super();
		this.schoolTypeId = schoolTypeId;
		this.schoolTypeName = schoolTypeName;
	}
	public SchoolTypeBean() {
		super();
	}
	@Override
	public String toString() {
		return "SchoolTypeBean [schoolTypeId=" + schoolTypeId + ", schoolTypeName=" + schoolTypeName + "]";
	}
	
}
