package bitcamp.myapp;

public class App {

  public static void main(String[] args) {

    goMainMenu();
    System.out.println("안녕히가세요!");

    Prompt.close();
  }

  private static void goMainMenu() {
    MemberHandler generalMemberHandler = new MemberHandler("일반학생");
    MemberHandler supportedMemberHandler = new MemberHandler("국비지원학생");
    MemberHandler companyMemberHandler = new MemberHandler("위탁교육생");
    BoardHandler boardHandler = new BoardHandler("게시판");

    while (true) {
      System.out.println("1. 일반학생관리");
      System.out.println("2. 국비지원학생관리");
      System.out.println("3. 위탁교육생관리");
      System.out.println("4. 게시판관리");
      System.out.println("9. 종료");
      int menuNo = Prompt.inputInt("메뉴> ");

      if (menuNo == 1) {
        generalMemberHandler.service();
      } else if (menuNo == 2) {
        supportedMemberHandler.service();
      } else if (menuNo == 3) {
        companyMemberHandler.service();
      } else if (menuNo == 4) {
        boardHandler.service();
      } else if (menuNo == 9) {
        break;
      } else {
        System.out.println("잘못된 메뉴 번호 입니다.");
      }
    }
  }
}