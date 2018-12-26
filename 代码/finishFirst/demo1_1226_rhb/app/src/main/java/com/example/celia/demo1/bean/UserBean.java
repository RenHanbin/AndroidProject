package com.example.celia.demo1.bean;

import java.io.Serializable;

public class UserBean implements Serializable{
    private int userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userTel;
    private String userSex;
    private double userBalance;
    private String userImg;
    private int userFans;
    private int userAtten;

    public UserBean(){
        userSex="男";
        userEmail=" ";
        userImg ="/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAFA3PEY8MlBGQUZaVVBfeMiCeG5uePWvuZHI////////////////////////////////////////////////////2wBDAVVaWnhpeOuCguv/////////////////////////////////////////////////////////////////////////wAARCACWAJYDASIAAhEBAxEB/8QAGAAAAwEBAAAAAAAAAAAAAAAAAAECAwT/xAArEAACAgEDAwMEAgMBAAAAAAAAAQIRIQMSMUFRYRMicTKBkaEEQiNSscH/xAAXAQEBAQEAAAAAAAAAAAAAAAAAAQID/8QAGREBAQEAAwAAAAAAAAAAAAAAAAERAiEx/9oADAMBAAIRAxEAPwDYAA0gAAABDABAMQQFQ5fwJJt0i9qjyyWrGEK3SSZZLlFSbSHGSl8lhTAACAAAAAAAQDAooB0FGWiEMAhAOhFAAABWn9RP8ltaeCoL3X2FrSio5TfgzVjl07tps10l7myISTbSg4suDqVdywrQAAqEAwAQpOmvLoomXT5AYDACxAZT16dQp TKtQOX1pytJ/ghNrr0GjsTUuHYHHF7XaeSnqSksvA0dE5bIt9TnWrJStyfwQ75FVgda1lSpvPgz1mprdnBkm0lnykP1HJZZFEaTTdmi1VeYmV RSfAHTHWi cEPXalhJoxpsb58pF1Gy/kK/pZopRatSVHGxK3gaO4h6keL6mCcm8yeMC5JeRI6VOLdJgcuPKAujRTkk4289yGlyO6y oty4ZlThyJquyJbvgG7QCpvoaJUqJgvcXdN9hSM5P5BOmF5wEk7KhyVK7uwiqi2/sX7WsoLi1TQXExzyCVW2iliTSTSG/ mbRm2 v5G9qyhTVCS6FRpGMdrt2wUtqpJImG7KQ5xdKuSKI 6V2l9gxboUYyXY1UVGKT6i0jCwKk1uxj4AqBu1n8E5XKHHjORSec8BQ ECzdBjFjwlhIqKUdvKyN5dtMlN3yPdkYahp3wx7ZSdVRW4FJ2UQ406GoqXHTubPWTXvgn5OewNY4SvIX7sK2iFbQOk8XZjF0pN2J4Bu3kJPBpGmnLdqLBpJUnfcnQXtXyVq8GL60mE71KK1OVkyTUWmW/crFRFK82ATVAVEtK7G5X0wTuePBe32p3Wao0FfgE8FbV3/QbPP6KYIxTy5foe2P zxzgmLUG0x rG37bTCntjV7nXeh7F0k OxPqRqtuPkcppxxhgZv5sE2kWoKuf0PZ5f4CYhNobUXG7pjenh1Lp2Mm7AAUqECQHRofQyp/SLQ4fllSwjlfWmHSvJrFYRnL/005otSIm/cAP6rACZwcGld4spJqEW LZtJRk7k89AaTSjil2OmDJOTtqCpYEtV9Y2l2RtSUHFdWEYKMZLvgDmkt0nSJ46HXGChu8qhbI7ZWstYBXMlfhDSo6VpabhT5M5aMoJ1lAR6uKUUHqyX9V BQWfLCT7AU9TcuEmZyjTH1 xMlToBBYIAi4zcY47mylujk5jWD9pjlFNqpNF37UZXl2X/AERLBLeQEwLg19TvH9C9XwJXXUHmjqyp6sUuAWrGvJE1WK 4JJVeSDTfHuG NcmcmlbM7sGuncu9gtRLG5V2Oa13C/ATXTGUN25NKkT7PUUvJjwO6Iutv8d5adsWrCMptt1Zlb4KuOy7al8A1eppqUpSTpClpxk01VfNEKeyqzY3J7afKZOzTlpZ9qtEvSlGVJN47FwkmspAtRXlPPQaus/SlSk4u7qqNlFpK1VdAc0krbzkqT91dG82S0ZajusAUox1HUWlXgC6hKaUeULeq5z4EkqA2J3LqpMremvpr7CeMDu0Zi1nJ2 BbSpImioVDsKYJECbbKuhNDCD3cpC6cF26oisBQruxtu1fAqG0ANvnoVuuNJ0uxNdh7cED3vDpOuMAtR5bWWLbgaTVgXv6pquwEbbAnSn0BgBtA0NLAAFG27BQVAARexbbJaoAIrOgoAAdC6ABAkN8AAQ0UAEqhjQAQLqAAB//9k=";
        userBalance=0.00;
    }

    public UserBean(int userId,double userBalance, String userName, String userPassword, String userEmail, String userTel, String userSex) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userTel = userTel;
        this.userSex = userSex;
        this.userBalance=userBalance;
    }

    public int getUserFans() {
        return userFans;
    }
    public void setUserFans(int userFans) {
        this.userFans = userFans;
    }

    public int getUserAtten() {
        return userAtten;
    }
    public void setUserAtten(int userAtten) {
        this.userAtten = userAtten;
    }
    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImage) {
        this.userImg = userImage;
    }
    public double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(double userBalance) {
        this.userBalance = userBalance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    @Override
    public String toString() {
        return "{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userBalance=" + userBalance +
                '}';
    }
}
