package bitcamp.bootapp;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// 다음 클래스가 클라이언트 요청을 처리하는 일을 한다는 것을 SpringBoot에게 알리는 표시!
// => SpringBoot는 다음 클래스의 인스턴스를 생성해서 보관해 둔다.
// => "/hello" 라는 URL로 클라이언트 요청이 들어오면 해당 메서드를 호출한다.
@RestController
public class BoardController {

  static final int SIZE = 100;

  int count;
  Board[] boards = new Board[SIZE];
  String title;

  public BoardController() {
    Board b = new Board();
    b.setNo(1);
    b.setTitle("제목입니다.1");
    b.setContent("내용입니다.1");
    b.setPassword("111222");
    b.setCreatedDate("2023-1-1");
    b.setViewCount(1);

    this.boards[this.count++] = b;
  }

  @GetMapping("/boards/{boardNo}")
  public Object getBoard(@PathVariable int boardNo) {

    Board b = this.findByNo(boardNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String, Object> contentMap = new HashMap<>();

    if (b == null) {
      contentMap.put("status", "failure");
      contentMap.put("message", "해당 번호의 게시글이 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("message", b);
    }

    return contentMap;
  }

  Board findByNo(int no) {
    for (int i = 0; i < this.count; i++) {
      if (this.boards[i].no == no) {
        return this.boards[i];
      }
    }
    return null;
  }
}
