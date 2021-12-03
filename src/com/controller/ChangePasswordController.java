package com.controller;

import com.helper.AccountDatabaseHelper;
import com.helper.CartDatabaseHelper;
import com.helper.NotificationManager;
import com.helper.ProjectManager;
import com.model.Account;
import com.model.Cart;
import com.view.Navigator;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ChangePasswordController implements Initializable {
  @FXML
  private TextField txtSearch;

  @FXML
  private Pane coutCart;

  @FXML
  private Label count;

  @FXML
  private Label account;

  @FXML
  private TextField showCurrentPassword;

  @FXML
  private PasswordField currentPassword;

  @FXML
  private RadioButton show_password_btn;

  @FXML
  private Label errCurrentPassword;

  @FXML
  private TextField showNewPassword;

  @FXML
  private PasswordField newPassword;

  @FXML
  private Label errNewPassword;

  @FXML
  private TextField showConfirmPassword;

  @FXML
  private PasswordField confirmPassword;

  @FXML
  private Label errConfirmPassword;

  @FXML
  private Button save;

  private String currentPasswordText;
  private String newPasswordText;
  private String confirmPasswordText;
  private List<Cart> listCart = new ArrayList<>();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    account.setText(ProjectManager.getInstance().getAccount().getUsername());
    listCart = CartDatabaseHelper.getAllCartByAccount(ProjectManager.getInstance().getAccount().getUsername());
    Integer cart = listCart.size();
    count.setText(cart.toString());
  }

  //Action
  @FXML
  void confirm(ActionEvent event) throws IOException {
    Account account = ProjectManager.getInstance().getAccount();

    if (currentPassword.getText().equalsIgnoreCase(account.getPassword())) {
      errCurrentPassword.setText("");
      if (newPassword.getText().equalsIgnoreCase(confirmPassword.getText())) {
        errConfirmPassword.setText("");
        AccountDatabaseHelper.changePassword(newPassword.getText(), account.getId());
        NotificationManager.getInstance().success("Change Password Success");
      } else {
        errConfirmPassword.setText("The passwords are not the same");
      }
    } else {
      errCurrentPassword.setText("Incorrect password");
    }
  }

  @FXML
  void show_Password(ActionEvent event) {
    if(show_password_btn.isSelected()==true){
      currentPasswordText= currentPassword.getText();
      newPasswordText= newPassword.getText();
      confirmPasswordText= confirmPassword.getText();

      currentPassword.setVisible(false);
      showCurrentPassword.setVisible(true);

      newPassword.setVisible(false);
      showNewPassword.setVisible(true);

      confirmPassword.setVisible(false);
      showConfirmPassword.setVisible(true);

      showCurrentPassword.setText(currentPasswordText);
      showNewPassword.setText(newPasswordText);
      showConfirmPassword.setText(confirmPasswordText);
    }
    else {
      currentPasswordText= showCurrentPassword.getText();
      newPasswordText= showNewPassword.getText();
      confirmPasswordText= showConfirmPassword.getText();

      showCurrentPassword.setVisible(false);
      currentPassword.setVisible(true);

      showNewPassword.setVisible(false);
      newPassword.setVisible(true);

      showConfirmPassword.setVisible(false);
      confirmPassword.setVisible(true);

      currentPassword.setText(currentPasswordText);
      newPassword.setText(newPasswordText);
      confirmPassword.setText(confirmPasswordText);
    }
  }

  //Dieu huong
  @FXML
  void goToHome (MouseEvent event) throws IOException {
    Navigator.getInstance().goToHome();
  }

  @FXML
  void goToCart (MouseEvent event) throws IOException {
    Navigator.getInstance().goToCart();
  }

  @FXML
  void goToMacBook(MouseEvent event) throws IOException {
    Navigator.getInstance().goToMacbook();
  }

  @FXML
  void goToIPhone(MouseEvent event) throws IOException {
    Navigator.getInstance().goToIPhone();
  }

  @FXML
  void goToIPad(MouseEvent event) throws IOException {
    Navigator.getInstance().goToIPad();
  }

  @FXML
  void goToSearch (MouseEvent event) throws IOException {
    Navigator.getInstance().goToSearch(txtSearch.getText());
  }

  @FXML
  void goToProfile (MouseEvent event) throws IOException {
    Navigator.getInstance().goToProfile();
  }

  @FXML
  void goToLogin (MouseEvent event) throws IOException {
    Navigator.getInstance().goToLogin();
  }

  @FXML
  void goToChangePassword (MouseEvent event) throws IOException {
    Navigator.getInstance().goToChangePassword();
  }

  @FXML
  void goToPurchaseOrder (MouseEvent event) throws IOException {
    Navigator.getInstance().goToPurchaseOrder();
  }



}
