package com.kbds.study.todo.board.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbds.study.todo.board.model.BoardCommentDto;
import com.kbds.study.todo.board.model.BoardCreateVo;
import com.kbds.study.todo.board.model.BoardDetailDto;
import com.kbds.study.todo.board.model.BoardDto;
import com.kbds.study.todo.board.model.CommentCreateVo;
import com.kbds.study.todo.board.service.BoardService;
import org.springframework.web.bind.annotation.PostMapping;

import com.kbds.study.todo.config.JweUtil;
import com.kbds.study.todo.config.exception.UnauthorizedException;
import com.kbds.study.todo.login.model.UserDto;


@RestController
@RequestMapping("/api")
public class BoardController {

    @Autowired
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(value = "/board", name = "boards")
    public List<BoardDto> findAllBoards() {
      return boardService.findAllBoards();
    }

    @GetMapping("/board/{boardId}")
    public BoardDetailDto getBoardDetail(@PathVariable long boardId) {
      return this.boardService.getBoardDetail(boardId);
    }
    
    @PostMapping("/board")
    public ResponseEntity<Map<String, Object>> createBoard(@RequestBody BoardCreateVo board) {
      BoardDto newBoard = boardService.createBoard(board);
      if(newBoard != null){
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("message", "게시글을 등록했습니다."));
      }else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "게시글 추가 실패"));
      }
    }

    @GetMapping("/board/comment/{boardId}")
    public List<BoardCommentDto> findBoardComments(@RequestHeader("Authorization") String authorizationHeader, @PathVariable long boardId) {
      String token = authorizationHeader.replace("Bearer ", "");
      try {
        System.out.println(token);
        JweUtil jweUtil = new JweUtil();
        UserDto userDto = jweUtil.getUserIdFromJweToken(token);
        System.out.println("api를 호출한 사용자는 " + userDto.getUserId() + " 입니다.");
      }catch (Exception e) {
        throw new UnauthorizedException("유효하지 않은 토큰입니다.");
      }
      return this.boardService.findBoardComments(boardId);
    }
    

    @PostMapping("path")
    public ResponseEntity<Map<String, Object>> createComment(@RequestHeader("Authorization") String authorizationHeader, @RequestBody BoardCommentDto comment) {
      BoardCommentDto newComment = boardService.createComment(comment);
      if(newComment != null){
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("message", "게시글을 등록했습니다."));
      }else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "게시글 추가 실패"));
      }
    }
    

}