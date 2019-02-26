package com.xa.xaufe.secmsweb.service;

import com.xa.xaufe.secmsweb.entity.Admin;
import com.xa.xaufe.secmsweb.entity.Student;
import com.xa.xaufe.secmsweb.repository.AdminRepository;
import org.springframework.data.domain.Page;

public interface AdminService extends BaseService<Admin, Integer, AdminRepository> {
    /**
     * 教务处管理员通过教务号密码登录
     *
     * @param ano      教务管理员教务号
     * @param password 教务管理员密码
     * @return
     */
    Admin login(String ano, String password);

    /**
     * 教务管理员登录
     *
     * @param admin 登录教务管理员的实体对象
     * @return
     */
    Admin login(Admin admin);

    /**
     * 通过教务号查找对象
     *
     * @param ano 用户名
     * @return
     */
    Admin findByAno(String ano);
}
