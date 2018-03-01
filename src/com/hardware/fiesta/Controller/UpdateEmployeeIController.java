package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.EmployeesDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.Employee;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class UpdateEmployeeIController {

    @FXML
    private JFXTextField tf_lastName;

    @FXML
    private JFXTextField tf_middleName;

    @FXML
    private JFXTextField tf_firstName;

    @FXML
    private JFXTextField tf_address;

    @FXML
    private JFXTextField tf_contact;

    @FXML
    private JFXTextField tf_birthDate;

    @FXML
    private JFXTextField tf_emailAddress;

    @FXML
    private Label prompText;


    private Employee employee;
    private EmployeesDatabaseConnector emdb;


    private EmployeeInformationFormController employeeInformationFormController;
    private GridPane                          employeeInformationViewRootLayout;


    private VBox mainView;

    private UILoader uiLoader;


    public void initialize(){

    }





    public void setDataOnUI(){


       prompText.setText("");
       tf_firstName.setText(employee.getFirstName());
       tf_middleName.setText(employee.getMiddleName());
       tf_lastName.setText(employee.getLastName());
       tf_address.setText(employee.getAddress());
       tf_contact.setText(employee.getContactNumber());
       tf_birthDate.setText(employee.getBirthDate());
       tf_emailAddress.setText(employee.getEmailAddress());

    }


    public void buttonUpdateDataAction() {

           String firstName = tf_firstName.getText();
           String middleName = tf_middleName.getText();
           String lastName = tf_lastName.getText();
           String address   = tf_address.getText();
           String contactNumber  = tf_contact.getText();
           String birthDate      = tf_birthDate.getText();
           String emailAddress   = tf_emailAddress.getText();

           Employee newEmployee = new Employee(firstName,middleName,lastName,address,contactNumber,birthDate,emailAddress);


        if(

           this.employee.getFirstName().equals(newEmployee.getFirstName()) &&
           this.employee.getMiddleName().equals(newEmployee.getMiddleName()) &&
           this.employee.getLastName().equals(newEmployee.getLastName()) &&
           this.employee.getAddress().equals(newEmployee.getAddress()) &&
           this.employee.getContactNumber().equals(newEmployee.getContactNumber()) &&
           this.employee.getBirthDate().equals(newEmployee.getBirthDate()) &&
           this.employee.getEmailAddress().equals(newEmployee.getEmailAddress())

          ){

            prompText.setText("The data is not Changed!!!");

        }else{

            emdb.openConnection();

            if(emdb.updateEmployee(this.employee, newEmployee)){

                prompText.setText("Updated Data Successfully!!");



                employeeInformationFormController.setEmployee(emdb.getEmployee(this.employee.getId()));
                employeeInformationFormController.displayData();
                emdb.closeConnection();

                mainView.getChildren().add(employeeInformationViewRootLayout);



            }else{
                prompText.setText("Cannot update Data !!!");
                emdb.closeConnection();
            }


        }

    }


    public void setEmdb(EmployeesDatabaseConnector emdb) {
        this.emdb = emdb;
    }
    public void setMainView(VBox mainView) {
        this.mainView = mainView;
    }
    public void setUiLoader(UILoader uiLoader) {
        this.uiLoader = uiLoader;

        this.employeeInformationViewRootLayout = this.uiLoader.getEmployeeInformationViewRootLayout();
        this.employeeInformationFormController = this.uiLoader.getEmployeeInformationFormController();

    }
    public void setEmployee(Employee employee){

        this.employee = employee;

    }

}
