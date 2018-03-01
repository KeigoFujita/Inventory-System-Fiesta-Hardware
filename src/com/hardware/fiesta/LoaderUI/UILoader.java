package com.hardware.fiesta.LoaderUI;

import com.hardware.fiesta.Controller.*;
import com.hardware.fiesta.Database.EmployeesDatabaseConnector;
import com.hardware.fiesta.Database.StockDatabaseConnector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UILoader {

    private EmployeesDatabaseConnector emdb;
    private StockDatabaseConnector stdb;

    //MAIN MENU VIEW COMPONENTS
    private FXMLLoader mainMenuViewLoader;
    private GridPane   mainMenuViewRootLayout;
    private MainMenuController mainMenuController;


    //DASH BOARD VIEW COMPONENTS
    private FXMLLoader dashBoardLoader;
    private GridPane dashBoardRootLayout;
    private DashboardFormController dashboardFormController;


    //EMPLOYEE VIEW COMPONENTS
    private FXMLLoader employeeViewloader;
    private VBox employeeViewRootlayout;
    private EmployeesFormController employeesFormController;


    //ACCOUNTS VIEW COMPONENTS
    private FXMLLoader accountsViewLoader;
    private VBox accountsViewRootLayout;
    private AccountsFormController accountsFormController;

    //EMPLOYEES INFORMATION VIEW COMPONENTS
    private FXMLLoader employeeInformationViewLoader;
    private GridPane employeeInformationViewRootLayout;
    private EmployeeInformationFormController employeeInformationFormController;

    //UPDATE UPDATE EMPLOYEE VIEW COMPONENTS
    private FXMLLoader updateEmployeeViewLoader;
    private GridPane updateEmployeeViewRootLayout;
    private UpdateEmployeeIController updateEmployeeController;

    //UPDATE UPDATE ACCOUNT COMPONENTS
    private FXMLLoader updateAccountViewLoader;
    private GridPane updateAccountViewRootLayout;
    private UpdateAccountController updateAccountController;

    //STOCK TABLE VIEW COMPONENTS
    private FXMLLoader stocksTableViewLoader;
    private VBox stocksTableViewRootLayout;
    private StocksTableViewController stocksTableViewController;

    //STOCK CATEGORY TABLE VIEW COMPONENTS
    private FXMLLoader stockCategoriesTableViewLoader;
    private VBox stockCategoryTableViewRootLayout;
    private StockCategorieController stockCategorieController;

    //STOCK TYPE TABLE VIEW COMPONENTS
    private FXMLLoader stockTypeTableViewLoader;
    private VBox stockTypeTableViewRootLayout;
    private StockTypeController stockTypeController;

    //STOCK BRAND NAME TABLE VIEW COMPONENTS
    private FXMLLoader stockBrandNameTableViewLoader;
    private VBox stockBrandNameTableViewRootLayout;
    private StockBrandNameController stockBrandNameController;

    //STOCK BRAND NAME TABLE VIEW COMPONENTS
    private FXMLLoader stockSupplierTableViewLoader;
    private VBox stockSupplierTableViewRootLayout;
    private StockSupplierController stockSupplierController;

    //ADD STOCK CATEGORY COMPONENTS

    private FXMLLoader addStockCategoryLoader;
    private GridPane   addStockCategoryRootLayout;
    private Stage      addStockCategoryStage;
    private Scene      addStockCategoryScene;

    //ADD STOCK CATEGORY COMPONENTS

    private FXMLLoader addStockTypeLoader;
    private GridPane   addStockTypeRootLayout;
    private Stage      addStockTypeStage;
    private Scene      addStockTypeScene;


    //ADD STOCK CATEGORY COMPONENTS

    private FXMLLoader addStockBrandLoader;
    private GridPane   addStockBrandRootLayout;
    private Stage      addStockBrandStage;
    private Scene      addStockBrandScene;




    public UILoader() {



        stdb = new StockDatabaseConnector();
        System.out.println(this + " Instance from the UI LOADER Class");

        mainMenuViewLoader = new FXMLLoader(getClass().getResource("../UI/mainMenu.fxml"));
        dashBoardLoader = new FXMLLoader(getClass().getResource("../UI/dashBoardForm.fxml"));
        employeeViewloader = new FXMLLoader(getClass().getResource("../UI/EmployeesForm.fxml"));
        accountsViewLoader = new FXMLLoader(getClass().getResource("../UI/AccountsForm.fxml"));
        employeeInformationViewLoader = new FXMLLoader(getClass().getResource("../UI/EmployeeInformationForm.fxml"));
        updateEmployeeViewLoader = new FXMLLoader(getClass().getResource("../UI/updateEmployeeForm.fxml"));
        updateAccountViewLoader = new FXMLLoader(getClass().getResource("../UI/UpdateAccountForm.fxml"));
        stocksTableViewLoader = new FXMLLoader(getClass().getResource("../UI/StocksTableView.fxml"));
        stockCategoriesTableViewLoader = new FXMLLoader(getClass().getResource("../UI/TableViewStockCategories.fxml"));
        stockTypeTableViewLoader = new FXMLLoader(getClass().getResource("../UI/TableViewStockTypes.fxml"));
        stockBrandNameTableViewLoader = new FXMLLoader(getClass().getResource("../UI/TableViewStockBrandNames.fxml"));
        stockSupplierTableViewLoader = new FXMLLoader(getClass().getResource("../UI/TableViewStockSuppliers.fxml"));
        addStockCategoryLoader = new FXMLLoader(getClass().getResource("../UI/AddCategoryForm.fxml"));
        addStockTypeLoader = new FXMLLoader(getClass().getResource("../UI/AddTypeForm.fxml"));
        addStockBrandLoader = new FXMLLoader(getClass().getResource("../UI/AddBrandNameForm.fxml"));


        loadFXMLS();
        System.out.println(getMainMenuViewRootLayout());
        loadControllers();

    }


    private void loadFXMLS() {

        try {
            mainMenuViewRootLayout = mainMenuViewLoader.load();
            dashBoardRootLayout = dashBoardLoader.load();
            employeeViewRootlayout = employeeViewloader.load();
            accountsViewRootLayout = accountsViewLoader.load();
            employeeInformationViewRootLayout = employeeInformationViewLoader.load();
            updateEmployeeViewRootLayout = updateEmployeeViewLoader.load();
            updateAccountViewRootLayout = updateAccountViewLoader.load();
            stocksTableViewRootLayout = stocksTableViewLoader.load();
            stockCategoryTableViewRootLayout = stockCategoriesTableViewLoader.load();
            stockTypeTableViewRootLayout = stockTypeTableViewLoader.load();
            stockBrandNameTableViewRootLayout = stockBrandNameTableViewLoader.load();
            stockSupplierTableViewRootLayout = stockSupplierTableViewLoader.load();
            addStockCategoryRootLayout = addStockCategoryLoader.load();
            addStockTypeRootLayout = addStockTypeLoader.load();
            addStockBrandRootLayout = addStockBrandLoader.load();


            addStockCategoryStage = new Stage();
            addStockCategoryStage.setResizable(false);
            addStockCategoryStage.centerOnScreen();
            addStockCategoryScene = new Scene(addStockCategoryRootLayout);
            addStockCategoryStage.setScene(addStockCategoryScene);
            addStockCategoryStage.initModality(Modality.APPLICATION_MODAL);


            addStockTypeStage = new Stage();
            addStockTypeStage.setResizable(false);
            addStockTypeStage.centerOnScreen();
            addStockTypeScene = new Scene(addStockTypeRootLayout);
            addStockTypeStage.setScene(addStockTypeScene);
            addStockTypeStage.initModality(Modality.APPLICATION_MODAL);




            addStockBrandStage = new Stage();
            addStockBrandStage.setResizable(false);
            addStockBrandStage.centerOnScreen();
            addStockBrandScene = new Scene(addStockBrandRootLayout);
            addStockBrandStage.setScene(addStockBrandScene);
            addStockBrandStage.initModality(Modality.APPLICATION_MODAL);



        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void loadControllers() {

        mainMenuController = mainMenuViewLoader.getController();

        dashboardFormController = dashBoardLoader.getController();
        employeesFormController = employeeViewloader.getController();
        accountsFormController = accountsViewLoader.getController();
        employeeInformationFormController = employeeInformationViewLoader.getController();
        updateEmployeeController = updateEmployeeViewLoader.getController();
        updateAccountController = updateAccountViewLoader.getController();
        stocksTableViewController = stocksTableViewLoader.getController();
        stockCategorieController = stockCategoriesTableViewLoader.getController();
        stockTypeController   = stockTypeTableViewLoader.getController();
        stockBrandNameController = stockBrandNameTableViewLoader.getController();
        stockSupplierController = stockSupplierTableViewLoader.getController();


    }


    public GridPane getMainMenuViewRootLayout() {
        return mainMenuViewRootLayout;
    }

    public MainMenuController getMainMenuController() {
        return mainMenuController;
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


    //GETTERS OF STOCKS TABLE VIEW COMPONENTS
    public VBox getStocksTableViewRootLayout() {
        return stocksTableViewRootLayout;
    }
    public StocksTableViewController getStocksTableViewController() {
        return stocksTableViewController;
    }



    //GETTERS OF STOCK CATEGORY TABLE VIEW COMPONENTS
    public VBox getStockCategoryTableViewRootLayout() {
        return stockCategoryTableViewRootLayout;
    }
    public StockCategorieController getStockCategorieController() {
        return stockCategorieController;
    }

    //GETTERS OF STOCK TYPE TABLE VIEW COMPONENTS
    public VBox getStockTypeTableViewRootLayout() {
        return stockTypeTableViewRootLayout;
    }
    public StockTypeController getStockTypeController() {
        return stockTypeController;
    }

    //GETTERS OF STOCK BRAND NAME TABLE VIEW COMPONENTS
    public VBox getStockBrandNameTableViewRootLayout() {
        return stockBrandNameTableViewRootLayout;
    }
    public StockBrandNameController getStockBrandNameController() {
        return stockBrandNameController;
    }

    public EmployeesDatabaseConnector getEmdb() {
        return emdb;
    }

    public StockDatabaseConnector getStdb() {
        return stdb;
    }


    public VBox getStockSupplierTableViewRootLayout() {
        return stockSupplierTableViewRootLayout;
    }
    public StockSupplierController getStockSupplierController() {
        return stockSupplierController;
    }


    public GridPane getAddStockCategoryRootLayout() {
        return addStockCategoryRootLayout;
    }

    public Stage getAddStockCategoryStage() {
        return addStockCategoryStage;
    }

    public Stage getAddStockTypeStage() {
        return addStockTypeStage;
    }

    public Stage getAddStockBrandStage() {
        return addStockBrandStage;
    }
}