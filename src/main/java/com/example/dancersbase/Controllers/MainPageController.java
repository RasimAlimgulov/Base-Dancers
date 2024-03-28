package com.example.dancersbase.Controllers;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.example.dancersbase.MyFXMLloader;
import com.example.dancersbase.TableInfo;
import com.example.dancersbase.ConnectorMananger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.dancer;

public class MainPageController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TableView<dancer> dancersTable;
    @FXML
    private TableColumn<dancer, String> col_Seconname;

    @FXML
    private TableColumn<dancer, Integer> col_id;

    @FXML
    private TableColumn<dancer, String> col_name;

    @FXML
    private TableColumn<dancer, String> col_thirdname;
    @FXML
    private Button createbutton;

    @FXML
    private Button deletebutton;

    @FXML
    private void refreshTable() throws SQLException {
        dancerlist.clear();
        query="select * from "+ TableInfo.USER_Table;
        prepareStatement= connection.prepareStatement(query);
        resultSet=prepareStatement.executeQuery();
        while (resultSet.next()){
            dancerlist.add(new dancer(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("secondname"),
                    resultSet.getString("thirdname")));
            dancersTable.setItems(dancerlist);
        }
    }

    @FXML
    private Button addbutton;
    ObservableList<dancer>  dancerlist =FXCollections.observableArrayList();
    Connection connection;
    String query;
    PreparedStatement prepareStatement;
    ResultSet resultSet;
    @FXML
    void initialize() throws SQLException {

        addbutton.setOnAction(ActionEvent ->{
            addbutton.getScene().getWindow().hide();
            MyFXMLloader.openNewWindow( "AddDancer-base");
        });
        deletebutton.setOnAction(ActionEvent ->{
            deletebutton.getScene().getWindow().hide();
            MyFXMLloader.openNewWindow("Delete");
        });

        connection =ConnectorMananger.getConnection();

        refreshTable();

col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
col_Seconname.setCellValueFactory(new PropertyValueFactory<>("secondname"));
col_thirdname.setCellValueFactory(new PropertyValueFactory<>("thirdname"));


createbutton.setOnAction(actionEvent -> {
    createbutton.getScene().getWindow().hide();
    MyFXMLloader.openNewWindow("change");
});
    }

}
