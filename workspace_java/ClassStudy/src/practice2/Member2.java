package practice2;

public class Member2 {
  private String name;
  private String id;
  private String password;
  private int age;

  public Member2(String name, String id){
    this.name = name;
    this.id = id;
  }

  public boolean login(String id, String pw){
    if(id.equals("hong") && password.equals("12345")){
      return true;
    }
    else{
      return false;
    }
  }

  public void logout(String id){

    System.out.println("로그아웃 되었습니다.");

  }

}
