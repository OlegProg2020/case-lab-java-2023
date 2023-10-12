package org.example;

import org.example.config.DataSource;
import org.example.config.DataSourceImpl;
import org.example.dao.PersonDao;
import org.example.dao.implementation.PersonDaoImpl;
import org.example.entity.Person;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        DataSource dataSource = new DataSourceImpl(
                "jdbc:postgresql://localhost:5432/jdbc-practice-db",
                "postgres",
                "admin1234"
        );

        PersonDao personDao = new PersonDaoImpl(dataSource);

        Person person = new Person();
        person.setName("Oleg");
        person.setAge(20);
        person.setBirthday(LocalDate.of(2002, 10, 23));
        person.setIsMarried(false);
        person.setCreationDateTime(LocalDateTime.now());

        // Создание
        System.out.println("Создание");
        try {
            Person createdPerson = personDao.create(person);
            System.out.println(createdPerson);
            person.setId(createdPerson.getId());
        } catch (SQLException e) {
            System.err.println(e.getLocalizedMessage());
        }

        person.setName("Kirill");
        person.setAge(22);
        person.setBirthday(LocalDate.of(2001, 11, 21));
        person.setIsMarried(true);
        person.setCreationDateTime(LocalDateTime.now());

        // Изменение
        System.out.println("изменение");
        Person updatedPerson = null;
        try {
            updatedPerson = personDao.update(person);
        } catch (SQLException e) {
            System.err.println(e.getLocalizedMessage());
        }
        System.out.println(updatedPerson);

        // Поиск по id
        System.out.println("Поиск по id");
        Optional<Person> foundPerson = null;
        try {
            foundPerson = personDao.findById(updatedPerson.getId());
        } catch (SQLException e) {
            System.err.println(e.getLocalizedMessage());
        }
        if (foundPerson.isPresent()) {
            System.out.println(foundPerson.get());
        } else {
            System.out.println("Person with id = " + updatedPerson.getId() + " not found.");
        }

        // Поиск по параметрам
        System.out.println("Поиск по параметрам");
        try {
            List<Person> people = personDao.search(
                    "O",
                    10, 70,
                    LocalDate.of(1980, 1, 1), LocalDate.of(2050, 1, 1),
                    false,
                    LocalDateTime.of(1970, 1, 1, 1, 1), LocalDateTime.now(),
                    0, 10);
            System.out.println(people);
        } catch (SQLException e) {
            System.err.println(e.getLocalizedMessage());
        }

        // Удаление по id
        System.out.println("Удаление по id");
        try {
            personDao.deleteById(updatedPerson.getId());
        } catch (SQLException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}