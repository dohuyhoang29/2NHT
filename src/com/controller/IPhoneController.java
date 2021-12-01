package com.controller;

import com.helper.CartDatabaseHelper;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class IPhoneController implements Initializable {
  @FXML
  private TextField txtSearch;

  @FXML
  private Pane coutCart;

  @FXML
  private Label count;

  @FXML
  private ScrollPane scroll;

  @FXML
  private CheckBox checkBox128;

  @FXML
  private CheckBox checkBox256;

  @FXML
  private CheckBox checkBox512;

  @FXML
  private CheckBox checkBox1;

  @FXML
  private Button refineSearch;

  @FXML
  private GridPane gridProduct;

  private List<Product> listData = new ArrayList<>();
  private List<Cart> listCart = new ArrayList<>();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    listData = ProductDatabaseHelper.getAllProductByCategory(Category.IPHONE);
    int column = 0;
    int row = 1;

    try {
      for (int i = 0; i < listData.size(); i++) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/view/ProductItemUI.fxml"));
        VBox vBox = loader.load();

        ProductItemController controller = loader.getController();
        controller.setData(listData.get(i));

        if (column == 3) {
          column = 0;
          row++;
        }

        gridProduct.add(vBox, column++, row);

        GridPane.setMargin(vBox, new Insets(20));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    listCart = CartDatabaseHelper.getAllCartByAccount(ProjectManager.getInstance().getAccount().getUsername());
    Integer cart = listCart.size();
    count.setText(cart.toString());
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
}
