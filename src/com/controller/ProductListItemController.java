package com.controller;

import com.helper.ProductDatabaseHelper;
import com.model.Product;
import com.view.Navigator;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ProductListItemController {
  @FXML
  private HBox productListItem;

  @FXML
  private Label code;

  @FXML
  private ImageView imgSrc;

  @FXML
  private Label name;

  @FXML
  private Label category;

  @FXML
  private Label quantity;

  @FXML
  private Label importPrice;

  @FXML
  private Label price;

  @FXML
  private Label importDate;

  @FXML
  private Label status;

  @FXML
  private ImageView view;

  @FXML
  private ImageView edit;

  @FXML
  private ImageView delete;

  Product product;

  public void setData(Product product) {
    this.product = product;
    Image image = new Image(getClass().getResourceAsStream("/com/images/" + product.getImgSrc()));
    imgSrc.setImage(image);
    code.setText(product.getProductCode());
    name.setText(product.getProductName());
    category.setText(product.getCategoryName());
    importPrice.setText(product.getWarrantyPeriod().toString());
    price.setText(product.getPrice().toString());
    importDate.setText(product.getWarrantyPeriod());

  }

  @FXML
  void viewProduct() throws IOException {
    Navigator.getInstance().goToViewProduct(product);
  }

  @FXML
  void editProduct() throws IOException {
    Navigator.getInstance().goToEditProduct(product);
  }

  @FXML
  void deleteProduct() {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setContentText("Are you sure you want to do it?");

    Optional<ButtonType> option = alert.showAndWait();
    if (option.get() == ButtonType.OK) {
      ProductDatabaseHelper.deleteProduct(product.getId());
    }
  }
}
