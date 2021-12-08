package com.controller;

import com.helper.CartDatabaseHelper;
import com.helper.ProjectManager;
import com.model.Cart;
import com.model.Product;
import com.view.Navigator;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ProductItemController {

  @FXML
  private ImageView imgSrc;

  @FXML
  private Label name;

  @FXML
  private Label price;

  Product product;
  String path = Paths.get(".").toAbsolutePath().normalize() + "/src/com/images/";

  public void setData (Product product) {
    this.product = product;
    Image image = new Image("file:///" + path + product.getImgSrc());

    imgSrc.setImage(image);
    name.setText(product.getProductName());
    price.setText(product.getPrice().toString());
  }

  public void viewProduct (MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToScreenProduct(product);
  }

  @FXML
  void clickAddToCart(MouseEvent event) {
    List<Cart> list = new ArrayList<>(
        CartDatabaseHelper.getAllCartByAccount(ProjectManager.getInstance().getAccount().getUsername()));
    for (int i = 0; i <list.size(); i++) {
      if (Objects.equals(product.getId(), list.get(i).getProductID())) {
        CartDatabaseHelper.changeQuantity(1 + list.get(i).getQuantity(), list.get(i).getId());
        return;
      }
    }
    CartDatabaseHelper.addToCart(1, ProjectManager.getInstance().getAccount().getId(), product.getId());
  }
}
