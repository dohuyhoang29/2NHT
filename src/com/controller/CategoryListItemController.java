package com.controller;

import com.Main;
import com.helper.CategoryDatabaseHelper;
import com.helper.NotificationManager;
import com.helper.ProductDatabaseHelper;
import com.model.Category;
import com.model.Product;
import com.view.Navigator;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class CategoryListItemController {
  @FXML
  private Label description;

  @FXML
  private ImageView edit;

  @FXML
  private ImageView lock;

  @FXML
  private Label name;

  @FXML
  private HBox productListItem;

  @FXML
  private Label status;

  @FXML
  private ImageView unlock;


  private Category category;

  public void setData (Category category) {
    this.category = category;
    name.setText(category.getName());
    description.setText(category.getDescription());
    status.setText(category.getStatus());

    if (category.getStatus().equalsIgnoreCase(Main.UNLOCK)) {
      lock.setVisible(true);
      unlock.setVisible(false);
    }

    if (category.getStatus().equalsIgnoreCase(Main.LOCK)) {
      unlock.setVisible(true);
      lock.setVisible(false);
    }
  }

  @FXML
  void lockCategory(MouseEvent event) throws IOException {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setContentText("Are you sure you want to do it?");

    Optional<ButtonType> option = alert.showAndWait();
    if (option.get() == ButtonType.OK) {
      CategoryDatabaseHelper.lockCategory(category.getId());
      Navigator.getInstance().goToCategoryList();
      List<Product> listProduct = ProductDatabaseHelper.getAllProductByCategory(category.getName());
      System.out.println(listProduct.size());
      for (Product p : listProduct) {
        ProductDatabaseHelper.lockProduct(p.getId());
      }
      NotificationManager.getInstance().success("Lock Category Success");
    }
  }

  @FXML
  void unLockCategory(MouseEvent event) throws IOException {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setContentText("Are you sure you want to do it?");

    Optional<ButtonType> option = alert.showAndWait();
    if (option.get() == ButtonType.OK) {
      CategoryDatabaseHelper.unLockCategory(category.getId());
      Navigator.getInstance().goToCategoryList();
      List<Product> listProduct = ProductDatabaseHelper.getAllProductByCategory(category.getName());
      System.out.println(listProduct.size());
      for (Product p : listProduct) {
        ProductDatabaseHelper.unLockProduct(p.getId());
      }
      NotificationManager.getInstance().success("Un-Lock Category Success");
    }
  }

  @FXML
  void editCategory(MouseEvent event) throws IOException {
    Navigator.getInstance().goToEditCategory(category);
  }
}
