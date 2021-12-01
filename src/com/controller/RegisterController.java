package com.controller;


import com.helper.AccountDatabaseHelper;
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
  private TextField show_tf;

  @FXML
  private PasswordField txtPassword;

  @FXML
  private PasswordField txtConfirmPassword;

  @FXML
  private Button btnCreate;

  @FXML
  private TextField txtAddress;

  @FXML
  private RadioButton rdShowPass;

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

  String written_text;

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
    }
    if (!check.validUsername(txtUsername.getText()) && !txtUsername.getText().isEmpty()) {
      errUsername.setText("Username can only have characters and numbers");
      count++;
    }
    if (txtEmail.getText().isEmpty()) {
      errEmail.setText("Email is required");
      count++;
    }
    if (!check.validEmail(txtEmail.getText()) && !txtEmail.getText().isEmpty()) {
      errEmail.setText("Email must have the same syntax as follows: xyz012@xyz.xyz");
      count++;
    }
    if (txtPassword.getText().isEmpty()) {
      errPassword.setText("Password is required");
      count++;
    }
    if (!check.validPassword(txtPassword.getText()) && !txtPassword.getText().isEmpty()) {
      errPassword.setText("Use 8 or more characters with a mix of letters, numbers & symbols");
      count++;
    }
    if (txtConfirmPassword.getText().isEmpty()) {
      errConfirmPassword.setText("Confirm Password is required");
      count++;
    }
    if (!txtConfirmPassword.getText().equalsIgnoreCase(txtPassword.getText())  && !txtConfirmPassword.getText().isEmpty()) {
      errConfirmPassword.setText("Those passwords didn’t match");
      count++;
    }
    if (txtPhone.getText().isEmpty()) {
      errPhone.setText("Phone Number is required");
      count++;
    }
    if (!check.validPhoneNumber(txtPhone.getText()) && !txtPhone.getText().isEmpty()) {
      errPhone.setText("Phone numbers can only be numeric and have 10 numbers");
      count++;
    }
    if (txtAddress.getText().isEmpty()) {
      errAddress.setText("Address is required");
      count++;
    }

    if (txtPassword.getText().equalsIgnoreCase(txtConfirmPassword.getText()) && count == 0) {
      boolean result = AccountDatabaseHelper.insertAccount(txtUsername.getText(), txtEmail.getText(),
          txtPassword.getText(), "USER", txtAddress.getText(), txtPhone.getText());
      if (result) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Register Successfully");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
          Navigator.getInstance().goToLogin();
        }
      } else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Register Failed");
        alert.show();
      }
    }
  }

  @FXML
  void show_Password() {
    if (rdShowPass.isSelected() == true) {
      written_text = txtPassword.getText();
      txtPassword.setVisible(false);
      show_tf.setVisible(true);
      show_tf.setText(written_text);
    } else {
      written_text = show_tf.getText();
      show_tf.setVisible(false);
      txtPassword.setVisible(true);

      txtPassword.setText(written_text);
    }
  }

  @FXML
  void back(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToLogin();
  }

}
