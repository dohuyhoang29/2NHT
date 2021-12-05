package com;

import com.view.Navigator;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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

//  public static void main(String[] args) {
//    Path path = Paths.get(".").toAbsolutePath().normalize();
//    System.out.println(path.toString());
//  }
}
