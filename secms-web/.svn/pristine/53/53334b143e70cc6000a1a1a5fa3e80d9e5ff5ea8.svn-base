package com.xa.xaufe.secmsweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 所有业务逻辑接口的父类通用接口
 * @param <T> 业务逻辑类型
 * @param <ID> 业务逻辑类型的主键类型
 */
@NoRepositoryBean
public interface BaseRepository<T,ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor {
    /**
     * 分页查询实体对象
     *
     * @param pageable
     * @return
     */
    Page<T> findAll(Pageable pageable);
}
