package com.example.celia.demo1.bean;

public class Work {
    private int WorkId;
    private String workName;
    public int getWorkId() {
        return WorkId;
    }
    public void setWorkId(int workId) {
        WorkId = workId;
    }
    public String getWorkName() {
        return workName;
    }
    public void setWorkName(String workName) {
        this.workName = workName;
    }
    public Work(int workId, String workName) {
        super();
        WorkId = workId;
        this.workName = workName;
    }
    public Work() {
        super();
    }
}
