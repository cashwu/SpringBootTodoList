package com.cashwu.todolist.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodoEntityTest {

    @Test
    public void get_id(){

        Todo todo = new Todo();
        todo.setId(1);

        Assertions.assertEquals(1, todo.getId());
    }
}
