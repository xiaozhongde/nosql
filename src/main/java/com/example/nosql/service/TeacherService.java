package com.example.nosql.service;

import com.example.nosql.entity.Teacher;
import org.springframework.data.domain.Page;

import java.text.ParseException;

public interface TeacherService {
    Page<Teacher> queryAllByPage(int page, int rows) throws Exception;
    Teacher getByName(String name);
    void save(Teacher teacher);
    boolean removeTeacherByTid(int tid);
    boolean updateTeacher(Teacher teacher);
}
