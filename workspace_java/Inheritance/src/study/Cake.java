package study;

public class Cake {
  public void eat(){
    System.out.println("케익을 먹는다");
  }
}


class CheeseCake extends Cake{
  public void eat(){
    System.out.println("치즈케익을 먹는다");
  }
}

class StrawberryCheeseCake extends CheeseCake{
  public void eat(){
    System.out.println("딸기치즈케익을 먹는다");
  }
}