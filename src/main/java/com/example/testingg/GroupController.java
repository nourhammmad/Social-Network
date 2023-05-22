package com.example.testingg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.testingg.GUIMain.*;

public class GroupController {
    @FXML
    private Button Back;

    @FXML
    private Label gpName;
    @FXML
    private Label GroupName;
    @FXML
    private ComboBox<String> MembersCombo;

    @FXML
    private VBox PostsVB;
    @FXML
    private ScrollPane PostScrolls;
    @FXML
    private Button addPost;

    @FXML
    private Button Remove;

    @FXML
    void SignOut(ActionEvent event) {


    }

    @FXML
    void BackToHomePage(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Stage appst = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root1);
        appst.setScene(scene1);
        appst.setTitle(" ");
        appst.show();
    }

    @FXML
    void addPost(ActionEvent event) throws IOException{
        Button button = new Button("Add Post");
        TextField tx = new TextField();
        VBox layout = new VBox(10);
        layout.getChildren().addAll(tx, button);
        layout.setAlignment(Pos.CENTER);
        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Pop-up Window");
        popUpWindow.setMinWidth(250);
        button.setOnAction(events -> {
            Post pt = new Post(tx.getText(), CurrentlyLoggedIn);
            Label p = new Label(pt.PostOwner.username);
            p.setFont(Font.font("Algerian", FontWeight.BOLD, 22));
            PostsVB.getChildren().add(p);
            Label po = new Label(pt.getPost());
            po.setFont(Font.font("Algerian", FontWeight.BOLD, 16));
            Label likes = new Label();
            PostsVB.getChildren().add(po);
            PostsVB.getChildren().add(likes);
            Button like = new Button("Like");
            like.setOnAction(event2 -> {
                pt.addLike(CurrentlyLoggedIn);
                System.out.println(pt.likers.size());
                likes.setText(" " + pt.likers.size());
            });
            like.setStyle("-fx-background-color: #afd3e2;");
            PostsVB.getChildren().add(like);
            Label pos = new Label("______________________________________________________________________________");
            pos.setFont(Font.font("Algerian", FontWeight.BOLD, 16));
            PostsVB.getChildren().add(pos);
            PostScrolls.setContent(PostsVB);
            try {
                CurrentlyViewedGroup.addPost(CurrentlyLoggedIn, pt);
            } catch (Exception e) {
                System.out.println(e.getMessage());;
            }
            popUpWindow.close();

        });
        Scene scene = new Scene(layout);
        popUpWindow.setScene(scene);

        popUpWindow.showAndWait();

    }

    @FXML
    void RemoveUser(ActionEvent event) {
        Button button = new Button("Remove User");
        TextField tx = new TextField();
        VBox layout = new VBox(10);
        layout.getChildren().addAll(tx, button);
        layout.setAlignment(Pos.CENTER);

        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Pop-up Window");
        popUpWindow.setMinWidth(250);
        button.setOnAction(events -> {
            System.out.println(CurrentlyViewedGroup.getMembers(CurrentlyLoggedIn).size());
            CurrentlyViewedGroup.removeUser(CurrentlyLoggedIn, Account.FetchAccountByUsername(tx.getText()));
            System.out.println(CurrentlyViewedGroup.getMembers(CurrentlyLoggedIn).size());
            popUpWindow.close();
        });
        Scene scene = new Scene(layout);
        popUpWindow.setScene(scene);
        popUpWindow.showAndWait();


    }

    public void initialize() {

        if (!(CurrentlyLoggedIn == CurrentlyViewedGroup.moderator)) {
            Remove.setDisable(true);

        }

        gpName.setFont(Font.font("Algerian", FontWeight.BOLD, 30));

        gpName.setText(CurrentlyViewedGroup.gName);

        if (CurrentlyViewedGroup.getPosts(CurrentlyLoggedIn) != null) {
            for (Post post : CurrentlyViewedGroup.getPosts(CurrentlyLoggedIn)) {
                Label p = new Label(post.PostOwner.username);
                p.setFont(Font.font("Algerian", FontWeight.BOLD, 22));
                PostsVB.getChildren().add(p);
                Label po = new Label(post.getPost() + " ");
                Label likes = new Label();
                po.setFont(Font.font("Algerian", FontWeight.BOLD, 16));
                PostsVB.getChildren().add(po);
                PostsVB.getChildren().add(likes);
                Button like = new Button("Like");

                likes.setText(" " + post.likers.size());
                like.setOnAction(event2 -> {
                    post.addLike(CurrentlyLoggedIn);
                    System.out.println(post.likers.size());
                    if (post.likers.size() > 0) likes.setText(" " + post.likers.size());
                });
                like.setStyle("-fx-background-color: #afd3e2;");
                PostsVB.getChildren().add(like);
                Label pos = new Label("______________________________________________________________________________");
                pos.setFont(Font.font("Algerian", FontWeight.BOLD, 16));
                PostsVB.getChildren().add(pos);
                PostScrolls.setContent(PostsVB);
            }
            for (Account accounts : CurrentlyViewedGroup.getMembers(CurrentlyLoggedIn)) {


                MembersCombo.getItems().add(accounts.username);

            }
        } else {
            gpName.setText(gpName.getText() + ": This Group is private");
            MembersCombo.setDisable(true);
        }
    }

}
