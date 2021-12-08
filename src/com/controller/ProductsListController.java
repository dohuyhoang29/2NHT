package com.controller;

import com.helper.CategoryDatabaseHelper;
import com.helper.ProductDatabaseHelper;
import com.helper.ProjectManager;
import com.model.Category;
import com.model.Product;
import com.view.Navigator;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProductsListController implements Initializable {

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
  private VBox changeLanguageContainer;

  @FXML
  private ChoiceBox<String> cpCategroy;

  @FXML
  private ChoiceBox<String> cbStatus;

  @FXML
  private TextField txtName;

  @FXML
  private DatePicker dpFrom;

  @FXML
  private DatePicker dpTo;

  @FXML
  private Button btnSearch;

  @FXML
  private VBox itemLayout;

  int count;

  ObservableList<Product> listData = FXCollections.observableArrayList();
  List<Category> listCategory = new ArrayList<>();
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    username.setText(ProjectManager.getInstance().getAccount().getUsername());
    listData = ProductDatabaseHelper.getAllProduct();
    listCategory = CategoryDatabaseHelper.getAllCategories();
    try {
      for (Product p : listData) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/view/ProductListItemUI.fxml"));
        VBox vBox = fxmlLoader.load();
        ProductListItemController productListItemController = fxmlLoader.getController();
        productListItemController.setData(p);
        itemLayout.getChildren().add(vBox);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    cpCategroy.getItems().add("All");
    cpCategroy.setValue("All");
    for (Category c : listCategory) {
      cpCategroy.getItems().add(c.getName());
    }
  }

  //Hanh dong
  @FXML
  private void showChangeLanguageMousePressed(MouseEvent mouseEvent) {
    count++;
    if (count % 2 != 0) {
      changeLanguageContainer.setVisible(true);
    } else {
      changeLanguageContainer.setVisible(false);
    }
  }

  @FXML
  void apply (ActionEvent event) {
    itemLayout.getChildren().clear();

    listData.clear();
    listData.addAll(ProductDatabaseHelper.getAllProductByCategoryAndName(cpCategroy.getValue(), txtName.getText()));

    try {
      for (Product p : listData) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/view/ProductListItemUI.fxml"));
        VBox vBox = fxmlLoader.load();
        ProductListItemController productListItemController = fxmlLoader.getController();
        productListItemController.setData(p);
        itemLayout.getChildren().add(vBox);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //Dieu huong
  @FXML
  private void goToInsertProduct(MouseEvent mouseEvent) throws IOException {
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
