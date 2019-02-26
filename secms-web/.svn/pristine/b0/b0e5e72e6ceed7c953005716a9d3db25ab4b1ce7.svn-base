package com.xa.xaufe.secmsweb.service.impl;

import com.xa.xaufe.secmsweb.entity.Admin;
import com.xa.xaufe.secmsweb.repository.AdminRepository;

import com.xa.xaufe.secmsweb.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin,Integer, AdminRepository> implements AdminService {
    @Override
    public Admin login(String ano, String password) {
        return dao.findByAnoAndPassword(ano,password);
    }

    @Override
    public Admin login(Admin admin) {
        return dao.findByAnoAndPassword(admin.getAno(),admin.getPassword());
    }

    @Override
    public Admin findByAno(String ano) {
        return dao.findByAno(ano);
    }
}
