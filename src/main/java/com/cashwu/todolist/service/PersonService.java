package com.cashwu.todolist.service;

import com.cashwu.todolist.dao.PersonDao;
import com.cashwu.todolist.entity.Person;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author cash
 */
@Service
public class PersonService {

    private final PersonDao userDao;

    public PersonService(PersonDao userDao) {
        this.userDao = userDao;
    }

    public Optional<Person> getTodoByUserId(Integer id) {

        return userDao.findById(id);
    }

    public Person savePerson(Person person) {
        return userDao.save(person);
    }
}
