package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.StockDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.Sales;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class SalesTableViewController {



    @FXML
    private TableView<Sales> tableView_Sales;

    @FXML
    private TableColumn<Sales, Integer> tc_salesId;

    @FXML
    private TableColumn<Sales, Integer> tc_inventory_id;

    @FXML
    private TableColumn<Sales, Integer> tc_item_id;

    @FXML
    private TableColumn<Sales, Integer> tc_item_sales;

    @FXML
    private TableColumn<Sales,Double> tc_gross_sales;

    @FXML
    private TableColumn<Sales,String> tc_inclusiveDates;



    private ObservableList<Sales> sales;

    private UILoader uiLoader;
    private StockDatabaseConnector stdb;




    private void loadDataInObservableList(){


        stdb.openConnection();
        sales = FXCollections.observableArrayList(stdb.getInventorySales());
        for(Sales sale: sales){

            sale.setItemName(stdb.getItemName(sale.getSalesItemId()));

        }
        stdb.closeConnection();

    }
    private void setDataInColumn(){

        tc_salesId.setCellValueFactory(new PropertyValueFactory<>("salesId"));
        tc_inventory_id.setCellValueFactory(new PropertyValueFactory<>("salesInventoryId"));
        tc_item_id.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tc_item_sales.setCellValueFactory(new PropertyValueFactory<>("itemSales"));
        tc_gross_sales.setCellValueFactory(new PropertyValueFactory<>("itemsGrossSales"));
        tc_inclusiveDates.setCellValueFactory(new PropertyValueFactory<>("inclusiveDates"));


    }



    public void displayDataInTableView(){

        setNamesInColumns();
        loadDataInObservableList();
        setDataInColumn();

        tableView_Sales.setItems(sales);
    }

    private void setNamesInColumns() {

        stdb.openConnection();
        ArrayList<String> columnNames = stdb.getSalesColumnName();
        stdb.closeConnection();

        tc_salesId.setText(columnNames.get(0));
        tc_inventory_id.setText(columnNames.get(1));
        tc_item_id.setText(columnNames.get(2));
        tc_item_sales.setText(columnNames.get(3));
        tc_gross_sales.setText(columnNames.get(4));


    }

    public void setUiLoader(UILoader uiLoader){

        this.uiLoader = uiLoader;
        this.stdb = uiLoader.getStdb();
        displayDataInTableView();


    }







}
