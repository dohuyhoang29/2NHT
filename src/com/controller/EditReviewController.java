package com.controller;

import com.helper.FeedbackDatabaseHelper;
import com.model.Feedback;
import com.model.OrderDetail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EditReviewController {
  @FXML
  private ImageView imgSrc;

  @FXML
  private Label name;

  @FXML
  private Label hardDrive;

  @FXML
  private TextField point;

  @FXML
  private TextArea feedback;

  private Stage stage;
  private OrderDetail orderDetail;
  private Feedback Feedback;

  public void setData (Feedback feedback, OrderDetail orderDetail, Stage stage) {
    this.Feedback = feedback;
    this.stage = stage;
    this.orderDetail = orderDetail;
    Image image = new Image(getClass().getResourceAsStream("/com/images/" + orderDetail.getImgSrc()));
    imgSrc.setImage(image);
    name.setText(orderDetail.getName());
    hardDrive.setText(orderDetail.getHardDrive());
    point.setText(feedback.getPoint().toString());
    this.feedback.setText(feedback.getFeedback());
  }

  @FXML
  void editFeedback (ActionEvent event) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setContentText("Edit success");
    alert.show();
    stage.close();
    FeedbackDatabaseHelper.editFeedback(Integer.parseInt(point.getText()), feedback.getText(), Feedback.getId());
  }
}
