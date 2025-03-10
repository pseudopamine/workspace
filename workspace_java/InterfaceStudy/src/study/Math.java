package study;


public class Math implements MathUtil{
  //매개변수로 전달된 세 정수의 합이 짝수이면서 동시에 그 합이 5의 배수일 경우에만 true 리턴
  @Override
  public boolean isEven(int a, int b, int c) {
//    if((a + b + c) % 2 == 0 && (a + b + c) % 5 == 0){
//      return true;
//    }
//    else{
//    }
//    return false;
      int sum = a + b + c;
      return sum % 2 == 0 && sum % 5 == 0 ? true : false;
  }

  //1부터 매개변수로 받은 정수까지의 합을 리턴
  @Override
  public double getSumFromOne(int num) {
    int sum = 0;
    for(int i = 1 ; i < num + 1 ; i++){
      sum = sum + i;
    }
    return sum;
  }

  //매개변수로 넘어오는 반지름을 갖는 원의 넓이 리턴
  //단, 매개변수로 넘어온 반지름이 음수라면 원의 넓이는 0으로 리턴
  @Override
  public double getCircleArea(int rad) {
    if(rad > 0){
      return java.lang.Math.PI * rad * rad;
    }
    else{
    }
    return 0;
    //return rad < 0 ? 0 : Math.PI * rad * rad;
  }
}
