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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SearchController implements Initializable {
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

  List<Category> listCategory = new ArrayList<>();
  List<Product> listProduct = new ArrayList<>();
  List<Cart> listCart = new ArrayList<>();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    listCategory = CategoryDatabaseHelper.getAllCategories();
    cbCategory.getItems().add("Select Category");
    cbCategory.setValue("Select Category");
    for (Category c : listCategory) {
      cbCategory.getItems().add(c.getName());
    }

    listCart = CartDatabaseHelper.getAllCartByAccount(ProjectManager.getInstance().getAccount().getUsername());
    Integer cart = listCart.size();
    count.setText(cart.toString());
  }

  public void setData (String key) {
    this.key.setText(key);
    search.setText(key);
    txtSearch.setText(key);
//
    listProduct = ProductDatabaseHelper.searchProduct(key);

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
  void goToMacBook(MouseEvent event) throws IOException {
    Navigator.getInstance().goToMacbook();
  }

  @FXML
  void goToIPhone(MouseEvent event) throws IOException {
    Navigator.getInstance().goToIPhone();
  }

  @FXML
  void goToIPad(MouseEvent event) throws IOException {
    Navigator.getInstance().goToIPad();
  }

  @FXML
  void goToSearch (MouseEvent event) throws IOException {
    Navigator.getInstance().goToSearch(txtSearch.getText());
  }
}
