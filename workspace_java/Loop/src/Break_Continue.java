public class Break_Continue {
  public static void main(String[] args) {
    //반복문에 break, continue 라는 명령어를 사용할 수 있음

    //break
    //가장 가까운 반복문을 종료시킴 (벗어남)
    int i = 1;
    while(i < 6){
      if(i == 3){
        break;
      }
      System.out.println(i);
      i++;
    }
    System.out.println();
    /// //////////////////////////////////////////
    //continue
    //가장 가까운 반복문의 마지막으로 이동한다.
    int a = 1;
    while(a < 6){
      a++;

      if(a == 3){
        continue;
      }
      System.out.println(a);
    }

  }
}
