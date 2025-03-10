package 접근제한자1;

/*
* 접근제한자
*   - 멤버변수, 메서드, 클래스의 사용 범위를 지정
* 종류 : public, > default, > protected, > private
* public 선언된 변수와 메서드 : 같은 프로젝트라면 접근 가능
* default 선언된 변수와 메서드 : 같은 패키지라면 접근 가능
*   - default는 키워드 없음
* private 선언된 변수와 메서드 : 변수, 메서드를 선언한 클래스 안에서만 접근 가능
*
* 결론
*   - 멤버변수에는 무조건 private
*   - 메서드에는 무조건 public
*
* */
public class Orange {
  private int a;
  int b;
  public int c;

  private int price;

  //setter
  public void setPrice(int price){
    this.price = price;
  }

  //getter




  private void aaa(){}
  void bbb(){}
  public void ccc(){}
}
