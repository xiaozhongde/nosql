package com.example.nosql.controller;

import com.example.nosql.dao.StudentRepository;
import com.example.nosql.entity.Student;
import com.example.nosql.entity.Student_Course;
import com.example.nosql.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;
    /**
     * 获取学生列表
     */
    @GetMapping("getStuList")
    public List<Student> queryAll() throws Exception {
        List<Student> students = studentService.queryAll();
        return students;
    }
    /**
     * 通过名字查询
     */
    @GetMapping("getByName")
    public Student getByName(@RequestParam String name){
        Student stu = studentService.getByName(name);
        return stu;
    }
    /**
     * 分页查询所有数据
     */
    @GetMapping("getStuByPage")
    public Page<Student> queryAllByPage(@RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "10") int rows){
        Page<Student> students =  studentService.queryAllByPage(page,rows);
        return students;
    }
    /**
     *统计总数
     */
    @GetMapping("getCount")
    public int count() throws Exception{
        int count = studentService.count();
        return count;
    }

    //通过学号查询

    @GetMapping("getBySid")
    public Student findBySid(@RequestParam Long sid){
        Student stu = studentRepository.findBySid(sid);
        return stu;
    }

    @RequestMapping("getByAge")
    public List<Student> getByAge(){
        List<Student> list = studentService.getByAge();
        return list;
    }


    //保存信息

    @PostMapping("save")
    public String save(Student student){

        Student stu = studentService.save(student);
        if (stu !=null){
            return "true";
        }else {
            return "false";
        }
    }
    //删除信息

    @GetMapping("delete")
    public String removeStudentBySid(@RequestParam Long sid) {
        boolean isDel = studentService.removeStudentBySid(sid);
        if (isDel == true){
            return ("删除成功");
        }else {
            return "删除失败或已经删除";
        }
    }


    //更新信息

    @PostMapping("update")
    public String update(Student student)  {
        boolean isUpdate = studentService.updateStudent(student);
        if (isUpdate){
            return "修改成功";
        }else {
            return "修改失败";
        }
    }
    //选课

    @GetMapping("selectcourse")
    public Student_Course selectCourse(Long sid, int cid){
        return studentService.selectCourse(sid,cid);
    }


}
