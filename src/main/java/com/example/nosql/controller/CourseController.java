package com.example.nosql.controller;

import com.example.nosql.dao.CourseRepository;
import com.example.nosql.entity.Course;
import com.example.nosql.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("course")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    CourseRepository courseRepository;
    @GetMapping("getCourseByPage")
    public Page<Course> queryAllByPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int rows) throws Exception {
        Page<Course> courses = courseService.queryAllByPage(page,rows);
        return courses;
    }
    @GetMapping("getByCid")
    public Course findBySid(@RequestParam int cid){
        Course course = courseRepository.findByCid(cid);
        return course;
    }
    @GetMapping("getByFcid")
    public List<Course> getByFcid(@RequestParam(defaultValue = "3000001") int fcid){
        List<Course> courses = courseRepository.getByFcid(fcid);
        return courses;
    }
    /**
     * 通过名字查询
     */
    @GetMapping("getByName")
    public Course getByName(@RequestParam String name){
        Course course = courseService.getByName(name);
        return course;
    }
    //保存

    @PostMapping("save")
    public void save(Course course){
        courseService.save(course);
    }
    //删除

    @GetMapping("delete")
    public String removeCourseByCid(@RequestParam int cid) {
        boolean isDel = courseService.removeCourseByCid(cid);
        if (isDel == true){
            return ("删除成功");
        }else {
            return "删除失败或已经删除";
        }

    }
    //更新

    @PostMapping("update")
    public String update(Course course)  {
        boolean isUpdate = courseService.updateCourse(course);
       if (isUpdate){
           return "修改成功";
       }else {
           return "修改失败";
       }
    }

    //查找已经选过的课程

    @GetMapping("findCourse")
    public List<HashMap<String, Object>> findCourse(Long sid){
        return courseService.findCourse(sid);
    }

    //列出student_course表中出现过的所有课程名称

    @GetMapping("courseDis")
    public List<String> findCourseDis(){
       return courseService.findCourseDis();
    }

    //求选课人数排名前10的课程

    @GetMapping("getTopTen")
    public List<HashMap<String, Object>> fingCourseTopTen(){

        return courseService.findCourseTopTen();
    }

}
