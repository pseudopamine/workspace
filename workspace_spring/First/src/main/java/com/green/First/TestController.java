package com.green.First;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//annotation
@RestController
public class TestController {

  //localhost:8080/t1
  @GetMapping("/t1")
  public String test1(){
    return "java";
  }

  @GetMapping("/t2")
  public int test2(){
    return 10;
  }

  @GetMapping("/t3")
  public Person test3(){
    //Person 클래스에 대한 객체를 하나 생성!
    //클래스명 객체명 = new 생성자호출;
    Person person = new Person("나훈아", 73, "부산시");
    return person;
  }

  @GetMapping("/t4")
  public List<Person> test4(){
    //person 클래스에 대한 객체를 다수 저장할 수 있는 리스트 생성
    //자료형 변수명;
    List<Person> list = new ArrayList<>();

    Person person1 = new Person("조용필", 72, "개성시");
    Person person2 = new Person("주현미", 64, "광주시");
    Person person3 = new Person("이승환", 55, "대구시");

    list.add(person1);
    list.add(person2);
    list.add(person3);

    return list;
  };

  @GetMapping("/t5")
  public List<Student> test5(){
    List<Student> studentList = new ArrayList<>();

    studentList.add(new Student("금강산", 45, 75, 69));
    studentList.add(new Student("한라산", 58, 91, 97));
    studentList.add(new Student("백두산", 86, 87, 63));
    studentList.add(new Student("가지산", 98, 65, 78));
    studentList.add(new Student("신불산", 88, 94, 100));

    return studentList;

  };

  //react에서 전달하는 데이터를 자바에서 받는 문법은 두 가지 존재
  //1. class를 활용하는 방법
  //react에서 전달하는 객체의 key값과
  //java에서 데이터를 받기 위해 매개변수에 선언한 클래스의 변수명이
  //동일하면 데이터를 자동으로 받아온다
  //2. Collection Framework의 Map을 활용하는 방법
  @PostMapping("/t6")
  public void test6(@RequestBody Student student){
    System.out.println(student.toString());
  };

  //DTO : Data Transfer Object
  // -> 데이터를 이동시켜주는 객체
  @PostMapping("/t7")
  public void test7(@RequestBody PhoneDTO phoneDTO){
    System.out.println(phoneDTO.toString());
  };

  @PostMapping("/t8")
  public int test8(@RequestBody Student student){
    System.out.println(student.toString());
    int sum = student.getKorScore() + student.getEngScore() + student.getMathScore();
    return sum;
  }

  @PostMapping("/t9")
  public void test9(@RequestBody FoodList foodList){
    System.out.println(foodList.toString());
  }

  @GetMapping("/t10")
  public int[] test10(){
    int[] lotto = new int[6];
    for(int i = 0 ; i < lotto.length ; i++){
      lotto[i] = (int)(Math.random() * 45) + 1;
    }
    return lotto;
  }

  @GetMapping("/getLottoNum")
  public int getLottoNum(){
    int num  = (int)(Math.random() * 45 + 1);
    return num;
  }


  @GetMapping("/getContents")
  public List<Contents> getContents() {
    List<Contents> content = new ArrayList<>();

    content.add(new Contents(5, "제목1", "김자바", 2, "안녕하세요."));
    content.add(new Contents(4, "제목2", "이자바", 3, "반갑습니다."));
    content.add(new Contents(3, "제목3", "박리액트", 0, "처음 뵙겠습니다."));
    content.add(new Contents(2, "제목4", "최파이썬", 7, "잘 부탁드립니다."));
    content.add(new Contents(1, "제목5", "육자바", 63, "실례지만 어디 육씨입니까?"));

    return content;
  }
  @GetMapping("/getBoardList")
  public List<Board> sendBoardList(){
    List<Board> boardList = new ArrayList<>();

    boardList.add(new Board(1, "제목입니다1", "김자바", 5, "안녕하세요."));
    boardList.add(new Board(2, "제목이지라2", "박리액트", 8, "반갑습니다."));
    boardList.add(new Board(3, "제목인디요3", "정파이썬", 12, "처음 뵙겠습니다."));
    boardList.add(new Board(4, "제목아입니까4", "구잡스", 2, "잘 부탁드립니다."));
    boardList.add(new Board(5, "제목입서예5", "최마리아", 37, "실례합니다."));

    return boardList;
  }

  @GetMapping("/getOrderList")
  public List<OrderList> sendOrderList(){
    List<OrderList> orderList = new ArrayList<>();

    orderList.add(new OrderList(1, "맨투맨 긴팔 티셔츠", 12000, 3, 1021, "KIM"));
    orderList.add(new OrderList(2, "롱패딩", 210000, 1, 2523, "HONG"));
    orderList.add(new OrderList(3, "오버핏 니트", 50000, 4, 4375, "KAN"));
    orderList.add(new OrderList(4, "옥스퍼드 셔츠", 70000, 3, 7459, "JIN"));
    orderList.add(new OrderList(5, "데님 청바지", 45000, 6, 2976, "YARN"));

    return orderList;
  }

}
