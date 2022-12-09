class Exam01 {

  public static void main(String[] args) {
    System.out.println("Hello");

    // 설계도
    class Student {
      String name;
      int age;
      boolean working;
    }

    // 설계도에 따라 객체 생성(메모리 준비)
    Student obj = new Student();

    obj.name = "hong";
    obj.age = 20;
    obj.working = true;
    // obj.tel = "010-1111-2222";  // 컴파일 오류!! 설계도에 없는 프로퍼티는 사용불가

    // 객체 프로퍼티에 저장된 값 꺼내기
    System.out.println(obj.name);
    System.out.println(obj.age);
    System.out.println(obj.working);

    // java.util.HashMap obj2 = new java.util.HashMap();
    // obj2.put("name", "Hong");
    // obj2.put("age", "5");
    // obj2.put("working", true);

    // System.out.println(obj2.name);
    // System.out.println(obj2.age);
    // System.out.println(obj2.working);
  } 
}