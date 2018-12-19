package bean;

public class MajorTypeBean {
	private String majorTypeName;
	private int majorTypeId;
	public String getMajorTypeName() {
		return majorTypeName;
	}
	public void setMajorTypeName(String majorTypeName) {
		this.majorTypeName = majorTypeName;
	}
	public int getMajorTypeId() {
		return majorTypeId;
	}
	public void setMajorTypeId(int majorTypeId) {
		this.majorTypeId = majorTypeId;
	}
	public MajorTypeBean(String majorTypeName, int majorTypeId) {
		super();
		this.majorTypeName = majorTypeName;
		this.majorTypeId = majorTypeId;
	}
	public MajorTypeBean() {
		super();
	}
	@Override
	public String toString() {
		return "MajorTypeBean [majorTypeName=" + majorTypeName + ", majorTypeId=" + majorTypeId + "]";
	}
	
}
