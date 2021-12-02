package com.controller;

import com.helper.CartDatabaseHelper;
import com.model.Cart;
import com.view.Navigator;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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

  @FXML
  void delete (MouseEvent event) throws IOException {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setContentText("Are you sure delete this product");
    Optional<ButtonType> option = alert.showAndWait();
    if (option.get() == ButtonType.OK) {
      CartDatabaseHelper.deleteCart(cart.getId());
      Navigator.getInstance().goToCart();
    }
  }
}
