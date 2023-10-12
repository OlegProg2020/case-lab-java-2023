package com.example.hibernatepractice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private LocalDate birthday;
    private Boolean isMarried;
    private LocalDateTime creationDateTime;

}
