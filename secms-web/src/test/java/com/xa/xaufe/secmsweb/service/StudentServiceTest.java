package com.xa.xaufe.secmsweb.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xa.xaufe.secmsweb.BaseSpringTest;
import com.xa.xaufe.secmsweb.entity.Course;
import com.xa.xaufe.secmsweb.entity.Student;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;


import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.*;
@Transactional()
@Rollback(false)
public class StudentServiceTest extends BaseSpringTest {
   /* @Resource
    private StudentService usersService;

    @Test
    public void testLogin() {
        Student loginUser = null;
        loginUser = usersService.login("1605990418", "123456");
        System.out.println(loginUser);
    }

    @Test
    public void testDeleteList() {
        List<Integer> ids = new ArrayList<>();

        //ids.add(15);
        // usersService.deleteList(ids);
    }

    @Test
    public void testObject() {

        String json = "{'sno':'1605990418','password':'1605990418','randomCode':'4wm5'}";

        Student student = JSON.parseObject(json, new TypeReference<Student>() {
        });
        System.out.println(student);
    }

    @Test
    public void testGetOne() {
        Student student = usersService.getOne(1);
        System.out.println(student);

        Set<Course> set = student.getCourses();
        for(Course course:set){
            System.out.println("选课、、"+course);
        }
    }
*/
}
