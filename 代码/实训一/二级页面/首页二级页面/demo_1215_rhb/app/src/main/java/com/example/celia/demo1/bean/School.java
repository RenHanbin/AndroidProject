package com.example.celia.demo1.bean;

public class School {
    private int schoolId;
    private String schoolName;
    private int schoolRank;
    private int cityId;
    private String cityName;
    private String schoolImg;
    private String schoolContent;
    private String schoolNum;
    private int schoolTypeId;
    private String schoolTypeName;
    private String schoolBestMajor;
    private String salary;
    private String need;
    private String low_grade;
    private int low_rank;

    public String getSchoolTypeName() {
        return schoolTypeName;
    }
    public void setSchoolTypeName(String schoolTypeName) {
        this.schoolTypeName = schoolTypeName;
    }
    public String getNeed() {
        return need;
    }
    public void setNeed(String need) {
        this.need = need;
    }
    public String getLow_grade() {
        return low_grade;
    }
    public void setLow_grade(String low_grade) {
        this.low_grade = low_grade;
    }
    public int getLow_rank() {
        return low_rank;
    }
    public void setLow_rank(int low_rank) {
        this.low_rank = low_rank;
    }
    public String getSalary() {
        return salary;
    }
    public void setSalary(String salary) {
        this.salary = salary;
    }
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

    public School() {
        super();
    }

    public School(int schoolId, String schoolName, int schoolRank, int cityId, String cityName, String schoolImg, String schoolContent, String schoolNum, int schoolTypeId, String schoolTypeName, String schoolBestMajor, String salary, String need, String low_grade, int low_rank) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.schoolRank = schoolRank;
        this.cityId = cityId;
        this.cityName = cityName;
        this.schoolImg = schoolImg;
        this.schoolContent = schoolContent;
        this.schoolNum = schoolNum;
        this.schoolTypeId = schoolTypeId;
        this.schoolTypeName = schoolTypeName;
        this.schoolBestMajor = schoolBestMajor;
        this.salary = salary;
        this.need = need;
        this.low_grade = low_grade;
        this.low_rank = low_rank;
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolId=" + schoolId +
                ", schoolName='" + schoolName + '\'' +
                ", schoolRank=" + schoolRank +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", schoolImg='" + schoolImg + '\'' +
                ", schoolContent='" + schoolContent + '\'' +
                ", schoolNum='" + schoolNum + '\'' +
                ", schoolTypeId=" + schoolTypeId +
                ", schoolTypeName='" + schoolTypeName + '\'' +
                ", schoolBestMajor='" + schoolBestMajor + '\'' +
                ", salary='" + salary + '\'' +
                ", need='" + need + '\'' +
                ", low_grade='" + low_grade + '\'' +
                ", low_rank=" + low_rank +
                '}';
    }
}
