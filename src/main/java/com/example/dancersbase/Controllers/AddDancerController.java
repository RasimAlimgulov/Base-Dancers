package com.example.dancersbase.Controllers;
import java.io.IOException;
import java.sql.*;

import com.example.dancersbase.MyFXMLloader;
import com.example.dancersbase.TableInfo;
import com.example.dancersbase.ConnectorMananger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddDancerController {
    @FXML
    public Label Idnumber;

    @FXML
    private Button backtoMAin;

    @FXML
    private Button addbutton;

    @FXML
    private TextField name;

    @FXML
    private TextField secname;

    @FXML
    private TextField thirthname;

    @FXML
    void initialize()  {
        Idnumber.setVisible(false);

        addbutton.setOnAction(ActionEvent ->{
            try {
                Idnumber.setText(add(name.getText(),secname.getText(),thirthname.getText()));
                Idnumber.setVisible(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

});
        backtoMAin.setOnAction(ActionEvent -> {

            backtoMAin.getScene().getWindow().hide();
            MyFXMLloader.openNewWindow( "MainPage");
        });
    }
    private String add(String _name, String _secname, String _thithname) throws SQLException {
        //Добавляю в базу данных спортсмена
        Connection connection= ConnectorMananger.getConnection();
        String insertQuery = "INSERT INTO infodancers (" + TableInfo.DANCERS_NAME + "," + TableInfo.DANCERS_SECNAME + "," + TableInfo.DANCERS_THIRTNAME + ") VALUES (?, ?, ?)";
        PreparedStatement statement1 = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        statement1.setString(1,_name);
        statement1.setString(2,_secname);
        statement1.setString(3,_thithname);
        statement1.executeUpdate();


        // Получаю Id того кого добавил
        // Получаю Id того кого добавил

        ResultSet generatedKeys = statement1.getGeneratedKeys();
        if (generatedKeys.next()) {
            long id_Num = generatedKeys.getLong(1); // Предполагается, что ID является числом
            return String.valueOf(id_Num);
        } else {
            throw new SQLException("Creating user failed, no ID obtained.");
        }

    }

}