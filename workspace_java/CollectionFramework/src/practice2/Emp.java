package practice2;

public class Emp {
  private int idNumber;
  private String name;
  private String part;
  private String phone;
  private int money;

  public Emp(){}

  public Emp(int idNumber, String name, String part, String phone, int money) {
    this.idNumber = idNumber;
    this.name = name;
    this.part = part;
    this.phone = phone;
    this.money = money;
  }

  public int getIdNumber() {
    return idNumber;
  }

  public void setIdNumber(int idNumber) {
    this.idNumber = idNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPart() {
    return part;
  }

  public void setPart(String part) {
    this.part = part;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public int getMoney() {
    return money;
  }

  public void setMoney(int money) {
    this.money = money;
  }
}
