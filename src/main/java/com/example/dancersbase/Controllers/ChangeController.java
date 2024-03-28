package com.example.dancersbase.Controllers;
import com.example.dancersbase.MyFXMLloader;
import com.example.dancersbase.TableInfo;
import com.example.dancersbase.ConnectorMananger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.dancer;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ChangeController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button change_button;

    @FXML
    private TableView<dancer> change_table;

    @FXML
    private Label click_id;

    @FXML
    private TableColumn<dancer, Integer> id_change;

    @FXML
    private TableColumn<dancer, String> name_change;

    @FXML
    private TextField newname;

    @FXML
    private TextField newsecname;

    @FXML
    private TextField newthirdnme;

    @FXML
    private Label infoChange;
@FXML
private Button back;

    @FXML
    private TableColumn<dancer, String> secname_change;

    @FXML
    private TableColumn<dancer, String> thirdname_change;
  ObservableList list_dancers= FXCollections.observableArrayList();
  String select= "select * from "+ TableInfo.USER_Table;
  Statement statement;
  Connection connect;
  ResultSet result;
  String nameCopy;
    String secCopy;
    String thirdCopy;
    boolean success1=false;
    private void refresh() throws SQLException {
        list_dancers.clear();
        connect=ConnectorMananger.getConnection();
        statement=connect.createStatement();
        result=statement.executeQuery(select);
        while (result.next()){
            list_dancers.add(new dancer(result.getInt("id"),result.getString("name"),
                    result.getString("secondname"),result.getString("thirdname")));
            change_table.setItems(list_dancers);
        }
    }
    @FXML
    void initialize() throws SQLException {

        refresh();
   id_change.setCellValueFactory(new PropertyValueFactory<>("id"));
   name_change.setCellValueFactory(new PropertyValueFactory<>("name"));
   secname_change.setCellValueFactory(new PropertyValueFactory<>("secondname"));
   thirdname_change.setCellValueFactory(new PropertyValueFactory<>("thirdname"));

        change_table.setOnMouseClicked(mouseEvent -> {
            dancer ob;
        ob=change_table.getSelectionModel().getSelectedItem();
        Integer id=ob.getId();
        String name=ob.getName();
        nameCopy=name;
        String secname=ob.getSecondname();
        secCopy= secname;
        String thirdname=ob.getThirdname();
        thirdCopy=thirdname;
       click_id.setText(id.toString());
       newname.setText(name.toString());
       newsecname.setText(secname);
       newthirdnme.setText(thirdname);
   });

   change_button.setOnAction(actionEvent ->{


       if (nameCopy!=newname.getText()){
           try {
               String update="update "+ TableInfo.USER_Table+" set name=? where id= "+click_id.getText();
               PreparedStatement prst=connect.prepareStatement(update);
               prst.setString(1,newname.getText());
               prst.executeUpdate();
               refresh();
               id_change.setCellValueFactory(new PropertyValueFactory<>("id"));
               name_change.setCellValueFactory(new PropertyValueFactory<>("name"));
               secname_change.setCellValueFactory(new PropertyValueFactory<>("secondname"));
               thirdname_change.setCellValueFactory(new PropertyValueFactory<>("thirdname"));
              success1=true;
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       }

       if (secCopy!=newsecname.getText()){
           try {
               String update="update "+ TableInfo.USER_Table+" set secondname=? where id= "+click_id.getText();
               PreparedStatement prst=connect.prepareStatement(update);
               prst.setString(1,newsecname.getText());
               prst.executeUpdate();
               refresh();
               id_change.setCellValueFactory(new PropertyValueFactory<>("id"));
               name_change.setCellValueFactory(new PropertyValueFactory<>("name"));
               secname_change.setCellValueFactory(new PropertyValueFactory<>("secondname"));
               thirdname_change.setCellValueFactory(new PropertyValueFactory<>("thirdname"));
               success1=true;
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       }

       if (thirdCopy!=newthirdnme.getText()){
           try { String update="update "+ TableInfo.USER_Table+" set thirdname=? where id="+click_id.getText();
               PreparedStatement prst=connect.prepareStatement(update);
               prst.setString(1,newthirdnme.getText());
               prst.executeUpdate();
               refresh();
               id_change.setCellValueFactory(new PropertyValueFactory<>("id"));
               name_change.setCellValueFactory(new PropertyValueFactory<>("name"));
               secname_change.setCellValueFactory(new PropertyValueFactory<>("secondname"));
               thirdname_change.setCellValueFactory(new PropertyValueFactory<>("thirdname"));
               success1=true;
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }}
if (success1){
    Timeline timeline = new Timeline(
            new KeyFrame(javafx.util.Duration.ZERO, e -> infoChange.setVisible(true)),
            new KeyFrame(javafx.util.Duration.seconds(3), e -> infoChange.setVisible(false))
    );
    timeline.setCycleCount(1); // чтобы сработал только один раз
    timeline.play();
}


   } );


back.setOnAction(actionEvent -> {
    back.getScene().getWindow().hide();
    MyFXMLloader.openNewWindow( "MainPage");
});



    }


}
