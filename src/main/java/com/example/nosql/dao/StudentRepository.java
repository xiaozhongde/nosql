package com.example.nosql.dao;

import com.example.nosql.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student,Long> {
    Student getByName(String name);
    List<Student> getByAge(int age);
}
