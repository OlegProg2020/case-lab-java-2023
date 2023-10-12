package com.example.hibernatepractice.service;

import com.example.hibernatepractice.entity.Person;

public interface PersonService {
    Person create(Person person);

    Person update(Person person);

    Person findById(Long id);

    void deleteById(Long id);
}
