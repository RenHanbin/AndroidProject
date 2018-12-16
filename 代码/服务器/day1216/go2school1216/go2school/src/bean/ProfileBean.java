package bean;

public class ProfileBean {
	private String schoolName;
	private int schoolRank;
	private String schoolBestMajor;
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public int getSchoolRank() {
		return schoolRank;
	}
	public void setSchoolRank(int schoolRank) {
		this.schoolRank = schoolRank;
	}
	public String getSchoolBestMajor() {
		return schoolBestMajor;
	}
	public void setSchoolBestMajor(String schoolBestMajor) {
		this.schoolBestMajor = schoolBestMajor;
	}
	public ProfileBean(String schoolName, int schoolRank, String schoolBestMajor) {
		super();
		this.schoolName = schoolName;
		this.schoolRank = schoolRank;
		this.schoolBestMajor = schoolBestMajor;
	}
	public ProfileBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ProfileBean [schoolName=" + schoolName + ", schoolRank=" + schoolRank + ", schoolBestMajor="
				+ schoolBestMajor + "]";
	}
	

}
