package com.example.testingg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIMain extends Application {
    public static Account CurrentlyLoggedIn;
    public static Group CurrentlyViewedGroup;
    public static Account CurrentlyViewedAccount;
    @Override
    public void start(Stage stage) throws IOException {
        Account ac1=new Account("nour","123","Employee");
        Account ac2=new Account("mark","123","Team Leader");

        Account.accounts.get(0).addFollower(Account.accounts.get(1));
        Account.accounts.get(0).addPost("sda");
        for(Account acc: Account.accounts){
            System.out.println(acc.username);
        }
        new Group(ac2, true, "asd").addPost(ac2, new Post("Hello First post"));

        System.out.println(Account.accounts.get(1).notifications.size());

        FXMLLoader fxmlLoader = new FXMLLoader(GUIMain.class.getResource("LogInPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 263);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}