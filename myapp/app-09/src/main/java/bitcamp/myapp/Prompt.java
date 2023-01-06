package bitcamp.myapp;

import java.util.Scanner;

public class Prompt {
  static Scanner scanner = new Scanner(System.in);

  static String inputString(String title) {
    System.out.print(title);
    return scanner.nextLine();
  }

  static int inputInt(String title) {
    return Integer.parseInt(inputString(title));
  }

  static void close () {
    scanner.close();
  }

}
