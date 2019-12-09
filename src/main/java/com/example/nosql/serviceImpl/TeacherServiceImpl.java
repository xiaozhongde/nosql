package com.example.nosql.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.example.nosql.dao.TeacherRepository;
import com.example.nosql.entity.Student;
import com.example.nosql.entity.Teacher;
import com.example.nosql.service.TeacherService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Page<Teacher> queryAllByPage(int page, int rows) throws Exception {
        PageRequest pageRequest = PageRequest.of(page-1,rows);
        return teacherRepository.findAll(pageRequest);
    }
    @Override
    public Teacher getByName(String name) {
        return teacherRepository.getByName(name);
    }



    @Override
    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
        System.out.println("保存成功");
    }

    @Override
    public boolean removeTeacherByTid(int tid) {
        Query q=new Query(Criteria.where("tid").is(tid));
        System.out.println(q);
        DeleteResult deleteResult =  mongoTemplate.remove(q, Teacher.class);
        return deleteResult.getDeletedCount() != 0 ? true:false ;
    }

    @Override
    public boolean updateTeacher(Teacher teacher){
        Query query=new Query();
        query.addCriteria(Criteria.where("tid").is(teacher.getTid()));
        Update update=new Update();
        Map<String, Object> teacherMap = JSON.parseObject(JSON.toJSONString(teacher));
        teacherMap.keySet().forEach(key->update.set(key, teacherMap.get(key)));
        UpdateResult result = mongoTemplate.updateFirst(query,update,Teacher.class);
        System.out.println(result);
        return result.getModifiedCount() !=0 ? true:false;

    }
}
