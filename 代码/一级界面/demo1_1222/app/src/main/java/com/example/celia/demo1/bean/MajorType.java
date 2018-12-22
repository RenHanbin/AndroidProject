package com.example.celia.demo1.bean;

public class MajorType {
    private String majorTypeName;
    private int majorTypeId;
    public String getMajorTypeName() {
        return majorTypeName;
    }
    public void setMajorTypeName(String majorTypeName) {
        this.majorTypeName = majorTypeName;
    }
    public int getMajorTypeId() {
        return majorTypeId;
    }
    public void setMajorTypeId(int majorTypeId) {
        this.majorTypeId = majorTypeId;
    }
    public MajorType(String majorTypeName, int majorTypeId) {
        super();
        this.majorTypeName = majorTypeName;
        this.majorTypeId = majorTypeId;
    }
    public MajorType() {
        super();
    }
    @Override
    public String toString() {
        return "MajorType [majorTypeName=" + majorTypeName + ", majorTypeId=" + majorTypeId + "]";
    }
}
