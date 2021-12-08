package com.controller;

import com.helper.ProjectManager;
import com.model.Feedback;
import com.model.OrderDetail;
import com.view.Navigator;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewReviewController {
  @FXML
  private ImageView imgSrc;

  @FXML
  private Label name;

  @FXML
  private Label hardDrive;

  @FXML
  private Label username;

  @FXML
  private Label point;

  @FXML
  private Label feedback;

  @FXML
  private Label date;

  OrderDetail orderDetail;
  Stage stage;
  Feedback Feedback;
  String path = Paths.get(".").toAbsolutePath().normalize() + "/src/com/images/";

  public void setData (Feedback feedBack, OrderDetail orderDetail, Stage stage) {
    this.orderDetail = orderDetail;
    this.Feedback = feedBack;
    this.stage = stage;
    Image image = new Image("file:///" + path + orderDetail.getImgSrc());
    imgSrc.setImage(image);
    name.setText(orderDetail.getName());
    hardDrive.setText(orderDetail.getHardDrive());
    username.setText(ProjectManager.getInstance().getAccount().getUsername());
    point.setText(feedBack.getPoint().toString());
    feedback.setText(feedBack.getFeedback());
    date.setText(feedBack.getDateProperty());
  }

  @FXML
  void goToEditFeedback () throws IOException {
    stage.close();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/com/view/EditReviewUI.fxml"));
    Parent root = loader.load();
    EditReviewController controller = loader.getController();
    Stage stage = new Stage();
    controller.setData(Feedback, orderDetail, stage);
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.initModality(Modality.WINDOW_MODAL);
    stage.initOwner(Navigator.getInstance().getStage());
    stage.show();
  }
}
