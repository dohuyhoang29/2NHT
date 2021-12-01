package com.controller;

import com.helper.OrderDatabaseHelper;
import com.model.Order;
import com.view.Navigator;
import java.io.IOException;
import java.net.URL;
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

public class OrderController implements Initializable {
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
  private HBox accountList;

  @FXML
  private VBox changeLanguageContainer;

  @FXML
  private TextField txtSearch;

  @FXML
  private ChoiceBox<String> cbStatus;

  @FXML
  private DatePicker dpFrom;

  @FXML
  private DatePicker dpTo;

  @FXML
  private Button btnSearch;

  @FXML
  private VBox orderBox;

  int count;
  ObservableList<Order> listOrder = FXCollections.observableArrayList();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    listOrder = OrderDatabaseHelper.getAllOrder();

    try {
      for (int i = 0; i < listOrder.size(); i++) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/view/OrderItemUI.fxml"));
        VBox hBox = loader.load();
        OrderItemController controller = loader.getController();
        controller.setData(listOrder.get(i));
        orderBox.getChildren().add(hBox);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    cbStatus.getItems().addAll(Order.TO_PAY, Order.TO_SHIP, Order.TO_RECEIVE, Order.COMPLETED);
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
  void search (ActionEvent event) {
    List<Order> list = OrderDatabaseHelper.searchOrder(txtSearch.getText(), cbStatus.getValue(), dpFrom.getValue(), dpTo.getValue());
    listOrder.clear();
    listOrder.addAll(list);
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
