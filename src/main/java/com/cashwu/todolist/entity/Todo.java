package com.cashwu.todolist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author cash
 */
@Entity
@Table
@Getter
@Setter
@ToString
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String task;
    @Column
    private Integer status;


    public Todo() {

    }
}
