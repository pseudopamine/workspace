package practice;

public class Phone {
  String madeBy;
  String name;
  String color;
  int price;
  String phoneNum;

  public void setMade(String str1){
    madeBy = str1;
  }

  public void setName(String str1){
    name = str1;
  }

  public void setColor(String str1){
    color = str1;
  }

  public void setPrice(int num){
    price = num;
  }

  public void setPhoneNum(String num){
    phoneNum = num;
  }

  public void printInfo(){
    System.out.println("제조사 : " + madeBy);
    System.out.println("모델명 : " + name);
    System.out.println("색상 : " + color);
    System.out.println("가격 : " + price);
    System.out.println("휴대폰 번호 : " + phoneNum);
  }

}
