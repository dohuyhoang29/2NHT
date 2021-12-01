package com.controller;

import com.helper.OrderDetailsDatabaseHelper;
import com.helper.ProjectManager;
import com.model.Order;
import com.model.OrderDetail;
import com.view.Navigator;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PurchaseOrderController implements Initializable {
  @FXML
  private TextField txtSearch;

  @FXML
  private Pane coutCart;

  @FXML
  private Label count;

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

  List<OrderDetail> listOrderDetail = new ArrayList<>();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    setDataAll();
    setDataToPay();
    setDataToShip();
    setDataToReceive();
    setDataCompleted();
  }

  //setData
  void setDataAll () {
    listOrderDetail = OrderDetailsDatabaseHelper.getOrderDetailByAccount(ProjectManager.getInstance().getAccount().getId());

    if (listOrderDetail != null) {
      try {
        for (int i = 0; i < listOrderDetail.size(); i++) {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("/com/view/PurchaseOrderItemUI.fxml"));
          VBox vBox = loader.load();
          PurchaseOrderItemController controller = loader.getController();
          controller.setData(listOrderDetail.get(i));
          allBox.getChildren().add(vBox);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  void setDataToPay () {
    listOrderDetail = OrderDetailsDatabaseHelper.getOrderDetailByAccountAndStatus(ProjectManager.getInstance().getAccount().getId(), Order.TO_PAY);

    if (listOrderDetail != null) {
      try {
        for (int i = 0; i < listOrderDetail.size(); i++) {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("/com/view/PurchaseOrderItemUI.fxml"));
          VBox vBox = loader.load();
          PurchaseOrderItemController controller = loader.getController();
          controller.setData(listOrderDetail.get(i));
          toPayBox.getChildren().add(vBox);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  void setDataToShip () {
    listOrderDetail = OrderDetailsDatabaseHelper.getOrderDetailByAccountAndStatus(ProjectManager.getInstance().getAccount().getId(), Order.TO_SHIP);

    if (listOrderDetail != null) {
      try {
        for (int i = 0; i < listOrderDetail.size(); i++) {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("/com/view/PurchaseOrderItemUI.fxml"));
          VBox vBox = loader.load();
          PurchaseOrderItemController controller = loader.getController();
          controller.setData(listOrderDetail.get(i));
          toShipBox.getChildren().add(vBox);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  void setDataToReceive () {
    listOrderDetail = OrderDetailsDatabaseHelper.getOrderDetailByAccountAndStatus(ProjectManager.getInstance().getAccount().getId(), Order.TO_RECEIVE);

    if (listOrderDetail != null) {
      try {
        for (int i = 0; i < listOrderDetail.size(); i++) {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("/com/view/PurchaseOrderItemUI.fxml"));
          VBox vBox = loader.load();
          PurchaseOrderItemController controller = loader.getController();
          controller.setData(listOrderDetail.get(i));
          toReceiveBox.getChildren().add(vBox);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  void setDataCompleted () {
    listOrderDetail = OrderDetailsDatabaseHelper.getOrderDetailByAccountAndStatus(ProjectManager.getInstance().getAccount().getId(), Order.COMPLETED);

    if (listOrderDetail != null) {
      try {
        for (int i = 0; i < listOrderDetail.size(); i++) {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("/com/view/PurchaseOrderItemUI.fxml"));
          VBox vBox = loader.load();
          PurchaseOrderItemController controller = loader.getController();
          controller.setData(listOrderDetail.get(i));
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
