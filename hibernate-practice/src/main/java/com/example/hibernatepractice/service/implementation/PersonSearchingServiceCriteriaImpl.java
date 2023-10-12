package com.example.hibernatepractice.service.implementation;

import com.example.hibernatepractice.data.repository.PersonCriteriaRepository;
import com.example.hibernatepractice.entity.Person;
import com.example.hibernatepractice.service.PersonSearchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Qualifier("PersonSearchingServiceCriteriaImpl")
@RequiredArgsConstructor
public class PersonSearchingServiceCriteriaImpl implements PersonSearchingService {
    private final PersonCriteriaRepository personRepository;

    @Override
    public List<Person> search(String name,
                               Integer minAge, Integer maxAge,
                               LocalDate minBirthDate, LocalDate maxBirthDate,
                               Boolean isMarried,
                               LocalDateTime minCreationDateTime, LocalDateTime maxCreationDateTime,
                               int from, int size) {
        return personRepository.search(name, minAge, maxAge, minBirthDate, maxBirthDate,
                isMarried, minCreationDateTime, maxCreationDateTime, from, size);
    }
}
