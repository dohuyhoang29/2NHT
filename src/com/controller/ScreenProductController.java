package com.controller;

import com.helper.CartDatabaseHelper;
import com.helper.FeedbackDatabaseHelper;
import com.helper.ProductDatabaseHelper;
import com.helper.ProjectManager;
import com.helper.TranslateManager;
import com.model.Cart;
import com.model.Feedback;
import com.model.Product;
import com.view.Navigator;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ScreenProductController implements Initializable {

  @FXML
  private TextField txtSearch;

  @FXML
  private Pane coutCart;

  @FXML
  private Label count;

  @FXML
  private ImageView imgSrc;

  @FXML
  private Label name;

  @FXML
  private Label hardDriveTop;

  @FXML
  private Label price;

  @FXML
  private TextField quantity;

  @FXML
  private VBox quantityBox;

  @FXML
  private Label quantityReviewTop;

  @FXML
  private VBox infomation;

  @FXML
  private Label hardDriveUnder;

  @FXML
  private Label screen;

  @FXML
  private Label rearCamera;

  @FXML
  private Label selfieCamera;

  @FXML
  private Label batteryCapacity;

  @FXML
  private Label sim;

  @FXML
  private Label cpu;

  @FXML
  private Label gpu;

  @FXML
  private Label ram;

  @FXML
  private Label operatingSystem;

  @FXML
  private Label weight;

  @FXML
  private Label dimensions;

  @FXML
  private VBox reviews;

  @FXML
  private VBox reviewBox;

  @FXML
  private Label quantityReviewUnder;

  @FXML
  private Label nameProductReview;

  @FXML
  private TextField point;

  @FXML
  private TextArea feedback;

  @FXML
  private HBox similarBox;

  Product product;
  List<Feedback> listFeedback = new ArrayList<>();
  private List<Cart> listCart = new ArrayList<>();
  String path = Paths.get(".").toAbsolutePath().normalize() + "/src/com/images/";

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    listCart = CartDatabaseHelper.getAllCartByAccount(ProjectManager.getInstance().getAccount().getUsername());
    Integer cart = listCart.size();
    count.setText(cart.toString());

    quantity.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue,
          String newValue) {
        if (!newValue.matches("\\d")) {
          quantity.setText(newValue.replaceAll("[^\\d]", ""));
        }
      }
    });
  }

  public void setData(Product product) {
    ProjectManager.getInstance().setProduct(product);

    Image image = new Image("file:///" + path + product.getImgSrc());
    imgSrc.setImage(image);

    name.setText(product.getProductName());
    hardDriveTop.setText(product.getHardDrive());
    price.setText(product.getPrice().toString());
    screen.setText(product.getScreen());
    rearCamera.setText(product.getRearCamera());
    selfieCamera.setText(product.getSelfieCamera());
    batteryCapacity.setText(product.getBatteryCapacity());
    sim.setText(product.getSim());
    cpu.setText(product.getCpu());
    gpu.setText(product.getGpu());
    ram.setText(product.getRam());
    operatingSystem.setText(product.getOperatingSystem());
    weight.setText(product.getWeight());
    dimensions.setText(product.getDimensions());

    printListFeedBack(product.getId());

  }

  //Action

  public void showInfomation(MouseEvent mouseEvent) {
    reviews.setVisible(false);
    infomation.setVisible(true);
  }

  public void showReviews(MouseEvent mouseEvent) {
    infomation.setVisible(false);
    reviews.setVisible(true);
  }

  public void printListFeedBack(Integer productID) {
    listFeedback = FeedbackDatabaseHelper.getFeedbackByProduct(productID);
    Integer quantity = listFeedback.size();
    quantityReviewUnder.setText(quantity.toString());
    quantityReviewTop.setText(quantity.toString());
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

  @FXML
  void up (MouseEvent event) {
    Integer count = Integer.parseInt(quantity.getText());
    count++;
    quantity.setText(count.toString());
  }

  @FXML
  void down (MouseEvent event) {
    Integer count = Integer.parseInt(quantity.getText());
    count--;
    if (count < 1) {
      quantity.setText("1");
    } else {
      quantity.setText(count.toString());
    }
  }

  @FXML
  void quantityEntered (MouseEvent event) {
    quantityBox.setVisible(true);
  }

  @FXML
  void quantityExited (MouseEvent event) {
    quantityBox.setVisible(false);
  }

  @FXML
  void clickAddToCart(ActionEvent event) throws IOException {
    List<Cart> list = new ArrayList<>(CartDatabaseHelper.getAllCartByAccount(ProjectManager.getInstance().getAccount().getUsername()));
    for (int i = 0; i <list.size(); i++) {
      if (Objects.equals(ProjectManager.getInstance().getProduct().getId(), list.get(i).getProductID())) {
        CartDatabaseHelper.changeQuantity(Integer.parseInt(quantity.getText()) + list.get(i).getQuantity(), list.get(i).getId());
        return;
      }
    }
    CartDatabaseHelper.addToCart(Integer.parseInt(quantity.getText()), ProjectManager.getInstance().getAccount().getId(), ProjectManager.getInstance().getProduct().getId());
    Navigator.getInstance().goToScreenProduct(ProjectManager.getInstance().getProduct());
  }

  @FXML
  void feedbackProduct (ActionEvent event) {
    FeedbackDatabaseHelper.insertFeedback(feedback.getText(), Integer.parseInt(point.getText()), ProjectManager.getInstance().getAccount().getId(), ProjectManager.getInstance().getProduct().getId());
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

}
