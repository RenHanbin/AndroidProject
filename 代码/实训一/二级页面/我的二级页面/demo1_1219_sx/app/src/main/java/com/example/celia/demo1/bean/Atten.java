package com.example.celia.demo1.bean;

public class Atten {
    private int followUserId;
    private int followedUserId;
    private String followedUserImage;
    private String FollowedUserName;

    public Atten() {
        super();
    }

    public Atten(int followUserId, int followedUserId, String followedUserImage, String followedUserName) {
        this.followUserId = followUserId;
        this.followedUserId = followedUserId;
        this.followedUserImage = followedUserImage;
        FollowedUserName = followedUserName;
    }

    public int getFollowedUserId() {
        return followedUserId;
    }

    public void setFollowedUserId(int followedUserId) {
        this.followedUserId = followedUserId;
    }

    public int getFollowUserId() {
        return followUserId;
    }

    public void setFollowUserId(int followUserId) {
        this.followUserId = followUserId;
    }

    public String getFollowedUserImage() {
        return followedUserImage;
    }

    public void setFollowedUserImage(String followedUserImage) {
        this.followedUserImage = followedUserImage;
    }

    public String getFollowedUserName() {
        return FollowedUserName;
    }

    public void setFollowedUserName(String followedUserName) {
        FollowedUserName = followedUserName;
    }

    @Override
    public String toString() {
        return "Atten{" +
                "followUserId=" + followUserId +
                ", followedUserId=" + followedUserId +
                ", followedUserImage='" + followedUserImage + '\'' +
                ", FollowedUserName='" + FollowedUserName + '\'' +
                '}';
    }
}