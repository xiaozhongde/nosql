package com.example.nosql.service;

import com.example.nosql.entity.Course;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

public interface CourseService {
    Page<Course> queryAllByPage(int page, int rows) throws Exception;
    Course getByName(String name);
    void save(Course course);
    boolean removeCourseByCid(int cid);
    boolean updateCourse(Course course);
    List<HashMap<String,Object>> findCourse(Long sid);
    List<String> findCourseDis();
    List<HashMap<String, Object>> findCourseTopTen();
}
