package com.example.nosql.dao;

import com.example.nosql.entity.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends MongoRepository<Course,String> {
    Course getByName(String name);
    Course findByCid(Integer cid);
    List<Course> getByFcid(Integer fcid);
}
