public class For_1_7 {
  public static void main(String[] args) {

    int cnt = 0;
    for(int i = 1; i <= 100; i++){
      if(i % 5 == 0){
        cnt++;
        System.out.print(i + " ");
      }
    }
    System.out.println();
    System.out.println("5의 배수는 " + cnt + "개 입니다.");
  }
}
