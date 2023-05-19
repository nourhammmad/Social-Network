package com.example.testingg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.testingg.GUIMain.CurrentlyLoggedIn;
import static com.example.testingg.GUIMain.CurrentlyViewedGroup;


public class HomePageController {
    @FXML
    private VBox PostsVB;
    @FXML
    private Button out;
    @FXML
    private Button Add;
    @FXML
    private Button AddGroup;
    @FXML
    private Button Create;
    @FXML
    private VBox GroupsVBox;
    @FXML
    private ScrollPane GroupsScroll;
    @FXML
    private Button AddPost;
    @FXML
    private ScrollPane PostScrolls;

    @FXML
    void AddFriendPopUpWindow(ActionEvent event) {
        Button button = new Button("Add Friend");
        TextField tx=new TextField();
        VBox layout = new VBox(10);
        layout.getChildren().addAll( tx,button);
        layout.setAlignment(Pos.CENTER);

        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Pop-up Window");
        popUpWindow.setMinWidth(250);
        button.setOnAction(events->{
            if(Account.isPresent(tx.getText()))
            {
                CurrentlyLoggedIn.addFollower(Account.FetchAccountByUsername(tx.getText()));
                popUpWindow.close();
            }
        });
        Scene scene = new Scene(layout);
        popUpWindow.setScene(scene);
        popUpWindow.showAndWait();
    }
    @FXML
    void AddGroupPopUpWindow(ActionEvent event) {

        Button button = new Button("Join Group");
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
            if(Group.isPresent(tx.getText()))
            {
                CurrentlyLoggedIn.joinGroup(Group.FetchGroupByName(tx.getText()));

                popUpWindow.close();

            }


        });


        // Set the scene for the pop-up window
        Scene scene = new Scene(layout);
        popUpWindow.setScene(scene);

        // Show the pop-up window and wait for it to be closed before continuing

        popUpWindow.showAndWait();
    }
    @FXML
    void CreateAGroup(ActionEvent event) {

        Button button = new Button("Create Group");
        TextField tx=new TextField();
        RadioButton pu=new RadioButton();
        CheckBox checkBox = new CheckBox("Private?");

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

            Group gp=new Group(CurrentlyLoggedIn,!checkBox.isSelected(),tx.getText());
            Button bt=new Button(gp.gName);
            GroupsVBox.getChildren().add(bt);
            bt.setOnAction(actionEvent -> {
                try {
                    Switch (gp,actionEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });



            GroupsScroll.setContent(GroupsVBox);

            popUpWindow.close();


        });


        // Set the scene for the pop-up window
        Scene scene = new Scene(layout);
        popUpWindow.setScene(scene);

        // Show the pop-up window and wait for it to be closed before continuing

        popUpWindow.showAndWait();


    }
    @FXML
    void SignOut(ActionEvent event) throws IOException {
        CurrentlyLoggedIn=null;
        Parent root1 = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Stage appst=(Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root1);
        appst.setScene(scene1);
        appst.setTitle(" ");
        appst.show();

    }
    public void initialize(){
       if(CurrentlyLoggedIn.role.equals("Employee"))
       {
           Create.setDisable(true);
       }

        for(Group group : CurrentlyLoggedIn.groups){
            Button bt=new Button(group.gName);
            GroupsVBox.getChildren().add(bt);
            bt.setOnAction(actionEvent -> {
                try {
                    Switch (group,actionEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        }

        GroupsScroll.setContent(GroupsVBox);

    }
    public void Switch(Group gp,ActionEvent event) throws IOException{
        CurrentlyViewedGroup=gp;
        Parent root1 = FXMLLoader.load(getClass().getResource("GroupPage.fxml"));
        Stage appst=(Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root1);
        appst.setScene(scene1);
        appst.setTitle(" ");
        appst.show();

    }
    @FXML
    void AddPostAction(ActionEvent event) {
        System.out.println("asdasdwqdfas");
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
            PostsVB.getChildren().add(new Label(pt.getPost()));
            PostScrolls.setContent(PostsVB);
            popUpWindow.close();

            });

        Scene scene = new Scene(layout);
        popUpWindow.setScene(scene);

        // Show the pop-up window and wait for it to be closed before continuing

        popUpWindow.showAndWait();


    }

    }


