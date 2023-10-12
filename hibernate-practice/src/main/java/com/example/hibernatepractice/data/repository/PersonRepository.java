package com.example.hibernatepractice.data.repository;

import com.example.hibernatepractice.entity.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("FROM Person WHERE " +
            "name ILIKE %:name% " +
            "AND age BETWEEN :minAge AND :maxAge " +
            "AND birthday BETWEEN :minBirthDate AND :maxBirthDate " +
            "AND isMarried = :isMarried " +
            "AND creationDateTime BETWEEN :minCreationDateTime AND :maxCreationDateTime")
    List<Person> search(@Param("name") String name,
                        @Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge,
                        @Param("minBirthDate") LocalDate minBirthDate, @Param("maxBirthDate") LocalDate maxBirthDate,
                        @Param("isMarried") Boolean isMarried,
                        @Param("minCreationDateTime") LocalDateTime minCreationDateTime,
                        @Param("maxCreationDateTime") LocalDateTime maxCreationDateTime,
                        Pageable page);

}
