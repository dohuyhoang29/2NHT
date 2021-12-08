package com.controller;

import com.helper.CartDatabaseHelper;
import com.model.Cart;
import com.view.Navigator;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

public class CartItemController implements Initializable {
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
  private VBox quantityBox;

  @FXML
  private Label totalPrice;

  Cart cart;
  String path = Paths.get(".").toAbsolutePath().normalize() + "/src/com/images/";

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    quantity.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue,
          String newValue) {
        if (!newValue.matches("\\d")) {
          quantity.setText(newValue.replaceAll("[^\\d]", ""));
        }
      }
    });
  }

  public int setData (Cart cart) {
    this.cart = cart;
    Image image = new Image("file:///" + path + cart.getImgSrc());

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

  @FXML
  void up (MouseEvent event) {
    Integer count = Integer.parseInt(quantity.getText());
    count++;
    quantity.setText(count.toString());
  }

  @FXML
  void down (MouseEvent event) {
    Integer count = Integer.parseInt(quantity.getText());
    count--;
    if (count < 1) {
      quantity.setText("1");
    } else {
      quantity.setText(count.toString());
    }
  }

  @FXML
  void quantityEntered (MouseEvent event) {
    quantityBox.setVisible(true);
  }

  @FXML
  void quantityExited (MouseEvent event) {
    quantityBox.setVisible(false);
  }
}
