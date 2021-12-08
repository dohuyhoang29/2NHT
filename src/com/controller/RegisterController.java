package com.controller;


import com.helper.AccountDatabaseHelper;
import com.helper.NotificationManager;
import com.helper.ValidationManager;
import com.view.Navigator;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class RegisterController implements Initializable {

  ScaleTransition scaleTransition1;
  @FXML
  private TextField txtEmail;

  @FXML
  private TextField txtPhone;

  @FXML
  private TextField txtUsername;

  @FXML
  private TextField showPassword;

  @FXML
  private TextField showConfirmPassword;

  @FXML
  private PasswordField txtPassword;

  @FXML
  private PasswordField txtConfirmPassword;

  @FXML
  private Button btnCreate;

  @FXML
  private TextField txtAddress;

  @FXML
  private RadioButton show_password_btn;

  @FXML
  private Label errUsername;

  @FXML
  private Label errAddress;

  @FXML
  private Label errPhone;

  @FXML
  private Label errEmail;

  @FXML
  private Label errConfirmPassword;

  @FXML
  private Label errPassword;

  private String passwordText;
  private String confirmPasswordText;

  @Override
  public void initialize(URL url, ResourceBundle rb) {

  }

  @FXML
  void createUserAccount(ActionEvent event) throws IOException {
    int count = 0;
    ValidationManager check = ValidationManager.getInstance();
    if (txtUsername.getText().isEmpty()) {
      errUsername.setText("Username is required");
      count++;
    } else if (!check.validUsername(txtUsername.getText()) && !txtUsername.getText().isEmpty()) {
      errUsername.setText("Username can only have characters and numbers");
      count++;
    } else errUsername.setText("");



    if (txtEmail.getText().isEmpty()) {
      errEmail.setText("Email is required");
      count++;
    } else if (!check.validEmail(txtEmail.getText()) && !txtEmail.getText().isEmpty()) {
      errEmail.setText("Email must have the same syntax as follows: xyz012@xyz.xyz");
      count++;
    } else errEmail.setText("");



    if (txtPassword.getText().isEmpty() && showPassword.getText().isEmpty()) {
      errPassword.setText("Password is required");
      count++;
    } else if ((!check.validPassword(txtPassword.getText()) && !txtPassword.getText().isEmpty()) && (!check.validPassword(showPassword.getText()) && !showPassword.getText().isEmpty())) {
      errPassword.setText("Use 8 or more characters with a mix of letters, numbers & symbols");
      count++;
    } else errPassword.setText("");



    if (txtConfirmPassword.getText().isEmpty() && showConfirmPassword.getText().isEmpty()) {
      errConfirmPassword.setText("Confirm Password is required");
      count++;
    } else if ((!txtConfirmPassword.getText().equalsIgnoreCase(txtPassword.getText())  && !txtConfirmPassword.getText().isEmpty()) && (!showConfirmPassword.getText().equalsIgnoreCase(showPassword.getText())  && !showConfirmPassword.getText().isEmpty())) {
      errConfirmPassword.setText("Those passwords didn’t match");
      count++;
    } else errConfirmPassword.setText("");



    if (txtPhone.getText().isEmpty()) {
      errPhone.setText("Phone Number is required");
      count++;
    } else if (!check.validPhoneNumber(txtPhone.getText()) && !txtPhone.getText().isEmpty()) {
      errPhone.setText("Phone numbers can only be numeric and have 10 numbers");
      count++;
    } else errPhone.setText("");



    if (txtAddress.getText().isEmpty()) {
      errAddress.setText("Address is required");
      count++;
    } else errAddress.setText("");

    if (txtPassword.getText().equalsIgnoreCase(txtConfirmPassword.getText()) && count == 0) {
      boolean result = AccountDatabaseHelper.insertAccount(txtUsername.getText(), txtEmail.getText(),
          txtPassword.getText(), "USER", txtAddress.getText(), txtPhone.getText());
      if (result) {
        NotificationManager.getInstance().success("Register Success");
        Navigator.getInstance().goToLogin();
      } else {
        NotificationManager.getInstance().warning("Register Failed");
      }
    }
  }

  @FXML
  void show_Password(ActionEvent event) {
    if(show_password_btn.isSelected()==true){
      passwordText= txtPassword.getText();
      confirmPasswordText= txtConfirmPassword.getText();

      txtPassword.setVisible(false);
      showPassword.setVisible(true);

      txtConfirmPassword.setVisible(false);
      showConfirmPassword.setVisible(true);

      showPassword.setText(passwordText);
      showConfirmPassword.setText(confirmPasswordText);
    }
    else {
      passwordText= showPassword.getText();
      confirmPasswordText= showConfirmPassword.getText();

      showPassword.setVisible(false);
      txtPassword.setVisible(true);

      showConfirmPassword.setVisible(false);
      txtConfirmPassword.setVisible(true);

      txtPassword.setText(passwordText);
      txtConfirmPassword.setText(confirmPasswordText);
    }
  }

  @FXML
  void back(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToLogin();
  }

}
