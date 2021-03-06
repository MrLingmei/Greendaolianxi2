package com.example.zld.greendaolianxi;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zld on 2018/5/15.
 */
@Entity
public class User {
    @Id
    private Long id;
    private String name;
    private String page;
    @Generated(hash = 1303070916)
    public User(Long id, String name, String page) {
        this.id = id;
        this.name = name;
        this.page = page;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPage() {
        return this.page;
    }
    public void setPage(String page) {
        this.page = page;
    }
}
