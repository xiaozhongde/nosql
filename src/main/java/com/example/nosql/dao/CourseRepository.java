package com.example.nosql.dao;

import com.example.nosql.entity.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends MongoRepository<Course,String> {
    Course getByName(String name);
    Course findByCid(Integer cid);
}
