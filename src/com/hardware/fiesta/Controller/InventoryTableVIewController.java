package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.StockDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.Inventory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class InventoryTableVIewController {


    @FXML
    private TableView<Inventory> tableView_Inventory;

    @FXML
    private TableColumn<Inventory, Integer> tc_id;

    @FXML
    private TableColumn<Inventory, Integer> tc_item_id;

    @FXML
    private TableColumn<Inventory, Integer> tc_init_stock;

    @FXML
    private TableColumn<Inventory, String> tc_init_date;

    @FXML
    private TableColumn<Inventory, Integer> tc_final_stock;

    @FXML
    private TableColumn<Inventory, String> tc_final_date;

    @FXML
    private TableColumn<Inventory, Integer> tc_sales;

    private ObservableList<Inventory> inventories;



    private UILoader uiLoader;
    private StockDatabaseConnector stdb;








    private void loadDataInObservableList(){

        stdb.openConnection();
        inventories = FXCollections.observableArrayList(stdb.getInventoryList());
        stdb.closeConnection();


    }
    private void setDataInColumn(){

        tc_id.setCellValueFactory(new PropertyValueFactory<>("inventoryId"));
        tc_item_id.setCellValueFactory(new PropertyValueFactory<>("inventoryItemId"));
        tc_init_stock.setCellValueFactory(new PropertyValueFactory<>("initialStock"));
        tc_init_date.setCellValueFactory(new PropertyValueFactory<>("initialDate"));
        tc_final_stock.setCellValueFactory(new PropertyValueFactory<>("finalStock"));
        tc_final_date.setCellValueFactory(new PropertyValueFactory<>("finalDate"));
        tc_sales.setCellValueFactory(new PropertyValueFactory<>("itemSales"));


    }

    private void setNamesInColumns(){

        stdb.openConnection();
        ArrayList<String> columnNames = stdb.getInventoryColumnName();
        stdb.closeConnection();

        tc_id.setText(columnNames.get(0));
        tc_item_id.setText(columnNames.get(1));
        tc_init_stock.setText(columnNames.get(2));
        tc_init_date.setText(columnNames.get(3));
        tc_final_stock.setText(columnNames.get(4));
        tc_final_date.setText(columnNames.get(5));
        tc_sales.setText(columnNames.get(6));


    }

    public void displayDataInTableView(){

        setNamesInColumns();
        loadDataInObservableList();
        setDataInColumn();

        tableView_Inventory.setItems(inventories);

    }




    public void setUiLoader(UILoader uiLoader){

        this.uiLoader = uiLoader;
        this.stdb = uiLoader.getStdb();
        displayDataInTableView();


    }





}
