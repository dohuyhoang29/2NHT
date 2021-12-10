package com.controller;

import com.helper.CartDatabaseHelper;
import com.helper.ProjectManager;
import com.helper.TranslateManager;
import com.model.Cart;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CartController implements Initializable {

  @FXML
  private TextField txtSearch;

  @FXML
  private Pane coutCart;

  @FXML
  private VBox cartBox;

  @FXML
  private Label count;

  @FXML
  private Label subTotal;

  @FXML
  private Button pay;

  List<Cart> listCart = new ArrayList<>();
  Integer sub = 0;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    listCart = CartDatabaseHelper.getAllCartByAccount(
        ProjectManager.getInstance().getAccount().getUsername());
    Integer countCart = listCart.size();
    count.setText(countCart.toString());
    try {
      for (int i = 0; i < listCart.size(); i++) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/view/CartItemUI.fxml"));
        loader.setResources(TranslateManager.getRb());
        HBox hBox = loader.load();
        CartItemController controller = loader.getController();
        sub += controller.setData(listCart.get(i));
        cartBox.getChildren().add(hBox);

      }
      subTotal.setText(sub.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    listCart = CartDatabaseHelper.getAllCartByAccount(ProjectManager.getInstance().getAccount().getUsername());
    Integer cart = listCart.size();
    count.setText(cart.toString());
  }

  //Action
  @FXML
  void clickPay(ActionEvent event) throws IOException {
    Navigator.getInstance().goToPay();
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

  @FXML
  void goToProfile (MouseEvent event) throws IOException {
    Navigator.getInstance().goToProfile();
  }

  @FXML
  void goToLogin (MouseEvent event) throws IOException {
    Navigator.getInstance().goToLogin();
  }
}
