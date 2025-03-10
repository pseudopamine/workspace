package practice2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmpService {
  private Scanner sc;
  private Emp emp;
  List<Emp> empList;

  public EmpService(){
    sc = new Scanner(System.in);
    emp = new Emp();
    empList = new ArrayList<>();
  }

  //List 객체에 임의로 데이터로 초기화된 사원 객체 5 ~ 10개 추가
  public void regEmp(){
    empList.add(new Emp(1001, "김자바", "기술개발1팀", "010-1111-2222", 2900000));
    empList.add(new Emp(1002, "이자바", "기술개발2팀", "010-1111-3333", 2900000));
    empList.add(new Emp(1003, "최자바", "기술개발3팀", "010-1111-4444", 2800000));
    empList.add(new Emp(1004, "박자바", "영업1팀", "010-1111-5555", 3500000));
    empList.add(new Emp(1005, "강자바", "영업2팀", "010-1111-6666", 3600000));
    empList.add(new Emp(1006, "장자바", "영업3팀", "010-1111-7777", 3700000));
    empList.add(new Emp(1007, "임자바", "재무회계1팀", "010-1111-8888", 4000000));
    empList.add(new Emp(1008, "윤자바", "재무회계2팀", "010-1111-9999", 4000000));
    empList.add(new Emp(1009, "조자바", "비서실", "010-1111-1111", 3500000));

  }


  //EmpService에 로그인 하는 서비스
  public void login(){
    System.out.print("사번 : ");
    int idNumber = sc.nextInt();
    System.out.print("비밀번호(연락처의 마지막 4자리) : ");

  }

  //부서명을 입력받아 사원들 월급 정보를 출력하는 메소드
  public void moneyInfo(){
    System.out.println("월급정보 인출");
  }

  //키보드로 입력받은 부서에 소속된 모든 사원의 월급을 인상하는 메소드
  public void moneyUpgrade(){
    System.out.println("월급 인상해줄게");
  }


}
