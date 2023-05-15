module com.example.bugmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bugmanager to javafx.fxml;
    exports com.example.bugmanager;
}