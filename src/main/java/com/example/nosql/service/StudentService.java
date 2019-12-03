package com.example.nosql.service;

import com.example.nosql.entity.Course;
import com.example.nosql.entity.Student;
import com.example.nosql.entity.Student_Course;
import org.springframework.data.domain.Page;


import java.text.ParseException;
import java.util.Date;
import java.util.List;


public interface StudentService {
     Student getByName(String name);
     List<Student> queryAll() throws Exception;
     Page<Student> queryAllByPage(int page, int rows);
     int count() throws Exception;
     List<Student> findByAgeLessThan(Integer age);
     Student save(Student student);
     boolean removeStudentBySid(Long sid);
     boolean updateStudent(Student student);
     Student_Course selectCourse(Long sid, Integer cid);
     List<Student> getByAge();

}
