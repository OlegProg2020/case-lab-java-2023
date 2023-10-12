package com.example.hibernatepractice.data.repository;

import com.example.hibernatepractice.entity.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PersonCriteriaRepository {

    List<Person> search(String name,
                        Integer minAge, Integer maxAge,
                        LocalDate minBirthDate, LocalDate maxBirthDate,
                        Boolean isMarried,
                        LocalDateTime minCreationDateTime, LocalDateTime maxCreationDateTime,
                        int from, int size);
}
