package study;

public class PersonManager2 {
  public static void main(String[] args) {
    //친구 객체를 열 개 저장할 수 있는 배열 생성
    Friend[] fs = new Friend[10];
    int cnt = 0;

    fs[cnt++] = new UnivFriend("김자바", "010-2653-8762", "생물학과");
    fs[cnt++] = new CompFriend("김사원", "010-7852-0152", "영업부");


    for(int i = 0 ; i < cnt ; i++){
      fs[i].showInfo();
    }


  }
}
