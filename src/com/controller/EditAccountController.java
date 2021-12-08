package com.controller;

import com.helper.AccountDatabaseHelper;
import com.helper.NotificationManager;
import com.helper.ProjectManager;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditAccountController implements Initializable {
  @FXML
  private ImageView changeLanguage;

  @FXML
  private Label username;

  @FXML
  private HBox dashboard;

  @FXML
  private ImageView imgdashboard;

  @FXML
  private RadioButton show_password_btn;

  @FXML
  private Label lbdashboard;

  @FXML
  private HBox addProduct2;

  @FXML
  private HBox productsList;

  @FXML
  private HBox accountList;

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
  private Label errPassword;

  @FXML
  private TextField showPassword;

  @FXML
  private PasswordField txtPassword;

  @FXML
  private Label errConfirmPassword;

  @FXML
  private TextField showConfirmPassword;

  @FXML
  private PasswordField txtConfirmPassword;

  @FXML
  private TextField txtPhoneNumber;

  @FXML
  private Label errPhone;

  @FXML
  private Button btnSave;

  private int count;
  private Account acc;
  private String passwordText;
  private String confirmPasswordText;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    username.setText(ProjectManager.getInstance().getAccount().getUsername());
  }

  public void setData(Account account) {
    this.acc = account;
    txtUsername.setText(account.getUsername());
    txtEmail.setText(account.getEmail());
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
  void editAccount(ActionEvent event) throws IOException {
    int count = 0;
    ValidationManager check = ValidationManager.getInstance();
    if (txtPassword.getText().isEmpty() && !showPassword.getText().isEmpty()) {
      txtPassword.setText(showPassword.getText());
    }
//    password
    if(txtPassword.getText().isEmpty() && showPassword.getText().isEmpty()) {
      errPassword.setText("Password is required");
      count++;
    }else if (!check.validPassword(txtPassword.getText()) && !check.validPassword(showPassword.getText())) {
      errPassword.setText("Use 8 or more characters with a mix of letters, numbers & symbols");
      count++;
    }else errPassword.setText("");

//    confirm password
    if (txtConfirmPassword.getText().isEmpty() && showConfirmPassword.getText().isEmpty()) {
      errConfirmPassword.setText("Confirm Password is required");
      count++;
    }else if(!txtConfirmPassword.getText().equalsIgnoreCase(txtPassword.getText()) && !showConfirmPassword.getText().equalsIgnoreCase(showPassword.getText())) {
      errConfirmPassword.setText("Those passwords didn’t match");
      count++;
    }else errConfirmPassword.setText("");

    if (count == 0) {

      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setContentText("Are you sure you want to do it?");

      Optional<ButtonType> option = alert.showAndWait();
      if (option.get() == ButtonType.OK) {
        AccountDatabaseHelper.resetPassword(txtPassword.getText(), acc.getId());
        NotificationManager.getInstance().success("Reset Password Success");
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
