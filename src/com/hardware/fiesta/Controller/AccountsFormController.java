package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.EmployeesDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.Account;
import com.hardware.fiesta.Model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class AccountsFormController {


    //The database connection used

    private EmployeesDatabaseConnector emdb;
    //Observable List of Accounts
    private static ObservableList<Account> accounts;

    //Table View of Accounts
    public TableView<Account> tv_accounts;

    //Table Columns of Accounts Table View

    public TableColumn <Account,String> tc_acc_username,tc_acc_password,tc_acc_type,tc_acc_lastlogin,tc_acc_status;
    public TableColumn<Account, Integer> tc_acc_id,tc_acc_emp_id;

    private int empId;
    private UILoader uiLoader;
    private EmployeeInformationFormController employeeInformationFormController;
    private GridPane employeeInformationViewRootLayout;
    private VBox mainView;

    //Context Menu used in the Table View
    private ContextMenu emp_context = new ContextMenu();
    private MenuItem view_employee = new MenuItem("View Employee");
    private MenuItem edit_account = new MenuItem("Edit Account");



    private void loadDataInObservableList(boolean isDecryted){

        emdb.openConnection();
        accounts = FXCollections.observableArrayList(this.emdb.getAccountList(isDecryted));
        emdb.closeConnection();

    }

    private void loadDataInObservableList(int empId, boolean isDecrypted){

        emdb.openConnection();
        accounts = FXCollections.observableArrayList(emdb.getAccountList(empId,isDecrypted));
        emdb.closeConnection();

    }



    public void displayAccounts(int empId){


        System.out.println(emdb);
        this.empId = empId;
        loadDataInObservableList(this.empId,false);
        loadAccountsDatabase();

    }
    public void displayAccounts(){

        System.out.println(emdb);
        this.empId = 0;
        loadDataInObservableList(false);
        loadAccountsDatabase();
        setContextMenuOnTableView();

    }

    private void loadAccountsDatabase(){

            tc_acc_emp_id.setCellValueFactory(new PropertyValueFactory<>("empId"));
            tc_acc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            tc_acc_username.setCellValueFactory(new PropertyValueFactory<>("username"));
            tc_acc_password.setCellValueFactory(new PropertyValueFactory<>("password"));
            tc_acc_type.setCellValueFactory(new PropertyValueFactory<>("type"));
            tc_acc_lastlogin.setCellValueFactory(new PropertyValueFactory<>("lastlogin"));
            tc_acc_status.setCellValueFactory(new PropertyValueFactory<>("status"));

            tv_accounts.setItems(accounts);

    }

    public void decryptPassword(){


        if(this.empId == 0){

            loadDataInObservableList(true);
            loadAccountsDatabase();

        }else{
            loadDataInObservableList(this.empId,true);
            loadAccountsDatabase();
        }


    }

    public void encryptPassword(){


        if(this.empId == 0){

            loadDataInObservableList(false);
            loadAccountsDatabase();

        }else{
            loadDataInObservableList(this.empId,false);
            loadAccountsDatabase();
        }
    }

   public void setEmdb(EmployeesDatabaseConnector emdb){
        this.emdb = emdb;
   }
   public void setUILoader(UILoader uiLoader){
        this.uiLoader = uiLoader;

        this.employeeInformationViewRootLayout = uiLoader.getEmployeeInformationViewRootLayout();
        this.employeeInformationFormController = uiLoader.getEmployeeInformationFormController();
        this.mainView = uiLoader.getMainMenuController().mainViewContainer;


   }



   private void setContextMenuOnTableView() {


        view_employee.setOnAction((event) -> {

            Account account = tv_accounts.getSelectionModel().getSelectedItem();
            //System.out.println(account);
            //System.out.println(account.getEmpId());
            viewEmployee(account);

        });

        edit_account.setOnAction((event) -> {

           Account account = tv_accounts.getSelectionModel().getSelectedItem();
           //System.out.println(account);
           //System.out.println(account.getEmpId());
          // viewEmployee(account);

            UpdateAccountController updateAccountController = uiLoader.getUpdateAccountController();
            updateAccountController.setUiLoader(uiLoader);
            updateAccountController.setMainView(mainView);
            updateAccountController.setEmdb(emdb);

            emdb.openConnection();
            Employee employee = emdb.getEmployee(account.getEmpId());
            emdb.closeConnection();


            updateAccountController.setEmployee(employee);
            updateAccountController.setAccount(account);
            updateAccountController.setDataOnUI();


            mainView.getChildren().clear();
            mainView.getChildren().add(uiLoader.getUpdateAccountViewRootLayout());

       });

        emp_context.getItems().add(view_employee);
        emp_context.getItems().add(edit_account);

        tv_accounts.setContextMenu(emp_context);
    }

    private void viewEmployee(Account account){

        employeeInformationFormController.setEmdb(this.emdb);
        employeeInformationFormController.setUILoader(this.uiLoader);
        employeeInformationFormController.setEmployee(account.getEmpId());
        employeeInformationFormController.displayData();

    }




}
