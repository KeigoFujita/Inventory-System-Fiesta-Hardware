package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.StockDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.StockType;
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

public class StockTypeController {

    @FXML
    private JFXTextField tf_search;


    @FXML
    private TableView<StockType> tableView_Type;

    @FXML
    private TableColumn<StockType, Integer> tc_typeId;

    @FXML
    private TableColumn<StockType, String> tc_typeName;

    @FXML
    private TableColumn<StockType, String> tc_typeStatus;



    private static ObservableList<StockType> stockTypes;

    private StockDatabaseConnector stdb;
    private UILoader uiLoader;

    public void loadDataInObservableList(){

        stdb.openConnection();
        stockTypes = FXCollections.observableArrayList(stdb.getStockTypeList());
        stdb.closeConnection();

    }

    public void setNameInColumn(){

        stdb.openConnection();
        ArrayList<String> columnNames = stdb.getStockTypesColumnName();
        stdb.closeConnection();

        tc_typeId.setText(columnNames.get(0));
        tc_typeName.setText(columnNames.get(1));
        tc_typeStatus.setText(columnNames.get(2));


    }

    public void setDataOnColumn(){


        tc_typeId.setCellValueFactory(new PropertyValueFactory<>("typeId"));
        tc_typeName.setCellValueFactory(new PropertyValueFactory<>("typeName"));
        tc_typeStatus.setCellValueFactory(new PropertyValueFactory<>("typeStatus"));



    }

    public void displayTableView(){

        setNameInColumn();
        loadDataInObservableList();
        setDataOnColumn();

        tableView_Type.setItems(this.stockTypes);

    }


    public void displayTableView(ObservableList<StockType> stockTypes){

        setNameInColumn();
        loadDataInObservableList();
        setDataOnColumn();

        tableView_Type.setItems(stockTypes);

    }

    @FXML
    void searchAction(KeyEvent event) {

        String newValue = tf_search.getText();
        FilteredList<StockType> filteredData = new FilteredList<>(stockTypes);

        filteredData.setPredicate(stockType -> {

            if(newValue == null|| newValue.isEmpty()){

                return true;
            }

            // search in Names

            String lowerCaseFilter = newValue.toLowerCase();

            return stockType.getTypeName().toLowerCase().contains(lowerCaseFilter);

        });

        SortedList<StockType> sortedList = new SortedList<>(filteredData);
        displayTableView(FXCollections.observableArrayList(sortedList));

    }



    public void buttonAddAction(){


        uiLoader.getAddStockTypeStage().setTitle("Add Category");
        uiLoader.getAddStockTypeStage().showAndWait();

    }


    public void setUiLoader(UILoader uiLoader) {
        this.uiLoader = uiLoader;
        this.stdb = uiLoader.getStdb();
    }
}
