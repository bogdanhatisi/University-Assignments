package com.example.demo;

import com.example.demo.domain.Proba;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AfterLogin {

    @FXML
    private Label afterLog;

    @FXML
    private TableView<Proba> tableCategories;

    private static <E> void initialize_table(TableView<E> tabel, List<String> columnNames) {
        for (String columnName : columnNames) {
            TableColumn<E, String> column = new TableColumn<>(columnName);
            column.setCellValueFactory(new PropertyValueFactory<>(columnName));
            tabel.getColumns().add(column);
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize_table(tableCategories, Arrays.asList("Proba","Participanti"));
        tableCategories.setItems(list);
    }
}
