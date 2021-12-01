package com.controller;

import com.helper.CategoryDatabaseHelper;
import com.model.Category;
import com.view.Navigator;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CategoryListController implements Initializable {
  @FXML
  private TextField txtSearch;

  @FXML
  private ImageView changeLanguage;

  @FXML
  private Label username;

  @FXML
  private HBox dashboard;

  @FXML
  private HBox addProduct;

  @FXML
  private HBox productList;

  @FXML
  private VBox changeLanguageContainer;

  @FXML
  private Button btnAddCategory;

  @FXML
  private TextField txtSearchCategory;

  @FXML
  private Button btnSearch;

  @FXML
  private VBox itemLayout;

  int count;
  List<Category> list = new ArrayList<>();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    list = CategoryDatabaseHelper.getAllCategories();

    try {
      for (Category c : list) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/view/CategoryListItemUI.fxml"));
        VBox vBox = fxmlLoader.load();
        CategoryListItemController controller = fxmlLoader.getController();
        controller.setData(c);
        itemLayout.getChildren().add(vBox);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
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
  private void addCategory(ActionEvent event) throws IOException {
    Navigator.getInstance().goToInsertCategory();
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