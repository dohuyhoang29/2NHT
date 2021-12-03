package com.controller;

import com.Main;
import com.helper.NotificationManager;
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
  private Label category;

  @FXML
  private Label code;

  @FXML
  private ImageView edit;

  @FXML
  private ImageView imgSrc;

  @FXML
  private Label importPrice;

  @FXML
  private ImageView lock;

  @FXML
  private Label name;

  @FXML
  private Label price;

  @FXML
  private HBox productListItem;

  @FXML
  private Label status;

  @FXML
  private ImageView unlock;

  @FXML
  private ImageView view;

  Product product;

  public void setData(Product product) {
    this.product = product;
    Image image = new Image("file:///C:/Users/hoang/IdeaProjects/2NHT/src/com/images/" + product.getImgSrc());
    imgSrc.setImage(image);
    code.setText(product.getProductCode());
    name.setText(product.getProductName());
    category.setText(product.getCategoryName());
    importPrice.setText(product.getWarrantyPeriod());
    price.setText(product.getPrice().toString());
    status.setText(product.getStatus());

    if (product.getStatus().equalsIgnoreCase(Main.LOCK)) {
      unlock.setVisible(true);
      lock.setVisible(false);
    }

    if (product.getStatus().equalsIgnoreCase(Main.UNLOCK)) {
      lock.setVisible(true);
      unlock.setVisible(false);
    }
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
  void unLockProduct() throws IOException {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setContentText("Are you sure you want to do it?");

    Optional<ButtonType> option = alert.showAndWait();
    if (option.get() == ButtonType.OK) {
      ProductDatabaseHelper.unLockProduct(product.getId());
      NotificationManager.getInstance().success("Un-Lock Product Success");
      Navigator.getInstance().goToProductsList();
    }
  }

  @FXML
  void lockProduct() throws IOException {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setContentText("Are you sure you want to do it?");

    Optional<ButtonType> option = alert.showAndWait();
    if (option.get() == ButtonType.OK) {
      ProductDatabaseHelper.lockProduct(product.getId());
      NotificationManager.getInstance().success("Lock Product Success");
      Navigator.getInstance().goToProductsList();
    }
  }
}
