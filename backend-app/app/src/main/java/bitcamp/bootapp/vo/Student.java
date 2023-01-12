package bitcamp.bootapp.vo;

// 회원 데이터를 담을 메모리를 설계한다.
public class Student extends Member {
  // Student 클래스는 Member 클래스의 코드를 사용할 수 있다.

  private String postNo;
  private String basicAddress;
  private String detailAddress;
  private boolean working;
  private  char gender;
  private byte level;

  public String getPostNo() {
    return postNo;
  }
  public void setPostNo(String postNo) {
    this.postNo = postNo;
  }
  public String getBasicAddress() {
    return basicAddress;
  }
  public void setBasicAddress(String basicAddress) {
    this.basicAddress = basicAddress;
  }
  public String getDetailAddress() {
    return detailAddress;
  }
  public void setDetailAddress(String detailAddress) {
    this.detailAddress = detailAddress;
  }
  public boolean isWorking() {
    return working;
  }
  public void setWorking(boolean working) {
    this.working = working;
  }
  public char getGender() {
    return gender;
  }
  public void setGender(char gender) {
    this.gender = gender;
  }
  public byte getLevel() {
    return level;
  }
  public void setLevel(byte level) {
    this.level = level;
  }

}
