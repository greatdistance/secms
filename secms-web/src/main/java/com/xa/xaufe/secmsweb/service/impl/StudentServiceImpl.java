package com.xa.xaufe.secmsweb.service.impl;

import com.xa.xaufe.secmsweb.entity.Student;
import com.xa.xaufe.secmsweb.repository.StudentRepository;
import com.xa.xaufe.secmsweb.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student, Integer, StudentRepository> implements StudentService {
    @Override
    public Student login(String sno, String password) {
        return dao.findBySnoAndPassword(sno, password);
    }

    @Override
    public Student login(Student student) {
        return dao.findBySnoAndPassword(student.getSno(), student.getPassword());
    }

    @Override
    public Page<Student> findAll(int currentPageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(currentPageNumber, pageSize);
        return dao.findAll(pageable);
    }

    @Override
    public void deleteList(List<Integer> ids) {
        dao.deleteBatch(ids);
    }

    @Override
    public Student findBySno(String sno) {
        return dao.findBySno(sno);
    }
}
