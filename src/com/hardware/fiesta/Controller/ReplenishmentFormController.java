package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.StockDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.StockReplenishment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ReplenishmentFormController {

    @FXML
    private TableView<StockReplenishment> tableView_Replenish;

    @FXML
    private TableColumn<StockReplenishment, Integer> tc_replenishId;

    @FXML
    private TableColumn<StockReplenishment, Integer> tc_item_id;

    @FXML
    private TableColumn<StockReplenishment, Integer> tc_stockRemain;

    @FXML
    private TableColumn<StockReplenishment, Integer> tc_stockAdded;

    @FXML
    private TableColumn<StockReplenishment, Integer> tc_stockFinal;

    @FXML
    private TableColumn<StockReplenishment, String> tc_date;

    ObservableList<StockReplenishment> stockReplenishments;





    private UILoader uiLoader;
    private StockDatabaseConnector stdb;









    private void loadDataInObservableList(){


        stdb.openConnection();
        stockReplenishments = FXCollections.observableArrayList(stdb.getReplenishmentList());
        stdb.closeConnection();

    }
    private void setDataInColumn(){

        tc_replenishId.setCellValueFactory(new PropertyValueFactory<>("replenishId"));
        tc_item_id.setCellValueFactory(new PropertyValueFactory<>("replenishItemId"));
        tc_stockRemain.setCellValueFactory(new PropertyValueFactory<>("replenishStockRemaining"));
        tc_stockAdded.setCellValueFactory(new PropertyValueFactory<>("replenishStockAdded"));
        tc_stockFinal.setCellValueFactory(new PropertyValueFactory<>("replenishStockFinal"));
        tc_date.setCellValueFactory(new PropertyValueFactory<>("replenishDate"));

    }

    private void setNamesInColumns(){

        stdb.openConnection();
        ArrayList<String> columnNames = stdb.getReplenishColumnName();
        stdb.closeConnection();

        tc_replenishId.setText(columnNames.get(0));
        tc_item_id.setText(columnNames.get(1));
        tc_stockRemain.setText(columnNames.get(2));
        tc_stockAdded.setText(columnNames.get(3));
        tc_stockFinal.setText(columnNames.get(4));
        tc_date.setText(columnNames.get(5));


    }

    public void displayDataInTableView(){

        setNamesInColumns();
        loadDataInObservableList();
        setDataInColumn();

        tableView_Replenish.setItems(stockReplenishments);
    }

    public void setUiLoader(UILoader uiLoader){

        this.uiLoader = uiLoader;
        this.stdb = uiLoader.getStdb();
        displayDataInTableView();


    }









}
