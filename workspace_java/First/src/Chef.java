public class Chef implements Cookable{
  public void cook(){
    System.out.println("요리사가 요리합니다.");
  }
}



class SuperChef implements Cookable{
  public void cook(){
    System.out.println("슈퍼요리사가 요리합니다.");
  }
}



interface Cookable{
  //cook()메서드로 요리할 수 있는 가능자 모집
  public void cook();
}