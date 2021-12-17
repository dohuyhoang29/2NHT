package com.controller;

import com.helper.CartDatabaseHelper;
import com.helper.CategoryDatabaseHelper;
import com.helper.ProductDatabaseHelper;
import com.helper.ProjectManager;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HomeController implements Initializable {

  @FXML
  private HBox categoryBox;

  @FXML
  private TextField txtSearch;

  @FXML
  private Pane coutCart;

  @FXML
  private Label count;

  @FXML
  private Label totalPrice;

  @FXML
  private ScrollPane scroll;

  @FXML
  private Button btnShopNowiPhone;

  @FXML
  private Button btnShopNowiPad;

  @FXML
  private Button btnShopNowMac;

  @FXML
  private GridPane gridProduct;

  private List<Product> listProduct = new ArrayList<>();
  private List<Cart> listCart = new ArrayList<>();
  private ObservableList<Category> listCategory = FXCollections.observableArrayList();

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    listProduct = ProductDatabaseHelper.getAllProductHome();
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

    listCart = CartDatabaseHelper.getAllCartByAccount(
        ProjectManager.getInstance().getAccount().getUsername());
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

  //Dieu huong
  @FXML
  void goToHome(MouseEvent event) throws IOException {
    Navigator.getInstance().goToHome();
  }

  @FXML
  void goToCart(MouseEvent event) throws IOException {
    Navigator.getInstance().goToCart();
  }

  @FXML
  void goToSearch(MouseEvent event) throws IOException {
    Navigator.getInstance().goToSearch(txtSearch.getText());
  }

  @FXML
  void goToProfile(MouseEvent event) throws IOException {
    Navigator.getInstance().goToProfile();
  }

  @FXML
  void goToLogin(MouseEvent event) throws IOException {
    Navigator.getInstance().goToLogin();
  }
}
