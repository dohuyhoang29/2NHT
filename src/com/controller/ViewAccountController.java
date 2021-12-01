package com.controller;

import com.model.Account;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ViewAccountController {
  @FXML
  private Label username;

  @FXML
  private Label email;

  @FXML
  private Label password;

  @FXML
  private Label type;

  @FXML
  private Label address;

  @FXML
  private Label phone;

  public void setData(Account account) {
    username.setText(account.getUsername());
    email.setText(account.getEmail());
    password.setText(account.getPassword());
    type.setText(account.getType());
    if (account.getAddress() != null) {
      address.setText(account.getAddress());
    }
    phone.setText(account.getPhone());
  }
}
