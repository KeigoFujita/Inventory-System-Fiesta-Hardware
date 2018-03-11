package com.hardware.fiesta.Controller;


import com.hardware.fiesta.Database.EmployeesDatabaseConnector;
import com.hardware.fiesta.Model.Account;
import com.hardware.fiesta.Model.Employee;
import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;


public class AccountCreateFormController {

    @FXML
    private JFXTextField tf_username;
    @FXML
    private JFXPasswordField tf_password,tf_repassword;
    public Label promptText,emp_name;
    public JFXButton bt_addAccount;
    public JFXRadioButton rb_admin,rb_user;


    private static EmployeesDatabaseConnector emdb;
    private Employee employee;


    public void initialize(){

        bt_addAccount.setDisable(true);
        promptText.setText("");
        emdb = new EmployeesDatabaseConnector();
        emdb.openConnection();
        rb_user.setSelected(true);
    }

    public void addAccount(){


            String username = tf_username.getText().trim();
            String password = tf_repassword.getText().trim();
            String type = "";

            if(rb_admin.isSelected()){
                type = "ADMIN";
            }
            if(rb_user.isSelected()) {

                type = "USER";
            }

            Account account = new Account(username, password, type);


            Runnable runnable = () -> {

                try {

                if (emdb.searchAccount(username,password) < 0) {

                    emdb.addEmployee(employee);
                    emdb.addAccount(account, emdb.getEmployeeId(employee));

                    Platform.runLater(() -> {

                        promptText.setText("Account added Successfully!!!!");
                        tf_password.setText("");
                        tf_repassword.setText("");
                        tf_username.setText("");
                        bt_addAccount.setDisable(true);

                    });

                    Thread.sleep(500);

                    Platform.runLater(() -> {

                            emdb.closeConnection();
                            promptText.setText("Account added succesfully!!!!!");

                    });
                        Thread.sleep(1000);

                     Platform.runLater(() -> {
                    Stage stage = (Stage) promptText.getScene().getWindow();
                    stage.close();


                     }
                    );

                } else {

                    Platform.runLater(() -> promptText.setText("Account is already in the Database!!!"));

                }

            }catch(InterruptedException e){

                e.getMessage();
                e.printStackTrace();

               }


            };

            Thread thread  = new Thread(runnable);
            thread.start();


     }

    public void checkData(){

        if(tf_username.getText().trim().isEmpty()||
           tf_password.getText().trim().isEmpty()||
           tf_repassword.getText().trim().isEmpty()){

                bt_addAccount.setDisable(true);

        }else{


            bt_addAccount.setDisable(false);
        }

    }


    public void setName(String name){

        emp_name.setText(name);

    }

    public void goBack() throws IOException{

        Stage stage = (Stage) promptText.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../UI/registerForm.fxml"));
        stage.setScene(new Scene(root));
        stage.show();

        emdb.closeConnection();


    }

    @FXML
    private void checkPassword(){

        String password  = tf_password.getText();
        String repassword = tf_repassword.getText();

        if(!password.equals(repassword)){

            Platform.runLater(() -> {

                promptText.setText("Password does not match!!!");
                bt_addAccount.setDisable(true);

            }
        );

        }else {


            Platform.runLater(() -> {

                promptText.setText("Password Match!!!");
                bt_addAccount.setDisable(false);

            });

        }
    }

    public void setEmployeeId(Employee employee){

        this.employee = employee;
    }

}
