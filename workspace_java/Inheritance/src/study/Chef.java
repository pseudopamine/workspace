package study;

//상속 관계에 있는 두 클래스에서
//자식클래스에서 부모클래스의 메서드명과 매개변수 정보를 동일하게 작성해도 메서드 선언을 허용
//이것이 메서드 오버라이딩
//오버라이딩을 하면 부모클래스에서 정의한 메서드를 덮어써버리기 때문에
//부모클래스에서 정의한 메서드 호출 불가!!
public class Chef {
  public void makeCook() {
    System.out.println("쉐프가 요리합니다.");
  }
}

class MasterChef extends Chef{
  public void makeCook(){
    System.out.println("마스터가 요리합니다.");
  }

  public void giveOrder(){
    super.makeCook();
    makeCook();
    this.makeCook();
    System.out.println("후임에게 지시합니다.");
  }
}

