package com.controller;

import com.view.Navigator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DashboardController implements Initializable {
    @FXML
    private TextField txtSearch;

    @FXML
    private ImageView changeLanguage;

    @FXML
    private Label username;

    @FXML
    private HBox dashboard;

    @FXML
    private HBox addProduct;

    @FXML
    private VBox addProductContainer;

    @FXML
    private HBox addPhone;

    @FXML
    private HBox addLaptop;

    @FXML
    private HBox addTablet;

    @FXML
    private HBox productList;

    @FXML
    private VBox changeLanguageContainer;

    @FXML
    private Label numberOfSales;

    @FXML
    private Label saleRevenue;

    @FXML
    private AreaChart<?, ?> acRevenue;

    @FXML
    private PieChart pcSalesAnalytics;

    @FXML
    private Label weeklyEarnings;

    @FXML
    private Label monthlyEarnings;

    int count;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Phone", 30),
                new PieChart.Data("Laptop", 30),
                new PieChart.Data("Tablet", 30)
        );
        pcSalesAnalytics.setData(pieChartData);

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
