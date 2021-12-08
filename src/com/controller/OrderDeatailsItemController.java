package com.controller;

import com.model.OrderDetail;
import java.nio.file.Paths;
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

  String path = Paths.get(".").toAbsolutePath().normalize() + "/src/com/images/";

  public void setData (OrderDetail orderDetail) {
    Image image = new Image("file:///" + path + orderDetail.getImgSrc());
    imgSrc.setImage(image);

    name.setText(orderDetail.getName());
    unitPrice.setText(orderDetail.getPrice().toString());
    quantity.setText(orderDetail.getQuantity().toString());
    total.setText(String.valueOf((orderDetail.getPrice() * orderDetail.getQuantity())));
  }
}
