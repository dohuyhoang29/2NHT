package com.controller;

import com.model.Category;
import com.view.Navigator;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class CategoryItemController {
  @FXML
  private Label name;

  Category category;

  public void setData (Category category) {
    this.category = category;
    name.setText(category.getName());
  }

  @FXML
  void goToCategory (MouseEvent event) throws IOException {
    Navigator.getInstance().goToCategory(category);
  }
}
