package bitcamp.bootapp.vo;

// 의료 장비 
public class Facility {
  int no;   // 장비 번호
  String companyName;   // 회사 이름
  String productName;   // 제품 이름
  String companyTel;    //  회사 연락처
  String installedPlace;    //  설치 장소
  boolean condition;    // 정상 작동 여부
  int price;
  int stock;    // 재고 수량
  int checkCycle;    // 점검 사이클
  String installedDate; // 설치 일자

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getCompanyTel() {
    return companyTel;
  }

  public void setCompanyTel(String companyTel) {
    this.companyTel = companyTel;
  }

  public String getInstalledPlace() {
    return installedPlace;
  }

  public void setInstalledPlace(String installedPlace) {
    this.installedPlace = installedPlace;
  }

  public boolean isCondition() {
    return condition;
  }

  public void setCondition(boolean condition) {
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

  public String getInstalledDate() {
    return installedDate;
  }

  public void setInstalledDate(String installedDate) {
    this.installedDate = installedDate;
  }




}
