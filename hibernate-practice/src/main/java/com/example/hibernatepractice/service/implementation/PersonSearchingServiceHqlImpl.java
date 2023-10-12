package com.example.hibernatepractice.service.implementation;

import com.example.hibernatepractice.data.repository.PersonRepository;
import com.example.hibernatepractice.entity.Person;
import com.example.hibernatepractice.service.PersonSearchingService;
import com.example.hibernatepractice.util.OffsetBasedPageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Qualifier("PersonSearchingServiceHqlImpl")
@RequiredArgsConstructor
public class PersonSearchingServiceHqlImpl implements PersonSearchingService {
    private final PersonRepository personRepository;

    @Override
    public List<Person> search(String name,
                               Integer minAge, Integer maxAge,
                               LocalDate minBirthDate, LocalDate maxBirthDate,
                               Boolean isMarried,
                               LocalDateTime minCreationDateTime, LocalDateTime maxCreationDateTime,
                               int from, int size) {
        OffsetBasedPageRequest page =
                new OffsetBasedPageRequest(from, size, Sort.by("id").ascending());
        return personRepository.search(name, minAge, maxAge, minBirthDate, maxBirthDate,
                isMarried, minCreationDateTime, maxCreationDateTime, page);
    }
}
