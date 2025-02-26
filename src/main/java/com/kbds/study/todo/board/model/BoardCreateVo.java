package com.kbds.study.todo.board.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardCreateVo {
  private Long boardId;
  private String boardTitle;
  private String boardContent;
  private Long userId;
  private LocalDateTime boardCreateAt;
  private LocalDateTime boardUpdateAt;
}
