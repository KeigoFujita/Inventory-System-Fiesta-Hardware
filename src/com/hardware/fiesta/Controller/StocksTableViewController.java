package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.StockDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class StocksTableViewController {


    @FXML
    private JFXComboBox<String> viewComboBox;

    @FXML
    public VBox tableViewContainer;

    private StockDatabaseConnector stdb;
    private UILoader uiLoader;

    public void initialize(){

        viewComboBox.getItems().addAll("Categories","Type","Brand Names","Suppliers");

    }








    public void setUILoader(UILoader uiLoader){
        this.uiLoader = uiLoader;


    }

    public void displayData(){

        viewComboBox.setOnAction( e -> {




            if(viewComboBox.getValue().equals("Categories")){

                displayTableCategories();

            }else if(viewComboBox.getValue().equals("Type")){

                displayTableTypes();

            } else if(viewComboBox.getValue().equals("Brand Names")){

                displayTableBrandNames();

            }else{
                displayTableStockSuppliers();

            }



        });

        viewComboBox.getSelectionModel().select("Categories");

        displayTableCategories();

    }

    private void displayTableCategories(){

        this.uiLoader.getStockCategorieController().setUiLoader(this.uiLoader);
        this.uiLoader.getStockCategorieController().displayTableView();

        tableViewContainer.getChildren().clear();
        tableViewContainer.getChildren().add(uiLoader.getStockCategoryTableViewRootLayout());


    }
    private void displayTableTypes(){

        this.uiLoader.getStockTypeController().setUiLoader(this.uiLoader);
        this.uiLoader.getStockTypeController().displayTableView();

        tableViewContainer.getChildren().clear();
        tableViewContainer.getChildren().add(uiLoader.getStockTypeTableViewRootLayout());

    }
    private void displayTableStockSuppliers(){

        this.uiLoader.getStockSupplierController().setUiLoader(this.uiLoader);
        this.uiLoader.getStockSupplierController().displayTableView();

        tableViewContainer.getChildren().clear();
        tableViewContainer.getChildren().add(uiLoader.getStockSupplierTableViewRootLayout());

    }
    private void displayTableBrandNames(){

        this.uiLoader.getStockBrandNameController().setUiLoader(this.uiLoader);
        this.uiLoader.getStockBrandNameController().displayTableView();

        tableViewContainer.getChildren().clear();
        tableViewContainer.getChildren().add(uiLoader.getStockBrandNameTableViewRootLayout());

    }


    public void setStdb(StockDatabaseConnector stdb) {
        this.stdb = stdb;
    }


}
