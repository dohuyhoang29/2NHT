package com.controller;

import com.model.OrderDetail;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OrderDeatailsItemController {
  @FXML
  private ImageView imgSrc;

  @FXML
  private Label name;

  @FXML
  private Label unitPrice;

  @FXML
  private Label quantity;

  @FXML
  private Label total;

  public void setData (OrderDetail orderDetail) {
    Image image = new Image(getClass().getResourceAsStream("/com/images/" + orderDetail.getImgSrc()));
    imgSrc.setImage(image);

    name.setText(orderDetail.getName());
    unitPrice.setText(orderDetail.getPrice().toString());
    quantity.setText(orderDetail.getQuantity().toString());
    total.setText(String.valueOf((orderDetail.getPrice() * orderDetail.getQuantity())));
  }
}
