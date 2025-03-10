package practice;

public class Student {
  String name;
  int age;
  String adr;
  int number;
  String phone;

  public void setAllInfo(String a, int num1, String b, int num2, String c){
    name = a;
    age = num1;
    adr = b;
    number = num2;
    phone = c;
  }

  public void setName(String a){
    name = a;
  }
  public void setAge(int num1){
    age = num1;
  }
  public void setAdr(String b){
    adr = b;
  }
  public void setNumber(int num2){
    number = num2;
  }
  public void setPhone(String c){
    phone = c;
  }

  public String setName1(String a){
    name = a;
    return name;
  }
  public int setAge1(int num1){
    age = num1;
    return age;
  }
  public String setAdr1(String b){
    adr = b;
    return adr;
  }
  public int setNumber1(int num2){
    number = num2;
    return number;
  }
  public String setPhone1(String c){
    phone = c;
    return phone;
  }
  public void printAllInfo(){
    System.out.println("이름 : " + name);
    System.out.println("나이 : " + age);
    System.out.println("주소 : " + adr);
    System.out.println("학번 : " + number);
    System.out.println("연락처 : " + phone);
  }



}
