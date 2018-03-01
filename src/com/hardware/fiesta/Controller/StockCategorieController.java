package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.StockDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.StockCategory;
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

public class StockCategorieController {


    @FXML
    private JFXTextField tf_search;

    @FXML
    private TableView<StockCategory> tableView_Category;

    @FXML
    private TableColumn<StockCategory, Integer> tc_categoryId;

    @FXML
    private TableColumn<StockCategory, String> tc_categoryName;

    @FXML
    private TableColumn<StockCategory, String> tc_categoryStatus;

    private static ObservableList<StockCategory> stockCategories;

    private StockDatabaseConnector stdb;
    private UILoader uiLoader;


    public void initialize(){




    }
    public void loadDataInObservableList(){

        stdb.openConnection();
        stockCategories = FXCollections.observableArrayList(stdb.getStockCategoryList());
        stdb.closeConnection();

    }

    public void setNameInColumn(){

        stdb.openConnection();
        ArrayList<String> columnNames = stdb.getStockCategoriesColumnName();
        stdb.closeConnection();

        tc_categoryId.setText(columnNames.get(0));
        tc_categoryName.setText(columnNames.get(1));
        tc_categoryStatus.setText(columnNames.get(2));


    }
    public void setDataOnColumn(){


        tc_categoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        tc_categoryName.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        tc_categoryStatus.setCellValueFactory(new PropertyValueFactory<>("categoryStatus"));



    }

    public void displayTableView(){

        setNameInColumn();
        loadDataInObservableList();
        setDataOnColumn();

        tableView_Category.setItems(this.stockCategories);

    }

    public void displayTableView(ObservableList<StockCategory> stockCategories){

        setNameInColumn();
        loadDataInObservableList();
        setDataOnColumn();

        tableView_Category.setItems(stockCategories);

    }


    public void setUiLoader(UILoader uiLoader) {
        this.uiLoader = uiLoader;
        this.stdb = uiLoader.getStdb();
    }


    @FXML
    void searchAction(KeyEvent event) {

        String newValue = tf_search.getText();
        FilteredList<StockCategory> filteredData = new FilteredList<>(stockCategories);

        filteredData.setPredicate(stockCategory -> {

            if(newValue == null|| newValue.isEmpty()){

                return true;
            }

            // search in Names

            String lowerCaseFilter = newValue.toLowerCase();

            return stockCategory.getCategoryName().toLowerCase().contains(lowerCaseFilter);

        });

        SortedList<StockCategory> sortedList = new SortedList<>(filteredData);
        displayTableView(FXCollections.observableArrayList(sortedList));

    }


    public void buttonAddAction(){


        uiLoader.getAddStockCategoryStage().setTitle("Add Category");
        uiLoader.getAddStockCategoryStage().showAndWait();

    }

}
