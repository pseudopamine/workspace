package study;

//이름, 아이디, 비밀번호, 나이
public class Member {
  String name;
  String id;
  String pw;
  int age;

  //회원의 모든 정보를 변경하는 기능
  public void setAllInfo(String name, String id, String pw, int age){
    //이 클래스에서 정의된(선언된) name
    this.name = name;
    this.id = id;
    this.pw = pw;
    this.age = age;
  }
  public void printAll(){
    System.out.println("이름 : " + name);
    System.out.println("ID : " + id);
    System.out.println("PW : " + pw);
    System.out.println("나이 : " + age);
  }

}
