public class 문제_12_for {
  public static void main(String[] args) {
    // 1부터 누적해서 합을 구하다 300이 최초로 넘었을 때
    // for문을 멈추고 그때까지의 합과
    // 마지막으로 더해진 값을 각각 출력하여라

    int sum = 0;
    for(int i = 1 ; i < 100 ; i++){
      sum += i;
      if(sum >= 300){
        System.out.println("합 : " + sum);
        System.out.println("마지막에 더해진 값 : " + i);
        break;
      }
    }


    //변수는 이름이 중복 불가!
    //변수는 해당 변수가 선언된 중괄호 안에서만 사용 가능!
    if(true){
      int a;
    }
    if(true){
      int a;
    }
  }
}
