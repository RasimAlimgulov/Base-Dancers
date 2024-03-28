module com.example.dancersbase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.dancersbase to javafx.fxml;
    exports com.example.dancersbase;
    exports models;
    opens models to javafx.fxml;
    exports com.example.dancersbase.Controllers;
    opens com.example.dancersbase.Controllers to javafx.fxml;
}