package bitcamp.myapp;

import java.sql.Date;

public class BoardHandler {

  // 모든 인스턴스가 공유하는 데이터를 스태틱 필드로 만든다
  // 특히 데이터를 조회하는 용으로 사용하는 final 변수는 스태틱 필드로 만들어야 한다.
  static final int SIZE = 100;

  int count;
  Board[] boards = new Board[SIZE];
  String title;

  // 인스턴스를 만들 때 프롬프트 제목을 반드시 입력하도록 강제한다
  BoardHandler(String title) {
    this.title = title;
  }

  void inputBoard() {
    Board b = new Board();
    b.no = Prompt.inputInt("번호? ");
    b.title = Prompt.inputString("제목? ");
    b.contents = Prompt.inputString("내용? ");
    b.password = Prompt.inputString("암호? ");
    //    b.views = Prompt.inputString("조회수? ");
    b.createdDate = new Date(System.currentTimeMillis()).toString();

    this.boards[count++] = b;
  }

  void printBoards() {
    System.out.println("번호\t제목\t작성일\t조회수");

    for (int i = 0; i < this.count; i++) {
      Board b = this.boards[i];
      System.out.printf("%d\t%s\t%s\t%d\n",
          b.no, b.title, b.createdDate,
          b.views);
    }
  }

  void printBoard() {
    int boardNo = Prompt.inputInt("게시글번호? ");

    Board b = this.findByNo(boardNo);

    if (b == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    //    System.out.printf("    번호: %s\n", b.no);
    System.out.printf("    제목: %s\n", b.title);
    System.out.printf("    내용: %s\n", b.contents);
    System.out.printf("  작성일: %s\n", b.createdDate);
    System.out.printf("  조회수: %d\n", ++b.views);

  }

  // 인스턴스 멤버(필드나 메서드)를 사용하지 않기 때문에
  // 그냥 스태틱 메서드로 두어라!!


  void modifyBoard() {
    int boardNo = Prompt.inputInt("게시글번호? ");

    Board old = this.findByNo(boardNo);

    if (old == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }


    String boardInputPassword = Prompt.inputString("암호? ");

    if (boardInputPassword == this.boards[boardNo].password) {

      // 변경할 데이터를 저장할 인스턴스 준비
      Board b = new Board();
      b.no = old.no;
      b.createdDate = old.createdDate;
      b.title = Prompt.inputString(String.format("제목(%s)? ", old.title));
      b.contents = Prompt.inputString(String.format("내용(%s)? ", old.contents));

      String str = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if (str.equalsIgnoreCase("Y")) {
        this.boards[this.indexOf(old)] = b;
        System.out.println("변경했습니다.");
      } else {
        System.out.println("변경 취소했습니다.");
      }
    }
  }

  void deleteBoard() {
    int boardNo = Prompt.inputInt("게시글번호? ");

    Board b = this.findByNo(boardNo);

    if (b == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String str = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (!str.equalsIgnoreCase("Y")) {
      System.out.println("삭제 취소했습니다.");
      return;
    }

    for (int i = this.indexOf(b) + 1; i < this.count; i++) {
      this.boards[i - 1] = this.boards[i];
    }
    this.boards[--this.count] = null; // 레퍼런스 카운트를 줄인다.

    System.out.println("삭제했습니다.");

  }

  Board findByNo(int no) {
    for (int i = 0; i < this.count; i++) {
      if (this.boards[i].no == no) {
        return this.boards[i];
      }
    }
    return null;
  }

  int indexOf(Board b) {
    for (int i = 0; i < this.count; i++) {
      if (this.boards[i].no == b.no) {
        return i;
      }
    }
    return -1;
  }

  //  void searchBoard() {
  //    int no = Prompt.inputInt("번호? ");
  //
  //    System.out.println("번호\n제목\n내용\n작성일\n조회수");
  //
  //    for (int i = 0; i < this.count; i++) {
  //      Board b = this.boards[i];
  //      if (b.no == no) {
  //        System.out.printf("%d\t%s\t%s\t%s\t%s\n",
  //            b.no, b.title, b.contents,
  //            b.createdDate, b.views);
  //      }
  //    }
  //  }



  void service() {
    while (true) {
      System.out.printf("[%s]\n", this.title);
      System.out.println("1. 입력");
      System.out.println("2. 목록");
      System.out.println("3. 조회");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      //      System.out.println("6. 검색");
      System.out.println("0. 이전");
      int menuNo = Prompt.inputInt(String.format("%s> ", this.title));

      switch (menuNo) {
        case 0: return;
        case 1: this.inputBoard(); break;
        case 2: this.printBoards(); break;
        case 3: this.printBoard(); break;
        case 4: this.modifyBoard(); break;
        case 5: this.deleteBoard(); break;
        //        case 6: this.searchBoard(); break;
        default:
          System.out.println("잘못된 메뉴 번호 입니다.");
      }
    }
  }

}
