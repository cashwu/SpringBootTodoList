package com.cashwu.todolist.dao;

import com.cashwu.todolist.entity.Todo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author cash
 */
public interface TodoDao extends CrudRepository<Todo, Integer> {
}
