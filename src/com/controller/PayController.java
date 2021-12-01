package com.controller;

import com.helper.CartDatabaseHelper;
import com.helper.OrderDatabaseHelper;
import com.helper.OrderDetailsDatabaseHelper;
import com.helper.ProjectManager;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class PayController implements Initializable {
  @FXML
  private TextField txtSearch;

  @FXML
  private Pane coutCart;

  @FXML
  private Label count;

  @FXML
  private TextField name;

  @FXML
  private TextField phoneNumber;

  @FXML
  private TextField address;

  @FXML
  private TextArea note;

  @FXML
  private Button pay;

  List<Cart> listCart = new ArrayList<>();
  Integer totalPrice = 0;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    name.setText(ProjectManager.getInstance().getAccount().getUsername());
    phoneNumber.setText(ProjectManager.getInstance().getAccount().getPhone());
    address.setText(ProjectManager.getInstance().getAccount().getAddress());
    System.out.println(ProjectManager.getInstance().getAccount().getAddress());

    listCart = CartDatabaseHelper.getAllCartByAccount(ProjectManager.getInstance().getAccount().getUsername());
    for (Cart  c : listCart) {
      totalPrice += c.getPrice();
    }
  }

  //Action
  @FXML
  void clickOrder(ActionEvent event) throws IOException {
    Integer orderID = OrderDatabaseHelper.insertOrder(ProjectManager.getInstance().getAccount().getId(), name.getText(), totalPrice, address.getText(), phoneNumber.getText(), note.getText());
    System.out.println(orderID.toString());
    Navigator.getInstance().goToHome();
    for (Cart c : listCart) {
      OrderDetailsDatabaseHelper.insertOrderDetails(c.getQuantity(), c.getProductID(), orderID);
      CartDatabaseHelper.deleteCart(c.getId());
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
}
