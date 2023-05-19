package com.example.testingg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.testingg.GUIMain.CurrentlyLoggedIn;

public class LogInPageController {

    @FXML
    private TextField IDText;
    @FXML
    private Label Status;
    @FXML
    private PasswordField Password;

    @FXML
    private Button SignUp;
    @FXML
    private Button LogIn;
    @FXML
    void LogIn(ActionEvent event) throws IOException {
       Account validity= Account.LogIn(IDText.getText().trim(),Password.getText().trim());



       if(validity!=null) {
           CurrentlyLoggedIn=validity;
           Parent root1 = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
           Stage appst = (Stage) ((Node) event.getSource()).getScene().getWindow();
           Scene scene1 = new Scene(root1);
           appst.setScene(scene1);
           appst.setTitle(" ");
           appst.show();
       }else {
           Status.setText("Wrong ID or password");
           Password.clear();
       }

    }

    @FXML
    void SwitchToSignUp(ActionEvent event) throws IOException{

        Parent root1 = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Stage appst=(Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root1);
        appst.setScene(scene1);
        appst.setTitle(" ");
        appst.show();
    }
}