package com.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cart {
  ObjectProperty<Integer> id;
  ObjectProperty<Integer> quantity;
  ObjectProperty<Integer> productID;
  ObjectProperty<Integer> accountID;
  StringProperty imgSrc;
  StringProperty name;
  StringProperty hardDrive;
  ObjectProperty<Integer> price;

  public Cart (Integer id, Integer quantity, Integer productID, Integer accountID, String imgSrc, String name, String hardDrive, Integer price) {
    this.id = new SimpleObjectProperty<>(id);
    this.quantity = new SimpleObjectProperty<>(quantity);
    this.productID = new SimpleObjectProperty<>(productID);
    this.accountID = new SimpleObjectProperty<>(accountID);
    this.imgSrc = new SimpleStringProperty(imgSrc);
    this.name = new SimpleStringProperty(name);
    this.hardDrive = new SimpleStringProperty(hardDrive);
    this.price = new SimpleObjectProperty<>(price);
  }

  public Integer getId() {
    return id.get();
  }

  public ObjectProperty<Integer> getIdProperty() {
    return id;
  }

  public void setId(Integer id) {
    this.id.set(id);
  }

  public Integer getQuantity() {
    return quantity.get();
  }

  public ObjectProperty<Integer> getQuantityProperty() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity.set(quantity);
  }

  public Integer getProductID() {
    return productID.get();
  }

  public ObjectProperty<Integer> getProductIDProperty() {
    return productID;
  }

  public void setProductID(Integer productID) {
    this.productID.set(productID);
  }

  public Integer getAccountID() {
    return accountID.get();
  }

  public ObjectProperty<Integer> getAccountIDProperty() {
    return accountID;
  }

  public void setAccountID(Integer accountID) {
    this.accountID.set(accountID);
  }

  public String getImgSrc() {
    return imgSrc.get();
  }

  public StringProperty getImgSrcProperty() {
    return imgSrc;
  }

  public void setImgSrc(String imgSrc) {
    this.imgSrc.set(imgSrc);
  }

  public String getName() {
    return name.get();
  }

  public StringProperty getNameProperty() {
    return name;
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public String getHardDrive() {
    return hardDrive.get();
  }

  public StringProperty getHardDriveProperty() {
    return hardDrive;
  }

  public void setHardDrive(String hardDrive) {
    this.hardDrive.set(hardDrive);
  }

  public Integer getPrice() {
    return price.get();
  }

  public ObjectProperty<Integer> priceProperty() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price.set(price);
  }
}
