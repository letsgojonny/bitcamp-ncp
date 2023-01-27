package bitcamp.myapp.dao;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import bitcamp.myapp.vo.Board;

public class BoardDao {

  List<Board> list;

  public BoardDao(List<Board> list) {
    // List 규칙에 따라서 만든 객체를 외부에서 주입받는다.
    // 이렇게 하면 이 클래스는 ArrayList 또는 LinkedList와 같은
    // 특정 클래스와 관계가 없어진다.
    this.list = list;
  }

  int lastNo;

  public void insert(Board board) {
    board.setNo(++lastNo);
    board.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    list.add(board);
  }

  public Board[] findAll() {
    Board[] boards = new Board[list.size()];
    Iterator<Board> i = list.iterator();
    int index = 0;
    while (i.hasNext()) {
      boards[index++] = i.next();
    }
    return boards;
  }

  public Board findByNo(int no) {
    Board b = new Board();
    b.setNo(no);

    int index = list.indexOf(b);
    if (index == -1) {
      return null;
    }

    return list.get(index);
  }

  public void update(Board b) {
    int index = list.indexOf(b);
    list.set(index, b);
  }

  public boolean delete(Board b) {
    return list.remove(b);
  }
}







