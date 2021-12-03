package com.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrderDetail {
 private ObjectProperty<Integer> id;
 private ObjectProperty<Integer> accountID;
 private ObjectProperty<Integer> productID;
 private StringProperty hardDrive;
 private StringProperty imgSrc;
 private StringProperty name;
 private ObjectProperty<Integer> price;
 private ObjectProperty<Integer> quantity;
 private StringProperty status;
 private StringProperty address;

  public OrderDetail (Integer id, Integer accountID, Integer productId, String hardDrive, String imgSrc, String name, Integer price, Integer quantity, String status, String address)  {
    this.id = new SimpleObjectProperty<>(id);
    this.accountID = new SimpleObjectProperty<>(accountID);
    this.productID = new SimpleObjectProperty<>(productId);
    this.hardDrive = new SimpleStringProperty(hardDrive);
    this.imgSrc = new SimpleStringProperty(imgSrc);
    this.name = new SimpleStringProperty(name);
    this.price = new SimpleObjectProperty<>(price);
    this.quantity = new SimpleObjectProperty<>(quantity);
    this.status = new SimpleStringProperty(status);
    this.address = new SimpleStringProperty(address);
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

  public Integer getAccountID() {
    return accountID.get();
  }

  public ObjectProperty<Integer> getAccountIDProperty() {
    return accountID;
  }

  public void setAccountID(Integer accountID) {
    this.accountID.set(accountID);
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

  public String getHardDrive() {
    return hardDrive.get();
  }

  public StringProperty getHardDriveProperty() {
    return hardDrive;
  }

  public void setHardDrive(String hardDrive) {
    this.hardDrive.set(hardDrive);
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

  public Integer getPrice() {
    return price.get();
  }

  public ObjectProperty<Integer> getPriceProperty() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price.set(price);
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

  public String getStatus() {
    return status.get();
  }

  public StringProperty getStatusProperty() {
    return status;
  }

  public void setStatus(String status) {
    this.status.set(status);
  }

  public String getAddress() {
    return address.get();
  }

  public StringProperty getAddressProperty() {
    return address;
  }

  public void setAddress(String address) {
    this.address.set(address);
  }
}
