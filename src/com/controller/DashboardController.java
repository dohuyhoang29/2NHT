package com.controller;

import com.helper.DashboardDatabaseHelper;
import com.helper.ProjectManager;
import com.model.Dashboard;
import com.view.Navigator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DashboardController implements Initializable {

  @FXML
  private ImageView changeLanguage;

  @FXML
  private Label username;

  @FXML
  private HBox dashboard;

  @FXML
  private HBox addProduct;

  @FXML
  private HBox productList;

  @FXML
  private VBox changeLanguageContainer;

  @FXML
  private Label numberOfSales;

  @FXML
  private Label saleRevenue;

  @FXML
  private BarChart<String, Number> barChart;

  @FXML
  private PieChart pcSalesAnalytics;

  int count;
  ObservableList<Dashboard> listDashboard = FXCollections.observableArrayList();
  XYChart.Series<String, Number> data = new Series<String, Number>();
  PieChart.Data slice;
  int price = 0;
  int quantity = 0;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    username.setText(ProjectManager.getInstance().getAccount().getUsername());

    listDashboard.addAll(DashboardDatabaseHelper.getPrice());

    for (Dashboard d : listDashboard) {
      data.getData().add(new Data<String, Number>(d.getName(), d.getPrice()));
      slice = new PieChart.Data(d.getName(), d.getQuantity());
      pcSalesAnalytics.getData().add(slice);
      numberOfSales.setText(String.valueOf(quantity + d.getQuantity()));
      saleRevenue.setText(String.valueOf(price + d.getPrice()));
    }
    barChart.getData().add(data);
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

  //Dieu Huong
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
