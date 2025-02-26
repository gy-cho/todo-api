package com.kbds.study.todo.board.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * <pre>
 * Class Name : BoardCommentDto
 * Description : 게시판 상세 댓글 조회 DTO
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2025.02.24	    조경란       New
 * </pre>
 *
 * @author 조경란
 * @since 2025.02.24
 */
@Getter
@Setter
public class BoardCommentDto {
  private Long boardId;
  private Long userId;
  private String userName;
  private String commentContent;
  @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
  private LocalDateTime commentCreatedAt;
  @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
  private LocalDateTime commentUpdatedAt;
}
