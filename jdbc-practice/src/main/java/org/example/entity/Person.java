package org.example.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Person {
    private Long id;
    private String name;
    private Integer age;
    private LocalDate birthday;
    private Boolean isMarried;
    private LocalDateTime creationDateTime;

    public Person(Long id, String name, Integer age, LocalDate birthday, Boolean isMarried, LocalDateTime creationDateTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.isMarried = isMarried;
        this.creationDateTime = creationDateTime;
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Boolean getIsMarried() {
        return isMarried;
    }

    public void setIsMarried(Boolean married) {
        isMarried = married;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", isMarried=" + isMarried +
                ", creationDateTime=" + creationDateTime +
                '}';
    }
}
