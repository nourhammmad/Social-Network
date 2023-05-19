package com.example.testingg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIMain extends Application {
    public static Account CurrentlyLoggedIn;
    public static Group CurrentlyViewedGroup;
    @Override
    public void start(Stage stage) throws IOException {
        Account.accounts.add(new Account("nour","123456789","Employee"));
        Account.accounts.add(new Account("mark","123456789","Team Leader"));
        FXMLLoader fxmlLoader = new FXMLLoader(GUIMain.class.getResource("LogInPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 263);
        stage.setResizable(false);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}