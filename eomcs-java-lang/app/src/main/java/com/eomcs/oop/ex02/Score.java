package com.eomcs.oop.ex02;

class Score {
  String name;
  int kor;
  int eng;
  int math;
  int sum;
  float aver;

  // static method ==> instance method
  void compute() {
    // 인스턴스 메서드를 호출할 때 넘겨준 인스턴스를 주소는
    // this 라면 내장 변수(built-in)에 보관된다.
    this.sum = this.kor + this.eng + this.math;
    this.aver = (float) this.sum / 3;
  }
}
