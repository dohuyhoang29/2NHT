package com.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Order {

  public final static String TO_PAY = "To Pay";
  public final static String TO_SHIP = "To Ship";
  public final static String TO_RECEIVE = "To Receive";
  public final static String COMPLETED = "Completed";

  private ObjectProperty<Integer> id;
  private ObjectProperty<Integer> accountID;
  private StringProperty name;
  private ObjectProperty<Integer> totalPrice;
  private LocalDate createDate;
  private StringProperty createDateProperty;
  private StringProperty status;
  private StringProperty address;
  private StringProperty phoneNumber;

  public Order (Integer id, Integer accountID, String name, Integer totalPrice, LocalDate createDate, String status, String address, String phoneNumber) {
    this.id = new SimpleObjectProperty<>(id);
    this.accountID = new SimpleObjectProperty<>(accountID);
    this.name = new SimpleStringProperty(name);
    this.totalPrice = new SimpleObjectProperty<>(totalPrice);
    this.createDate = createDate;
    this.createDateProperty = new SimpleStringProperty(createDate.toString());
    this.status = new SimpleStringProperty(status);
    this.address = new SimpleStringProperty(address);
    this.phoneNumber = new SimpleStringProperty(phoneNumber);
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

  public String getName() {
    return name.get();
  }

  public StringProperty getNameProperty() {
    return name;
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public Integer getTotalPrice() {
    return totalPrice.get();
  }

  public ObjectProperty<Integer> getTotalPriceProperty() {
    return totalPrice;
  }

  public void setTotalPrice(Integer totalPrice) {
    this.totalPrice.set(totalPrice);
  }

  public LocalDate getCreateDate() {
    return createDate;
  }

  public void setCreateDate(LocalDate createDate) {
    this.createDate = createDate;
  }

  public String getCreateDateProperty() {
    return createDateProperty.get();
  }

  public StringProperty getCreateDatePropertyProperty() {
    return createDateProperty;
  }

  public void setCreateDateProperty(String createDateProperty) {
    this.createDateProperty.set(createDateProperty);
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

  public String getPhoneNumber() {
    return phoneNumber.get();
  }

  public StringProperty getPhoneNumberProperty() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber.set(phoneNumber);
  }
}
