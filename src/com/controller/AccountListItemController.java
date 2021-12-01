package com.controller;

import com.helper.AccountDatabaseHelper;
import com.model.Account;
import com.view.Navigator;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AccountListItemController {
  @FXML
  private HBox productListItem;

  @FXML
  private Label username;

  @FXML
  private Label email;

  @FXML
  private Label password;

  @FXML
  private Label type;

  @FXML
  private ImageView view;

  @FXML
  private ImageView edit;

  @FXML
  private ImageView delete;

  private Account account;

  public void setData(Account account) {
    this.account = account;
    username.setText(account.getUsername());
    email.setText(account.getEmail());
    password.setText(account.getPassword());
    type.setText(account.getType());
  }

  @FXML
  private void editAccount () throws IOException {
    Navigator.getInstance().goToEditAccount(account);
  }

  @FXML
  private void viewAccount () throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("/com/view/ViewAccountUI.fxml"));
    Parent root = fxmlLoader.load();
    ViewAccountController controller = fxmlLoader.getController();
    controller.setData(account);
    Scene scene = new Scene(root);
    Stage viewAccount = new Stage();
    viewAccount.setScene(scene);
    viewAccount.initModality(Modality.WINDOW_MODAL);
    viewAccount.initOwner(Navigator.getInstance().getStage());
    viewAccount.show();
  }

  @FXML
  private void deleteAccount () throws IOException {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setContentText("Are you sure you want to do it?");

    Optional<ButtonType> option = alert.showAndWait();
    if (option.get() == ButtonType.OK) {
      AccountDatabaseHelper.deleteAccount(account.getId());
    }
    Navigator.getInstance().goToAccountList();
  }
}
