public class IF_ELSE_1 {
  public static void main(String[] args) {
    //정수가 짝수이면 1을 출력, 그렇지 않으면 2를 출력
    int x = 3;

    //만약에 ~~라면...
    //if와 else를 동시에 사용하면 둘 중 하나만 반드시 실행!
    if(x % 2 == 0){
      System.out.println(1);
    }
    //그렇지 않으면...
    else{
      System.out.println(2);
    }

  }
}
