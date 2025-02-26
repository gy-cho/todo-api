package com.kbds.study.todo.board.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentCreateVo {
  private Long boardId;
  private Long userId;
  private String userName;
  private String commentContent;
  private LocalDateTime boardCreateAt;
  private LocalDateTime boardUpdateAt;
}
