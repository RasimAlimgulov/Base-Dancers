package com.example.dancersbase;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MyFXMLloader {
    public static void openNewWindow(String nameFxmlFile){
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(MyFXMLloader.class.getResource(nameFxmlFile+".fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene=new Scene(loader.getRoot());
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
