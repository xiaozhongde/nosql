package com.example.nosql.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="student_course")
public class Student_Course {
    private Long sid;
    private Integer cid;
    private Integer score;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    private Integer credit;
    private Integer tid;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Student_Course{" +
                "sid=" + sid +
                ", cid=" + cid +
                ", score=" + score +
                ", credit=" + credit +
                ", tid=" + tid +
                '}';
    }
}
