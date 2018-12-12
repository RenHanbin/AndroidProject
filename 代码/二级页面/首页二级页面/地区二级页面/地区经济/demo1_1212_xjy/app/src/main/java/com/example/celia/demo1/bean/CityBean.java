package com.example.celia.demo1.bean;

public class CityBean {
	private int cityId;
	private String cityName;
	private double cityGdp;
	private double citySalary;
	private ProvinceBean province;
	private CityTypeBean cityType;
	private String schoolName;
	private int schoolRank;
	private String schoolBestMajor;
	
	public CityBean(int cityId, String cityName, double cityGdp, double citySalary, ProvinceBean province,
			CityTypeBean cityType, String schoolName, int schoolRank, String schoolBestMajor) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.cityGdp = cityGdp;
		this.citySalary = citySalary;
		this.province = province;
		this.cityType = cityType;
		this.schoolName = schoolName;
		this.schoolRank = schoolRank;
		this.schoolBestMajor = schoolBestMajor;
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
	public CityBean() {
		super();
		// TODO Auto-generated constructor stub
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cityGdp);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + cityId;
		result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
		temp = Double.doubleToLongBits(citySalary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((cityType == null) ? 0 : cityType.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
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
		CityBean other = (CityBean) obj;
		if (Double.doubleToLongBits(cityGdp) != Double.doubleToLongBits(other.cityGdp))
			return false;
		if (cityId != other.cityId)
			return false;
		if (cityName == null) {
			if (other.cityName != null)
				return false;
		} else if (!cityName.equals(other.cityName))
			return false;
		if (Double.doubleToLongBits(citySalary) != Double.doubleToLongBits(other.citySalary))
			return false;
		if (cityType == null) {
			if (other.cityType != null)
				return false;
		} else if (!cityType.equals(other.cityType))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CityBean [cityId=" + cityId + ", cityName=" + cityName + ", cityGdp=" + cityGdp + ", citySalary="
				+ citySalary + ", province=" + province + ", cityType=" + cityType + ", schoolName=" + schoolName
				+ ", schoolRank=" + schoolRank + ", schoolBestMajor=" + schoolBestMajor + "]";
	}
	
	
}
