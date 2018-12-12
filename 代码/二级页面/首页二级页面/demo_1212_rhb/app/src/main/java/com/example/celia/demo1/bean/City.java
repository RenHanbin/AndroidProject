package com.example.celia.demo1.bean;

public class City {
    private int cityId;
    private String cityName;
    private double cityGdp;
    private double citySalary;
    private Province province;
    private CityType cityType;
    private String cityImg;
    private String cityContent;
    public City() {
        super();
        // TODO Auto-generated constructor stub
    }
    public City(int cityId, String cityName, double cityGdp, double citySalary, Province province,
                    CityType cityType,String cityImg,String cityContent) {
        super();
        this.cityId = cityId;
        this.cityName = cityName;
        this.cityGdp = cityGdp;
        this.citySalary = citySalary;
        this.province = province;
        this.cityType = cityType;
        this.cityImg=cityImg;
        this.cityContent=cityContent;
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
    public Province getProvince() {
        return province;
    }
    public void setProvince(Province province) {
        this.province = province;
    }
    public CityType getCityType() {
        return cityType;
    }
    public void setCityType(CityType cityType) {
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
