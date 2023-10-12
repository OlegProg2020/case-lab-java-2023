package com.example.hibernatepractice;

import com.example.hibernatepractice.entity.Person;
import com.example.hibernatepractice.service.PersonSearchingService;
import com.example.hibernatepractice.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@Slf4j
public class HibernatePracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernatePracticeApplication.class, args);
    }

    @Bean
    public ApplicationRunner example(
            PersonService personService,
            @Qualifier("PersonSearchingServiceHqlImpl") PersonSearchingService personSearchingServiceHql,
            @Qualifier("PersonSearchingServiceCriteriaImpl") PersonSearchingService personSearchingServiceCriteria) {
        return args -> {
            Person person = new Person();
            person.setName("Oleg");
            person.setAge(20);
            person.setBirthday(LocalDate.of(2002, 10, 23));
            person.setIsMarried(false);
            person.setCreationDateTime(LocalDateTime.now());

            // Создание
            Person createdPerson = personService.create(person);
            log.info("Создание: {}", createdPerson.toString());

            createdPerson.setName("Kirill");
            createdPerson.setAge(22);
            createdPerson.setBirthday(LocalDate.of(2001, 11, 21));
            createdPerson.setIsMarried(true);
            createdPerson.setCreationDateTime(LocalDateTime.now());

            // Изменение
            Person updatedPerson = personService.update(createdPerson);
            log.info("Изменение: {}", updatedPerson.toString());

            // Поиск по id
            Person foundPerson = personService.findById(updatedPerson.getId());
            log.info("Поиск по id: {}", foundPerson.toString());

            // Поиск по параметрам (hql)
            List<Person> people = personSearchingServiceHql.search(
                    "O",
                    10, 70,
                    LocalDate.of(1980, 1, 1), LocalDate.of(2050, 1, 1),
                    false,
                    LocalDateTime.of(1970, 1, 1, 1, 1), LocalDateTime.now(),
                    0, 10);
            log.info("Поиск по параметрам (hql): {}", people);

            // Поиск по параметрам (criteria)
            people = personSearchingServiceCriteria.search(
                    "O",
                    10, 70,
                    LocalDate.of(1980, 1, 1), LocalDate.of(2050, 1, 1),
                    false,
                    LocalDateTime.of(1970, 1, 1, 1, 1), LocalDateTime.now(),
                    0, 10);
            log.info("Поиск по параметрам (criteria): {}", people);

            // Удаление по id
            personService.deleteById(updatedPerson.getId());
        };
    }

}
