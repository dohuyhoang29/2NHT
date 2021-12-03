package com;

import com.view.Navigator;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
  public static final String LOCK = "Lock";
  public static final String UNLOCK = "Un-Lock";

  @Override
  public void start(Stage primaryStage) throws Exception {
    Navigator.getInstance().setStage(primaryStage);
    Navigator.getInstance().goToLogin();
  }
}
