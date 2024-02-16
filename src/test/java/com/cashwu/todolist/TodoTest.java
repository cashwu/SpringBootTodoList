package com.cashwu.todolist;

import com.cashwu.todolist.entity.Todo;
import com.cashwu.todolist.service.TodoService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:test/data.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TodoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TodoService todoService;

    @Test
    void todos() throws Exception {

        var todos = new ArrayList<Todo>();

        Todo todo = new Todo();
        todo.setId(11);
        todo.setTask("task-999");
        todo.setStatus(1);
        todos.add(todo);

        String response = this.mockMvc.perform(get("/api/todo")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Iterable<Todo> responseObj = objectMapper.readValue(response, new TypeReference<>() {
        });

        assertThat(responseObj)
                .usingRecursiveComparison()
                .isEqualTo(todos);

    }
}
