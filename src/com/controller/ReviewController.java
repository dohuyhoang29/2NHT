package com.controller;

import com.helper.FeedbackDatabaseHelper;
import com.helper.ProjectManager;
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

public class ReviewController {
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

  OrderDetail orderDetail;
  Stage stage;

  public void setData (OrderDetail orderDetail, Stage stage) {
    this.stage = stage;
    this.orderDetail = orderDetail;
    Image image = new Image(getClass().getResourceAsStream("/com/images/" + orderDetail.getImgSrc()));
    imgSrc.setImage(image);
    name.setText(orderDetail.getName());
    hardDrive.setText(orderDetail.getHardDrive());
  }

  @FXML
  void complete (ActionEvent event) {
    FeedbackDatabaseHelper.insertFeedback(feedback.getText(), Integer.parseInt(point.getText()), ProjectManager.getInstance().getAccount().getId(), orderDetail.getId());
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setContentText("Product reviews success");
    alert.show();
    stage.close();
  }
}
