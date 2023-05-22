package com.example.testingg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField ID;

    @FXML
    private PasswordField pw;
    @FXML
    private Label error1;

    @FXML
    private Label error2;

    @FXML
    private Label error3;
    @FXML
    private ComboBox<String> Role;
    @FXML
    private Button SignUp;
    @FXML
    private Button Back;

    @FXML
    private void handleSignUpButton(ActionEvent event) throws IOException {

        try {
            Account ac = new Account(ID.getText(), pw.getText(), Role.getValue());

            Parent root1 = FXMLLoader.load(getClass().getResource("LogInPage.fxml"));
            Stage appst = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene1 = new Scene(root1);
            appst.setScene(scene1);
            appst.setTitle(" ");
            appst.show();

        } catch (Exception e) {
            if (e.getMessage() == "Password is weak") {
                error2.setText("Password is weak");
            } else if (e.getMessage() == "Username taken") {
                error1.setText("Username taken");
            } else {
                error3.setText("Role is not chosen");
            }

        }
    }

    @FXML
    void HandleBack(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("LogInPage.fxml"));
        Stage appst = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root1);
        appst.setScene(scene1);
        appst.setTitle(" ");
        appst.show();

    }
}