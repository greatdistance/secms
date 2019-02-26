package com.xa.xaufe.secmsweb.repository;

import com.xa.xaufe.secmsweb.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentRepository extends BaseRepository<Student, Integer> {
    /**
     * 通过学号密码查询学生实体
     *
     * @param sno      学生学号
     * @param password 学生密码
     * @return
     */
    Student findBySnoAndPassword(String sno, String password);

    @Modifying
    @Transactional
    @Query("delete from Student s where s.id in (?1)")
    void deleteBatch(List<Integer> ids);

    /**
     * 通过学号查找对象
     *
     * @param sno 用户名
     * @return
     */
    Student findBySno(String sno);
}
