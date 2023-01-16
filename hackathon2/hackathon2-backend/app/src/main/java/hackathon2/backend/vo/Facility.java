package hackathon2.backend.vo;

// 의료 장비
public class Facility extends General {
  private String company;   // 회사 이름
  private String installedPlace;    //  설치 장소
  private int condition;    // 정상 작동 여부
  private int price;
  private int stock;    // 재고 수량
  private int checkCycle;    // 점검 사이클

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getInstalledPlace() {
    return installedPlace;
  }

  public void setInstalledPlace(String installedPlace) {
    this.installedPlace = installedPlace;
  }

  public int getCondition() {
    return condition;
  }

  public void setCondition(int condition) {
    this.condition = condition;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public int getCheckCycle() {
    return checkCycle;
  }

  public void setCheckCycle(int checkCycle) {
    this.checkCycle = checkCycle;
  }
}
