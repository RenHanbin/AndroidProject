package com.example.celia.demo1.bean;

public class Major {

    private int majorId;
    private String majorName;
    private double majorWorkPercent;
    private int majorSalary;
    private int majorTypeId;
    private String majorTypeName;
    private int majorWant;
    private int majorNeed;
    private double majorStudy;
    private double majorGo;
    private String majorIntroduce;
    private String majorSubject;
    private String majorWork;


    public String getMajorIntroduce() {
        return majorIntroduce;
    }
    public void setMajorIntroduce(String majorIntroduce) {
        this.majorIntroduce = majorIntroduce;
    }
    public String getMajorSubject() {
        return majorSubject;
    }
    public void setMajorSubject(String majorSubject) {
        this.majorSubject = majorSubject;
    }
    public String getMajorWork() {
        return majorWork;
    }
    public void setMajorWork(String majorWork) {
        this.majorWork = majorWork;
    }
    public int getMajorWant() {
        return majorWant;
    }
    public void setMajorWant(int majorWant) {
        this.majorWant = majorWant;
    }
    public int getMajorNeed() {
        return majorNeed;
    }
    public void setMajorNeed(int majorNeed) {
        this.majorNeed = majorNeed;
    }
    public double getMajorStudy() {
        return majorStudy;
    }
    public void setMajorStudy(double majorStudy) {
        this.majorStudy = majorStudy;
    }
    public double getMajorGo() {
        return majorGo;
    }
    public void setMajorGo(double majorGo) {
        this.majorGo = majorGo;
    }
    public Major() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Major{" +
                "majorId=" + majorId +
                ", majorName='" + majorName + '\'' +
                ", majorWorkPercent=" + majorWorkPercent +
                ", majorSalary=" + majorSalary +
                ", majorTypeId=" + majorTypeId +
                ", majorTypeName='" + majorTypeName + '\'' +
                ", majorWant=" + majorWant +
                ", majorNeed=" + majorNeed +
                ", majorStudy=" + majorStudy +
                ", majorGo=" + majorGo +
                ", majorIntroduce='" + majorIntroduce + '\'' +
                ", majorSubject='" + majorSubject + '\'' +
                ", majorWork='" + majorWork + '\'' +
                '}';
    }

    public int getMajorId() {
        return majorId;
    }
    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }
    public String getMajorName() {
        return majorName;
    }
    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
    public double getMajorWorkPercent() {
        return majorWorkPercent;
    }
    public void setMajorWorkPercent(double majorWorkPercent) {
        this.majorWorkPercent = majorWorkPercent;
    }
    public int getMajorSalary() {
        return majorSalary;
    }
    public void setMajorSalary(int majorSalary) {
        this.majorSalary = majorSalary;
    }
    public int getMajorTypeId() {
        return majorTypeId;
    }
    public void setMajorTypeId(int majorTypeId) {
        this.majorTypeId = majorTypeId;
    }
    public String getMajorTypeName() {
        return majorTypeName;
    }
    public void setMajorTypeName(String majorTypeName) {
        this.majorTypeName = majorTypeName;
    }

    public Major(int majorId, String majorName, double majorWorkPercent, int majorSalary, int majorTypeId, String majorTypeName, int majorWant, int majorNeed, double majorStudy, double majorGo, String majorIntroduce, String majorSubject, String majorWork) {
        this.majorId = majorId;
        this.majorName = majorName;
        this.majorWorkPercent = majorWorkPercent;
        this.majorSalary = majorSalary;
        this.majorTypeId = majorTypeId;
        this.majorTypeName = majorTypeName;
        this.majorWant = majorWant;
        this.majorNeed = majorNeed;
        this.majorStudy = majorStudy;
        this.majorGo = majorGo;
        this.majorIntroduce = majorIntroduce;
        this.majorSubject = majorSubject;
        this.majorWork = majorWork;
    }
}
