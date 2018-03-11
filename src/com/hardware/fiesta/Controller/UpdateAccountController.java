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
import javafx.scene.layout.VBox;

public class UpdateAccountController {

    @FXML
    private Label lbl_employeeName;

    @FXML
    private Label lbl_prompText;



    @FXML
    private JFXTextField tf_userName;

    @FXML
    private JFXPasswordField tf_oldPassword;

    @FXML
    private JFXPasswordField tf_newPassword;

    @FXML
    private JFXPasswordField tf_reEnterPassword;

    @FXML
    private JFXButton bt_UpdateAccount;

    private VBox mainView;
    private UILoader uiLoader;
    private EmployeesDatabaseConnector emdb;
    private Employee employee;
    private Account oldAccount;


    public void initialize(){



    }


    public void setDataOnUI(){

        lbl_prompText.setText("");
        lbl_employeeName.setText(employee.getFirstName());
        tf_userName.setText(oldAccount.getUsername());
        bt_UpdateAccount.setDisable(true);

    }



    public void buttonUpdateAccountAction(){

        String userName = tf_userName.getText();
        String oldPassword = tf_oldPassword.getText();
        String newPassword = tf_reEnterPassword.getText();

        Account account = new Account(userName,oldPassword);

        emdb.openConnection();

        System.out.println(emdb.searchAccount(userName,oldPassword));

        if(emdb.searchAccount(oldAccount.getUsername(),account.getPassword())> 0){

            Account newAccount = new Account(userName,newPassword,this.oldAccount.getType());

            emdb.updateAccount(this.oldAccount, newAccount);

            lbl_prompText.setText("The account is updated Successfully!!1!");
            tf_userName.setText("");
            tf_oldPassword.setText("");
            tf_newPassword.setText("");
            tf_reEnterPassword.setText("");

        }else{
            lbl_prompText.setText("The old password is not correct!!!");
        }

        emdb.closeConnection();
    }
    public void buttonReEnterPasswordAction(){

        String password = tf_newPassword.getText().trim();
        String rePassword = tf_reEnterPassword.getText().trim();

        if(password.isEmpty() | rePassword.isEmpty()){


            bt_UpdateAccount.setDisable(true);
        }else{

            if(!rePassword.equals(password)){

                bt_UpdateAccount.setDisable(true);

            }else{

                bt_UpdateAccount.setDisable(false);
            }

        }


    }




    public void setMainView(VBox mainView) {
        this.mainView = mainView;
    }

    public void setUiLoader(UILoader uiLoader) {
        this.uiLoader = uiLoader;
    }

    public void setEmdb(EmployeesDatabaseConnector emdb) {
        this.emdb = emdb;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setAccount(Account account) {
        this.oldAccount = account;
    }
}
