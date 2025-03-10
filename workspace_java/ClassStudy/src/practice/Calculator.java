package practice;

import java.util.PrimitiveIterator;
import java.util.Scanner;

public class Calculator {
  /*int num1;
  int num2;
  String operator;
  Scanner sc = new Scanner(System.in);*/
  private int a;
  private int b;

  public Calculator(int a, int b){
    this.a = a;
    this.b = b;
  }

  public void setA(int a){
    this.a = a;
  }
  public void setB(int b){
    this.b = b;
  }

  //더한 결과를 리턴하는 기능
  public int getSum(){
    return a + b;
  }

  //빼기 기능
  public int getSub(){
    return a - b;
  }

  //곱하기 기능
  public int getMulti(){
    return a * b;
  }

  //나누기 기능
  public double getDiv(){
    return a / (double)b;
  }



 /* public void firstNum(int num1){
    this.num1 = num1;
  }
  public void secondNum(int num){
    this.num2 = num;
  }
  public void setOperator(String operator){
    this.operator = operator;
  }

  public void setAll(){
    System.out.print("첫 번째 수 : ");
    num1 = sc.nextInt();
    System.out.print("두 번째 수 : ");
    num2 = sc.nextInt();
    System.out.print("연산자 : ");
    operator = sc.next();
  }*/

  /*public void printAll(){
    System.out.print(num1 + " " + operator + " " + num2 + " = ");
    if(operator.equals("+")){
      System.out.println(num1 + num2);
    }
    else if(operator.equals("-")){
      System.out.println(num1 - num2);
    }
    else if(operator.equals("*")){
      System.out.println(num1 * num2);
    }
    else if(operator.equals("/")){
      System.out.println(num1 / num2);
    }
  }*/
}
