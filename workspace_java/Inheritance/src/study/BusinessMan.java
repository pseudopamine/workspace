package study;

//클래스 선언문에서 클래스명 다음에 'extends 상속할클래스명' 문법을 적용하면 상속할 수 있다.
//상속을 사용하면 상속하는 클래스의 멤버변수, 메서드를 물려 받는다.
//물려받는다 : 멤버변수와 메서드를 내 것처럼 사용 가능
public class BusinessMan extends Man{
  String company;

//  public BusinessMan(String name, String company){
//    this.name = name; //가능은 하지만 좋은 코드는 아님. 초기화는 맴버변수를 선언한 클래스가 책임지고 초기화하는 것이 맞기 때문
//    this.company = company;
//  }


  //자식 클래스는 부모 클래스의 멤버변수, 메서드를 사용하기 위해 내부적으로 부모 클래스의 생성자를 호출해서 객체를 하나 생성
  //부모 클래스의 생성자를 호출하는 코드는 자식 클래스의 생성자 첫 줄에 숨겨져있음.
  //부모 클래스의 생성자는 매개변수 정보가 없는 생성자를 호출
  public BusinessMan(String company){
    super(); //부모클래스의 매개변수 정보가 없는 생성자 호출
    this.company = company;
  }



  public void tellCompany(){
    System.out.println("my company is " +  company);
  }
}
