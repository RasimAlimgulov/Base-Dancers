package com.example.dancersbase.Controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.dancersbase.MyFXMLloader;
import com.example.dancersbase.TableInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FirstWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button EnterButton;

    @FXML
    private Label Massage;

    @FXML
    private Button RepasswordButton;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    void initialize() {
     EnterButton.setOnAction(ActionEvent -> {
         if (TableInfo.login.equals(loginTextField.getText()) && TableInfo.parol.equals(passwordTextField.getText())) {
               EnterButton.getScene().getWindow().hide();
             MyFXMLloader.openNewWindow(  "MainPage");
         }
         else {
             Massage.setVisible(true);
         }

     });


    }

}
