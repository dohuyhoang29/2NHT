package com;

import com.view.Navigator;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
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


  public static String random() {
    String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    StringBuilder salt = new StringBuilder();
    Random random = new Random();
    while (salt.length() < 5) {
      int index = (int) (random.nextFloat() * SALTCHARS.length());
      salt.append(SALTCHARS.charAt(index));
    }
    String string = salt.toString();
    return "#" + string;
  }
}
