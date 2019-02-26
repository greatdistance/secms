package com.xa.xaufe.secmsweb.OneToMany;

import com.xa.xaufe.secmsweb.BaseSpringTest;
import com.xa.xaufe.secmsweb.entity.Course;
import com.xa.xaufe.secmsweb.entity.Teacher;
import com.xa.xaufe.secmsweb.service.CourseService;
import com.xa.xaufe.secmsweb.service.TeacherService;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional()
@Rollback(false)
public class ArrangeTeacherTest extends BaseSpringTest {
   /* @Resource
    private TeacherService teacherService;
    @Resource
    private CourseService courseService;
    @Test
    public void testArrangeTeacher(){
        Course course1 = courseService.getOne(1);
        Course course2 = courseService.getOne(2);
        Teacher teacher1 = teacherService.getOne(1);
        Teacher teacher2 = teacherService.getOne(2);
        Set<Course> courseSet = new HashSet<>();
        courseSet.add(course1);
        courseSet.add(course2);
        teacher1.setCourses(courseSet); // 给教师1安排1、2号课程
        course1.setTeacher(teacher1);
        course2.setTeacher(teacher1);
        teacherService.save(teacher1);
    }*/
}
