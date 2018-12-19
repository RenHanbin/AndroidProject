package bean;

public class Index3_CityBean {
	private String cityName;
	private int cityGdp;
	private String cityImg;
	private String cityType;
	private String cityContent;
	private int citySalary;
	private String province;
	private String cityTitle;
	public Index3_CityBean(String cityName, int cityGdp, String cityImg, String cityType, String cityContent,
			int citySalary, String province, String cityTitle) {
		super();
		this.cityName = cityName;
		this.cityGdp = cityGdp;
		this.cityImg = cityImg;
		this.cityType = cityType;
		this.cityContent = cityContent;
		this.citySalary = citySalary;
		this.province = province;
		this.cityTitle = cityTitle;
	}
	public String getCityTitle() {
		return cityTitle;
	}
	public void setCityTitle(String cityTitle) {
		this.cityTitle = cityTitle;
	}
	@Override
	public String toString() {
		return "Index3_CityBean [cityName=" + cityName + ", cityGdp=" + cityGdp + ", cityImg=" + cityImg + ", cityType="
				+ cityType + ", cityContent=" + cityContent + ", citySalary=" + citySalary + ", province=" + province
				+ ", cityTitle=" + cityTitle + "]";
	}
	public Index3_CityBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Index3_CityBean(String cityName, int cityGdp, String cityImg, String cityType, String cityContent,
			int citySalary, String province) {
		super();
		this.cityName = cityName;
		this.cityGdp = cityGdp;
		this.cityImg = cityImg;
		this.cityType = cityType;
		this.cityContent = cityContent;
		this.citySalary = citySalary;
		this.province = province;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public int getCityGdp() {
		return cityGdp;
	}
	public void setCityGdp(int cityGdp) {
		this.cityGdp = cityGdp;
	}
	public String getCityImg() {
		return cityImg;
	}
	public void setCityImg(String cityImg) {
		this.cityImg = cityImg;
	}
	public String getCityType() {
		return cityType;
	}
	public void setCityType(String cityType) {
		this.cityType = cityType;
	}
	public String getCityContent() {
		return cityContent;
	}
	public void setCityContent(String cityContent) {
		this.cityContent = cityContent;
	}
	public int getCitySalary() {
		return citySalary;
	}
	public void setCitySalary(int citySalary) {
		this.citySalary = citySalary;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}

}
