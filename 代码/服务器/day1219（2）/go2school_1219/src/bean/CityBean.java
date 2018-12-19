package bean;

public class CityBean {
	private int cityId;
	private String cityName;
	private double cityGdp;
	private String cityImg;
	private String cityContent;
	private double citySalary;
	private ProvinceBean province;
	private CityTypeBean cityType;
	private String schoolName;
	private int schoolRank;
	private String schoolBestMajor;
	
	public CityBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CityBean(int cityId, String cityName, double cityGdp, double citySalary, ProvinceBean province,
			CityTypeBean cityType,String cityImg,String cityContent,String schoolName,int schoolRank,String schoolBestMajor) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.cityGdp = cityGdp;
		this.citySalary = citySalary;
		this.province = province;
		this.cityType = cityType;
		this.cityImg=cityImg;
		this.cityContent=cityContent;
		this.schoolName = schoolName;
		this.schoolRank = schoolRank;
		this.schoolBestMajor = schoolBestMajor;
	}
	
	public CityBean(int cityId, String cityName, double cityGdp, double citySalary, ProvinceBean province,
			CityTypeBean cityType) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.cityGdp = cityGdp;
		this.citySalary = citySalary;
		this.province = province;
		this.cityType = cityType;
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
	public String getSchoolBestMajor() {
		return schoolBestMajor;
	}
	public void setSchoolBestMajor(String schoolBestMajor) {
		this.schoolBestMajor = schoolBestMajor;
	}
	public String getCityContent() {
		return cityContent;
	}
	public void setCityContent(String cityContent) {
		this.cityContent = cityContent;
	}
	public String getCityImg() {
		return cityImg;
	}
	public void setCityImg(String cityImg) {
		this.cityImg = cityImg;
	}
	public ProvinceBean getProvince() {
		return province;
	}
	public void setProvince(ProvinceBean province) {
		this.province = province;
	}
	public CityTypeBean getCityType() {
		return cityType;
	}
	public void setCityType(CityTypeBean cityType) {
		this.cityType = cityType;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public double getCityGdp() {
		return cityGdp;
	}
	public void setCityGdp(double cityGdp) {
		this.cityGdp = cityGdp;
	}
	public double getCitySalary() {
		return citySalary;
	}
	public void setCitySalary(double citySalary) {
		this.citySalary = citySalary;
	}
	
	@Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", cityGdp=" + cityGdp +
                ", citySalary=" + citySalary +
                ", province=" + province +
                ", cityType=" + cityType +
                ", cityImg='" + cityImg + '\'' +
                ", cityContent='" + cityContent + '\'' +
                '}';
    }
	
	
	
	
}
