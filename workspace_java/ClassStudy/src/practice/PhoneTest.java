package practice;

public class PhoneTest {
  public static void main(String[] args) {
    Phone iphone = new Phone();

    iphone.madeBy = "APPLE";
    iphone.name = "iPhone22";
    iphone.color = "Space Gray";
    iphone.price = 1700000;
    iphone.phoneNum = "01012340987";

    iphone.printInfo();

  }
}
