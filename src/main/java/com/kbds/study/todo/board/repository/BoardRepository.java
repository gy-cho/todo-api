package com.kbds.study.todo.board.repository;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kbds.study.todo.board.model.BoardCommentDto;
import com.kbds.study.todo.board.model.BoardCreateVo;
import com.kbds.study.todo.board.model.BoardDetailDto;
import com.kbds.study.todo.board.model.BoardDto;
import com.kbds.study.todo.board.model.CommentCreateVo;

@Mapper
public interface BoardRepository {
  List<BoardDto> selectAllBoards();
  BoardDetailDto selectBoardDetail(long boardId);
  int updateBoardView(long boardId);
  List<BoardCommentDto> selectCommentDetail(long boardId);
  int insertBoard(BoardCreateVo board);
  int insertBoardDetail(BoardCreateVo board);
  int insertBoardComment(CommentCreateVo comment);
}
