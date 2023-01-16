package hackathon2.backend.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import hackathon2.backend.dao.BoardDao;
import hackathon2.backend.vo.Board;

@RestController
public class BoardController {

  BoardDao boardDao = new BoardDao();

  public BoardController(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @PostMapping("/boards")
  public Object addBoard(
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String contents){

    Board b = new Board();
    b.setTitle(title);
    b.setContents(contents);
    b.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    this.boardDao.insert(b);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");

    return contentMap;
  }


  @GetMapping("/boards")
  public Object getBoards() {

    Board[] boards = this.boardDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", boards);

    return contentMap;
  }

  @GetMapping("/boards/{boardNo}")
  public Object getBoard(@PathVariable int boardNo) {

    Board b = this.boardDao.findByNo(boardNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (b == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "해당 번호의 게시글이 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", b);
    }

    return contentMap;
  }

  @PutMapping("/boards/{boardNo}")
  public Object updateBoard(
      @PathVariable int boardNo,
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String contents) {

    Map<String,Object> contentMap = new HashMap<>();

    Board old = this.boardDao.findByNo(boardNo);
    if (old == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "게시글이 없습니다.");
      return contentMap;
    }

    Board b = new Board();
    b.setNo(boardNo);
    b.setTitle(title);
    b.setContents(contents);
    b.setCreatedDate(old.getCreatedDate());

    this.boardDao.update(b);

    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/boards/{boardNo}")
  public Object deleteBoard(
      @PathVariable int boardNo) {

    Board b = this.boardDao.findByNo(boardNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (b == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "게시글이 없습니다.");

    } else {
      this.boardDao.delete(b);
      contentMap.put("status", "success");
    }

    return contentMap;
  }


}