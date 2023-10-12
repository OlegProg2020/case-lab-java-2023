package org.example.dao;

import org.example.entity.Person;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PersonDao {

    Person create(Person person) throws SQLException;

    Person update(Person person) throws SQLException;

    Optional<Person> findById(Long id) throws SQLException;

    /**
     * Все параметры обязательны.
     *
     * @param name                имя или его часть с учетом регистра.
     * @param minAge              минимальная граница возраста.
     * @param maxAge              максимальная граница возраста.
     * @param minBirthDate        минимальная граница даты рождения.
     * @param maxBirthDate        максимальная граница даты рождения.
     * @param isMarried           значение isMarried.
     * @param minCreationDateTime минимальная граница creationDateTime.
     * @param maxCreationDateTime максимальная граница creationDateTime.
     * @param from                номер строки, с которой возвращать значение (начиная с 0).
     * @param size                количество элементов для возврата (начиная с 1).
     */
    List<Person> search(String name,
                        Integer minAge, Integer maxAge,
                        LocalDate minBirthDate, LocalDate maxBirthDate,
                        Boolean isMarried,
                        LocalDateTime minCreationDateTime, LocalDateTime maxCreationDateTime,
                        int from, int size) throws SQLException;

    void deleteById(Long id) throws SQLException;
}
