package com.example.celia.demo1.bean;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

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

    public Major(int majorId, String majorName, double majorWorkPercent,double majorStudy,double majorGo, int majorSalary, int majorTypeId,int majorNeed,int majorWant,String majorTypeName) {
        this.majorId = majorId;
        this.majorName = majorName;
        this.majorWorkPercent = majorWorkPercent;
        this.majorSalary = majorSalary;
        this.majorTypeId = majorTypeId;
        this.majorTypeName=majorTypeName;
        this.majorWant=majorWant;
        this.majorNeed=majorNeed;
        this.majorStudy=majorStudy;
        this.majorGo=majorGo;
    }
    public Major() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public String toString() {
        return "MajorBean [majorId=" + majorId + ", majorName=" + majorName + ", majorWorkPercent=" + majorWorkPercent
                + ", majorSalary=" + majorSalary + ", majorTypeId=" + majorTypeId + ",majorTypeName"+majorTypeName +
                ", majorWant=" + majorWant + ", majorNeed=" + majorNeed + ", majorStudy=" + majorStudy +
                ", majorGo=" + majorGo +"]";
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
    public int getMajorNeed() {
        return majorNeed;
    }
    public void setMajorNeed(int majorWant) {
        this.majorNeed = majorNeed;
    }
    public int getMajorWant() {
        return majorWant;
    }
    public void setMajorWant(int majorWant) {
        this.majorWant = majorWant;
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Major major = (Major) o;
        return majorId == major.majorId &&
                Double.compare(major.majorWorkPercent, majorWorkPercent) == 0 &&
                Double.compare(major.majorStudy, majorStudy) == 0 &&
                Double.compare(major.majorGo, majorGo) == 0 &&
                majorSalary == major.majorSalary &&
                majorTypeId == major.majorTypeId &&
                majorWant == major.majorWant &&
                majorNeed == major.majorNeed &&
                Objects.equals(majorName, major.majorName) &&
                Objects.equals(majorTypeName, major.majorTypeName);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {

        return Objects.hash(majorId, majorName, majorWorkPercent, majorStudy, majorGo, majorSalary, majorTypeId, majorTypeName, majorWant, majorNeed);
    }
}
