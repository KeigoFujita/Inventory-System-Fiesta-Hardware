package com.hardware.fiesta.Controller;


import com.hardware.fiesta.Database.EmployeesDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainMenuController {

    //Employees Database Connection used in the system
    private EmployeesDatabaseConnector emdb;


    //Root Element of the mainMenuForm
    public GridPane gridPane;

    //Gridpane Button of for Styling purposes
    public GridPane gp_employees,gp_accounts,gp_dashBoard;

    //GridPane Button that is currently Active
    private GridPane gp_button_active;



    //Container holding the Root Elements in Dashboard, Stocks, Inventory etc.
    public VBox mainViewContainer;

    private UILoader uiLoader;

    //UI OBJECT OF DASHBOARD VIEW
    private GridPane dashBoardGridPane;
    private DashboardFormController dashboardFormController;

    //UI OBJECT OF EMPLOYEES VIEW
    private VBox  employeeVbox;
    private EmployeesFormController employeesFormController;

    //UI OBJECT OF ACCOUNTS VIEW
    private VBox  accountsVbox;
    private AccountsFormController accountsFormController;





    public void initialize(){

        emdb = new EmployeesDatabaseConnector();
        uiLoader = new UILoader();

        System.out.println(uiLoader+" UI LOADER FROM the Main Menu Controller");


        dashBoardGridPane = uiLoader.getDashBoardRootLayout();
        dashboardFormController = uiLoader.getDashboardFormController();
        dashboardFormController.setLabel("Hello Words");

        employeeVbox            = uiLoader.getEmployeeViewRootlayout();
        employeesFormController = uiLoader.getEmployeesFormController();

        accountsVbox = uiLoader.getAccountsViewRootLayout();
        accountsFormController = uiLoader.getAccountsFormController();


       //SETTING THE DASH BOARD AS FIRST DISPLAY
       setDashboardOnDisplay();

       //SETTING THE CURRENT ACTIVE BUTTON
       gp_button_active = gp_dashBoard;
       setOnFocusedStyleforButtons(gp_dashBoard);


    }

    public void dashBoardAction(){

        setOnFocusedStyleforButtons(gp_dashBoard);
        setDashboardOnDisplay();

    }
    public void stockAction(){

    }
    public void salesAction(){

        System.out.println("clicked sales!!!");
    }
    public void employeesAction(){

       setOnFocusedStyleforButtons(gp_employees);
       setTableViewEmployees();
    }
    public void accountsAction(){

        setOnFocusedStyleforButtons(gp_accounts);
        setTableViewAccounts();
    }
    public void generateReportsAction(){

        System.out.println("Clicked generarte Reports!!!");
    }
    public void settingsAction(){

        System.out.println("clicked settings!!!");
    }



    private void setTableViewEmployees(){


        employeesFormController.setUiLoader(this.uiLoader);
        employeesFormController.setMainView(mainViewContainer);
        employeesFormController.setEmdb(emdb);
        employeesFormController.displayEmployees(true);


        //setting the VBox view from EmployeeTableView.fxml in the Vbox Layout
        mainViewContainer.getChildren().clear();
        mainViewContainer.getChildren().add(employeeVbox);



    }
    private void setTableViewAccounts(){


        accountsFormController.setEmdb(emdb);
        accountsFormController.setUILoader(this.uiLoader);
        accountsFormController.setMainView(this.mainViewContainer);
        accountsFormController.displayAccounts();


        //setting the Table View in the Vbox Layout
        mainViewContainer.getChildren().clear();
        mainViewContainer.getChildren().add(accountsVbox);

    }
    private void setDashboardOnDisplay(){

        //setting the Dashboard View in the Main View Container
        mainViewContainer.getChildren().clear();
        mainViewContainer.getChildren().add(dashBoardGridPane);


    }
    private void setOnFocusedStyleforButtons(GridPane gp){

        gp_button_active.getStyleClass().clear();
        gp_button_active.getStyleClass().add("buttonStyle");
        gp.getStyleClass().clear();
        gp.getStyleClass().add("buttonFocused");
        gp_button_active = gp;
    }






}
