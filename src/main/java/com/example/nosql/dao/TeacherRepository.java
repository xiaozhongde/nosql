package com.example.nosql.dao;

import com.example.nosql.entity.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaozhongde
 */
@Repository
public interface TeacherRepository extends MongoRepository<Teacher,String>{
    Teacher getByName(String name);
    Teacher findByTid(Integer tid);
    List<Teacher> findBySex(String sex);
}
