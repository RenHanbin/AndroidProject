package com.example.celia.demo1.bean;

import java.io.Serializable;

public class Writer implements Serializable{
    private int writerId;
    private String writerName;
    private String writerImg;


    public Writer() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Writer(int writerId, String writerName, String writerImg) {
        this.writerId = writerId;
        this.writerName = writerName;
        this.writerImg = writerImg;
    }

    public int getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getWriterImg() {
        return writerImg;
    }

    public void setWriterImg(String writerImg) {
        this.writerImg = writerImg;
    }

    @Override
    public String toString() {
        return "{" +
                "writerId=" + writerId +
                ", writerName='" + writerName + '\'' +
                ", writerImg='" + writerImg + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Writer other = (Writer) obj;
        if (writerId != other.writerId)
            return false;
        if (writerImg == null) {
            if (other.writerImg != null)
                return false;
        } else if (!writerImg.equals(other.writerImg))
            return false;
        if (writerName == null) {
            if (other.writerName != null)
                return false;
        } else if (!writerName.equals(other.writerName))
            return false;
        return true;
    }

}
