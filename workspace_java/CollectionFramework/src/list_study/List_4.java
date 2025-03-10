package list_study;


/* System.out.println(p1.toString());
    //출력문에 객체명만 작성하면 객체명.toString()메서드를 알아서 호출!
    System.out.println(p2);
*/

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

public class List_4 {
  public static void main(String[] args) {
    //Person 클래스의 객체를 다수 저장할 수 있는 list 객체 생성

    List<Person> list = new ArrayList<>();

    //list 객체에 저장시킬 Person 클래스의 객체 생성
    Person p1 = new Person("장만월", 20, "울산시");
    Person p2 = new Person("고애신", 23, "평양시");
    Person p3 = new Person("김희성", 27, "함북시");

    //위에서 만든 Person 객체를 list에 저장

    list.add(p1);
    list.add(p2);
    list.add(p3);

    //list에 저장된 모든 Person 객체의 정보를 출력(toString() 메서드 활용)
    for(int i = 0 ; i < list.size() ; i++){
      System.out.println(list.get(i));
    }

    System.out.println();

    //울산시에 거주하는 회원의 모든 정보 출력
    for(int i = 0 ; i < list.size() ; i++){
      if(list.get(i).getAddr().equals("울산시")){
        System.out.println(list.get(i));
      }
    }
    for( Person p :  list ){
      if(p.getAddr().equals("울산시")){
        System.out.println(p);
      }
    }
    System.out.println();

    System.out.println(list);





  }
}
