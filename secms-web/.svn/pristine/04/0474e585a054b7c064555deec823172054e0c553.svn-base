package com.xa.xaufe.secmsweb.repository;

import com.xa.xaufe.secmsweb.entity.Admin;
import com.xa.xaufe.secmsweb.entity.Student;

public interface AdminRepository extends BaseRepository<Admin, Integer> {
    /**
     * 通过学号密码查询管理员实体
     *
     * @param ano      管理员学号
     * @param password 管理员密码
     * @return
     */
    Admin findByAnoAndPassword(String ano, String password);

    /**
     * 通过教务号查找对象
     *
     * @param ano 用户名
     * @return
     */
    Admin findByAno(String ano);
}
