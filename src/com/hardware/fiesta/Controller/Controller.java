package com.hardware.fiesta.Controller;

import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    Stage primaryStage;


public void clicked() throws IOException{

    Platform.exit();
//    FXMLLoader loginXml = new FXMLLoader(getClass().getResource("../UI/loginForm.fxml"));
//    Parent loginForm =loginXml.load();
//    Scene loginForm_scene = new Scene (loginForm,600,400);
//    primaryStage.setTitle("Hello World");
//    primaryStage.setScene(loginForm_scene);
//    primaryStage.setResizable(false);
//    primaryStage.show();


}

public void setPrimaryStage(Stage primaryStage){

    this.primaryStage = primaryStage;

}



}
