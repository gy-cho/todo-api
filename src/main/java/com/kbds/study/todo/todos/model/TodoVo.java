package com.kbds.study.todo.todos.model;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class TodoVo {
  private int id;
  private String title;      // 할 일의 제목
  private boolean completed; // 완료 여부
}
