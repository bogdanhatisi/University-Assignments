module com.example.bugmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires org.testng;
    requires junit;
    requires java.naming;


    opens com.example.bugmanager to javafx.fxml;
    opens com.example.bugmanager.model;
    exports com.example.bugmanager;
}