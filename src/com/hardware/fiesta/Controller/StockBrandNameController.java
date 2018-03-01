package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.StockDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.StockBrandName;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class StockBrandNameController {

    @FXML
    private JFXTextField tf_search;



    @FXML
    private TableView<StockBrandName> tableView_BrandName;

    @FXML
    private TableColumn<StockBrandName, Integer> tc_brandId;

    @FXML
    private TableColumn<StockBrandName, String> tc_brandName;

    @FXML
    private TableColumn<StockBrandName, String> tc_brandStatus;



    private static ObservableList<StockBrandName> stockBrandNames;

    private StockDatabaseConnector stdb;
    private UILoader uiLoader;


    public void loadDataInObservableList(){

        stdb.openConnection();
        stockBrandNames = FXCollections.observableArrayList(stdb.getStockBrandNameList());
        stdb.closeConnection();

    }

    public void setNameInColumn(){

        stdb.openConnection();
        ArrayList<String> columnNames = stdb.getStockBrandNamesColumnName();
        stdb.closeConnection();

        tc_brandId.setText(columnNames.get(0));
        tc_brandName.setText(columnNames.get(1));
        tc_brandStatus.setText(columnNames.get(2));


    }

    public void setDataOnColumn(){


        tc_brandId.setCellValueFactory(new PropertyValueFactory<>("brandId"));
        tc_brandName.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        tc_brandStatus.setCellValueFactory(new PropertyValueFactory<>("brandStatus"));



    }

    public void displayTableView(){

        setNameInColumn();
        loadDataInObservableList();
        setDataOnColumn();

        tableView_BrandName.setItems(this.stockBrandNames);
    }




    public void setUiLoader(UILoader uiLoader) {
        this.uiLoader = uiLoader;
        this.stdb = uiLoader.getStdb();
    }

    public void displayTableView(ObservableList<StockBrandName> stockBrandNames){

        setNameInColumn();
        loadDataInObservableList();
        setDataOnColumn();

        tableView_BrandName.setItems(stockBrandNames);

    }


    @FXML
    void searchAction(KeyEvent event) {

        String newValue = tf_search.getText();
        FilteredList<StockBrandName> filteredData = new FilteredList<>(stockBrandNames);

        filteredData.setPredicate(stockBrandName -> {

            if(newValue == null|| newValue.isEmpty()){

                return true;
            }

            // search in Names

            String lowerCaseFilter = newValue.toLowerCase();

            return stockBrandName.getBrandName().toLowerCase().contains(lowerCaseFilter);

        });

        SortedList<StockBrandName> sortedList = new SortedList<>(filteredData);
        displayTableView(FXCollections.observableArrayList(sortedList));

    }

    public void buttonAddAction(){


        uiLoader.getAddStockBrandStage().setTitle("Add Category");
        uiLoader.getAddStockBrandStage().showAndWait();

    }


}
