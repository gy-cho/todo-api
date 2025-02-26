package com.kbds.study.todo.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbds.study.todo.board.model.BoardCreateVo;
import com.kbds.study.todo.board.model.BoardDetailDto;
import com.kbds.study.todo.board.model.BoardCommentDto;
import com.kbds.study.todo.board.model.BoardDto;
import com.kbds.study.todo.board.model.CommentCreateVo;
import com.kbds.study.todo.board.repository.BoardRepository;
import com.kbds.study.todo.config.exception.ResourceNotFoundException;

@Service
public class BoardService {
  private final BoardRepository boardRepository;

  public BoardService(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }

  @Transactional
  public List<BoardDto> findAllBoards() {
    List<BoardDto> boards = boardRepository.selectAllBoards();
    System.out.println("SERVICE TEST  : " + boards);
    return boards;
  }

  @Transactional
  public BoardDto createBoard(BoardCreateVo board) {
    int result = boardRepository.insertBoard(board);
    int resultDetail = boardRepository.insertBoardDetail(board);

    if (result > 0 && resultDetail > 0) {
      BoardDto boardDto = new BoardDto();
      boardDto.setBoardId(board.getBoardId());
      boardDto.setBoardTitle(board.getBoardTitle());
      boardDto.setUserId(board.getUserId());
      boardDto.setBoardCreatedAt(board.getBoardCreateAt());
      boardDto.setBoardUpdatedAt(board.getBoardUpdateAt());
      return boardDto;
    } else {
      return null;
    }
  }

  @Transactional
  public BoardDetailDto getBoardDetail(long boardId) {
    // 조회수 업데이트
    int result = boardRepository.updateBoardView(boardId);

    if(result > 0){
      BoardDetailDto boardDetail = boardRepository.selectBoardDetail(boardId);
      return boardDetail;
    }
    throw new ResourceNotFoundException("해당 게시글이 존재하지 않습니다.");
  }

  @Transactional
  public List<BoardCommentDto> findBoardComments(long boardId){
    List<BoardCommentDto> boardComments = boardRepository.selectCommentDetail(boardId);
    return boardComments;
  }

  @Transactional
  public BoardCommentDto createComment(BoardCommentDto comment){
    int result = boardRepository.insertBoardComment(comment);

    if (result > 0) {
      return comment;
    } else {
      return null;
    }
  }
}
