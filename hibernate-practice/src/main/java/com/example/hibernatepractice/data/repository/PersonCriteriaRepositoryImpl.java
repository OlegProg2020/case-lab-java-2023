package com.example.hibernatepractice.data.repository;

import com.example.hibernatepractice.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonCriteriaRepositoryImpl implements PersonCriteriaRepository {
    private final EntityManager entityManager;

    @Override
    public List<Person> search(String name,
                               Integer minAge, Integer maxAge,
                               LocalDate minBirthDate, LocalDate maxBirthDate,
                               Boolean isMarried,
                               LocalDateTime minCreationDateTime, LocalDateTime maxCreationDateTime,
                               int from, int size) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = builder.createQuery(Person.class);
        Root<Person> root = query.from(Person.class);

        query.select(root)
                .where(builder.and(
                        builder.like(builder.lower(root.get("name")), "%" + name.toLowerCase() + "%"),
                        builder.between(root.get("age"), minAge, maxAge),
                        builder.between(root.get("birthday"), minBirthDate, maxBirthDate),
                        builder.equal(root.get("isMarried"), isMarried),
                        builder.between(root.get("creationDateTime"), minCreationDateTime, maxCreationDateTime)
                ))
                .orderBy(builder.asc(root.get("id")));

        return entityManager.createQuery(query)
                .setFirstResult(from)
                .setMaxResults(size)
                .getResultList();
    }
}
