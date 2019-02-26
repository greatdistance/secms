package com.xa.xaufe.secmsweb.service.impl;

import com.xa.xaufe.secmsweb.repository.BaseRepository;
import com.xa.xaufe.secmsweb.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<T, I extends Serializable, R extends BaseRepository<T, I>> implements BaseService<T, I, R> {
    @Autowired
    protected R dao;

    @Transactional
    @Override
    public List<T> findAll() {
        return dao.findAll();
    }

    @Transactional
    @Override
    public T getOne(I id) {
        return dao.getOne(id);
    }

    @Transactional
    @Override
    public Page<T> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Transactional
    @Override
    public void save(T obj) {
        dao.save(obj);

    }

    @Transactional
    @Override
    public void update(T obj) {
        dao.save(obj);

    }

    @Transactional
    @Override
    public void deleteById(I id) {
        dao.deleteById(id);

    }
}
