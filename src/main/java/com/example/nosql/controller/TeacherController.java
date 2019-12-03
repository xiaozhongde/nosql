package com.example.nosql.controller;


import com.example.nosql.dao.TeacherRepository;
import com.example.nosql.entity.Teacher;
import com.example.nosql.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    TeacherService teacherService;

    /**
     * 分页查询所有数据
     */
    @GetMapping("getByPage")
    public Page<Teacher> queryAllByPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int rows) throws Exception {
        Page<Teacher> teachers =  teacherService.queryAllByPage(page,rows);
        return teachers;
    }
    @GetMapping("getByTid")
    public Teacher findBySid(@RequestParam Integer tid){
        Teacher teacher = teacherRepository.findByTid(tid);
        return teacher;

    }
    /**
     * 通过名字查询
     */
    @GetMapping("getByName")
    public Teacher getByName(@RequestParam String name){
        Teacher teacher = teacherService.getByName(name);
        return teacher;
    }

    @PostMapping("save")
    public void save(Teacher teacher){
        teacherService.save(teacher);
    }

    @GetMapping("delete")
    public String removeTeacherByTid(@RequestParam int tid) {
        boolean isDel = teacherService.removeTeacherByTid(tid);
        if (isDel == true){
            return ("删除成功");
        }else {
            return "删除失败或已经删除";
        }

    }

    @PostMapping("update")
    public String update(Teacher teacher)  {
        boolean isUpdate = teacherService.updateTeacher(teacher);
        if (isUpdate){
            return "修改成功";
        }else {
            return "修改失败";
        }
    }
}
