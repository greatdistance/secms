package com.xa.xaufe.secmsweb.service;

import com.xa.xaufe.secmsweb.entity.Student;
import com.xa.xaufe.secmsweb.repository.BaseRepository;
import com.xa.xaufe.secmsweb.repository.StudentRepository;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

public interface StudentService extends BaseService<Student,Integer,StudentRepository> {
    /**
     * 学生通过学号密码登录
     * @param sno 学生学号
     * @param password 学生密码
     * @return
     */
    Student login(String sno, String password);

    /**
     * 学生登录
     * @param student 登录学生的实体对象
     * @return
     */
    Student login(Student student);

    /**
     * 分页查找学生对象集合
     * @param currentPageNumber 当前页
     * @param pageSize 每页多少条
     * @return
     */
    Page<Student> findAll(int currentPageNumber, int pageSize);

    /**
     * 批量删除学生
     * @param ids 要删除学生的id集合
     */
    void deleteList(List<Integer> ids);

    /**
     * 通过用户名查找对象
     * @param sno
     * @return
     */
    Student findBySno(String sno);
}
