package com.controller;

import com.helper.CartDatabaseHelper;
import com.helper.CategoryDatabaseHelper;
import com.helper.OrderDatabaseHelper;
import com.helper.OrderDetailsDatabaseHelper;
import com.helper.ProjectManager;
import com.helper.TranslateManager;
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

public class PurchaseOrderController implements Initializable {
  @FXML
  private HBox categoryBox;

  @FXML
  private TextField txtSearch;

  @FXML
  private Pane coutCart;

  @FXML
  private Label count;

  @FXML
  private Label account;

  @FXML
  private VBox all;

  @FXML
  private VBox allBox;

  @FXML
  private VBox toPay;

  @FXML
  private VBox toPayBox;

  @FXML
  private VBox toShip;

  @FXML
  private VBox toShipBox;

  @FXML
  private VBox toReceive;

  @FXML
  private VBox toReceiveBox;

  @FXML
  private VBox completed;

  @FXML
  private VBox completedBox;

  ObservableList<Order> listOrder = FXCollections.observableArrayList();
  private List<Cart> listCart = new ArrayList<>();
  private ObservableList<Category> listCategory = FXCollections.observableArrayList();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    account.setText(ProjectManager.getInstance().getAccount().getUsername());
    setDataAll();
    setDataToPay();
    setDataToShip();
    setDataToReceive();
    setDataCompleted();

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

  //setData
  void setDataAll () {
    listOrder = OrderDatabaseHelper.getAllOrderByAccount(ProjectManager.getInstance().getAccount().getId());

    if (listOrder != null) {
      try {
        for (int i = 0; i < listOrder.size(); i++) {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("/com/view/PurchaseOrderItemUI.fxml"));
          loader.setResources(TranslateManager.getRb());
          VBox vBox = loader.load();
          PurchaseOrderItemController controller = loader.getController();
          controller.setData(listOrder.get(i));
          allBox.getChildren().add(vBox);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  void setDataToPay () {
    listOrder = OrderDatabaseHelper.getAllOrderByAccountAndStatus(ProjectManager.getInstance().getAccount().getId(), Order.TO_PAY);

    if (listOrder != null) {
      try {
        for (int i = 0; i < listOrder.size(); i++) {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("/com/view/PurchaseOrderItemUI.fxml"));
          loader.setResources(TranslateManager.getRb());
          VBox vBox = loader.load();
          PurchaseOrderItemController controller = loader.getController();
          controller.setData(listOrder.get(i));
          toPayBox.getChildren().add(vBox);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  void setDataToShip () {
    listOrder = OrderDatabaseHelper.getAllOrderByAccountAndStatus(ProjectManager.getInstance().getAccount().getId(), Order.TO_SHIP);

    if (listOrder != null) {
      try {
        for (int i = 0; i < listOrder.size(); i++) {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("/com/view/PurchaseOrderItemUI.fxml"));
          loader.setResources(TranslateManager.getRb());
          VBox vBox = loader.load();
          PurchaseOrderItemController controller = loader.getController();
          controller.setData(listOrder.get(i));
          toShipBox.getChildren().add(vBox);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  void setDataToReceive () {
    listOrder = OrderDatabaseHelper.getAllOrderByAccountAndStatus(ProjectManager.getInstance().getAccount().getId(), Order.TO_RECEIVE);

    if (listOrder != null) {
      try {
        for (int i = 0; i < listOrder.size(); i++) {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("/com/view/PurchaseOrderItemUI.fxml"));
          loader.setResources(TranslateManager.getRb());
          VBox vBox = loader.load();
          PurchaseOrderItemController controller = loader.getController();
          controller.setData(listOrder.get(i));
          toReceiveBox.getChildren().add(vBox);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  void setDataCompleted () {
    listOrder = OrderDatabaseHelper.getAllOrderByAccountAndStatus(ProjectManager.getInstance().getAccount().getId(), Order.COMPLETED);

    if (listOrder != null) {
      try {
        for (int i = 0; i < listOrder.size(); i++) {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("/com/view/PurchaseOrderItemUI.fxml"));
          loader.setResources(TranslateManager.getRb());
          VBox vBox = loader.load();
          PurchaseOrderItemController controller = loader.getController();
          controller.setData(listOrder.get(i));
          completedBox.getChildren().add(vBox);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  //Action


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

  @FXML
  void goToAllBox (MouseEvent event) {
    all.setVisible(true);
    toPay.setVisible(false);
    toReceive.setVisible(false);
    toShip.setVisible(false);
    completed.setVisible(false);
  }

  @FXML
  void goToPayBox (MouseEvent event) {
    all.setVisible(false);
    toPay.setVisible(true);
    toReceive.setVisible(false);
    toShip.setVisible(false);
    completed.setVisible(false);
  }

  @FXML
  void goToShipBox (MouseEvent event) {
    all.setVisible(false);
    toPay.setVisible(false);
    toReceive.setVisible(false);
    toShip.setVisible(true);
    completed.setVisible(false);
  }

  @FXML
  void goToReceiveBox (MouseEvent event) {
    all.setVisible(false);
    toPay.setVisible(false);
    toReceive.setVisible(true);
    toShip.setVisible(false);
    completed.setVisible(false);
  }

  @FXML
  void goToCompletedBox (MouseEvent event) {
    all.setVisible(false);
    toPay.setVisible(false);
    toReceive.setVisible(false);
    toShip.setVisible(false);
    completed.setVisible(true);
  }
}
