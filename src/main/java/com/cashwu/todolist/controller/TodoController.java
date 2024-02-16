package com.cashwu.todolist.controller;

import com.cashwu.todolist.entity.Todo;
import com.cashwu.todolist.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author cash
 */
@RestController
@RequestMapping("/api")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo")
    public ResponseEntity<Iterable<Todo>> todo() {

        var todo = todoService.getTodo();

        return ResponseEntity.status(HttpStatus.OK)
                .body(todo);
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<Optional<Todo>> todo(@PathVariable Integer id) {

        Optional<Todo> todo = todoService.getTodo(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(todo);
    }

    @PostMapping("/todo")
    public ResponseEntity<Todo> todo(@RequestBody Todo todo) {
        Todo newTodo = todoService.saveTodo(todo);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newTodo);
    }

    @PutMapping("/todo/{id}")
    public ResponseEntity<String> updateTodo(@PathVariable Integer id, @RequestBody Todo todo) {
        boolean isSuccess = todoService.updateTodo(id, todo);

        if (isSuccess) {
            return ResponseEntity.status(HttpStatus.OK).body("");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("error ~");

    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Integer id) {
        boolean isSuccess = todoService.deleteTodo(id);

        if (isSuccess) {
            return ResponseEntity.status(HttpStatus.OK).body("");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("error ~");

    }

}
