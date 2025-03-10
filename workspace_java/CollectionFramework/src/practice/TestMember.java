package practice;

import java.util.ArrayList;
import java.util.List;

public class TestMember {
  public static void main(String[] args) {

    List<Member> list = new ArrayList<>();

    Member member1 = new Member(36, "홍진호", "123qwer", "Stormzerg");
    Member member2 = new Member(45, "임요한", "adfhee3", "EmperorTerran");
    Member member3 = new Member(21, "이상혁", "49437ad", "Faker");

    list.add(member1);
    list.add(member2);
    list.add(member3);

    //문제 6번 : 저장된 객체의 정보를 모두 출력하는 프로그램
    for( Member m : list){
      System.out.println(m.toString());
    }
    System.out.println();

    //문제 7번 : List에 저장된 모든 회원의 나이의 합을 출력하여라
    int sum = 0;
    for(int i = 0 ; i < list.size() ; i++){
      sum = sum + list.get(i).getAge();
    }
    System.out.print("List에 저장된 모든 회원의 나이의 합은 " + sum + "세 입니다.");
    System.out.println();

    //문제 8번 : List에 저장된 회원 중 id가 Faker인 회원을 지우는 코드를 작성하세요.
    for(int i = 0 ; i < list.size() ; i++){
      if(list.get(i).getId().equals("Faker")){
        list.remove(i);
        break;
      }
    }
    System.out.println();

    for( Member m : list){
      System.out.println(m.toString());
    }
    System.out.println();







  }
}
