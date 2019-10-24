package com.example.day02_lxc.Baen;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Person {
    @Id(autoincrement = true)
    private Long id;
    private String title;
    private String img;
    @Generated(hash = 1179471723)
    public Person(Long id, String title, String img) {
        this.id = id;
        this.title = title;
        this.img = img;
    }
    @Generated(hash = 1024547259)
    public Person() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImg() {
        return this.img;
    }
    public void setImg(String img) {
        this.img = img;
    }
}
