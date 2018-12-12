package com.example.celia.demo1.bean;

public class RegionalEconomyBean {
	private String cityName;
	private int cityGdp;
	private int collegeNum;
	public int getCollegeNum() {
		return collegeNum;
	}
	public void setCollegeNum(int collegeNum) {
		this.collegeNum = collegeNum;
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
	public RegionalEconomyBean(String cityName, int cityGdp, int collegeNum) {
		super();
		this.cityName = cityName;
		this.cityGdp = cityGdp;
		this.collegeNum = collegeNum;
	}
	public RegionalEconomyBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "RegionalEconomyBean [cityName=" + cityName + ", cityGdp=" + cityGdp + ", collegeNum=" + collegeNum
				+ "]";
	}

}


