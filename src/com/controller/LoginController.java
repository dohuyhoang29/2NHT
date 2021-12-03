/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;



import com.Main;
import com.helper.AccountDatabaseHelper;
import com.helper.NotificationManager;
import com.helper.ProjectManager;
import com.model.Account;
import com.view.Navigator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    ScaleTransition scaleTransition1;
    @FXML
    
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private RadioButton show_password_btn;
    @FXML
    private Label errUsername;

    @FXML
    private Label errPassword;

    @FXML
    private TextField texfield11;
    
    String written_text;
    boolean valid;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void goToDashBoard() throws IOException {
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            errUsername.setText("Username is required");
            errPassword.setText("Username is required");
        } else {
            Account account = AccountDatabaseHelper.getAccountByUsername(username.getText());
            if (username.getText().equalsIgnoreCase(account.getUsername())) {
                if (password.getText().equalsIgnoreCase(account.getPassword()) || texfield11.getText().equalsIgnoreCase(account.getPassword())) {
                    if (account.getStatus().equalsIgnoreCase(Main.UNLOCK)) {
                        NotificationManager.getInstance().success("Login Success");
                        ProjectManager.getInstance().setAccount(account);
                        if (account.getType().equalsIgnoreCase("ADMIN")) {
                            Navigator.getInstance().goToDashboard();
                        } else {
                            Navigator.getInstance().goToHome();
                        }
                    } else {
                        NotificationManager.getInstance().warning("This account has been locked, please login another account");
                    }
                }
            }
        }
    }
    
    @FXML
    void show_Password(){
        if(show_password_btn.isSelected()==true){
            written_text= password.getText();
            password.setVisible(false);
            texfield11.setVisible(true);
            texfield11.setText(written_text);
        }
        else {
            written_text=texfield11.getText();
            texfield11.setVisible(false);
            password.setVisible(true);
            
            password.setText(written_text);
        }
        
    }
    
    @FXML
    void goToRegister() throws IOException {
        Navigator.getInstance().goToRegister();
    }

    @FXML
    void forgotPassword() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/view/ForgotUI.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage viewAccount = new Stage();
        viewAccount.setScene(scene);
        viewAccount.initModality(Modality.WINDOW_MODAL);
        viewAccount.initOwner(Navigator.getInstance().getStage());
        viewAccount.show();
    }
}
