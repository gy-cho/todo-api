package com.kbds.study.todo.todos.repository;

import com.kbds.study.todo.todos.model.TodoDto;
import com.kbds.study.todo.todos.model.TodoVo;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface TodoRepository {
    List<TodoDto> selectAllTodos();  // Todo 전체 목록 가져오기
    TodoDto selectTodoId(int id);  // Todo 가져오기
    int insertTodo(TodoVo todo);  // Todo 추가
    int updateTodo(TodoVo todo);  // Todo 수정
    int updateTodoStatus(TodoVo todo);  // Todo 상태 변경
    int deleteTodo(int id);  // Todo 삭제
}
