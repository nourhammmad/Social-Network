package com.example.testingg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.testingg.GUIMain.CurrentlyLoggedIn;
import static com.example.testingg.GUIMain.CurrentlyViewedGroup;

public class GroupController {
    @FXML
    private Button Back;

    @FXML
    private Label gpName;
    @FXML
    private Label GroupName;
    @FXML
    private VBox PostsVB;
    @FXML
    private ScrollPane PostScrolls;
    @FXML
    private Button addPost;
    @FXML
    void SignOut(ActionEvent event) {

    }
    @FXML
    void BackToHomePage(ActionEvent event)throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Stage appst=(Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root1);
        appst.setScene(scene1);
        appst.setTitle(" ");
        appst.show();
    }

    @FXML
    void addPost(ActionEvent event) {
        Button button = new Button("Add Post");
        TextField tx=new TextField();
        // Create a VBox layout and add the label and button to it
        VBox layout = new VBox(10);
        layout.getChildren().addAll( tx,button);
        layout.setAlignment(Pos.CENTER);
        // Create a new stage for the pop-up window
        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Pop-up Window");
        popUpWindow.setMinWidth(250);
        button.setOnAction(events->{
            Post pt=new Post(tx.getText());
            Label p=new Label(CurrentlyLoggedIn.username);
            p.setFont(Font.font("Algerian", FontWeight.BOLD, 22));
            PostsVB.getChildren().add(p);
            Label po=new Label(pt.getPost());
            po.setFont(Font.font("Algerian", FontWeight.BOLD, 16));
            PostsVB.getChildren().add(po);
            Button like = new Button("Like");
            like.setStyle("-fx-background-color: #afd3e2;");
            PostsVB.getChildren().add(like);

            Label pos=new Label("______________________________________________________________________________");
            pos.setFont(Font.font("Algerian", FontWeight.BOLD, 16));
            PostsVB.getChildren().add(pos);
            PostScrolls.setContent(PostsVB);
            CurrentlyLoggedIn.addPost(pt.getPost());

            popUpWindow.close();

        });

        Scene scene = new Scene(layout);
        popUpWindow.setScene(scene);

        // Show the pop-up window and wait for it to be closed before continuing

        popUpWindow.showAndWait();

    }
    public void initialize(){
        gpName.setFont(Font.font("Algerian", FontWeight.BOLD, 30));

        gpName.setText(CurrentlyViewedGroup.gName);
    }

}
