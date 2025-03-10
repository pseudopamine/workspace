package practice;

public class Member {
  String name;
  String id;
  String password;
  int age;

  public void setAllInfo(String str1, String str2, String str3, int num){
    name = str1;
    id = str2;
    password = str3;
    age = num;
  }

  public void printInfo(){
    System.out.println("회 원 명 : " + name);
    System.out.println("회원 ID : " + id);
    System.out.println("비밀번호 : " + password);
    System.out.println("나   이 : " + age);
  }

}
