package com.controller;

import com.model.Order;
import com.view.Navigator;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PurchaseOrderItemController {
  @FXML
  private Label code;

  @FXML
  private Label name;

  @FXML
  private Label address;

  @FXML
  private Label status;

  @FXML
  private Label totalPrice;

  Order order;

  public void setData (Order order) {
    this.order = order;

    code.setText(order.getCode());
    name.setText(order.getName());
    address.setText(order.getAddress());
    status.setText(order.getStatus());
    totalPrice.setText(order.getTotalPrice().toString());
  }

  @FXML
  void view(ActionEvent event) throws IOException {
    Navigator.getInstance().goToPurchaseOrderDetail(order);
  }
}
