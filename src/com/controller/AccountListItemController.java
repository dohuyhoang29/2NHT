package com.controller;

import com.Main;
import com.helper.AccountDatabaseHelper;
import com.helper.TranslateManager;
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
  private Label email;

  @FXML
  private ImageView lock;

  @FXML
  private Label status;

  @FXML
  private Label type;

  @FXML
  private ImageView unlock;

  @FXML
  private Label username;

  private Account account;

  public void setData(Account account) {
    this.account = account;
    username.setText(account.getUsername());
    status.setText(account.getStatus());
    email.setText(account.getEmail());
    type.setText(account.getType());

    if (account.getStatus().equalsIgnoreCase(Main.UNLOCK)) {
      lock.setVisible(true);
      unlock.setVisible(false);
    }

    if (account.getStatus().equalsIgnoreCase(Main.LOCK)) {
      lock.setVisible(false);
      unlock.setVisible(true);
    }
  }

  @FXML
  private void editAccount () throws IOException {
    Navigator.getInstance().goToEditAccount(account);
  }

  @FXML
  private void viewAccount () throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("/com/view/ViewAccountUI.fxml"));
    fxmlLoader.setResources(TranslateManager.getRb());
    Parent root = fxmlLoader.load();
    ViewAccountController controller = fxmlLoader.getController();
    controller.setData(account);
    Scene scene = new Scene(root);
    Stage viewAccount = new Stage();
    viewAccount.setScene(scene);
    viewAccount.setTitle("View Account");
    viewAccount.initModality(Modality.WINDOW_MODAL);
    viewAccount.initOwner(Navigator.getInstance().getStage());
    viewAccount.show();
  }

  @FXML
  private void lockAccount () throws IOException {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setContentText("Are you sure you want to do it?");

    Optional<ButtonType> option = alert.showAndWait();
    if (option.get() == ButtonType.OK) {
      AccountDatabaseHelper.lockAccount(account.getId());
    }
    Navigator.getInstance().goToAccountList();
  }

  @FXML
  private void unLockAccount () throws IOException {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setContentText("Are you sure you want to do it?");

    Optional<ButtonType> option = alert.showAndWait();
    if (option.get() == ButtonType.OK) {
      AccountDatabaseHelper.unLockAccount(account.getId());
    }
    Navigator.getInstance().goToAccountList();
  }
}
