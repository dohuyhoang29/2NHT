package com.controller;

import com.helper.AccountDatabaseHelper;
import com.helper.ValidationManager;
import com.model.Account;
import com.view.Navigator;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditAccountController implements Initializable {
  @FXML
  private TextField txtSearch;

  @FXML
  private ImageView changeLanguage;

  @FXML
  private Label username;

  @FXML
  private HBox dashboard;

  @FXML
  private ImageView imgdashboard;

  @FXML
  private Label lbdashboard;

  @FXML
  private HBox addProduct2;

  @FXML
  private HBox productsList;

  @FXML
  private VBox changeLanguageContainer;

  @FXML
  private TextField txtUsername;

  @FXML
  private Label errUsername;

  @FXML
  private TextField txtEmail;

  @FXML
  private Label errEmail;

  @FXML
  private TextField txtAddress;

  @FXML
  private PasswordField pfPassword;

  @FXML
  private Label errPassword;

  @FXML
  private PasswordField pfConfirmPassword;

  @FXML
  private Label errConfirmPassword;

  @FXML
  private ChoiceBox<String> cbType;

  @FXML
  private TextField txtPhoneNumber;

  @FXML
  private Label errPhone;

  @FXML
  private Button btnSave;

  private int count;
  private Account acc;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }

  public void setData(Account account) {
    this.acc = account;
    txtUsername.setText(account.getUsername());
    txtEmail.setText(account.getEmail());
    pfPassword.setText(account.getPassword());
    pfConfirmPassword.setText(account.getPassword());
    if (account.getAddress() != null) {
      txtAddress.setText(account.getAddress());
    }
    txtPhoneNumber.setText(account.getPhone());
  }

  //Hanh dong
  @FXML
  private void showChangeLanguageMousePressed (MouseEvent mouseEvent) {
    count++;
    if (count % 2 != 0) {
      changeLanguageContainer.setVisible(true);
    } else {
      changeLanguageContainer.setVisible(false);
    }
  }

  @FXML
  void editAccount(ActionEvent event) throws IOException {
    int count = 0;
    ValidationManager check = ValidationManager.getInstance();

//    username
    if(txtUsername.getText().isEmpty()) {
      errUsername.setText("Username is required");
      count++;
    }else if(AccountDatabaseHelper.getAccountByUsernameOrEmail(txtUsername.getText()) != null && !txtUsername.getText().equalsIgnoreCase(acc.getUsername())) {
      errUsername.setText("Username exists");
      count++;
    }else if (!check.validUsername(txtUsername.getText())) {
      errUsername.setText("Username can only have characters and numbers");
      count++;
    }else {
      errUsername.setText("");
    }

//    password
    if(pfPassword.getText().isEmpty()) {
      errPassword.setText("Password is required");
      count++;
    }else if (!check.validPassword(pfPassword.getText())) {
      errPassword.setText("Use 8 or more characters with a mix of letters, numbers & symbols");
      count++;
    }else errPassword.setText("");

//    confirm password
    if (pfConfirmPassword.getText().isEmpty()) {
      errConfirmPassword.setText("Confirm Password is required");
      count++;
    }else if(!pfConfirmPassword.getText().equalsIgnoreCase(pfPassword.getText())) {
      errConfirmPassword.setText("Those passwords didn’t match");
      count++;
    }else errConfirmPassword.setText("");

//    email
    if (txtEmail.getText().isEmpty()) {
      errEmail.setText("Email is required");
      count++;
    }else if (!check.validEmail(txtEmail.getText())) {
      errEmail.setText("Email must have the same syntax as follows: xyz012@xyz.xyz");
      count++;
    }else errEmail.setText("");

//    phone
    if (txtPhoneNumber.getText().isEmpty()) {
      errPhone.setText("Phone Number is required");
      count++;
    }else if (!check.validPhoneNumber(txtPhoneNumber.getText())) {
      errPhone.setText("Phone numbers can only be numeric and have 10 numbers");
      count++;
    }else errPhone.setText("");

    if (count == 0) {
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setContentText("Are you sure you want to do it?");

      Optional<ButtonType> option = alert.showAndWait();
      if (option.get() == ButtonType.OK) {
        AccountDatabaseHelper.editAccount(txtEmail.getText(), pfPassword.getText(), "STAFF", txtAddress.getText(), txtPhoneNumber.getText(), acc.getId());
        Navigator.getInstance().goToAccountList();
      }
    }
  }

  //Dieu Huong
  @FXML
  private void goToInsertProduct (MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToInsertProduct();
  }

  @FXML
  private void goToDashboard(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToDashboard();
  }

  @FXML
  private void goToProductList(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToProductsList();
  }

  @FXML
  private void goToAccountList(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToAccountList();
  }

  @FXML
  private void goToCategoryList(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToCategoryList();
  }

  @FXML
  private void goToOrder(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToOrder();
  }
  @FXML
  private void logout(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToLogin();
  }
}
