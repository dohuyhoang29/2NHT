package com.controller;

import com.helper.AccountDatabaseHelper;
import com.helper.OrderDatabaseHelper;
import com.helper.OrderDetailsDatabaseHelper;
import com.model.Account;
import com.model.Order;
import com.model.OrderDetail;
import com.view.Navigator;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OrderDetailsController implements Initializable {

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
  private HBox accountList;

  @FXML
  private VBox changeLanguageContainer;

  @FXML
  private Label date;

  @FXML
  private Label orderID;

  @FXML
  private ChoiceBox<String> cbStatus;

  @FXML
  private Button btnSave;

  @FXML
  private Label name;

  @FXML
  private Label email;

  @FXML
  private Label phone;

  @FXML
  private Label status;

  @FXML
  private Label address;

  @FXML
  private VBox itemLayout;

  @FXML
  private Label subtotal;

  int count;
  Order order;
  List<OrderDetail> listOrderDetail = new ArrayList<>();
  Account account;
  Integer sub = 0;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    cbStatus.getItems().addAll(Order.TO_PAY, Order.TO_SHIP, Order.TO_RECEIVE, Order.COMPLETED);
  }

  public void setData(Order order) {
    this.order = order;
    account = AccountDatabaseHelper.getAccountById(order.getAccountID());
    name.setText(order.getName());
    email.setText(account.getEmail());
    phone.setText(account.getPhone());
    status.setText(order.getStatus());
    address.setText(order.getAddress());
    cbStatus.setValue(order.getStatus());
    date.setText(order.getCreateDateProperty());

    listOrderDetail = OrderDetailsDatabaseHelper.getOrderDetail(order.getId());

    try {
      for (OrderDetail o : listOrderDetail) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/view/OrderDetailsItemUI.fxml"));
        VBox vBox = loader.load();
        sub += o.getPrice() * o.getQuantity();

        OrderDeatailsItemController controller = loader.getController();
        controller.setData(o);
        itemLayout.getChildren().add(vBox);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    subtotal.setText(sub.toString());

  }

  //Hanh dong
  @FXML
  private void showChangeLanguageMousePressed(MouseEvent mouseEvent) {
    count++;
    if (count % 2 != 0) {
      changeLanguageContainer.setVisible(true);
    } else {
      changeLanguageContainer.setVisible(false);
    }
  }

  @FXML
  void changeStatus (ActionEvent event) {
    OrderDatabaseHelper.changeStatus(cbStatus.getValue(), order.getId());
  }

  //Dieu Huong
  @FXML
  private void goToInsertProduct(MouseEvent mouseEvent) throws IOException {
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
