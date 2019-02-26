package com.xa.xaufe.secmsweb.service;


import com.xa.xaufe.secmsweb.BaseSpringTest;
import com.xa.xaufe.secmsweb.entity.Course;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
@Transactional()
@Rollback(false)
public class CourseServiceTest extends BaseSpringTest {
    /*@Resource
    private CourseService courseService;


    @Test
    public void testFindAllCourses() {
        List<Course> list = courseService.findAll();
        Iterator<Course> it = list.iterator();
        while (it.hasNext())
        {
            System.out.println(it.next());
        }

    }*/


}
