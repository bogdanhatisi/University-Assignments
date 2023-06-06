package com.example.bugmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {

    private Service service;

    @FXML
    private CheckBox verifierCheck;

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

        String setUsername = username.getText().toString();
        String setPassword = password.getText().toString();
        boolean isVerifier = verifierCheck.isSelected();



        if ( isVerifier )
        {
            if(service.findVerifier(setUsername,setPassword)) {
                error.setText("Successfully logged in");

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("afterLogin.fxml"));
                Parent root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Dashboard");
                stage.setScene(new Scene(root1));

                AfterLogin controller = fxmlLoader.getController();
                controller.setService(service);
                controller.init();
                stage.show();
            }
            else {
                error.setText("Please enter a valid verifier");
            }

        }
        else if(service.findProgrammer(setUsername,setPassword)!=0)
        {
            error.setText("Successfully logged in");

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("afterLoginProgrammer.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Dashboard");
            stage.setScene(new Scene(root1));
            int programmerId = service.findProgrammer(setUsername,setPassword);

            AfterLoginProgrammer controller = fxmlLoader.getController();
            service.setProgrammerId(programmerId);
            controller.setService(service);
            controller.init();
            stage.show();


        }
        else {
            error.setText("Please enter valid data");
        }
    }

    public void setService(Service service) {
        this.service = service;
    }
}
