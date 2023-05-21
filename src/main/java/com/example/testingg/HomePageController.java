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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.testingg.GUIMain.CurrentlyLoggedIn;
import static com.example.testingg.GUIMain.CurrentlyViewedGroup;
import static com.example.testingg.GUIMain.CurrentlyViewedAccount;


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
    private VBox NotificationsVBox;
    @FXML
    private VBox FriendsVBox;
    @FXML
    private ScrollPane NotificationsScrollPane;
    @FXML
    private TextField SearchBar;

    @FXML
    private Label WelcomeLabel;


    @FXML
    void AddFriendPopUpWindow(ActionEvent event) {
        Button button = new Button("Add Friend");
        TextField tx = new TextField();
        VBox layout = new VBox(10);
        layout.getChildren().addAll(tx, button);
        layout.setAlignment(Pos.CENTER);

        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Pop-up Window");
        popUpWindow.setMinWidth(250);
        button.setOnAction(events -> {
            if (Account.isPresent(tx.getText())) {
                CurrentlyLoggedIn.addFollower(Account.FetchAccountByUsername(tx.getText()));
                popUpWindow.close();
                Button bt = new Button(tx.getText());
                FriendsVBox.getChildren().add(bt);
                bt.setOnAction(actionEvent -> {
                    try {
                        SwitchToAccount(Account.FetchAccountByUsername(tx.getText()), actionEvent);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });


                GroupsScroll.setContent(GroupsVBox);

                popUpWindow.close();
            }
        });
        Scene scene = new Scene(layout);
        popUpWindow.setScene(scene);
        popUpWindow.showAndWait();
    }

    @FXML
    void AddGroupPopUpWindow(ActionEvent event) throws IOException{

        Button button = new Button("Join Group");
        TextField tx = new TextField();
        // Create a VBox layout and add the label and button to it
        VBox layout = new VBox(10);
        layout.getChildren().addAll(tx, button);
        layout.setAlignment(Pos.CENTER);

        // Create a new stage for the pop-up window
        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Pop-up Window");
        popUpWindow.setMinWidth(250);
        button.setOnAction(events -> {
            if (Group.isPresent(tx.getText())) {
                Group gp = Group.FetchGroupByName(tx.getText());
                System.out.println("Joining " + gp.gName);
                CurrentlyLoggedIn.joinGroup(gp);
                Button bt = new Button(gp.gName);
                CurrentlyViewedGroup=gp;
                bt.setOnAction(event1 -> {
                    try {
                        SwitchToGroup(gp, event1);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                GroupsVBox.getChildren().add(bt);
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
        TextField tx = new TextField();
        Label error = new Label();
        CheckBox checkBox = new CheckBox("Private?");

        // Create a VBox layout and add the label and button to it
        VBox layout = new VBox(10);
        layout.getChildren().addAll(tx, checkBox, button, error);
        layout.setAlignment(Pos.CENTER);

        // Create a new stage for the pop-up window
        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Pop-up Window");
        popUpWindow.setMinWidth(250);
        button.setOnAction(events -> {
            try {

                Group gp = new Group(CurrentlyLoggedIn, !checkBox.isSelected(), tx.getText());
                Button bt = new Button(gp.gName);
                GroupsVBox.getChildren().add(bt);
                bt.setOnAction(actionEvent -> {
                    try {
                        SwitchToGroup(gp, actionEvent);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });


                GroupsScroll.setContent(GroupsVBox);

                popUpWindow.close();
            } catch (Exception E) {
                error.setText(E.getMessage());

            }


        });


        // Set the scene for the pop-up window
        Scene scene = new Scene(layout);
        popUpWindow.setScene(scene);

        // Show the pop-up window and wait for it to be closed before continuing

        popUpWindow.showAndWait();


    }

    @FXML
    void SignOut(ActionEvent event) throws IOException {
        CurrentlyLoggedIn = null;
        Parent root1 = FXMLLoader.load(getClass().getResource("LogInPage.fxml"));
        Stage appst = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root1);
        appst.setScene(scene1);
        appst.setTitle(" ");
        appst.show();

    }

    public void initialize() {
        if (CurrentlyLoggedIn.role.equals("Employee")) {
            Create.setDisable(true);
        }

        for (Group group : CurrentlyLoggedIn.groups) {
            Button bt = new Button(group.gName);
            GroupsVBox.getChildren().add(bt);
            bt.setOnAction(actionEvent -> {
                try {
                    SwitchToGroup(group, actionEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        }
        for (Post post : CurrentlyLoggedIn.posts) {
            PostsVB.getChildren().add(new Label(post.getPost()));

        }
        for (Notification notification : CurrentlyLoggedIn.notifications) {

            NotificationsVBox.getChildren().add(new Label(notification.content));

        }
        for (Account following : CurrentlyLoggedIn.friends) {

            FriendsVBox.getChildren().add(new Label(following.username));

        }
        WelcomeLabel.setText(WelcomeLabel.getText() + " " + CurrentlyLoggedIn.username);
        GroupsScroll.setContent(GroupsVBox);
        NotificationsScrollPane.setContent(NotificationsVBox);

    }

    public void SwitchToGroup(Group gp, ActionEvent event) throws IOException {
        CurrentlyViewedGroup = gp;
        Parent root1 = FXMLLoader.load(getClass().getResource("GroupPage.fxml"));
        Stage appst = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root1);
        appst.setScene(scene1);
        appst.setTitle(" ");
        appst.show();

    }

    public void SwitchToAccount(Account ac, ActionEvent event) throws IOException {
        CurrentlyViewedAccount = ac;
        Parent root1 = FXMLLoader.load(getClass().getResource("Account.fxml"));
        Stage appst = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root1);
        appst.setScene(scene1);
        appst.setTitle(" ");
        appst.show();

    }

    @FXML
    void AddPostAction(ActionEvent event) {
        Button button = new Button("Add Post");
        TextField tx = new TextField();
        // Create a VBox layout and add the label and button to it
        VBox layout = new VBox(10);
        layout.getChildren().addAll(tx, button);
        layout.setAlignment(Pos.CENTER);
        // Create a new stage for the pop-up window
        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Pop-up Window");
        popUpWindow.setMinWidth(250);
        button.setOnAction(events -> {
            Post pt = new Post(tx.getText(),CurrentlyLoggedIn);
            PostsVB.getChildren().add(new Label(pt.getPost()));
            PostScrolls.setContent(PostsVB);
            CurrentlyLoggedIn.addPost(pt.getPost());

            popUpWindow.close();

        });

        Scene scene = new Scene(layout);
        popUpWindow.setScene(scene);

        // Show the pop-up window and wait for it to be closed before continuing

        popUpWindow.showAndWait();


    }

    @FXML
    void Search(ActionEvent event) throws IOException {
        String temp = SearchBar.getText();
        for (Account account : Account.accounts) {
            if (temp.equals(account.username)) {
                SwitchToAccount(account, event);
                return;
            }
        }
        for (Group group : Group.ListofGroups) {
            if (temp.equals(group.gName)) {
                SwitchToGroup(group, event);
                return;
            }
        }

    }

}


