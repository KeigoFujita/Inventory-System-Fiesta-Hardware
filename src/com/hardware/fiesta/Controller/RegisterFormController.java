 package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.EmployeesDatabaseConnector;
import com.hardware.fiesta.Model.Employee;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;


public class RegisterFormController {

    @FXML
    public JFXTextField tf_firstName,tf_middleName,tf_lastName,tf_birthDate,tf_address,tf_contactNumber,tf_emailAddress,
                        tf_id;

    @FXML
    public Label promptText;
    public JFXButton bt_id,setUpAccount,bt_addEmployee;

    private static EmployeesDatabaseConnector empDatabase;
    private Employee employee;



    public void initialize(){

    promptText.setText("");
    empDatabase = new EmployeesDatabaseConnector();

    bt_id.setDisable(true);
    setUpAccount.setDisable(true);
    bt_addEmployee.setDisable(true);


    }


@FXML
    private void addEmployee(){

            empDatabase.openConnection();

            String firstName = tf_firstName.getText();
            String middleName = tf_middleName.getText();
            String lastName = tf_lastName.getText();
            String birthDate = tf_birthDate.getText();
            String addresss = tf_address.getText();
            String contactNumber = tf_contactNumber.getText();
            String emailAddress = tf_emailAddress.getText();

            employee = new Employee(firstName, middleName, lastName, addresss, contactNumber, birthDate, emailAddress);


            Runnable runnable = () -> {

                try {
                    System.out.println(employee);
                    System.out.println(!empDatabase.searchEmployee(employee));
                    if (!empDatabase.searchEmployee(employee)) {

                        Platform.runLater(() -> {
                                empDatabase.closeConnection();
                                promptText.setText("Register Successful!!!");
                    });

                        Thread.sleep(500);
                        Platform.runLater(() -> {
                            try {

                                setUpAccount();

                            } catch (IOException e) {

                                e.getMessage();
                            }

                        });

                    } else {
                        Platform.runLater(() -> {
                            promptText.setText("Employee is already in the Database!!!!");
                            empDatabase.closeConnection();
                        });
                    }

                } catch (InterruptedException e) {

                    e.getMessage();
                }
            };

            Thread thread = new Thread(runnable);


            thread.start();

}


     public void searchID(){

        empDatabase.openConnection();

        int id = Integer.parseInt(tf_id.getText());

        Employee employee = empDatabase.getEmployee(id);

        if(employee!=null) {

            Platform.runLater(() -> {

                promptText.setText("");
                tf_firstName.setText(employee.getFirstName());
                tf_lastName.setText(employee.getLastName());
                tf_middleName.setText(employee.getMiddleName());
                tf_birthDate.setText(employee.getBirthDate());
                tf_address.setText(employee.getAddress());
                tf_contactNumber.setText(employee.getContactNumber());
                tf_emailAddress.setText(employee.getEmailAddress());
                setUpAccount.setDisable(false);

                empDatabase.closeConnection();

            });
        }else{

            empDatabase.openConnection();

            promptText.setText("Cannot find the Employee!!!!!");
            setUpAccount.setDisable(true);

            tf_firstName.setText("");
            tf_lastName.setText("");
            tf_middleName.setText("");
            tf_birthDate.setText("");
            tf_address.setText("");
            tf_contactNumber.setText("");
            tf_emailAddress.setText("");

            empDatabase.closeConnection();
        }



     }

    public void searchIDText() {

        String id = tf_id.getText();

        if (id.trim().isEmpty()) {

            bt_id.setDisable(true);

        }else{

            bt_id.setDisable(false);
        }
    }


    public void setUpAccount() throws IOException{

        String firstName = tf_firstName.getText();
        String middleName    =tf_middleName.getText();
        String lastName  = tf_lastName.getText();
        String birthDate  = tf_birthDate.getText();
        String addresss = tf_address.getText();
        String contactNumber = tf_contactNumber.getText();
        String emailAddress = tf_emailAddress.getText();

        employee = new Employee(firstName,middleName,lastName,addresss,contactNumber,birthDate,emailAddress);

        empDatabase.closeConnection();

        Stage stage = (Stage) promptText.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../UI/accountCreateForm.fxml"));
        Parent root = loader.load();
        AccountCreateFormController controller = loader.getController();
        controller.setName(tf_firstName.getText());
        controller.setEmployeeId(employee);
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("ACCOUNT SETUP");
        stage.show();

    }

    public void checkInputtedData() {


        if (tf_firstName.getText().trim().isEmpty() ||
                tf_middleName.getText().trim().isEmpty() ||
                tf_lastName.getText().trim().isEmpty() ||
                tf_birthDate.getText().trim().isEmpty() ||
                tf_address.getText().trim().isEmpty() ||
                tf_contactNumber.getText().trim().isEmpty() ||
                tf_emailAddress.getText().trim().isEmpty()) {

            bt_addEmployee.setDisable(true);
            promptText.setText("No value given!!!!");

        }else{

            bt_addEmployee.setDisable(false);
            promptText.setText("");

        }
    }
}


