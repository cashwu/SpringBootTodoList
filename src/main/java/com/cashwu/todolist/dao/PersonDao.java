package com.cashwu.todolist.dao;

import com.cashwu.todolist.entity.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * @author cash
 */
public interface PersonDao extends CrudRepository<Person, Integer> {
}
