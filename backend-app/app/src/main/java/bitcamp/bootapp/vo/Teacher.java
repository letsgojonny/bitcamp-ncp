package bitcamp.bootapp.vo;

// 회원 데이터를 담을 메모리를 설계한다.
public class Teacher {
  private int no;
  private String name;
  private String tel;
  private String email;
  private String finalEducation;
  private String university;
  private String major;
  private byte pay;
  private String createdDate;


  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getFinalEducation() {
    return finalEducation;
  }
  public void setFinalEducation(String finalEducation) {
    this.finalEducation = finalEducation;
  }
  public String getUniversity() {
    return university;
  }
  public void setUniversity(String university) {
    this.university = university;
  }
  public String getMajor() {
    return major;
  }
  public void setMajor(String major) {
    this.major = major;
  }
  public byte getPay() {
    return pay;
  }
  public void setPay(byte pay) {
    this.pay = pay;
  }
  public String getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }



}
