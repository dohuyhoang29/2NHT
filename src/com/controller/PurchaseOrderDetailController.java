package com.controller;

import com.helper.AccountDatabaseHelper;
import com.helper.CartDatabaseHelper;
import com.helper.CategoryDatabaseHelper;
import com.helper.OrderDetailsDatabaseHelper;
import com.helper.ProjectManager;
import com.helper.TranslateManager;
import com.model.Account;
import com.model.Cart;
import com.model.Category;
import com.model.Order;
import com.model.OrderDetail;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PurchaseOrderDetailController implements Initializable {
  @FXML
  private HBox categoryBox;

  @FXML
  private TextField txtSearch;

  @FXML
  private Label count;

  @FXML
  private Label account;

  @FXML
  private Label date;

  @FXML
  private Label orderID;

  @FXML
  private Label name;

  @FXML
  private Label email;

  @FXML
  private Label phone;

  @FXML
  private Label status;

  @FXML
  private Label address;

  @FXML
  private VBox itemLayout;

  @FXML
  private Label subtotal;

  private List<Cart> listCart = new ArrayList<>();
  private ObservableList<Category> listCategory = FXCollections.observableArrayList();
  Order order;
  List<OrderDetail> listOrderDetail = new ArrayList<>();
  Account Account;
  Integer sub = 0;

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

  public void setData(Order order) {
    this.order = order;
    Account = AccountDatabaseHelper.getAccountById(order.getAccountID());
    name.setText(order.getName());
    email.setText(Account.getEmail());
    phone.setText(Account.getPhone());
    status.setText(order.getStatus());
    address.setText(order.getAddress());
    date.setText(order.getCreateDateProperty());
    orderID.setText(order.getCode());

    listOrderDetail = OrderDetailsDatabaseHelper.getOrderDetail(order.getId());

    try {
      for (OrderDetail o : listOrderDetail) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/view/PurchaseOrderDetailItemUI.fxml"));
        loader.setResources(TranslateManager.getRb());
        VBox vBox = loader.load();
        sub += o.getPrice() * o.getQuantity();

        PurchaseOrderDetailItemController controller = loader.getController();
        controller.setData(o);
        itemLayout.getChildren().add(vBox);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    subtotal.setText(sub.toString());

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

  @FXML
  void goToChangePassword (MouseEvent event) throws IOException {
    Navigator.getInstance().goToChangePassword();
  }

  @FXML
  void goToPurchaseOrder (MouseEvent event) throws IOException {
    Navigator.getInstance().goToPurchaseOrder();
  }
}
