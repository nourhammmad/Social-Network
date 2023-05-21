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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.testingg.GUIMain.*;
import static com.example.testingg.GUIMain.CurrentlyViewedGroup;

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
        accName.setFont(Font.font("Algerian", FontWeight.BOLD, 30));

        accName.setText(CurrentlyViewedAccount.username);


        for (Post post : CurrentlyViewedAccount.posts) {

            Label p = new Label(CurrentlyViewedAccount.username);
            p.setFont(Font.font("Algerian", FontWeight.BOLD, 22));
            PostsVB.getChildren().add(p);
            Label po = new Label(post.getPost() + " " );
            Label likes=new Label();
            po.setFont(Font.font("Algerian", FontWeight.BOLD, 16));
            PostsVB.getChildren().add(po);
            PostsVB.getChildren().add(likes);
            Button like = new Button("Like");

            likes.setText(" "+post.likers.size());
            like.setOnAction(event2 -> {
                post.addLike(CurrentlyLoggedIn);
                System.out.println(post.likers.size());
                likes.setText(" "+post.likers.size());
            });
            like.setStyle("-fx-background-color: #afd3e2;");
            PostsVB.getChildren().add(like);
            Label pos = new Label("______________________________________________________________________________");
            pos.setFont(Font.font("Algerian", FontWeight.BOLD, 16));
            PostsVB.getChildren().add(pos);
          //  PostScrolls.setContent(PostsVB);
        }
    }
}
