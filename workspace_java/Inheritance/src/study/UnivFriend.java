package study;

//대학동창정보
public class UnivFriend extends Friend{
  private String major;

  public UnivFriend(String name, String tell, String major) {
    super(name, tell);
    this.major = major;
  }

  public void showInfo(){
    super.showInfo();
    System.out.println("전공 : " + major);
  }

}
