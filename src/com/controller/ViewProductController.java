package com.controller;

import com.helper.FeedbackDatabaseHelper;
import com.helper.ProjectManager;
import com.helper.TranslateManager;
import com.model.Feedback;
import com.model.Product;
import com.view.Navigator;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ViewProductController implements Initializable {
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
  private ImageView imgSrc;

  @FXML
  private Label code;

  @FXML
  private Label name;

  @FXML
  private Label warrantyPreiod;

  @FXML
  private Label importPrice;

  @FXML
  private Label price;

  @FXML
  private Label hardDrive;

  @FXML
  private Label origin;

  @FXML
  private Label sim;

  @FXML
  private Label weight;

  @FXML
  private Label dimensions;

  @FXML
  private Label screen;

  @FXML
  private Label cpu;

  @FXML
  private Label gpu;

  @FXML
  private Label ram;

  @FXML
  private Label color;

  @FXML
  private Label operatingSystem;

  @FXML
  private Label rearCamera;

  @FXML
  private Label selfieCamera;

  @FXML
  private Label batteryCapacity;

  @FXML
  private VBox reviewBox;

  int count;
  List<Feedback> listFeedback = new ArrayList<>();
  String path = Paths.get(".").toAbsolutePath().normalize() + "/src/com/images/";

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    username.setText(ProjectManager.getInstance().getAccount().getUsername());
  }

  public void setData(Product product) {
    Image image = new Image("file:///" + path + product.getImgSrc());

    imgSrc.setImage(image);
    code.setText(product.getProductCode());
    name.setText(product.getProductName());
    warrantyPreiod.setText(product.getWarrantyPeriod().toString());
    importPrice.setText(product.getImportPrice().toString());
    price.setText(product.getPrice().toString());
    hardDrive.setText(product.getHardDrive());
    origin.setText(product.getOrigin());
    sim.setText(product.getSim());
    weight.setText(product.getWeight());
    dimensions.setText(product.getDimensions());
    screen.setText(product.getScreen());
    cpu.setText(product.getCpu());
    gpu.setText(product.getGpu());
    ram.setText(product.getRam());
    operatingSystem.setText(product.getOperatingSystem());
    rearCamera.setText(product.getRearCamera());
    selfieCamera.setText(product.getSelfieCamera());
    batteryCapacity.setText(product.getBatteryCapacity());

    printListFeedBack(product.getId());
  }

  public void printListFeedBack(Integer productID) {
    listFeedback = FeedbackDatabaseHelper.getFeedbackByProduct(productID);
    try {
      for (int i = 0; i < listFeedback.size(); i++) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/view/FeedbackItemUI.fxml"));
        loader.setResources(TranslateManager.getRb());
        VBox vBox = loader.load();
        FeedbackItemController controller = loader.getController();
        controller.setData(listFeedback.get(i));
        reviewBox.getChildren().add(vBox);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
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
  private void back(MouseEvent mouseEvent) throws IOException {
    Navigator.getInstance().goToProductsList();
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
