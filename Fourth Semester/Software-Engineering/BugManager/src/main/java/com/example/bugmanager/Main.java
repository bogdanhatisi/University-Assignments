package com.example.bugmanager;

import com.example.bugmanager.model.Project;
import com.example.bugmanager.repository.BugRepository;
import com.example.bugmanager.repository.ProgrammerRepository;
import com.example.bugmanager.repository.ProjectRepository;
import com.example.bugmanager.repository.VerifierRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage stg;
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("logInView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),600,600);
        primaryStage.setTitle("Bug Manager");
        primaryStage.setScene(scene);

        VerifierRepository verifierRepo = new VerifierRepository();
        BugRepository bugRepo = new BugRepository();
        ProgrammerRepository programmerRepo = new ProgrammerRepository();


        LogInController controller = fxmlLoader.getController();
        controller.setService(new Service(bugRepo,programmerRepo,verifierRepo));
        primaryStage.show();


    }

    public void changeScene(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch();
    }
}