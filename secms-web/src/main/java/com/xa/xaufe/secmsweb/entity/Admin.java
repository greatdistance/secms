package com.xa.xaufe.secmsweb.entity;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="admin")
public class Admin implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 主键id
    private String ano;// 教务号
    private String username; // 姓名
    private String password; // 密码
    private String avatar = "defaultavatar.jpg";
    @Transient
    private String randomCode; // 随机验证码 不作为数据库字段

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", ano='" + ano + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", randomCode='" + randomCode + '\'' +
                '}';
    }
}
