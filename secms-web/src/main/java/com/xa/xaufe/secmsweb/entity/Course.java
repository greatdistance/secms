package com.xa.xaufe.secmsweb.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 主键id
    private String cno; // 课程号
    private String cname; // 课程名称
    private String beginTime; // 上课时间
    private String location; // 上课地点
    private Integer credit; // 学分
    /**
     * 课程和学生是多对多关系
     * 即一个学生可以选多门课程
     * 一门课程可以被多个学生选择
     * 设置了级联删除
     */
    /**
     * cascade表示级联操作，all是全部，一般用MERGE 更新,persist表示持久化即新增
     * 此类是维护关系的类，删除它，可以删除对应的外键,但是如果需要删除对应的权限就需要CascadeType.all
     * cascade:作用在本放，对于删除或其他操作本方时，对标注连接方的影响！和数据库一样！！
     */
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    @Column(name = "stu_id")
    private Set<Student> students = new HashSet<Student>();
    /**
     * 教师和课程是一对多的关系
     * 一个教师可以教授多门课程
     * 一门课程只能被一个教师教授
     * 多的一方存放的是一的方的对象
     */
    /**
     * 1对多，多的一方必须维护关系，即不能指定mapped=""
     * 一的一方存放多的一方的对象
     **/
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "tea_id")
    private Teacher teacher;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", location='" + location + '\'' +
                ", credit=" + credit +
                ", teacher=" + teacher +
                '}';
    }
}
