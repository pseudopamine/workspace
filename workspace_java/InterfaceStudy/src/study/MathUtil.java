package study;

//아래 인터페이스에서 제시한 메서드를 구현하는 클래스를 만들고 요구사항에 맞게 메서드를 구현
public interface MathUtil {
  //매개변수로 전달된 세 정수의 합이 짝수이면서 동시에 그 합이 5의 배수일 경우에만 true 리턴
  public boolean isEven(int a, int b, int c);

  //1부터 매개변수로 받은 정수까지의 합을 리턴
  public double getSumFromOne(int num);

  //매개변수로 넘어오는 반지름을 갖는 원의 넓이 리턴
  //단, 매개변수로 넘어온 반지름이 음수라면 원의 넓이는 0으로 리턴
  public double getCircleArea(int rad);
}
