package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.EmployeesDatabaseConnector;
import com.hardware.fiesta.Model.Account;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginFormController {

    private EmployeesDatabaseConnector emdb;
    @FXML
    public JFXTextField tf_username;
    @FXML
    public JFXPasswordField tf_password;
    @FXML
    public JFXButton loginButton;
    @FXML
    public Label prompText;



    public void initialize(){

        emdb = new EmployeesDatabaseConnector();
        emdb.openConnection();

    }

    @FXML
    public void loginButtonClicked(){

        String username = tf_username.getText().trim();
        String password = tf_password.getText().trim();



        Account account = new Account(username,password,"ADMIN");

        Runnable runnable = () -> {

            if (emdb.searchAccount(account)) {

                Platform.runLater(() -> prompText.setText("login Successful!!!"));
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.getMessage();
                    e.printStackTrace();
                }

                Platform.runLater(() -> {


                    try {
                        emdb.closeConnection();
                        Stage stage = (Stage) tf_username.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("../UI/mainMenu.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.setMaximized(true);
                        stage.show();
                    } catch (IOException e) {
                        e.getMessage();
                        e.printStackTrace();
                    }


                });

            }else{

                Platform.runLater(() -> prompText.setText("Cannot login!!"));
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }


}
