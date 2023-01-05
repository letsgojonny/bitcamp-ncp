package com.eomcs.oop.ex02.test;

import com.eomcs.oop.ex02.test.util.Calculator;

// # 관련된 기능(메서드)을 묶어 분류하기
// 1) 분류 전
// 2) 메서드를 클래스로 묶어 분류하기
// 3) 클래스 변수 도입
// 4) 인스턴스 변수 도입
// 5) 인스턴스 메서드 활용
// 6) 패키지 멤버 클래스로 분리
// 7) 클래스를 역할에 따라 패키지로 분류하기
//
public class CalculatorTest {



  public static void main(String[] args) {


    Calculator c1 = new Calculator();


    c1.plus(2);
    c1.plus(3);
    c1.minus(1);
    c1.multiple(7);
    c1.divide(3);

    System.out.printf("result = %d\n", c1.result);

  }


}


