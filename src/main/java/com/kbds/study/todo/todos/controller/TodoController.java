package com.kbds.study.todo.todos.controller;

import com.kbds.study.todo.todos.model.TodoDto;
import com.kbds.study.todo.todos.model.TodoVo;
import com.kbds.study.todo.todos.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(value = "/todos", name = "test")
    public List<TodoDto> findAllTodos() {
        List<TodoDto> test = todoService.findAllTodos();
        System.out.println("TEST : " + test);
        return test;
    }
    @GetMapping("/todos/{id}")
    public TodoDto findTodoId(@PathVariable int id) {
        return todoService.findTodoById(id);
    }
    @PostMapping("/todos")
    public ResponseEntity<Map<String, Object>> createTodo(@RequestBody TodoVo todo) {
        TodoDto newTodo = todoService.createTodo(todo);
        if (newTodo != null) { 
            Map<String, Object> response = new HashMap<>();
            response.put("message", "할 일이 추가 되었습니다.");
            response.put("contents", newTodo);
    
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "할 일 추가 실패"));
        }
    }
    @PutMapping("/todos/{id}")
    public ResponseEntity<Map<String, String>> updateTodo(@PathVariable int id, @RequestBody TodoVo todo) {
        int result = todoService.updateTodo(todo);
        if (result > 0) {
            return ResponseEntity.ok(Collections.singletonMap("message", "할 일이 수정 되었습니다."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "할 일을 찾을 수 없습니다."));
        }
    }
    @PutMapping("/todos/{id}/status")
    public ResponseEntity<Map<String, String>> updateTodoStatus(@PathVariable int id, @RequestBody TodoVo todo) {
        int result = todoService.updateTodoStatus(todo);
        if (result > 0) {
            return ResponseEntity.ok(Collections.singletonMap("message", "상태가 업데이트 되었습니다."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "할 일을 찾을 수 없습니다."));
        }
    }
    
    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Map<String, String>> deleteTodo(@PathVariable int id) { 
        int result = todoService.deleteTodo(id);
        if (result > 0) {
            return ResponseEntity.ok(Collections.singletonMap("message", "할 일이 삭제 되었습니다."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "할 일을 찾을 수 없습니다."));
        }
    }
}
