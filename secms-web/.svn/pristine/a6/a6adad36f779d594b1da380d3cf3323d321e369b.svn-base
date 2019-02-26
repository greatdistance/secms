package com.xa.xaufe.secmsweb.service.impl;

import com.xa.xaufe.secmsweb.entity.Teacher;
import com.xa.xaufe.secmsweb.repository.TeacherRepository;
import com.xa.xaufe.secmsweb.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl extends BaseServiceImpl<Teacher, Integer, TeacherRepository> implements TeacherService {
    @Override
    public Teacher login(String tno, String password) {
        return dao.findByTnoAndPassword(tno, password);
    }

    @Override
    public Teacher login(Teacher teacher) {
        return dao.findByTnoAndPassword(teacher.getTno(), teacher.getPassword());
    }

    @Override
    public Page<Teacher> findAll(int currentPageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(currentPageNumber, pageSize);
        return dao.findAll(pageable);
    }

    @Override
    public void deleteList(List<Integer> ids) {
        dao.deleteBatch(ids);
    }

    @Override
    public Teacher findByTno(String tno) {
        return dao.findByTno(tno);
    }
}
