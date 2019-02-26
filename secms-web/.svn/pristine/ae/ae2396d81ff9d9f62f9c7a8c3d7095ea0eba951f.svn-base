package com.xa.xaufe.secmsweb.repository;

import com.xa.xaufe.secmsweb.entity.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CourseRepository extends BaseRepository<Course,Integer> {
    @Modifying
    @Transactional
    @Query("delete from Course s where s.id in (?1)")
    void deleteBatch(List<Integer> ids);
}
