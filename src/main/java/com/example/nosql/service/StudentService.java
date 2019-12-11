package com.example.nosql.service;

import com.example.nosql.entity.Student;
import com.example.nosql.entity.Student_Course;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import java.util.HashMap;
import java.util.List;


public interface StudentService {
     Student getByName(String name);
     List<Student> queryAll() throws Exception;
     Page<Student> queryAllByPage(int page, int rows);
     int count() throws Exception;
     Student save(Student student);
     boolean removeStudentBySid(Long sid);
     boolean updateStudent(Student student);
     Student_Course selectCourse(Long sid, Integer cid);
     Page<Student> getByAge(int page, int rows,String dname);
     List<HashMap<String, Object>> getAvgTop();

}
