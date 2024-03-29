package com.example.nosql.dao;

import com.example.nosql.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student,String> {
    Student getByName(String name);
    Student findBySid(Long sid);
    List<Student> findByAgeLessThan(Integer age);

}
