package com.hardware.fiesta.Controller;


import com.hardware.fiesta.LoaderUI.UILoader;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainMenuController {


    //Root Element of the mainMenuForm
    public GridPane gridPane;

    //GridPane Button of for Styling purposes
    public GridPane gp_employees,gp_accounts,gp_dashBoard,gp_stocks,gp_inventory,gp_sales;

    //GridPane Button that is currently Active
    private GridPane gp_button_active;




    //Container holding the Root Elements in Dashboard, Stocks, Inventory etc.
    public VBox mainViewContainer;

    private UILoader uiLoader;

    //UI OBJECT OF DASHBOARD VIEW
    private VBox dashBoardGridPane;
    private DashboardFormController dashboardFormController;

    //UI OBJECT OF EMPLOYEES VIEW
    private VBox employeeVBox;
    private EmployeesFormController employeesFormController;

    //UI OBJECT OF ACCOUNTS VIEW
    private VBox accountsVBox;
    private AccountsFormController accountsFormController;


    public Label lbl_signedInAs;

    @FXML
    private Label lbl_emp_name;

    @FXML
    private Label lblTitleHead;


    public void initialize(){


    }


    @FXML
    private void buttonLogoutAction(){

        setOnFocusedStyleForButtons(gp_dashBoard);
        uiLoader.getMainMenuStage().close();
        uiLoader.getLoginFormController().setUiLoader(this.uiLoader);
        uiLoader.getLoginStage().show();

    }


    @FXML
    private void dashBoardAction(){

        setOnFocusedStyleForButtons(gp_dashBoard);
        setDashboardOnDisplay();
        lblTitleHead.setText("Dashboard");

    }

    @FXML
    private void stockAction(){

        setOnFocusedStyleForButtons(gp_stocks);

        uiLoader.getStocksMainViewController().setUILoader(this.uiLoader);
        uiLoader.getStocksMainViewController().displayData();

        mainViewContainer.getChildren().clear();
        mainViewContainer.setAlignment(Pos.TOP_CENTER);
        mainViewContainer.getChildren().add(uiLoader.getStocksMainViewRootLayout());

        lblTitleHead.setText("Stocks");

    }

    @FXML
    private void inventoryAction(){

        uiLoader.getInventoryMainViewController().setUiLoader(this.uiLoader);
        uiLoader.getInventoryMainViewController().viewComboBox.getSelectionModel().select(0);

        mainViewContainer.getChildren().clear();
        mainViewContainer.getChildren().add(uiLoader.getInventoryMainViewMainRootLayout());
        setOnFocusedStyleForButtons(gp_inventory);

        lblTitleHead.setText("Inventory");
    }

    @FXML
    private void salesAction(){

        uiLoader.getSalesTableViewController().setUiLoader(this.uiLoader);

        mainViewContainer.getChildren().clear();
        mainViewContainer.getChildren().add(uiLoader.getSalesTableViewRootLayout());

        setOnFocusedStyleForButtons(gp_sales);
        lblTitleHead.setText("Sales");


    }

    @FXML
    private void employeesAction(){


       setOnFocusedStyleForButtons(gp_employees);
       setTableViewEmployees();

        lblTitleHead.setText("Employees");
    }

    @FXML
    private void accountsAction(){

        setOnFocusedStyleForButtons(gp_accounts);
        setTableViewAccounts();

        lblTitleHead.setText("Accounts");
    }

    @FXML
    private void generateReportsAction(){

        System.out.println("Clicked generate Reports!!!");
        lblTitleHead.setText("Generate Reports");

    }

    @FXML
    private void settingsAction(){

       System.out.println("clicked settings!!!");
        lblTitleHead.setText("Settings");

    }



    private void setTableViewEmployees(){


        employeesFormController.setUiLoader(this.uiLoader);
        employeesFormController.displayEmployees(true);


       //setting the VBox view from EmployeeTableView.fxml in the VBox Layout
      mainViewContainer.getChildren().clear();
      mainViewContainer.getChildren().add(employeeVBox);

    }
    private void setTableViewAccounts(){

        accountsFormController.setUILoader(this.uiLoader);
        accountsFormController.displayAccounts();


        //setting the Table View in the VBox Layout
        mainViewContainer.getChildren().clear();
        mainViewContainer.getChildren().add(accountsVBox);

    }
    private void setDashboardOnDisplay(){


        System.out.println(dashboardFormController);
        dashboardFormController.setUiLoader(this.uiLoader);
        dashboardFormController.setLabel();

        //setting the Dashboard View in the Main View Container
        mainViewContainer.getChildren().clear();
        mainViewContainer.getChildren().add(dashBoardGridPane);


    }
    private void setOnFocusedStyleForButtons(GridPane gp){

        gp_button_active.getStyleClass().clear();
        gp_button_active.getStyleClass().add("buttonStyle");
        gp.getStyleClass().clear();
        gp.getStyleClass().add("buttonFocused");
        gp_button_active = gp;
    }


    public void setUiLoader(UILoader uiLoader){
        this.uiLoader = uiLoader;
        System.out.println(uiLoader+" UI LOADER FROM the Main Menu Controller");

        dashBoardGridPane = uiLoader.getDashBoardRootLayout();
        dashboardFormController = uiLoader.getDashboardFormController();
        System.out.println(uiLoader.getDashboardFormController());

        employeeVBox = uiLoader.getEmployeeViewRootlayout();
        employeesFormController = uiLoader.getEmployeesFormController();

        accountsVBox = uiLoader.getAccountsViewRootLayout();
        accountsFormController = uiLoader.getAccountsFormController();


        //SETTING THE DASH BOARD AS FIRST DISPLAY
        setDashboardOnDisplay();
        lbl_signedInAs.setText("Signed in as "+this.uiLoader.getLoginAccount().getType().toLowerCase());
        lbl_emp_name.setText(this.uiLoader.getLoginEmployee().getFirstName()+" "+this.uiLoader.getLoginEmployee().getLastName());

        //SETTING THE CURRENT ACTIVE BUTTON
        gp_button_active = gp_dashBoard;
        setOnFocusedStyleForButtons(gp_dashBoard);

    }



}
