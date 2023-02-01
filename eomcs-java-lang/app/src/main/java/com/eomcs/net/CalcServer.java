package com.eomcs.net;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class CalcServer {

  public static void main(String[] args) throws Exception {

    //    Scanner keyScan = new Scanner(System.in);

    System.out.println("서버 실행 중...");

    ServerSocket serverSocket = new ServerSocket(8888);

    Socket socket = serverSocket.accept();
    System.out.println("계산해 달래요!");

    Scanner in = new Scanner(socket.getInputStream());
    PrintStream out = new PrintStream(socket.getOutputStream());


    while (true) {
      // 클라이언트가 보낸 문자열을 한 줄 읽을 떄까지 리턴하지 않는다.
      String method = in.nextLine();
      System.out.printf("계산해주세요: %s\n", method);

      if (method.equals("quit")) {
        break;
      }

      String[] compute = method.split(" ");

      int a = Integer.parseInt(compute[0]);
      int b = Integer.parseInt(compute[2]);
      String c = compute[1];

      int result = 0;
      String failed = null;

      switch (c) {
        case "+" : result = a + b; break;
        case "-" : result = a - b; break;
        case "*" : result = a * b; break;
        case "/" : result = a / b; break;
        default: 
          failed = "Cannot Calculate";
          out.println(failed);
          System.out.println("계산 불가요");
      }
      //      if (c != "+" || c != "-" || c != "*" || c != "/") {
      //        System.out.println("연산자 똑바로 입력 바람!!");
      //      } else {
      System.out.printf("계산 결과 나왔습니다.\n %d %s %d = %d\n", a, c, b, result);
      out.println(result);
      //      }
    }
    //      if (c != "+" || c != "-" || c != "*" || c != "/") {
    //        result = a + b;
    //      } 
    //      } else if (c == "-") {
    //        result = a - b;
    //      } else if (c == "*") {
    //        result = a * b;
    //      } else if (c == "/") {
    //        result = a / b;
    //      } else {
    //        out.println("연산자 똑바로 입력하세요");         
    //      }

    //      break;
    //      }
    //    } catch (Exception e) {
    //      e.printStackTrace();
    //      //      System.out.println("연산자를 제대로 입력하세요");
    //    }

    //
    //
    socket.close();
    serverSocket.close();

    System.out.println("계산 종료!!");
  }
  //    keyScan.close();
}
