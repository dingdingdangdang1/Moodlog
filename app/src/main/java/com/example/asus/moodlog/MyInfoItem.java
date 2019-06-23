package com.example.asus.moodlog;

public class MyInfoItem {
    private int id;
    private String nickname;
    private String sex;
    private String qianming;
    private String xinzuo;

    public MyInfoItem() {
        super();
        nickname="";
        sex = "";
        qianming = "";
        xinzuo="";

    }
    public MyInfoItem(String nickname, String sex, String qianming, String xinzuo) {
        super();
        this.nickname = nickname;
        this.sex = sex;
        this.qianming = qianming;
        this.xinzuo = xinzuo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getQianming() {
        return qianming;
    }

    public void setQianming(String qianming) {
        this.qianming = qianming;
    }

    public String getXinzuo() {
        return xinzuo;
    }

    public void setXinzuo(String xinzuo) {
        this.xinzuo = xinzuo;
    }
}
