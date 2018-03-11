package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.EmployeesDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.Account;
import com.hardware.fiesta.Model.Employee;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;


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

    private UILoader uiLoader;


    public void initialize(){


    }

    @FXML
    public void loginButtonClicked(){

        String username = tf_username.getText().trim();
        String password = tf_password.getText().trim();
        Account account = new Account(username,password);

            emdb.openConnection();

            int accountNumber = emdb.searchAccount(username, account.getPassword());
            System.out.println(accountNumber);

            if ( accountNumber > 0) {


                prompText.setText("login Successful!!!");



                account = emdb.searchAccount(accountNumber);
                Employee employee = emdb.getEmployee(account.getEmpId());



                emdb.closeConnection();
                System.out.println(this.uiLoader);
                this.uiLoader.setLoginDetails(account,employee);
                this.uiLoader.getMainMenuController().setUiLoader(this.uiLoader);
                Stage stage = (Stage) tf_username.getScene().getWindow();
                stage.close();
                uiLoader.getMainMenuStage().show();

                prompText.setText("");
                tf_username.setText("");
                tf_password.setText("");


            }else{

               prompText.setText("Cannot login!!");
                emdb.closeConnection();

           }


        }




    public void setUiLoader(UILoader uiLoader){

        this.uiLoader = uiLoader;
        this.emdb = uiLoader.getEmdb();
    }


}
