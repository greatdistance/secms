package com.xa.xaufe.secmsweb.service;


import com.xa.xaufe.secmsweb.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;


/**
 * 封装所有表的共同的单表操作 增、删、改、查
 * @param <T> 实体类
 * @param <I> 实体类对象的主键类型
 * @param <R> 实体类的repository层的实现类型
 */
public interface BaseService<T, I extends Serializable, R extends BaseRepository<T, I>> {
    /**
     * 查找所有
     * @return 表中所有对象的List集合
     */
    List<T> findAll();

    /**
     * 通过id查询一个实体对象
     * @param id 实体id
     * @return
     */
    T getOne(I id);

    /**
     * 分页查询
     * @param pageable 该页的数据对象Pageable
     * @return
     */
    Page<T> findAll(Pageable pageable);

    /**
     * 保存实体对象
     * @param obj 实体对象
     */
    void save(T obj);

    /**
     * 修改实体对象
     * @param obj 修改后的实体对象数据
     */
    void update(T obj);

    /**
     * 通过id删除实体对象
     * @param id 实体对象id
     */
    void deleteById(I id);
}

