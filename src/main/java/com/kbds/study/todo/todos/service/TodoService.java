package com.kbds.study.todo.todos.service;

import com.kbds.study.todo.todos.model.TodoDto;
import com.kbds.study.todo.todos.model.TodoVo;
import com.kbds.study.todo.todos.repository.TodoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional(readOnly = true)
    public List<TodoDto> findAllTodos() {
        List<TodoDto> todos = todoRepository.selectAllTodos();
        return todos;
    }

    @Transactional(readOnly = true)
    public TodoDto findTodoById(int id) {
        TodoDto todos = todoRepository.selectTodoId(id);
        return todos;
    }

    @Transactional
    public TodoDto createTodo(TodoVo todo) {
        int result = todoRepository.insertTodo(todo);
        if (result > 0) {
            TodoDto todoDto = new TodoDto();
            todoDto.setId(todo.getId());
            todoDto.setTitle(todo.getTitle());
            todoDto.setCompleted(todo.isCompleted());
            
            return todoDto;
        } else {
            return null;
        }
    }

    @Transactional
    public int updateTodo(TodoVo todo) {
        int result = todoRepository.updateTodo(todo);
        return result;
    }

    @Transactional
    public int updateTodoStatus(TodoVo todo) {
        int result = todoRepository.updateTodoStatus(todo);
        return result;
    }

    @Transactional
    public int deleteTodo(int id) {
        int result = todoRepository.deleteTodo(id);
        return result;
    }
}
