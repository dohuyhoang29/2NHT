package com.controller;

import com.helper.*;
import com.model.Cart;
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
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SearchController implements Initializable {
  @FXML
  private HBox categoryBox;

  @FXML
  private TextField txtSearch;

  @FXML
  private Pane coutCart;

  @FXML
  private Label count;

  @FXML
  private ScrollPane scroll;

  @FXML
  private Label key;

  @FXML
  private TextField search;

  @FXML
  private ChoiceBox<String> cbCategory;

  @FXML
  private Button btnSearch;

  @FXML
  private GridPane gridProduct;

  List<Product> listProduct = new ArrayList<>();
  List<Cart> listCart = new ArrayList<>();
  private ObservableList<Category> listCategory = FXCollections.observableArrayList();

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    listCart = CartDatabaseHelper.getAllCartByAccount(ProjectManager.getInstance().getAccount().getUsername());
    Integer cart = listCart.size();
    count.setText(cart.toString());

    listCategory.addAll(CategoryDatabaseHelper.getAllCategories());

    try {
      for (int i = 0; i < listCategory.size(); i++) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/view/CategoryItemUI.fxml"));
        Label label = loader.load();
        CategoryItemController controller = loader.getController();
        controller.setData(listCategory.get(i));
        categoryBox.getChildren().add(label);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setData (String key) {
    if (key != null) {
      this.key.setText(key);
      txtSearch.setText(key);
//
      listProduct = ProductDatabaseHelper.searchProduct(key);

      int column = 0;
      int row = 1;

      try {
        for (int i = 0; i < listProduct.size(); i++) {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("/com/view/ProductItemUI.fxml"));
          loader.setResources(TranslateManager.getRb());
          VBox vBox = loader.load();

          ProductItemController controller = loader.getController();
          controller.setData(listProduct.get(i));

          if (column == 4) {
            column = 0;
            row++;
          }

          gridProduct.add(vBox, column++, row);

          GridPane.setMargin(vBox, new Insets(20));
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void setDataWithCategory (String name, String category) {
    this.key.setText(name);
    search.setText(name);
    cbCategory.setValue(category);

    listProduct = ProductDatabaseHelper.searchProductByCategoryAndName(name, category);

    int column = 0;
    int row = 1;

    try {
      for (int i = 0; i < listProduct.size(); i++) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/view/ProductItemUI.fxml"));
        VBox vBox = loader.load();

        ProductItemController controller = loader.getController();
        controller.setData(listProduct.get(i));

        if (column == 4) {
          column = 0;
          row++;
        }

        gridProduct.add(vBox, column++, row);

        GridPane.setMargin(vBox, new Insets(20));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //Action
  @FXML
  void search (ActionEvent event) throws IOException {
    if (cbCategory.getValue().equalsIgnoreCase("Select Category")) {
      Navigator.getInstance().goToSearch(search.getText());
    } else {
      Navigator.getInstance().goToSearchWithCategory(search.getText(), cbCategory.getValue());
    }
  }

  //Dieu huong
  @FXML
  void goToHome (MouseEvent event) throws IOException {
    Navigator.getInstance().goToHome();
  }

  @FXML
  void goToCart (MouseEvent event) throws IOException {
    Navigator.getInstance().goToCart();
  }

  @FXML
  void goToSearch (MouseEvent event) throws IOException {
    Navigator.getInstance().goToSearch(txtSearch.getText());
  }


  @FXML
  void goToProfile (MouseEvent event) throws IOException {
    Navigator.getInstance().goToProfile();
  }

  @FXML
  void goToLogin (MouseEvent event) throws IOException {
    Navigator.getInstance().goToLogin();
  }

}
