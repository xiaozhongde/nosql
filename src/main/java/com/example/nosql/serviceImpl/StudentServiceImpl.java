package com.example.nosql.serviceImpl;

import com.example.nosql.dao.StudentRepository;
import com.example.nosql.entity.Student;
import com.example.nosql.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private StudentRepository studentRepository;


    @Override
    public Student getByName(String name) {
        return studentRepository.getByName(name);
    }

    @Override
    public Student getBySid(String sid){
        return studentRepository.getBySid(sid);
    }


}
