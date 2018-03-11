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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
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


    private ContextMenu brand_context = new ContextMenu();

    private MenuItem disable_brand = new MenuItem("Disable Brand");
    private MenuItem enable_brand= new MenuItem("Enable Brand");
    private MenuItem edit_brand =  new MenuItem("Edit Brand");

    private static ObservableList<StockBrandName> stockBrandNames;

    private StockDatabaseConnector stdb;
    private UILoader uiLoader;


    public void loadDataInObservableList(){

        stdb.openConnection();
        stockBrandNames = FXCollections.observableArrayList(stdb.getStockBrandNameList());
        stdb.closeConnection();

    }

    public void setNameInColumn(){

        System.out.println(stdb);
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
        setContextMenuOnTableView();
    }

    public void displayTableView(ObservableList<StockBrandName> stockBrandNames){

        setNameInColumn();
        loadDataInObservableList();
        setDataOnColumn();
        setContextMenuOnTableView();

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

        uiLoader.getAddBrandController().setUiLoader(this.uiLoader);
        uiLoader.getAddBrandController().bt_addButton.setText("Add");
        uiLoader.getAddBrandController().tf_BrandName.setText("");
        uiLoader.getAddBrandController().lbl_prompText.setText("");

        uiLoader.getAddStockBrandStage().setTitle("Add Brand Name");
        uiLoader.getAddStockBrandStage().showAndWait();

    }


    public void buttonEditAction(){

        StockBrandName stockBrandName = tableView_BrandName.getSelectionModel().getSelectedItem();

        if(stockBrandName!=null){

            uiLoader.getAddBrandController().setUiLoader(this.uiLoader);
            uiLoader.getAddBrandController().setOldStockBrandName(stockBrandName);
            uiLoader.getAddBrandController().bt_addButton.setText("Update Data");
            uiLoader.getAddBrandController().tf_BrandName.setText(stockBrandName.getBrandName());
            uiLoader.getAddBrandController().lbl_prompText.setText("");

            uiLoader.getAddStockBrandStage().setTitle("Update Brand Name");
            uiLoader.getAddStockBrandStage().showAndWait();


        }
    }

    private void setContextMenuOnTableView(){

        disable_brand.setOnAction(event -> {

            stdb.openConnection();
            StockBrandName stockBrandName = tableView_BrandName.getSelectionModel().getSelectedItem();

            if(stockBrandName!= null){

                stdb.disableBrand(stockBrandName);
                stdb.closeConnection();
                displayTableView();
            }

        });
        enable_brand.setOnAction(event -> {

            stdb.openConnection();
            StockBrandName stockBrandName = tableView_BrandName.getSelectionModel().getSelectedItem();

            if(stockBrandName!= null){

                stdb.enableBrand(stockBrandName);
                stdb.closeConnection();
                displayTableView();
            }

        });

        edit_brand.setOnAction(event -> {

            StockBrandName stockBrandName = tableView_BrandName.getSelectionModel().getSelectedItem();

            if(stockBrandName!=null){

                uiLoader.getAddBrandController().setUiLoader(this.uiLoader);
                uiLoader.getAddBrandController().setOldStockBrandName(stockBrandName);
                uiLoader.getAddBrandController().bt_addButton.setText("Update Data");
                uiLoader.getAddBrandController().tf_BrandName.setText(stockBrandName.getBrandName());
                uiLoader.getAddBrandController().lbl_prompText.setText("");

                uiLoader.getAddStockBrandStage().setTitle("Update Brand Name");
                uiLoader.getAddStockBrandStage().showAndWait();


            }

        });


        brand_context.getItems().add(disable_brand);
        brand_context.getItems().add(enable_brand);
        brand_context.getItems().add(edit_brand);

        tableView_BrandName.setContextMenu(brand_context);


    }

}
