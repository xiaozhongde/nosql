package com.example.nosql.entity;

public class Course {
    private int cid;
    private String name;
    private int fcid;
    private short credit;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFcid() {
        return fcid;
    }

    public void setFcid(int fcid) {
        this.fcid = fcid;
    }

    public short getCredit() {
        return credit;
    }

    public void setCredit(short credit) {
        this.credit = credit;
    }
}
