package com.cashwu.todolist.controller;

import com.cashwu.todolist.entity.Todo;
import com.cashwu.todolist.entity.Person;
import com.cashwu.todolist.service.TodoService;
import com.cashwu.todolist.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author cash
 */
@Tag(name = "todo controller", description = "todo api")
@RestController
@RequestMapping("/api")
public class TodoController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final TodoService todoService;
    private final PersonService userService;

    public TodoController(TodoService todoService, PersonService userService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    @Operation(summary = "get todos", description = "get all todo")
    @GetMapping("/todo")
    public ResponseEntity<Iterable<Todo>> todo() {

        var todo = todoService.getTodo();

        logger.info("get todos --");
        logger.error("get todos error --");
        logger.warn("get todos warn --");

        return ResponseEntity.status(HttpStatus.OK)
                .body(todo);
    }

    @GetMapping("/person/{id}/todo")
    public ResponseEntity<Optional<Person>> getTodoByPersonId(@PathVariable Integer id) {

        var todo = userService.getTodoByUserId(id);

        return ResponseEntity.status(HttpStatus.OK).body(todo);
    }

    @PostMapping("/person")
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {

        Person newPerson = userService.savePerson(person);

        return ResponseEntity.status(HttpStatus.OK).body(newPerson);
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<Todo> todo(@PathVariable Integer id) {

        Optional<Todo> todo = todoService.getTodo(id);

        return todo.map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));

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
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
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
