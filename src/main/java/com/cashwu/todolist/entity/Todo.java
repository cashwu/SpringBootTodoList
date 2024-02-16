package com.cashwu.todolist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @Column
    private LocalDateTime createTime;

    public Todo() {

    }
}
