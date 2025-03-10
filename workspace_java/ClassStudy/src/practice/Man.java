package practice;
//멤버변수 각각의 값을 변경하는 메소드
//setter 만드세요~
//setter -> 멤버변수 하나의 값을 변경시키는 메서드
//       -> setter 메서드는 이름이 정해져있음 (set멤버변수명)
// name값을 변경하는 setter 메서드 정의

//각 멤버변수의 값을 리턴하는 메소드
//name을 리턴하는 메서드
//getter -> 멤버변수 하나의 값을 리턴하는 메서드
//       -> 메서드의 이름 : get변수명
public class Man {
  String name;
  int age;
  String adr;

  //멤버변수의 모든 값을 초기화 할 수 있는 메소드
  public void initMember(String name , int age, String adr){
    this.name = name;
    this.age = age;
    this.adr = adr;
  }


  public void setName(String name){
    this.name = name;
  }
  // age값을 변경하는 setter 정의
  public void setAge(int age){
    this.age = age;
  }
  public void setAdr(String adr){
    this.adr = adr;
  }


  public String getName(){
    return name;
  }

  //age를 리턴하는 메서드
  public int getAge(){
    return age;
  }

  public String getAdr(){
    return this.adr;  //똑같다
  }

  public void printInfo(){
    System.out.println("이름 : " + getName());
    System.out.println("나이 : " + age);
    System.out.println("주소 : " + adr);
  }


}
