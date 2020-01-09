package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.UUID;

public interface PersonDao {

    int insertPerson(UUID uuid, Person person);

    public List<Person> selectAllPersons();

    default int insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }
}
