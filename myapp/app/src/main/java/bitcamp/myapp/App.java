package bitcamp.myapp;

public class App {

  public static void main(String[] args) {

    goMainMenu();
    System.out.println("Good Bye");

    Prompt.close();

  } // main()

  private static void goMainMenu() {
    while (true) {

      System.out.println("1. 회원 관리");
      System.out.println("9. 종료");

      int menuNo = Prompt.inputInt("메뉴1--> ");

      // 첫 번째 메뉴
      if (menuNo == 1) {
        MemberHandler.service();
      } else if (menuNo == 9) {
        break;
      } else {
        System.out.println("잘못된 입력입니다");
      }
    }
  }
}// class App









