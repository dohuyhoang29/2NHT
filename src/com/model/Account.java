package com.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account {
  private ObjectProperty<Integer> id;
  private StringProperty username;
  private StringProperty email;
  private StringProperty password;
  private StringProperty status;
  private StringProperty type;
  private StringProperty address;
  private StringProperty phone;

  public Account() {}

  public Account (Integer id, String username, String email, String password, String status, String type, String address, String phone) {
    this.id = new SimpleObjectProperty<>(id);
    this.username = new SimpleStringProperty(username);
    this.email = new SimpleStringProperty(email);
    this.password = new SimpleStringProperty(password);
    this.status = new SimpleStringProperty(status);
    this.type = new SimpleStringProperty(type);
    this.address = new SimpleStringProperty(address);
    this.phone = new SimpleStringProperty(phone);
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

  public String getUsername() {
    return username.get();
  }

  public StringProperty getUsernameProperty() {
    return username;
  }

  public void setUsername(String username) {
    this.username.set(username);
  }

  public String getEmail() {
    return email.get();
  }

  public StringProperty getEmailProperty() {
    return email;
  }

  public void setEmail(String email) {
    this.email.set(email);
  }

  public String getPassword() {
    return password.get();
  }

  public StringProperty getPasswordProperty() {
    return password;
  }

  public void setPassword(String password) {
    this.password.set(password);
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

  public String getType() {
    return type.get();
  }

  public StringProperty getTypeProperty() {
    return type;
  }

  public void setType(String type) {
    this.type.set(type);
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

  public String getPhone() {
    return phone.get();
  }

  public StringProperty getPhoneProperty() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone.set(phone);
  }
}
