package com.kbds.study.todo.todos.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoDto {
  private int id;            // int 타입의 PK
  private String title;      // 할 일의 제목
  private boolean completed; // 완료 여부
}
