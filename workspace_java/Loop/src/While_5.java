public class While_5 {
  public static void main(String[] args) {
    //1~5까지의 합

    //1+2+3+4+5

    int num = 1;
    int sum = 0;

    while(num < 6){
      sum = sum + num;
      num++;
    }
    System.out.println(sum);
  }
}
