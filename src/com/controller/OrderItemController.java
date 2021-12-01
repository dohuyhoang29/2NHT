package com.controller;

import com.model.Order;
import com.view.Navigator;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OrderItemController {
  @FXML
  private Label name;

  @FXML
  private Label totalPrice;

  @FXML
  private Label status;

  @FXML
  private Label date;

  Order order;

  public void setData (Order order) {
    this.order = order;
    name.setText(order.getName());
    totalPrice.setText(order.getTotalPrice().toString());
    status.setText(order.getStatus());
    date.setText(order.getCreateDateProperty());
  }

  @FXML
  void goToOrderDetail() throws IOException {
    Navigator.getInstance().goToOrderDetails(order);
  }
}
