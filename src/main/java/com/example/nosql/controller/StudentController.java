package com.example.nosql.controller;

import com.example.nosql.dao.StudentRepository;
import com.example.nosql.entity.Student;
import com.example.nosql.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;

    @GetMapping("getStuList")
    public List<Student> findAll(){
        List<Student> students = studentRepository.findAll();
        return students;
    }

    @GetMapping("getByName")
    public Student getByName(@RequestParam String name){
        Student stu = studentService.getByName(name);
        return stu;
    }
    @GetMapping("getBySid")
    public Student getBySid(@RequestParam String sid){
        Student stu = studentService.getBySid(sid);
        return stu;
    }

}
