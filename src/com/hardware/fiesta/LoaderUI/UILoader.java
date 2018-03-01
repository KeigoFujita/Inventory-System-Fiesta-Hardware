package com.hardware.fiesta.LoaderUI;

import com.hardware.fiesta.Controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class UILoader {

    //DASH BOARD VIEW COMPONENTS
    private FXMLLoader              dashBoardLoader;
    private GridPane                dashBoardRootLayout;
    private DashboardFormController dashboardFormController;


    //EMPLOYEE VIEW COMPONENTS
    private FXMLLoader employeeViewloader;
    private VBox       employeeViewRootlayout;
    private EmployeesFormController employeesFormController;


    //ACCOUNTS VIEW COMPONENTS
    private FXMLLoader accountsViewLoader;
    private VBox       accountsViewRootLayout;
    private AccountsFormController accountsFormController;

    //EMPLOYEES INFORMATION VIEW COMPONENTS
    private FXMLLoader employeeInformationViewLoader;
    private GridPane   employeeInformationViewRootLayout;
    private EmployeeInformationFormController employeeInformationFormController;

    //UPDATE UPDATE EMPLOYEE VIEW COMPONENTS
    private FXMLLoader updateEmployeeViewLoader;
    private GridPane updateEmployeeViewRootLayout;
    private UpdateEmployeeIController updateEmployeeController;

    //UPDATE UPDATE ACCOOUNT COMPONENTS
    private FXMLLoader updateAccountViewLoader;
    private GridPane   updateAccountViewRootLayout;
    private UpdateAccountController updateAccountController;



    public UILoader(){

        System.out.println(this+" Instance from the UI LOADER Class");
        dashBoardLoader = new FXMLLoader(getClass().getResource("../UI/dashBoardForm.fxml"));
        employeeViewloader = new FXMLLoader(getClass().getResource("../UI/EmployeesForm.fxml"));
        accountsViewLoader = new FXMLLoader(getClass().getResource("../UI/AccountsForm.fxml"));
        employeeInformationViewLoader = new FXMLLoader(getClass().getResource("../UI/EmployeeInformationForm.fxml"));
        updateEmployeeViewLoader = new FXMLLoader(getClass().getResource("../UI/updateEmployeeForm.fxml"));
        updateAccountViewLoader = new FXMLLoader(getClass().getResource("../UI/UpdateAccountForm.fxml"));


        loadFXMLS();
        loadControllers();

    }


    private void loadFXMLS(){

        try {
            dashBoardRootLayout =  dashBoardLoader.load();
            employeeViewRootlayout = employeeViewloader.load();
            accountsViewRootLayout = accountsViewLoader.load();
            employeeInformationViewRootLayout = employeeInformationViewLoader.load();
            updateEmployeeViewRootLayout = updateEmployeeViewLoader.load();
            updateAccountViewRootLayout = updateAccountViewLoader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private void loadControllers(){
        dashboardFormController = dashBoardLoader.getController();
        employeesFormController = employeeViewloader.getController();
        accountsFormController = accountsViewLoader.getController();
        employeeInformationFormController = employeeInformationViewLoader.getController();
        updateEmployeeController = updateEmployeeViewLoader.getController();
        updateAccountController = updateAccountViewLoader.getController();

    }


    //GETTERS OF DASHBOARD VIEW COMPONENTS
    public GridPane getDashBoardRootLayout() {
        return dashBoardRootLayout;
    }
    public DashboardFormController getDashboardFormController() {
        return dashboardFormController;
    }


    //GETTERS OF EMPLOYEE VIEW COMPONENTS
    public VBox getEmployeeViewRootlayout() {
        return employeeViewRootlayout;
    }
    public EmployeesFormController getEmployeesFormController() {
        return employeesFormController;
    }

    //GETTERS OF ACCOUNTS VIEW COMPONENTS
    public VBox getAccountsViewRootLayout() {
        return accountsViewRootLayout;
    }
    public AccountsFormController getAccountsFormController() {
        return accountsFormController;
    }

    //GETTERS OF EMPLOYEE VIEW COMPONENTS
    public GridPane getEmployeeInformationViewRootLayout() {
        return employeeInformationViewRootLayout;
    }
    public EmployeeInformationFormController getEmployeeInformationFormController() {
        return employeeInformationFormController;
    }

    //GETTERS OF UPDATE EMPLOYEE VIEW COMPONENTS
    public GridPane getUpdateEmployeeViewRootLayout() {
        return updateEmployeeViewRootLayout;
    }
    public UpdateEmployeeIController getUpdateEmployeeController() {
        return updateEmployeeController;
    }

    //GETTERS OF ACCOUNT UPDATE EMPLOYEE VIEW COMPONENTS
    public GridPane getUpdateAccountViewRootLayout() {
        return updateAccountViewRootLayout;
    }
    public UpdateAccountController getUpdateAccountController() {
        return updateAccountController;
    }
}
