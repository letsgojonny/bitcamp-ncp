package hackathon2.backend.vo;

// member 번호 이름 전화 등록일
public class Patient extends General {
  private String symptome; // 증상
  private int bloodType; // 혈액형
  private float bodyHeat; // 체온
  private int bloodPressure; // 혈압
  private int height; // 키
  private int weight; // 몸무게
  private boolean oversea; // 최근 14일간 해외 여행 여부
  private boolean insurance;
  private boolean hospitalized;

  public boolean isInsurance() {
    return insurance;
  }
  public void setInsurance(boolean insurance) {
    this.insurance = insurance;
  }
  public boolean isHospitalized() {
    return hospitalized;
  }
  public void setHospitalized(boolean hospitalized) {
    this.hospitalized = hospitalized;
  }
  public String getSymptome() {
    return symptome;
  }
  public void setSymptome(String symptome) {
    this.symptome = symptome;
  }
  public int getBloodType() {
    return bloodType;
  }
  public void setBloodType(int bloodType) {
    this.bloodType = bloodType;
  }
  public boolean getOversea() {
    return oversea;
  }
  public void setOversea(boolean oversea) {
    this.oversea = oversea;
  }
  public float getBodyHeat() {
    return bodyHeat;
  }
  public void setBodyHeat(float bodyHeat) {
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
}
