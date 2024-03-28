package com.example.dancersbase.Controllers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.dancersbase.MyFXMLloader;
import com.example.dancersbase.TableInfo;
import com.example.dancersbase.ConnectorMananger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.stage.Stage;
import models.dancer;


public class DeleteController {
    @FXML
    private Button backbutton;
    @FXML
    private Label selectedid;

    @FXML
    private Label Selectedname;

    @FXML
    private Label selectedsecname;

    @FXML
    private Label selectedthirdname;

    @FXML
    private TableColumn<dancer, Integer> col_IDdelete;

    @FXML
    private TableColumn<dancer, String> col_NAMEdelete;

    @FXML
    private TableColumn<dancer, String> col_SECNAMEdelete;

    @FXML
    private TableColumn<dancer, String> col_THIRDNAMEdelete;

    @FXML
    private TableView<dancer> delete_table;
    @FXML
    private Button deletebutton;
    @FXML
    private Label succesfull;


    Connection connect;
    ObservableList<dancer> dancer_list = FXCollections.observableArrayList();
    PreparedStatement statement;
    String query;
    Integer value1;
    ResultSet result;
    protected static boolean DeleteGo=false;
    String delete;
    boolean repeat=false;
    public DeleteController() {
    }


    private void refresh_table () throws SQLException {
       dancer_list.clear();
       query="select * from "+ TableInfo.USER_Table;
       statement = connect.prepareStatement(query);
       result= statement.executeQuery();
       while (result.next()){
           dancer_list.add(new dancer(result.getInt("id"),
                   result.getString("name"),
                   result.getString("secondname"),
                   result.getString("thirdname")));
                   delete_table.setItems(dancer_list);
       }
   }

    @FXML
    void initialize() throws SQLException {
        connect= ConnectorMananger.getConnection();
        refresh_table();   // обновляю таблицу
        col_IDdelete.setCellValueFactory((new PropertyValueFactory<>("id")));
        col_NAMEdelete.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_SECNAMEdelete.setCellValueFactory(new PropertyValueFactory<>("secondname"));
        col_THIRDNAMEdelete.setCellValueFactory(new PropertyValueFactory<>("thirdname"));

        delete_table.setOnMouseClicked(mouseEvent -> { //при нажатии на спортсмена выдаются данные в lable
            if (mouseEvent.getClickCount() == 1 && ! delete_table.getSelectionModel().isEmpty())
            {
                repeat=false;
                dancer data;
                data=delete_table.getSelectionModel().getSelectedItem();
                value1=data.getId();
                String value2=data.getName();
                String value3=data.getSecondname();
                String value4=data.getThirdname();
              selectedid.setText(value1.toString());
              Selectedname.setText(value2);
              selectedsecname.setText(value3);
              selectedthirdname.setText(value4);
            }

    });

deletebutton.setOnAction(ActionEvent->{
    if (DeleteGo){
        delete="delete from "+ TableInfo.USER_Table+ " where id = "+value1.toString();
        try {
            statement = connect.prepareStatement(delete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            refresh_table();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        col_IDdelete.setCellValueFactory((new PropertyValueFactory<>("id")));
        col_NAMEdelete.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_SECNAMEdelete.setCellValueFactory(new PropertyValueFactory<>("secondname"));
        col_THIRDNAMEdelete.setCellValueFactory(new PropertyValueFactory<>("thirdname"));
        DeleteGo=false;
        selectedid.setText("id");
        Selectedname.setText("name");
        selectedsecname.setText("secondname");
        selectedthirdname.setText("thirdname");
        succesfull.setVisible(true);
    }
    else {

        if (!repeat){
            MyFXMLloader.openNewWindow( "MassegeDelete");
        repeat=true;
    }}


});

           backbutton.setOnAction(actionEvent -> {
               backbutton.getScene().getWindow().hide();
               MyFXMLloader.openNewWindow("MainPage");
           });

    }

}
