package bean;

public class SchoolBean {
	private int schoolId;
	private String schoolName;
	private int schoolRank;
	private int cityId;
	private String cityName;
	private String schoolImg;
	private String schoolContent;
	private String schoolNum;
	private int schoolTypeId;
	private String schoolBestMajor;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
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
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getSchoolImg() {
		return schoolImg;
	}
	public void setSchoolImg(String schoolImg) {
		this.schoolImg = schoolImg;
	}
	public String getSchoolContent() {
		return schoolContent;
	}
	public void setSchoolContent(String schoolContent) {
		this.schoolContent = schoolContent;
	}
	public String getSchoolNum() {
		return schoolNum;
	}
	public void setSchoolNum(String schoolNum) {
		this.schoolNum = schoolNum;
	}
	public int getSchoolTypeId() {
		return schoolTypeId;
	}
	public void setSchoolTypeId(int schoolTypeId) {
		this.schoolTypeId = schoolTypeId;
	}
	public String getSchoolBestMajor() {
		return schoolBestMajor;
	}
	public void setSchoolBestMajor(String schoolBestMajor) {
		this.schoolBestMajor = schoolBestMajor;
	}
	
	public SchoolBean(int schoolId, String schoolName, int schoolRank, int cityId, String cityName, String schoolImg,
			String schoolContent, String schoolNum, int schoolTypeId, String schoolBestMajor) {
		super();
		this.schoolId = schoolId;
		this.schoolName = schoolName;
		this.schoolRank = schoolRank;
		this.cityId = cityId;
		this.cityName = cityName;
		this.schoolImg = schoolImg;
		this.schoolContent = schoolContent;
		this.schoolNum = schoolNum;
		this.schoolTypeId = schoolTypeId;
		this.schoolBestMajor = schoolBestMajor;
	}
	public SchoolBean() {
		super();
	}
	@Override
	public String toString() {
		return "SchoolBean [schoolId=" + schoolId + ", schoolName=" + schoolName + ", schoolRank=" + schoolRank
				+ ", cityId=" + cityId + ", schoolImg=" + schoolImg + ", schoolContent=" + schoolContent
				+ ", schoolNum=" + schoolNum + ", schoolTypeId=" + schoolTypeId + ", schoolBestMajor=" + schoolBestMajor
				+ "]";
	}
	
}
