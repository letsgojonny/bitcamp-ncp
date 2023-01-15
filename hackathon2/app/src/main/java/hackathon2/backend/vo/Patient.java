package hackathon2.backend.vo;

// member 번호 이름 전화 등록일
public class Patient {

  private int no;
  private String name;
  private int tel;
  private String symptome; // 증상
  private char bloodType; // 혈액형
  private String basicAddress; // 주소
  private String detailAddress; // 상세주소
  private String oversea; // 최근 14일간 해외 여행 여부
  private byte bodyHeat; // 체온
  private int bloodPressure; // 혈압
  private int height; // 키
  private int weight; // 몸무게
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
  public int getTel() {
    return tel;
  }
  public void setTel(int tel) {
    this.tel = tel;
  }
  public String getSymptome() {
    return symptome;
  }
  public void setSymptome(String symptome) {
    this.symptome = symptome;
  }
  public char getBloodType() {
    return bloodType;
  }
  public void setBloodType(char bloodType) {
    this.bloodType = bloodType;
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
  public String getOversea() {
    return oversea;
  }
  public void setOversea(String oversea) {
    this.oversea = oversea;
  }
  public byte getBodyHeat() {
    return bodyHeat;
  }
  public void setBodyHeat(byte bodyHeat) {
    this.bodyHeat = bodyHeat;
  }
  public int getBloodPressure() {
    return bloodPressure;
  }
  public void setBloodPressure(int bloodPressure) {
    this.bloodPressure = bloodPressure;
  }
  public int getHeight() {
    return height;
  }
  public void setHeight(int height) {
    this.height = height;
  }
  public int getWeight() {
    return weight;
  }
  public void setWeight(int weight) {
    this.weight = weight;
  }
  public String getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }


}
