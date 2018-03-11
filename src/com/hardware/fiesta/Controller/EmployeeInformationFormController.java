package com.hardware.fiesta.Controller;


import com.hardware.fiesta.Database.EmployeesDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.Employee;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class EmployeeInformationFormController {

    //The Database Connection used in this Class
    private EmployeesDatabaseConnector emdb;

    //The root Layout of this FXML
    public VBox vb_EmpInformation;

    public Label lbl_firstName,lbl_lastName,lbl_id,lbl_birthday,lbl_address,lbl_contact,lbl_email,lbl_status,lbl_lastUpdated;


    private Employee employee;
    private VBox mainView;

    //The Accounts View Components needed
    private AccountsFormController accountsFormController;
    private VBox accountsFormRootLayout;

    //The Update Employee View Components needed
    private UpdateEmployeeIController updateEmployeeIController;
    private GridPane                  updateEmployeeRootLayout;


    public GridPane  viewEmployeeContainer;
    private UILoader uiLoader;

    public void initialize(){


    }

    //Display data in the Employees Information View
    public void displayData(){

        if(this.employee == null){

            lbl_id.setText("");
            lbl_firstName.setText("");
            lbl_lastName.setText("");
            lbl_birthday.setText("");
            lbl_address.setText("");
            lbl_contact.setText("");
            lbl_email.setText("");
            lbl_status.setText("");
            lbl_lastUpdated.setText("");




        }else{
           lbl_id.setText(Integer.toString(this.employee.getId()));
           lbl_firstName.setText(this.employee.getFirstName());
           lbl_lastName.setText(this.employee.getLastName().toUpperCase()+", ");
           lbl_birthday.setText(this.employee.getBirthDate());
           lbl_address.setText(this.employee.getAddress());
           lbl_contact.setText(this.employee.getContactNumber());
           lbl_email.setText(this.employee.getEmailAddress());
           lbl_status.setText(this.employee.getStatus());
           lbl_lastUpdated.setText(this.employee.getLastUpdated());
        }

         mainView.getChildren().clear();
         mainView.getChildren().add(vb_EmpInformation);

    }


    public void setEmployee(Employee employee){

        this.employee = employee;

    }

    public void setEmployee(int employeeID){


        emdb.openConnection();
        this.employee = emdb.getEmployee(employeeID);
        emdb.closeConnection();
    }
    public void setEmdb(EmployeesDatabaseConnector emdb){

        this.emdb = emdb;
    }
    public void setUILoader(UILoader uiLoader){

        this.uiLoader = uiLoader;
        System.out.println(uiLoader+" UI LOADER FROM THE EMPLOYEE INFORMATION CONTROLLER CLASS");

        this.accountsFormRootLayout = this.uiLoader.getAccountsViewRootLayout();
        this.accountsFormController = this.uiLoader.getAccountsFormController();

        this.updateEmployeeRootLayout = this.uiLoader.getUpdateEmployeeViewRootLayout();
        this.updateEmployeeIController = this.uiLoader.getUpdateEmployeeController();

        this.mainView = uiLoader.getMainMenuController().mainViewContainer;
    }


    public void buttonManageAction(){


        accountsFormController.setUILoader(this.uiLoader);
        accountsFormController.displayAccounts(employee.getId());


        //clearing the Main View Container and setting it with the Accounts View
        mainView.getChildren().clear();
        mainView.getChildren().add(accountsFormRootLayout);

    }

    public void buttonEditEmployeeAction(){


          updateEmployeeIController.setEmdb(this.emdb);
          updateEmployeeIController.setUiLoader(this.uiLoader);
          updateEmployeeIController.setMainView(mainView);
          updateEmployeeIController.setEmployee(employee);
          updateEmployeeIController.setDataOnUI();


      //clearing the main View and adding it with the Edit Employee Gridpane
        mainView.getChildren().clear();
        mainView.getChildren().add(updateEmployeeRootLayout);


    }

}
