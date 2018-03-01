package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.EmployeesDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.Employee;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class EmployeesFormController {

    // Employeees Database Connector used
    private EmployeesDatabaseConnector emdb;

    //Observable List of Employee
    private ObservableList<Employee> employees;

    //Table View of Employees
    public TableView<Employee> tv_employees;

    //Table Column of Table Employees
    public TableColumn<Employee,String> tc_emp_first,tc_emp_middle,tc_emp_last,tc_emp_birth,tc_emp_address,tc_emp_number,tc_emp_email,tc_emp_status,tc_emp_last_updated;
    public TableColumn<Employee, Integer> tc_emp_id;

    //Textfield used for Searching
    @FXML
    private JFXTextField tf_search;

    @FXML
    private JFXRadioButton rb_viewAll;

    @FXML
    private JFXRadioButton rb_viewEnabled;

    @FXML
    private JFXRadioButton rb_viewDisabled;

    private ContextMenu  emp_context = new ContextMenu();

    private MenuItem view_employee = new MenuItem("View Employee");
    private MenuItem disable_employee = new MenuItem("Disable Employee");
    private MenuItem enable_employee = new MenuItem("Enable Employee");

    private EmployeeInformationFormController employeeInformationFormController;

    private VBox mainView;

    private UILoader uiLoader;

    public void initialize(){

        setActionOnDoubleClickOnTableRow();


    }

    //Get Data from the database and load it in the FX Observable list
    private void loadDatainObservableList(boolean isEnabled){

        emdb.openConnection();
        employees = FXCollections.observableArrayList(this.emdb.getEmployeeList(isEnabled));
        emdb.closeConnection();

    }
    private void loadDatainObservableList(){

        emdb.openConnection();
        employees = FXCollections.observableArrayList(this.emdb.getEmployeeList());
        emdb.closeConnection();

    }

    //Display the data in the Table View
    public void displayEmployees(boolean isEnabled){


        System.out.println(emdb);
        loadDatainObservableList(isEnabled);
        setValuesToTableView();
        setContextMenuOnTableView();

        if(isEnabled){
            emp_context.getItems().remove(enable_employee);
            rb_viewEnabled.setSelected(true);
        }else {
            emp_context.getItems().remove(disable_employee);
            rb_viewDisabled.setSelected(true);
        }

    }

    private void displayEmployees(){

        System.out.println(emdb);
        loadDatainObservableList();
        setValuesToTableView();
        setContextMenuOnTableView();
        rb_viewAll.setSelected(true);


    }
    //Setting the values to the Table View
    //load all of the data in the employees Observable List
    private void setValuesToTableView(){

            tc_emp_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            tc_emp_first.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            tc_emp_middle.setCellValueFactory(new PropertyValueFactory<>("middleName"));
            tc_emp_last.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            tc_emp_birth.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
            tc_emp_address.setCellValueFactory(new PropertyValueFactory<>("address"));
            tc_emp_number.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
            tc_emp_email.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
            tc_emp_status.setCellValueFactory(new PropertyValueFactory<>("status"));
            tc_emp_last_updated.setCellValueFactory(new PropertyValueFactory<>("lastUpdated"));

            tv_employees.setItems(employees);

        }


    //Setting the values to the Table View
    //Parameter: Observable List
    private void setValuesToTableView(ObservableList<Employee> empList){

        tc_emp_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc_emp_first.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tc_emp_middle.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        tc_emp_last.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tc_emp_birth.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        tc_emp_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        tc_emp_number.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        tc_emp_email.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        tc_emp_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        tc_emp_last_updated.setCellValueFactory(new PropertyValueFactory<>("lastUpdated"));

        tv_employees.setItems(empList);

    }


    public void btAddEmployeeAction() throws IOException{


        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../UI/registerForm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();


        displayEmployees();

    }
    public void radioButtonViewEmployees(ActionEvent e){

        if(e.getSource()== rb_viewAll){
             displayEmployees();

        }else if(e.getSource() == rb_viewEnabled){
            displayEmployees(true);

        }else{
            displayEmployees(false);


        }



    }
    public void tfSearchAction(){

        String newValue = tf_search.getText();
        FilteredList<Employee> filteredData = new FilteredList<>(employees);

        filteredData.setPredicate(employee -> {

          if(newValue == null|| newValue.isEmpty()){

              return true;
            }

            // Compare first, middle last name of every person with filter text.

            String lowerCaseFilter = newValue.toLowerCase();

            return employee.getFirstName().toLowerCase().contains(lowerCaseFilter)
                || employee.getLastName().toLowerCase().contains(lowerCaseFilter)
                || employee.getMiddleName().toLowerCase().contains(lowerCaseFilter);

        });

        SortedList<Employee> sortedList = new SortedList<>(filteredData);
        setValuesToTableView(FXCollections.observableArrayList(sortedList));

        setContextMenuOnTableView();


    }

    private void setContextMenuOnTableView(){




        view_employee.setOnAction((event) -> {

            Employee employee = tv_employees.getSelectionModel().getSelectedItem();

            if(employee != null){
                viewEmployee(employee);

            }
        });

        disable_employee.setOnAction(event -> {

            Employee employee = tv_employees.getSelectionModel().getSelectedItem();

            if(employee != null) {
                disableEmployee(employee);
            }

        });

        enable_employee.setOnAction(event -> {


            Employee employee = tv_employees.getSelectionModel().getSelectedItem();
            if(employee != null) {
                enableEmployee(employee);
            }

        });



        emp_context.getItems().add(view_employee);
        emp_context.getItems().add(disable_employee);
        emp_context.getItems().add(enable_employee);
        tv_employees.setContextMenu(emp_context);


    }
    private void setActionOnDoubleClickOnTableRow(){

        tv_employees.setRowFactory( tv -> {
            TableRow<Employee> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Employee rowData = row.getItem();
                    viewEmployee(rowData);
                }
            });
            return row;
        });


    }


    private void viewEmployee(Employee employee){


      employeeInformationFormController.setEmdb(emdb);
      employeeInformationFormController.setUILoader(this.uiLoader);
      employeeInformationFormController.setEmployee(employee);
      employeeInformationFormController.setMainView(mainView);
      employeeInformationFormController.displayData();


    }
    private void disableEmployee(Employee employee){

        emdb.openConnection();
        emdb.disableEmployee(employee.getId());
        emdb.closeConnection();

        displayEmployees(true);

    }
    private void enableEmployee(Employee employee){

        emdb.openConnection();
        emdb.enableEmployee(employee.getId());
        emdb.closeConnection();

        displayEmployees(false);

    }




   /*/
   * Setters of this Class
   * */
    public void setMainView(VBox mainView){

        this.mainView = mainView;

    }
    public void setEmdb(EmployeesDatabaseConnector emdb){

        this.emdb = emdb;


    }
    public void setUiLoader(UILoader uiLoader){
        this.uiLoader = uiLoader;
        System.out.println(uiLoader+" UI LOADER FROM the Employees Form Controller Class");

        this.employeeInformationFormController = this.uiLoader.getEmployeeInformationFormController();




    }









}


