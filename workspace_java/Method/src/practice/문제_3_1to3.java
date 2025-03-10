package practice;

public class 문제_3_1to3 {
  public static void main(String[] args) {
    //문제 1번
    test1(3);
    System.out.println();

    //문제 2번
    test2(7);
    System.out.println();

    //문제 3번
    System.out.println(test3());
    int random = test3();
    System.out.println(random);


  }

  public static void test1(int num){
    int googoodan = 0;
    for(int i = 1 ; i < 10 ; i++){
      googoodan = num * i;
      System.out.println(num + " * " + i + " = " + googoodan);
    }
  }

  public static void test2(int num){
    for(int i = 1 ; i < 101 ; i++){
      if(i % num == 0){
        System.out.print(i + " ");
      }
    }
  }

  public static int test3(){
    return (int)(Math.random() * 50) + 1;

  }

}















