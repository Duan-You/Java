package com.dxl.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "course", catalog = "student_grade_system")
public class Course implements java.io.Serializable {

    private Integer id;
    private String name;
    private String class_;
    private String credit;
    private String gpa;
    private User user;

    public Course() {
    }


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "class", nullable = false, length = 45)
    public String getClass_() {
        return this.class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    @Column(name = "credit", nullable = false, length = 6)
    public String getCredit() {
        return this.credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    @Column(name = "gpa", nullable = false, length = 6)
    public String getGpa() {
        return this.gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}