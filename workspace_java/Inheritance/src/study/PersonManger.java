package study;

public class PersonManger {
  public static void main(String[] args) {
    //대학동창을 다섯 명 저장할 수 있는 배열 생성
    UnivFriend[] ufs = new UnivFriend[5];
    //대학동창이 저장될 배열의 index 정보
    int ucnt = 0;
    //직장동료가 저장될 배열의 index 정보
    int ccnt = 0;

    //직장동료를 다섯 명 저장할 수 있는 배열 생성
    CompFriend[] cfs = new CompFriend[5];

    //대학동창을 생성한 후 대학 동창 객체를 ufs에 저장
    ufs[ucnt++] = new UnivFriend("김자바", "010-2653-8762", "생물학과");
    ufs[ucnt++] = new UnivFriend("이자바", "010-4634-9543", "방송학과");


    //직장동료를 생성한 후 대학 동창 객체를 cfs에 저장
    cfs[ccnt++] = new CompFriend("김사원", "010-7852-0152", "영업부");
    cfs[ccnt++] = new CompFriend("박대리님", "010-7454-9912", "인사부");

    //저장된 대학동창 정보 출력
    for(int i = 0 ; i < ucnt ; i++){
      //ufs[i].showUnivInfo();
    }

    //저장된 직장동료 정보 출력
    for(int i = 0 ; i < ccnt ; i++){
      //cfs[i].showCompInfo();
    }
  }
}
