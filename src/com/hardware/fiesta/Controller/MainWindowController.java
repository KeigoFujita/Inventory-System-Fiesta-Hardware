package com.hardware.fiesta.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {

    @FXML
    private JFXButton bt_manage;

    @FXML
    private void manageAccounts() throws IOException{


        Stage stage = (Stage) bt_manage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../UI/employeesDatabaseForm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.show();


    }


}
