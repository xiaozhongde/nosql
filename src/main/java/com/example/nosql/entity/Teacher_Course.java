package com.example.nosql.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="teacher_course")
public class Teacher_Course {
    private Integer tid;
    private Integer cid;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "Teacher_Course{" +
                "tid=" + tid +
                ", cid=" + cid +
                '}';
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}
