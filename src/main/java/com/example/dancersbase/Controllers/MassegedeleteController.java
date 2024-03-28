package com.example.dancersbase.Controllers;
import java.util.ResourceBundle;

import com.example.dancersbase.TableInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MassegedeleteController extends DeleteController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private TextField deletelogin;

    @FXML
    private PasswordField deletepassword;

    @FXML
    private Button finaldeletebutton;

    @FXML
    void initialize() {
        finaldeletebutton.setOnAction(ActionEvent->{
            if (TableInfo.login.equals(deletelogin.getText()) && TableInfo.parol.equals(deletepassword.getText())){
             finaldeletebutton.getScene().getWindow().hide();
             DeleteController.DeleteGo=true;

            }
        });
    }



}
