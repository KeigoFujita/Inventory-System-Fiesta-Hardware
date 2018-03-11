package com.hardware.fiesta.LoaderUI;

import com.hardware.fiesta.Controller.*;
import com.hardware.fiesta.Database.EmployeesDatabaseConnector;
import com.hardware.fiesta.Database.StockDatabaseConnector;
import com.hardware.fiesta.Model.Account;
import com.hardware.fiesta.Model.Employee;
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

    private FXMLLoader loginFormLoader;
    private GridPane   loginFormRootLayout;
    private LoginFormController loginFormController;

    private Stage loginStage;
    private Scene loginScene;


    //MAIN MENU VIEW COMPONENTS
    private FXMLLoader mainMenuViewLoader;
    private GridPane   mainMenuViewRootLayout;
    private MainMenuController mainMenuController;

    private Stage mainMenuStage;
    private Scene mainMenuScene;


    //DASH BOARD VIEW COMPONENTS
    private FXMLLoader dashBoardLoader;
    private VBox dashBoardRootLayout;
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

    //STOCK MAIN VIEW COMPONENTS
    private FXMLLoader stocksMainViewLoader;
    private VBox stocksMainViewRootLayout;
    private StocksMainViewController stocksMainViewController;



    //STOCK ITEM TABLE VIEW COMPONENTS

    private FXMLLoader stockItemTableViewLoader;
    private VBox stockItemTableViewRootLayout;
    private StockTableViewController stockTableViewController;



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


    //ADD STOCK ITEM COMPONENTS
    private FXMLLoader addItemLoader;
    private GridPane   addItemRootLayout;
    private AddItemController addItemController;



    //ADD STOCK CATEGORY COMPONENTS
    private FXMLLoader addStockCategoryLoader;
    private GridPane   addStockCategoryRootLayout;
    private AddStockCategoryController addStockCategoryController;
    private Stage      addStockCategoryStage;
    private Scene      addStockCategoryScene;


    //ADD STOCK TYPE COMPONENTS
    private FXMLLoader addStockTypeLoader;
    private GridPane   addStockTypeRootLayout;
    private AddStockTypeController addStockTypeController;
    private Stage      addStockTypeStage;
    private Scene      addStockTypeScene;



    //ADD STOCK BRAND COMPONENTS

    private FXMLLoader addStockBrandLoader;
    private GridPane   addStockBrandRootLayout;
    private AddBrandController addBrandController;

    private Stage      addStockBrandStage;
    private Scene      addStockBrandScene;


    //ADD SUPPLIER COMPONENTS

    private FXMLLoader addSupplierLoader;
    private GridPane addSupplierRootLayout;
    private AddSupplierController addSupplierController;

    private Stage     addSupplierStage;
    private Scene     addSupplierScene;

    //UPDATE STOCK ITEM COMPONENTS
    private FXMLLoader updateItemLoader;
    private GridPane   updateItemRootLayout;
    private UpdateItemController updateItemController;


    private FXMLLoader disableItemLoader;
    private GridPane   disableItemRootLayout;
    private DisableItemController disableItemController;
    private Stage disableItemStage;
    private Scene disableItemScene;


    //INVENTORY MAIN VIEW COMPONENTS

    private FXMLLoader inventoryMainViewLoader;
    private VBox       inventoryMainViewMainRootLayout;
    private InventoryMainViewController inventoryMainViewController;

    //INVENTORY TABLE VIEW COMPONENTS

    private FXMLLoader inventoryTableViewLoader;
    private VBox       inventorTableViewRootLayout;
    private InventoryTableVIewController inventoryTableVIewController;

    //INVENTORY TABLE VIEW COMPONENTS

    private FXMLLoader replenishTableViewLoader;
    private VBox       replenishTableViewRootLayout;
    private ReplenishmentFormController replenishmentFormController;

    //INVENTORY TABLE VIEW COMPONENTS

    private FXMLLoader salesTableViewLoader;
    private VBox       salesTableViewRootLayout;
    private SalesTableViewController salesTableViewController;


    //REPLENISH PROMPT FORM

    private FXMLLoader replenishPromptLoader;
    private GridPane   replenishPromptRootLayout;
    private ReplenishPromptFormController replenishPromptFormController;

    private Stage  replenishPromptStage;
    private Scene  replenishPromptScene;


    //DEDUCT PROMPT FORM

    private FXMLLoader deductItemQtyLoader;
    private GridPane   deductItemQtyRootLayout;
    private DeductItemQtyFormController deductItemQtyFormController;

    private Stage  deductItemQtyStage;
    private Scene  deductItemQtyScene;


    private Account loginAccount;
    private Employee loginEmployee;







    public UILoader() {

        stdb = new StockDatabaseConnector();
        emdb = new EmployeesDatabaseConnector();


        System.out.println(this + " Instance from the UI LOADER Class");


        loginFormLoader = new FXMLLoader(getClass().getResource("../UI/loginForm.fxml"));
        mainMenuViewLoader = new FXMLLoader(getClass().getResource("../UI/mainMenu.fxml"));
        dashBoardLoader = new FXMLLoader(getClass().getResource("../UI/dashBoardForm.fxml"));
        employeeViewloader = new FXMLLoader(getClass().getResource("../UI/EmployeesForm.fxml"));
        accountsViewLoader = new FXMLLoader(getClass().getResource("../UI/AccountsForm.fxml"));
        employeeInformationViewLoader = new FXMLLoader(getClass().getResource("../UI/EmployeeInformationForm.fxml"));
        updateEmployeeViewLoader = new FXMLLoader(getClass().getResource("../UI/updateEmployeeForm.fxml"));
        updateAccountViewLoader = new FXMLLoader(getClass().getResource("../UI/UpdateAccountForm.fxml"));
        stocksMainViewLoader = new FXMLLoader(getClass().getResource("../UI/mainStockTableView.fxml"));
        stockItemTableViewLoader = new FXMLLoader(getClass().getResource("../UI/StockTableView.fxml"));
        stockCategoriesTableViewLoader = new FXMLLoader(getClass().getResource("../UI/TableViewStockCategories.fxml"));
        stockTypeTableViewLoader = new FXMLLoader(getClass().getResource("../UI/TableViewStockTypes.fxml"));
        stockBrandNameTableViewLoader = new FXMLLoader(getClass().getResource("../UI/TableViewStockBrandNames.fxml"));
        stockSupplierTableViewLoader = new FXMLLoader(getClass().getResource("../UI/TableViewStockSuppliers.fxml"));
        addStockCategoryLoader = new FXMLLoader(getClass().getResource("../UI/AddCategoryForm.fxml"));
        addItemLoader = new FXMLLoader(getClass().getResource("../UI/AddItemForm.fxml"));
        addStockTypeLoader = new FXMLLoader(getClass().getResource("../UI/AddTypeForm.fxml"));
        addStockBrandLoader = new FXMLLoader(getClass().getResource("../UI/AddBrandNameForm.fxml"));
        addSupplierLoader = new FXMLLoader(getClass().getResource("../UI/AddSupplierForm.fxml"));
        updateItemLoader = new FXMLLoader(getClass().getResource("../UI/UpdateItemForm.fxml"));
        disableItemLoader = new FXMLLoader(getClass().getResource("../UI/DisableItemForm.fxml"));
        inventoryMainViewLoader = new FXMLLoader(getClass().getResource("../UI/InventoryMainView.fxml"));
        inventoryTableViewLoader = new FXMLLoader(getClass().getResource("../UI/InventoryTableViewForm.fxml"));
        replenishTableViewLoader = new FXMLLoader(getClass().getResource("../UI/ReplenishmentForm.fxml"));
        salesTableViewLoader = new FXMLLoader(getClass().getResource("../UI/SalesTableViewForm.fxml"));
        replenishPromptLoader = new FXMLLoader(getClass().getResource("../UI/ReplenishPromptForm.fxml"));
        deductItemQtyLoader = new FXMLLoader(getClass().getResource("../UI/DeductItemQtyForm.fxml"));





        loadFXMLS();
        System.out.println(getMainMenuViewRootLayout());
        loadControllers();

    }


    private void loadFXMLS() {

        try {

            loginFormRootLayout = loginFormLoader.load();
            mainMenuViewRootLayout = mainMenuViewLoader.load();
            dashBoardRootLayout = dashBoardLoader.load();
            employeeViewRootlayout = employeeViewloader.load();
            accountsViewRootLayout = accountsViewLoader.load();
            employeeInformationViewRootLayout = employeeInformationViewLoader.load();
            updateEmployeeViewRootLayout = updateEmployeeViewLoader.load();
            updateAccountViewRootLayout = updateAccountViewLoader.load();
            stocksMainViewRootLayout = stocksMainViewLoader.load();
            stockItemTableViewRootLayout = stockItemTableViewLoader.load();
            stockCategoryTableViewRootLayout = stockCategoriesTableViewLoader.load();
            stockTypeTableViewRootLayout = stockTypeTableViewLoader.load();
            stockBrandNameTableViewRootLayout = stockBrandNameTableViewLoader.load();
            stockSupplierTableViewRootLayout = stockSupplierTableViewLoader.load();
            addItemRootLayout = addItemLoader.load();
            addStockCategoryRootLayout = addStockCategoryLoader.load();
            addStockTypeRootLayout = addStockTypeLoader.load();
            addStockBrandRootLayout = addStockBrandLoader.load();
            addSupplierRootLayout = addSupplierLoader.load();
            disableItemRootLayout = disableItemLoader.load();
            inventoryMainViewMainRootLayout = inventoryMainViewLoader.load();
            inventorTableViewRootLayout = inventoryTableViewLoader.load();
            replenishTableViewRootLayout = replenishTableViewLoader.load();
            salesTableViewRootLayout = salesTableViewLoader.load();
            replenishPromptRootLayout = replenishPromptLoader.load();
            deductItemQtyRootLayout = deductItemQtyLoader.load();

            mainMenuStage = new Stage();
            mainMenuStage.centerOnScreen();
            mainMenuStage.setMinHeight(720);
            mainMenuStage.setMinWidth(1280);
            mainMenuScene = new Scene(mainMenuViewRootLayout);
            mainMenuStage.setScene(mainMenuScene);



            disableItemStage  = new Stage();
            disableItemStage.setResizable(false);
            disableItemStage.centerOnScreen();
            disableItemStage.initModality(Modality.APPLICATION_MODAL);
            disableItemScene = new Scene(disableItemRootLayout);
            disableItemStage.setScene(disableItemScene);

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


            addSupplierStage = new Stage();
            addSupplierStage.setResizable(false);
            addSupplierStage.centerOnScreen();
            addSupplierScene = new Scene(addSupplierRootLayout);
            addSupplierStage.setScene(addSupplierScene);
            addSupplierStage.initModality(Modality.APPLICATION_MODAL);


            updateItemRootLayout = updateItemLoader.load();



            replenishPromptStage = new Stage();
            replenishPromptStage.setResizable(false);
            replenishPromptStage.centerOnScreen();
            replenishPromptScene = new Scene(replenishPromptRootLayout);
            replenishPromptStage.setScene(replenishPromptScene);
            replenishPromptStage.initModality(Modality.APPLICATION_MODAL);


            deductItemQtyStage = new Stage();
            deductItemQtyStage.setResizable(false);
            deductItemQtyStage.centerOnScreen();
            deductItemQtyScene = new Scene(deductItemQtyRootLayout);
            deductItemQtyStage.setScene(deductItemQtyScene);
            deductItemQtyStage.initModality(Modality.APPLICATION_MODAL);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void loadControllers() {

        loginFormController = loginFormLoader.getController();
        mainMenuController = mainMenuViewLoader.getController();


        dashboardFormController = dashBoardLoader.getController();
        employeesFormController = employeeViewloader.getController();
        accountsFormController = accountsViewLoader.getController();
        employeeInformationFormController = employeeInformationViewLoader.getController();
        updateEmployeeController = updateEmployeeViewLoader.getController();
        updateAccountController = updateAccountViewLoader.getController();
        stocksMainViewController = stocksMainViewLoader.getController();
        stockTableViewController = stockItemTableViewLoader.getController();
        stockCategorieController = stockCategoriesTableViewLoader.getController();
        stockTypeController   = stockTypeTableViewLoader.getController();
        stockBrandNameController = stockBrandNameTableViewLoader.getController();
        stockSupplierController = stockSupplierTableViewLoader.getController();
        addItemController = addItemLoader.getController();
        addStockCategoryController = addStockCategoryLoader.getController();
        addStockTypeController =  addStockTypeLoader.getController();
        addBrandController = addStockBrandLoader.getController();
        addSupplierController = addSupplierLoader.getController();
        updateItemController = updateItemLoader.getController();
        disableItemController = disableItemLoader.getController();
        inventoryMainViewController = inventoryMainViewLoader.getController();
        inventoryTableVIewController = inventoryTableViewLoader.getController();
        replenishmentFormController = replenishTableViewLoader.getController();
        salesTableViewController = salesTableViewLoader.getController();
        replenishPromptFormController = replenishPromptLoader.getController();
        deductItemQtyFormController = deductItemQtyLoader.getController();

    }


    public GridPane getLoginFormRootLayout() {
        return loginFormRootLayout;
    }

    public LoginFormController getLoginFormController() {
        return loginFormController;
    }

    public Stage getLoginStage() {
        return loginStage;
    }

    public GridPane getMainMenuViewRootLayout() {
        return mainMenuViewRootLayout;
    }

    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }


    public Stage getMainMenuStage() {
        return mainMenuStage;
    }

    //GETTERS OF DASHBOARD VIEW COMPONENTS
    public VBox getDashBoardRootLayout() {
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


    //GETTERS OF STOCKS MAIN VIEW COMPONENTS
    public VBox getStocksMainViewRootLayout() {
        return stocksMainViewRootLayout;
    }
    public StocksMainViewController getStocksMainViewController() {
        return stocksMainViewController;
    }

    public VBox getStockItemTableViewRootLayout() {
        return stockItemTableViewRootLayout;
    }

    public StockTableViewController getStockTableViewController() {
        return stockTableViewController;
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


    public GridPane getAddItemRootLayout() {
        return addItemRootLayout;
    }

    public AddItemController getAddItemController() {
        return addItemController;
    }

    public GridPane getAddStockCategoryRootLayout() {
        return addStockCategoryRootLayout;
    }

    public AddStockCategoryController getAddStockCategoryController() {
        return addStockCategoryController;
    }

    public Stage getAddStockCategoryStage() {
        return addStockCategoryStage;
    }




    public Stage getAddStockTypeStage() {
        return addStockTypeStage;
    }


    public AddStockTypeController getAddStockTypeController() {
        return addStockTypeController;
    }

    public Stage getAddStockBrandStage() {
        return addStockBrandStage;
    }


    public AddBrandController getAddBrandController() {
        return addBrandController;
    }


    public GridPane getAddSupplierRootLayout() {
        return addSupplierRootLayout;
    }

    public AddSupplierController getAddSupplierController() {
        return addSupplierController;
    }

    public Stage getAddSupplierStage() {
        return addSupplierStage;
    }


    public GridPane getUpdateItemRootLayout() {
        return updateItemRootLayout;
    }

    public UpdateItemController getUpdateItemController() {
        return updateItemController;
    }


    public GridPane getDisableItemRootLayout() {
        return disableItemRootLayout;
    }

    public DisableItemController getDisableItemController() {
        return disableItemController;
    }

    public Stage getDisableItemStage() {
        return disableItemStage;
    }



    public void setLoginDetails(Account loginAccount, Employee loginEmployee){
        this.loginAccount = loginAccount;
        this.loginEmployee = loginEmployee;

    }

    public Account getLoginAccount() {
        return loginAccount;
    }

    public Employee getLoginEmployee() {
        return loginEmployee;
    }

    public void setLoginStage(Stage stage){

        this.loginStage = stage;
        loginScene = new Scene(loginFormRootLayout);
        loginStage.setScene(loginScene);
        loginStage.setTitle("Login Form");
        loginStage.centerOnScreen();
    }


    public VBox getInventoryMainViewMainRootLayout() {
        return inventoryMainViewMainRootLayout;
    }

    public InventoryMainViewController getInventoryMainViewController() {
        return inventoryMainViewController;
    }

    public VBox getInventorTableViewRootLayout() {
        return inventorTableViewRootLayout;
    }

    public InventoryTableVIewController getInventoryTableVIewController() {
        return inventoryTableVIewController;
    }

    public VBox getReplenishTableViewRootLayout() {
        return replenishTableViewRootLayout;
    }

    public ReplenishmentFormController getReplenishmentFormController() {
        return replenishmentFormController;
    }

    public VBox getSalesTableViewRootLayout() {
        return salesTableViewRootLayout;
    }

    public SalesTableViewController getSalesTableViewController() {
        return salesTableViewController;
    }

    public ReplenishPromptFormController getReplenishPromptFormController() {
        return replenishPromptFormController;
    }

    public Stage getReplenishPromptStage() {
        return replenishPromptStage;
    }

    public DeductItemQtyFormController getDeductItemQtyFormController() {
        return deductItemQtyFormController;
    }

    public Stage getDeductItemQtyStage() {
        return deductItemQtyStage;
    }


}