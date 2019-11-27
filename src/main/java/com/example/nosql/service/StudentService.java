package com.example.nosql.service;

import com.example.nosql.entity.Student;


import java.util.List;


public interface StudentService {
     Student getByName(String name);
     List<Student> getByAge(int age);

}
