package com.example.nosql.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.example.nosql.dao.CourseRepository;
import com.example.nosql.dao.TeacherRepository;
import com.example.nosql.entity.Course;
import com.example.nosql.entity.Student_Course;
import com.example.nosql.service.CourseService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.bson.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


import java.lang.reflect.Field;
import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public Page<Course> queryAllByPage(int page, int rows) throws Exception {
        PageRequest pageRequest = PageRequest.of(page-1,rows);
        return courseRepository.findAll(pageRequest);
    }
    @Override
    public Course getByName(String name) {
        return courseRepository.getByName(name);
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
        System.out.println("保存成功");
    }

    @Override
    public boolean removeCourseByCid(int cid) {
        Query q= new Query(Criteria.where("cid").is(cid));
        DeleteResult deleteResult =  mongoTemplate.remove(q, Course.class);
        return deleteResult.getDeletedCount() != 0 ? true:false ;
    }

    @Override
    public boolean updateCourse(Course course)  {
        Query query=new Query();
        query.addCriteria(Criteria.where("cid").is(course.getCid()));
        Update update=new Update();
        System.out.println(course.toString());
        Map<String, Object>  courseMap = JSON.parseObject(JSON.toJSONString(course));
        courseMap.keySet().forEach(key->update.set(key, courseMap.get(key)));
        UpdateResult result = mongoTemplate.updateFirst(query,update,Course.class);
        System.out.println(result);
        return result.getModifiedCount() !=0 ? true:false;
    }
    @Override
    public List<HashMap<String,Object>> findCourse(Long sid) {
        Query query = new Query();
        query.addCriteria(Criteria.where("sid").is(sid));
        List<HashMap> result = mongoTemplate.find(query, HashMap.class, "student_course");
        List<HashMap<String, Object>> resultCourse = new ArrayList<>();
        for(HashMap<String,Object> r:result){
            Course course = courseRepository.findByCid((Integer) r.get("cid"));
            HashMap<String, Object> data = new HashMap<>();
            data.put("courseName", course.getName());
            data.put("courseCredit", course.getCredit());
            data.put("teacherName", teacherRepository.findByTid((Integer) r.get("tid")).getName());
            data.put("score", r.get("score"));
            resultCourse.add(data);
        }

        System.out.println(resultCourse);
        return resultCourse;
    }

    @Override
    public List<String> findCourseDis(){
       List<Integer> cids =  mongoTemplate.findDistinct("cid", Student_Course.class,Integer.class);
        List<String> lists = new ArrayList<>();
        for(int i=0;i<cids.size();i++) {
          Course course = courseRepository.findByCid(cids.get(i));
            lists.add(course.getName());
        }
        return lists;
    }

    @Override
    public List<HashMap<String, Object>> findCourseTopTen() {
        TypedAggregation<Student_Course> agg = Aggregation.newAggregation(Student_Course.class,
                Aggregation.group("cid").count()
                        .as("count"),
                Aggregation.sort(Sort.Direction.fromString("desc"),"count"),
                Aggregation.limit(10)

        );
        AggregationResults<Document> result = mongoTemplate.aggregate(agg,Document.class);

        List<HashMap<String, Object>> listResult = new ArrayList<>();
        for (Iterator i = result.iterator();i.hasNext();){
            Map<String, Object> data = (Map<String, Object>) i.next();
            HashMap<String, Object> re = new HashMap<>();
            re.put("_id", data.get("_id"));
            re.put("name",courseRepository.findByCid((Integer) data.get("_id")).getName());
            re.put("count", data.get("count") );
            listResult.add(re);
        }
        System.out.println(result);
        return listResult;
    }

}
