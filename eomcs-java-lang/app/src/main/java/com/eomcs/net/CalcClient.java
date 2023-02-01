package com.eomcs.net;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class CalcClient {
  public static void main(String[] args) throws Exception {

    Scanner keyScan = new Scanner(System.in);

    System.out.println("client 실행 중...");

    Socket socket = new Socket("127.0.0.1", 8888);
    System.out.println("server에 연결되었음!!");

    PrintStream out = new PrintStream(socket.getOutputStream());
    Scanner in = new Scanner(socket.getInputStream());
    System.out.println("종료는 quit을 입력해 주세요!");

    //    String quit = keyScan.nextLine();
    while (true) {

      System.out.println("식 입력>\nex) 15 / 3 \n");
      String method = keyScan.nextLine();
      out.println(method);
      System.out.println("서버에 메세지를 보냈음!");

      if(method.equals("quit")) {
        break;
      }

      // 서버에서 응답이 올 떄까지 리턴하지 않는다.
      String result = in.nextLine();
      System.out.printf("계산결과 나와라! : %s\n", result);

    }


    out.close();
    in.close();
    socket.close();

    System.out.println("계산기 종료!!");
    keyScan.close();
  }
}
