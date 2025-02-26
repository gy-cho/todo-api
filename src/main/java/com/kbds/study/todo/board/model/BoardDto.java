package com.kbds.study.todo.board.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * <pre>
 * Class Name : BoardDto
 * Description : 게시판 목록 조회 DTO
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2025.02.20	    조경란       New
 * </pre>
 *
 * @author 조경란
 * @since 2025.02.20
 */
@Getter
@Setter
public class BoardDto {
  private Long boardId;
  private String boardTitle;
  private Long userId;
  private String userName;
  private int boardView;
  @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
  private LocalDateTime boardCreatedAt;
  @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
  private LocalDateTime boardUpdatedAt;
}
