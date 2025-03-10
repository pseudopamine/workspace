
public class Restaurant {
  private String addr;
  private Cookable chef = new Chef();

  public void takeOrder(){
    chef.cook();
  }

}
