package static_study;

//static이 붙은 멤버변수는
//객체에 종속적이지 않고, 모든 객체가 공유하는 데이터
//static 변수는 생성자에서 초기화하지 않는다!!
//static 변수는 초기화 구문이 따로 존재!
//생성자보다 static 초기화가 먼저 진행된다!! (객체 생성보다 static 실행이 우선됨)
//static 변수는 객체명.멤버변수; 의 문법으로 호출 권장하지 않는다
//static 변수는 클래스명.변수명;
//static은 변수 및 메서드에 적용할 수 있다.


public class CountTest {
  public static void main(String[] args) {
    Count count1 = new Count();
    Count count2 = new Count();
    Count count3 = new Count();

    //static 변수는 클래스명.변수명; (객체로 접근하지 않는다)
    System.out.println(Count.cnt);

    System.out.println(Math.PI);
    System.out.println(Math.max(10, 20));

  }




}
