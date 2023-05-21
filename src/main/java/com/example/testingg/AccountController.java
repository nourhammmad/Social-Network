package com.example.testingg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.testingg.GUIMain.CurrentlyLoggedIn;
import static com.example.testingg.GUIMain.CurrentlyViewedAccount;

public class AccountController {
    @FXML
    private Button Back;

    @FXML
    private Label accName;

    @FXML
    private Label gpName;
    @FXML
    private VBox PostsVB;

    @FXML
    void BackToHomePage(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Stage appst=(Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root1);
        appst.setScene(scene1);
        appst.setTitle(" ");
        appst.show();

    }
    public void initialize() {
        for (Post post : CurrentlyViewedAccount.posts) {
            PostsVB.getChildren().add(new Label(post.getPost()));
        }

    }
}
