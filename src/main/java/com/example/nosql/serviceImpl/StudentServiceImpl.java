package com.example.nosql.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.example.nosql.dao.StudentRepository;
import com.example.nosql.entity.Course;
import com.example.nosql.entity.Student;
import com.example.nosql.entity.Student_Course;
import com.example.nosql.entity.Teacher_Course;
import com.example.nosql.service.StudentService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.bson.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;

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
    public List<Student> queryAll() throws Exception {
        return studentRepository.findAll();
    }
    @Override
    public Page<Student> queryAllByPage(int page, int rows) {
        PageRequest pageRequest = PageRequest.of(page-1,rows);
        return studentRepository.findAll(pageRequest);
    }
    @Override
    public int count() throws Exception {
        long size = studentRepository.count();
        int count = Integer.valueOf(String.valueOf(size));
        return count;
    }
    @Override
    public Student save(Student student) {
        System.out.println(studentRepository.save(student));
       return studentRepository.save(student);
    }

    @Override
    public boolean removeStudentBySid(Long sid) {
        Query q=new Query(Criteria.where("sid").is(sid));
        System.out.println(q);
        DeleteResult deleteResult =  mongoTemplate.remove(q, Student.class);
        return deleteResult.getDeletedCount() != 0 ? true:false ;
    }

    @Override
    public boolean updateStudent(Student student){
            Query query=new Query();
            query.addCriteria(Criteria.where("sid").is(student.getSid()));
            Update update=new Update();
            Map<String, Object> stuMap = JSON.parseObject(JSON.toJSONString(student));
            stuMap.keySet().forEach(key->update.set(key, stuMap.get(key)));
            UpdateResult result = mongoTemplate.updateFirst(query,update,Student.class);
            System.out.println(result);
            return result.getModifiedCount() !=0 ? true:false;

    }

    @Override
    public Student_Course selectCourse(Long sid,Integer cid){
        Query query = new Query();
        query.addCriteria(Criteria.where("cid").is(cid));
        int tid = mongoTemplate.findOne(query, Teacher_Course.class,
        "teacher_course").getTid();
        int credit = mongoTemplate.findOne(query, Course.class,
                "course").getCredit();
        Student_Course sc = new Student_Course();
        sc.setSid(sid);
        sc.setCid(cid);
        sc.setTid(tid);
        sc.setCredit(credit);
        Student_Course result = mongoTemplate.save(sc,"student_course");
        System.out.println(result);
        return result;
    }

    @Override
    public Page<Student> getByAge(int page, int rows,String dname){
        Query query = new Query();
        PageRequest pageRequest = PageRequest.of(page-1,rows);
        query.with(pageRequest);
        query.addCriteria(Criteria.where("age").lt(20).and("dname").is(dname));
        List<Student> list = mongoTemplate.find(query,Student.class,"student");
        PageImpl page1 = new PageImpl(list,pageRequest,list.size());
        return page1;
    }

    @Override
    public List<HashMap<String, Object>> getAvgTop(){
        TypedAggregation<Student_Course> agg = Aggregation.newAggregation(Student_Course.class,
                Aggregation.group("sid").avg("score")
                        .as("avg"),
                Aggregation.sort(Sort.Direction.fromString("desc"),"avg"),
                Aggregation.limit(10)
        );
        System.out.println(agg);
        AggregationResults<Document> result = mongoTemplate.aggregate(agg,Document.class);
        result.getMappedResults().forEach(document -> System.out.println(document));
        List<HashMap<String, Object>> stu = new ArrayList<>();
        for (Iterator i = result.iterator(); i.hasNext();){
            Map<String, Object> data = (Map<String, Object>) i.next();
            HashMap<String, Object> re = new HashMap<>();
            re.put("name",studentRepository.findBySid((Long) data.get("_id")).getName());
            re.put("avg", data.get("avg") );
            re.put("_id", data.get("_id"));
            stu.add(re);
        }
        System.out.println(stu);
        return stu;
    }

}
