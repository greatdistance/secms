package com.xa.xaufe.secmsweb.repository;

import com.xa.xaufe.secmsweb.entity.Teacher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeacherRepository extends BaseRepository<Teacher, Integer> {
    /**
     * 通过教工号密码查询教师实体
     *
     * @param tno      教工号
     * @param password 教师密码
     * @return
     */
    Teacher findByTnoAndPassword(String tno, String password);

    @Modifying
    @Transactional
    @Query("delete from Teacher t where t.id in (?1)")
    void deleteBatch(List<Integer> ids);

    /**
     * 通过教工号查找对象
     *
     * @param tno 用户名
     * @return
     */
    Teacher findByTno(String tno);
}
