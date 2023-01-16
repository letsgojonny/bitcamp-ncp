package hackathon2.backend.vo;

public class Staff extends General {
  private String position;
  private int department;
  private char holiday;
  private int annualPay;

  public String getPosition() {
    return position;
  }
  public void setPosition(String position) {
    this.position = position;
  }
  public int getDepartment() {
    return department;
  }
  public void setDepartment(int department) {
    this.department = department;
  }
  public char getHoliday() {
    return holiday;
  }
  public void setHoliday(char holiday) {
    this.holiday = holiday;
  }
  public int getAnnualPay() {
    return annualPay;
  }
  public void setAnnualPay(int annualPay) {
    this.annualPay = annualPay;
  }
}
