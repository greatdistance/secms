package com.xa.xaufe.secmsweb.service.impl;

import com.xa.xaufe.secmsweb.entity.Course;
import com.xa.xaufe.secmsweb.repository.CourseRepository;
import com.xa.xaufe.secmsweb.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl extends BaseServiceImpl<Course, Integer, CourseRepository> implements CourseService {
    @Override
    public Page<Course> findAll(int currentPageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(currentPageNumber, pageSize);
        return dao.findAll(pageable);
    }

    @Override
    public void deleteList(List<Integer> ids) {
        dao.deleteBatch(ids);
    }
}
