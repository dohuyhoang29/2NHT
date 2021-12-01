package com.controller;

import com.helper.AccountDatabaseHelper;
import com.helper.ProjectManager;
import com.view.Navigator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ProfileController implements Initializable {
  @FXML
  private TextField txtSearch;

  @FXML
  private Pane coutCart;

  @FXML
  private Label count;

  @FXML
  private Label username;

  @FXML
  private TextField txtEmail;

  @FXML
  private TextField txtPhone;

  @FXML
  private TextField txtAddress;

  @FXML
  private Button save;

  @FXML
  private Button changePassword;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    username.setText(ProjectManager.getInstance().getAccount().getUsername());
    txtEmail.setText(ProjectManager.getInstance().getAccount().getEmail());
    txtPhone.setText(ProjectManager.getInstance().getAccount().getPhone());
    txtAddress.setText(ProjectManager.getInstance().getAccount().getAddress());
  }

  //Action
  @FXML
  void editAccount (ActionEvent event) throws IOException {
    AccountDatabaseHelper.editAccount(txtEmail.getText(), ProjectManager.getInstance().getAccount().getPassword(), ProjectManager.getInstance().getAccount().getType(), txtAddress.getText(), txtPhone.getText(), ProjectManager.getInstance().getAccount().getId());
    ProjectManager.getInstance().setAccount(AccountDatabaseHelper.getAccountByUsernameOrEmail(ProjectManager.getInstance().getAccount().getUsername()));
    Navigator.getInstance().goToProfile();
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
