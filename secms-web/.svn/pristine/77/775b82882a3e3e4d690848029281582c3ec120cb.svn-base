package com.xa.xaufe.secmsweb.service;

import com.xa.xaufe.secmsweb.entity.Course;
import com.xa.xaufe.secmsweb.entity.Student;
import com.xa.xaufe.secmsweb.repository.CourseRepository;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService extends BaseService<Course,Integer, CourseRepository>{
    /**
     * 分页查找课程对象集合
     * @param currentPageNumber 当前页
     * @param pageSize 每页多少条
     * @return
     */
    Page<Course> findAll(int currentPageNumber, int pageSize);

    /**
     * 批量删除课程
     * @param ids 要删除课程的id集合
     */
    void deleteList(List<Integer> ids);
}
