package study;

//interface는 class와 동일하게 첫 글자를 대문자로 작성!
//클래스의 구성요소(작성할 수 있는 것) : 멤버변수, 메서드의 정의, 생성자
//인터페이스의 구성요소 : 추상메서드.
public interface Print {
  //메서드의 정의는 있지만, 내용부가 없는 메서드를 추상메서드로 부름.

  // 흑백 출력 기능
  public void print();

  // 컬러 출력 기능
  public void colorPrint();
}
