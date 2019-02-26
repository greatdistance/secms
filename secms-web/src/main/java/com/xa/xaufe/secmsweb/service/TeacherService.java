package com.xa.xaufe.secmsweb.service;

import com.xa.xaufe.secmsweb.entity.Student;
import com.xa.xaufe.secmsweb.entity.Teacher;
import com.xa.xaufe.secmsweb.repository.TeacherRepository;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeacherService extends BaseService<Teacher, Integer, TeacherRepository> {
    /**
     * 教师通过教工号密码登录
     *
     * @param tno      教工号号
     * @param password 教师密码
     * @return
     */
    Teacher login(String tno, String password);

    /**
     * 教师登录
     *
     * @param teacher 登录教师的实体对象
     * @return
     */
    Teacher login(Teacher teacher);

    /**
     * 分页查找教师对象集合
     *
     * @param currentPageNumber 当前页
     * @param pageSize          每页多少条
     * @return
     */
    Page<Teacher> findAll(int currentPageNumber, int pageSize);

    /**
     * 批量删除教师
     *
     * @param ids 要删除教师的id集合
     */
    void deleteList(List<Integer> ids);

    /**
     * 通过教工号号查找对象
     *
     * @param tno 用户名
     * @return
     */
    Teacher findByTno(String tno);
}
