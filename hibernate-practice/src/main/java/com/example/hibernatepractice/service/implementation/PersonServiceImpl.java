package com.example.hibernatepractice.service.implementation;

import com.example.hibernatepractice.data.repository.PersonRepository;
import com.example.hibernatepractice.entity.Person;
import com.example.hibernatepractice.exception.DataMissingException;
import com.example.hibernatepractice.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    @Transactional
    public Person create(Person person) {
        return personRepository.save(person);
    }

    /**
     * @throws DataMissingException если person с person.id не существует в базе данных.
     */
    @Override
    @Transactional
    public Person update(Person person) {
        if (personRepository.existsById(person.getId())) {
            return personRepository.save(person);
        } else {
            throw new DataMissingException("Attempt to update a missing resource. " +
                    "Person with id = " + person.getId() + " does not exist in the database.");
        }
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new DataMissingException(
                        "Person with id = " + id + " does not exist in the database."));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }
}
