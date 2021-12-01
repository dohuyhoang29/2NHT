package com.controller;

import com.helper.CartDatabaseHelper;
import com.helper.FeedbackDatabaseHelper;
import com.helper.ProjectManager;
import com.model.Feedback;
import com.model.OrderDetail;
import com.view.Navigator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PurchaseOrderItemController implements Initializable {
  @FXML
  private ImageView imgSrc;

  @FXML
  private Label name;

  @FXML
  private Label hardDrive;

  @FXML
  private Label quantity;

  @FXML
  private Label price;

  @FXML
  private Label totalPrice;

  @FXML
  private Button viewReview;

  @FXML
  private Button review;

  private OrderDetail orderDetail;
  Feedback feedback;


  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public void setData (OrderDetail orderDetail) {
    this.orderDetail = orderDetail;

    Image image = new Image(getClass().getResourceAsStream("/com/images/" + orderDetail.getImgSrc()));
    imgSrc.setImage(image);
    name.setText(orderDetail.getName());
    hardDrive.setText(orderDetail.getHardDrive());
    quantity.setText(orderDetail.getQuantity().toString());
    price.setText(orderDetail.getPrice().toString());
    totalPrice.setText(String.valueOf(orderDetail.getQuantity() * orderDetail.getPrice()));

    feedback = FeedbackDatabaseHelper.getFeedbackByProductAndAccount(orderDetail.getProductID(), ProjectManager.getInstance().getAccount().getId());

    if (feedback == null) {
      viewReview.setVisible(false);
      review.setVisible(true);
    } else {
      review.setVisible(false);
      viewReview.setVisible(true);
    }
  }

  @FXML
  void rePurchase (ActionEvent event) throws IOException {
    CartDatabaseHelper.addToCart(orderDetail.getQuantity(), ProjectManager.getInstance().getAccount().getId(), orderDetail.getProductID());
    Navigator.getInstance().goToCart();
  }

  @FXML
  void feedback (ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/com/view/ReviewUI.fxml"));
    Parent root = loader.load();
    ReviewController controller = loader.getController();
    Stage stage = new Stage();
    controller.setData(orderDetail, stage);
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.initModality(Modality.WINDOW_MODAL);
    stage.initOwner(Navigator.getInstance().getStage());
    stage.show();
  }

  @FXML
  void viewReview (ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/com/view/ViewReviewUI.fxml"));
    Parent root = loader.load();
    ViewReviewController controller = loader.getController();
    Stage stage = new Stage();
    controller.setData(feedback, orderDetail, stage);
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.initModality(Modality.WINDOW_MODAL);
    stage.initOwner(Navigator.getInstance().getStage());
    stage.show();
  }
}
