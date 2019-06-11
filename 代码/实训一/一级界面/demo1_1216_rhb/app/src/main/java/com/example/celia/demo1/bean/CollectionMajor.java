package com.example.celia.demo1.bean;

public class CollectionMajor {
    private int majorId;
    private int userId;

    public CollectionMajor(int majorId, int userId) {
        this.majorId = majorId;
        this.userId = userId;
    }

    public CollectionMajor() {
        super();
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CollectionMajor{" +
                "majorId=" + majorId +
                ", userId=" + userId +
                '}';
    }
}
