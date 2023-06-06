package com.example.bugmanager;

import com.example.bugmanager.model.Bug;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AfterLogin implements Initializable {

    private Service service;

    @FXML
    private Label error;

    @FXML
    private TextField searchField;

    @FXML
    private TextField bugName;


    @FXML
    private TextField bugDescription;


    @FXML
    private TextField programmerId;


    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<Bug> bugView;
    public ObservableList<Bug> list;

    public void setService(Service service) {
        this.service = service;
    }

    public void init()
    {
        list = FXCollections.observableList(Service.toList(service.getAllBugs()));
        initialize_table(bugView, Arrays.asList("id","description","status","programmerId"));
        bugView.setItems(list);

    }

    private static <E> void initialize_table(TableView<E> tabel, List<String> columnNames) {
        for (String columnName : columnNames) {
            TableColumn<E, String> column = new TableColumn<>(columnName);
            column.setCellValueFactory(new PropertyValueFactory<>(columnName));
            tabel.getColumns().add(column);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void addBug(ActionEvent event) throws IOException {

        if(bugDescription.getText().isEmpty()|| bugName.getText().isEmpty() || programmerId.getText().isEmpty())
        {
            error.setText("Please fill all the fields correctly");
            return;
        }
        service.addBug(bugDescription.getText().toString(),bugName.getText().toString(),Integer.parseInt(programmerId.getText()));
        list = FXCollections.observableList(Service.toList(service.getAllBugs()));
        bugView.setItems(list);
    }

    @FXML
    private void deleteBug(ActionEvent event) {
        Bug bug = bugView.getSelectionModel().getSelectedItem();

        service.deleteBug(bug.getId());
        list = FXCollections.observableList(Service.toList(service.getAllBugs()));
        bugView.setItems(list);
    }

    @FXML
    private void filterBugs(ActionEvent event)
    {

        if(searchField.getText().isEmpty())
        {
            error.setText("Please fill the searchbar");
            list = FXCollections.observableList(Service.toList(service.getAllBugs()));
            bugView.setItems(list);
            return;
        }
        list = FXCollections.observableList(Service.toList(service.getAllBugsWithDescription(searchField.getText().toString())));
        if(list.isEmpty())
        {
            error.setText("No bugs found!");
            bugView.setItems(list);
            return;
        }
        bugView.setItems(list);
    }
}
