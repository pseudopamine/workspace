package com.green.First;

public class OrderList {
  private int listNum;
  private String product;
  private int price;
  private int cnt;
  private int productNum;
  private String orderId;

  public OrderList(int listNum, String product, int price, int cnt, int productNum, String orderId) {
    this.listNum = listNum;
    this.product = product;
    this.price = price;
    this.cnt = cnt;
    this.productNum = productNum;
    this.orderId = orderId;
  }

  public int getListNum() {
    return listNum;
  }

  public void setListNum(int listNum) {
    this.listNum = listNum;
  }

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getCnt() {
    return cnt;
  }

  public void setCnt(int cnt) {
    this.cnt = cnt;
  }

  public int getProductNum() {
    return productNum;
  }

  public void setProductNum(int productNum) {
    this.productNum = productNum;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }
}
