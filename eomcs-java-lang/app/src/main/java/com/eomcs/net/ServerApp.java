package com.eomcs.net;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerApp {

  public static void main(String[] args) throws Exception {

    Scanner keyScan = new Scanner(System.in);

    System.out.println("서버 실행 중...");

    ServerSocket serverSocket = new ServerSocket(8888);

    Socket socket = serverSocket.accept();
    System.out.println("클라이언트와 연결됨!");

    Scanner in = new Scanner(socket.getInputStream());
    PrintStream out = new PrintStream(socket.getOutputStream());

    // 클라이언트가 보낸 문자열을 한 줄 읽을 떄까지 리턴하지 않는다.
    String message = in.nextLine();
    System.out.printf("계산해주세요: %s\n", message);
    try {
      String[] compute = message.split(" ");

      int a = Integer.parseInt(compute[0]);
      int b = Integer.parseInt(compute[2]);
      String c = compute[1];

      int result = 0;

      switch (c) {
        case "+" : result = a + b; break;
        case "-" : result = a - b; break;
        case "*" : result = a * b; break;
        case "/" : result = a / b; break;
        default: return ;

      }
      System.out.printf("계산 결과 나왔습니다.\n %d %s %d = %d\n", a, c, b, result);
      out.println(result);

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("연산자를 제대로 입력하세요");
    }

    //
    //
    socket.close();
    serverSocket.close();

    System.out.println("서버 종료!!");
    //    keyScan.close();
  }
}
