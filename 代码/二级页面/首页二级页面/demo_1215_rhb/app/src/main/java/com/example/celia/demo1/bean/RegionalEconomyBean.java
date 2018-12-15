package com.example.celia.demo1.bean;

public class RegionalEconomyBean {

        private String cityName;
        private int cityGdp;
        private int collegeNum;
        private String cityImg;
        private String cityType;
        private String cityContent;
        private int citySalary;
        private String province;
        public String getCityTitle() {
            return cityTitle;
        }
        public void setCityTitle(String cityTitle) {
            this.cityTitle = cityTitle;
        }
        public RegionalEconomyBean(String cityName, int cityGdp, int collegeNum, String cityImg, String cityType,
                                   String cityContent, int citySalary, String province, String cityTitle) {
            super();
            this.cityName = cityName;
            this.cityGdp = cityGdp;
            this.collegeNum = collegeNum;
            this.cityImg = cityImg;
            this.cityType = cityType;
            this.cityContent = cityContent;
            this.citySalary = citySalary;
            this.province = province;
            this.cityTitle = cityTitle;
        }
        private String cityTitle;
        public RegionalEconomyBean(String cityName, int cityGdp, int collegeNum, String cityImg, String cityType,
                                   String cityContent, int citySalary, String province) {
            super();
            this.cityName = cityName;
            this.cityGdp = cityGdp;
            this.collegeNum = collegeNum;
            this.cityImg = cityImg;
            this.cityType = cityType;
            this.cityContent = cityContent;
            this.citySalary = citySalary;
            this.province = province;
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
                    + ", cityImg=" + cityImg + ", cityType=" + cityType + ", cityContent=" + cityContent + ", citySalary="
                    + citySalary + ", province=" + province + ", cityTitle=" + cityTitle + "]";
        }

    }



