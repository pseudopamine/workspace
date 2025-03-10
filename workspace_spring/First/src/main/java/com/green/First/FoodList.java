package com.green.First;

public class FoodList {
  private String menu;
  private int each;
  private String add;
  private String please;
  private String date;

  public FoodList(String menu, int each, String add, String please, String date) {
    this.menu = menu;
    this.each = each;
    this.add = add;
    this.please = please;
    this.date = date;
  }

  public String getMenu() {
    return menu;
  }

  public void setMenu(String menu) {
    this.menu = menu;
  }

  public int getEach() {
    return each;
  }

  public void setEach(int each) {
    this.each = each;
  }

  public String getAdd() {
    return add;
  }

  public void setAdd(String add) {
    this.add = add;
  }

  public String getPlease() {
    return please;
  }

  public void setPlease(String please) {
    this.please = please;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "FoodList{" +
            "menu='" + menu + '\'' +
            ", each=" + each +
            ", add='" + add + '\'' +
            ", please='" + please + '\'' +
            ", date='" + date + '\'' +
            '}';
  }
}
