package com.example.celia.demo1.bean;


public class ProvinceStudent {

    private int provinceId;
    private int studentTypeId;
    private int firstGradeLine;
    private int secondGradeLine;
    private int thirdGradeLine;
    private int otherGradeLine;
    public int getProvinceId() {
        return provinceId;
    }
    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
    public int getStudentTypeId() {
        return studentTypeId;
    }
    public void setStudentTypeId(int studentTypeId) {
        this.studentTypeId = studentTypeId;
    }
    public int getFirstGradeLine() {
        return firstGradeLine;
    }
    public void setFirstGradeLine(int firstGradeLine) {
        this.firstGradeLine = firstGradeLine;
    }
    public int getSecondGradeLine() {
        return secondGradeLine;
    }
    public void setSecondGradeLine(int secondGradeLine) {
        this.secondGradeLine = secondGradeLine;
    }
    public int getThirdGradeLine() {
        return thirdGradeLine;
    }
    public void setThirdGradeLine(int thirdGradeLine) {
        this.thirdGradeLine = thirdGradeLine;
    }
    public int getOtherGradeLine() {
        return otherGradeLine;
    }
    public void setOtherGradeLine(int otherGradeLine) {
        this.otherGradeLine = otherGradeLine;
    }
    public ProvinceStudent(int provinceId, int studentTypeId, int firstGradeLine, int secondGradeLine,
                           int thirdGradeLine, int otherGradeLine) {
        super();
        this.provinceId = provinceId;
        this.studentTypeId = studentTypeId;
        this.firstGradeLine = firstGradeLine;
        this.secondGradeLine = secondGradeLine;
        this.thirdGradeLine = thirdGradeLine;
        this.otherGradeLine = otherGradeLine;
    }
    public ProvinceStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "{" +
                "provinceId=" + provinceId +
                ", studentTypeId=" + studentTypeId +
                ", firstGradeLine=" + firstGradeLine +
                ", secondGradeLine=" + secondGradeLine +
                ", thirdGradeLine=" + thirdGradeLine +
                ", otherGradeLine=" + otherGradeLine +
                '}';
    }
}

