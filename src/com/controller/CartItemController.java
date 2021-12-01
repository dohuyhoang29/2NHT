package com.controller;

import com.model.Cart;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartItemController {
  @FXML
  private ImageView imgSrc;

  @FXML
  private Label name;

  @FXML
  private Label hardDrive;

  @FXML
  private Label price;

  @FXML
  private TextField quantity;

  @FXML
  private Label totalPrice;

  Cart cart;

  public int setData (Cart cart) {
    this.cart = cart;
    Image image = new Image(getClass().getResourceAsStream("/com/images/" + cart.getImgSrc()));

    imgSrc.setImage(image);
    name.setText(cart.getName());
    hardDrive.setText(cart.getHardDrive());
    price.setText(cart.getPrice().toString());
    quantity.setText(cart.getQuantity().toString());
    totalPrice.setText(String.valueOf((cart.getQuantity() * cart.getPrice())));

    return  cart.getQuantity() * cart.getPrice();
  }
}
