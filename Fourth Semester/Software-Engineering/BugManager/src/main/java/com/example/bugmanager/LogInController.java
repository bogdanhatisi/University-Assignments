package com.example.bugmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {

    @FXML
    private Button logInButton;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label error;


    @FXML
    private void userLogIn(ActionEvent event) throws IOException{
        checkLogIn();
    }

    private void checkLogIn() throws IOException{

        if(username.getText().toString().equals("admin")&& password.getText().toString().equals("admin"))
        {
            error.setText("Successfully logged in");

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("afterLogin.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Dashboard");
            stage.setScene(new Scene(root1));
            stage.show();

        }

        else if(username.getText().isEmpty() && password.getText().toString().isEmpty())
        {
            error.setText("Please enter valid data");
        }
    }
}
