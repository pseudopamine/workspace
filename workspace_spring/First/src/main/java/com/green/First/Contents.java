package com.green.First;

public class Contents {
  private int num;
  private String title;
  private String name;
  private int cnt;
  private String content;

  public Contents(int num, String title, String name, int cnt, String content) {
    this.num = num;
    this.title = title;
    this.name = name;
    this.cnt = cnt;
    this.content = content;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCnt() {
    return cnt;
  }

  public void setCnt(int cnt) {
    this.cnt = cnt;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
