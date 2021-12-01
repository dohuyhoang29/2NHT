package com.controller;

import com.helper.CategoryDatabaseHelper;
import com.model.Category;
import com.view.Navigator;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditCategoryController implements Initializable {
  @FXML
  private TextField txtSearch;

  @FXML
  private ImageView changeLanguage;

  @FXML
  private Label username;

  @FXML
  private HBox dashboard;

  @FXML
  private ImageView imgdashboard;

  @FXML
  private Label lbdashboard;

  @FXML
  private HBox addProduct2;

  @FXML
  private HBox productsList;

  @FXML
  private HBox accountList;

  @FXML
  private VBox changeLanguageContainer;

  @FXML
  private TextField txtName;

  @FXML
  private Label errName;

  @FXML
  private TextArea txtDescription;

  @FXML
  private Button btnSave;

  int count;
  Category category;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public void setData (Category category) {
    this.category = category;
    txtName.setText(category.getName());
    txtDescription.setText(category.getDescription());
  }

  //Hanh dong
  @FXML
  private void showChangeLanguageMousePressed (MouseEvent mouseEvent) {
    count++;
    if (count % 2 != 0) {
      changeLanguageContainer.setVisible(true);
    } else {
      changeLanguageContainer.setVisible(false);
    }
  }

  @FXML
  private void editCategory() throws IOException {
    if(txtName.getText().isEmpty()) {
      errName.setText("Category's name is required");
    }else if(CategoryDatabaseHelper.getCategoryByName(txtName.getText()) != null && !txtName.getText().equalsIgnoreCase(category.getName())) {
      errName.setText("Category exists");
    }else {
      errName.setText("");
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setContentText("Are you sure you want to do it?");

      Optional<ButtonType> option = alert.showAndWait();
      if (option.get() == ButtonType.OK) {
        CategoryDatabaseHelper.editCategory(txtName.getText(), txtDescription.getText(), category.getId());
        Navigator.getInstance().goToCategoryList();
      }
    }
  }

  //Dieu Huong
  @FXML
  private void goToInsertProduct (MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToInsertProduct();
  }

  @FXML
  private void goToDashboard(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToDashboard();
  }

  @FXML
  private void goToProductList(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToProductsList();
  }

  @FXML
  private void goToAccountList(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToAccountList();
  }

  @FXML
  private void goToCategoryList(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToCategoryList();
  }

  @FXML
  private void goToOrder(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToOrder();
  }

  @FXML
  private void logout(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToLogin();
  }
}
