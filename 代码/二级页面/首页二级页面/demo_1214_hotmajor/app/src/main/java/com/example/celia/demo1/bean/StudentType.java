package com.example.celia.demo1.bean;

public class StudentType {
    private int studentTypeId;
    private String StudentTypeName;

    public StudentType() {
        super();
        // TODO Auto-generated constructor stub
    }
    public StudentType(int studentTypeId, String studentTypeName) {
        this.studentTypeId = studentTypeId;
        StudentTypeName = studentTypeName;
    }

    @Override
    public String toString() {
        return "{" +
                "studentTypeId=" + studentTypeId +
                ", StudentTypeName='" + StudentTypeName + '\'' +
                '}';
    }

    public int getStudentTypeId() {
        return studentTypeId;
    }

    public void setStudentTypeId(int studentTypeId) {
        this.studentTypeId = studentTypeId;
    }

    public String getStudentTypeName() {
        return StudentTypeName;
    }

    public void setStudentTypeName(String studentTypeName) {
        StudentTypeName = studentTypeName;
    }
}
