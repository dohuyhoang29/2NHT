package com.controller;

import com.model.Product;
import com.view.Navigator;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class HardDriveItemController {
  @FXML
  private Label hardDrive;

  Product product;

  public void  setData (Product product) {
    this.product = product;
    hardDrive.setText(product.getHardDrive());
  }

  public void chooseHardDrive(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToScreenProduct(product);
  }
}
