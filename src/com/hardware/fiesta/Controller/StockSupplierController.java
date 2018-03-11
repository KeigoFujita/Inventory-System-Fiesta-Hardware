package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.StockDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.StockSupplier;
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

public class StockSupplierController {


    @FXML
    private JFXTextField tf_search;


    @FXML
    private TableView<StockSupplier> tableView_Suppliers;

    @FXML
    private TableColumn<StockSupplier, Integer> tc_supplierId;

    @FXML
    private TableColumn<StockSupplier, String> tc_supplierName;

    @FXML
    private TableColumn<StockSupplier, String> tc_supplierAddress;

    @FXML
    private TableColumn<StockSupplier, String> tc_supplierContact;

    @FXML
    private TableColumn<StockSupplier, String> tc_supplierEmail;

    @FXML
    private TableColumn<StockSupplier, String> tc_supplierStatus;

    private static ObservableList<StockSupplier> stockSuppliers;


    private ContextMenu supplier_context = new ContextMenu();

    private MenuItem disable_supplier = new MenuItem("Disable Supplier");
    private MenuItem enable_supplier = new MenuItem("Enable Supplier");
    private MenuItem edit_supplier = new MenuItem("Edit Supplier");


    private StockDatabaseConnector stdb;
    private UILoader uiLoader;

    public void loadDataInObservableList(){

        stdb.openConnection();
        stockSuppliers = FXCollections.observableArrayList(stdb.getStockSupplierList());
        stdb.closeConnection();

    }

    public void setNameInColumn(){

        stdb.openConnection();
        ArrayList<String> columnNames = stdb.getStockSuppliersColumnName();
        stdb.closeConnection();

        tc_supplierId.setText(columnNames.get(0));
        tc_supplierName.setText(columnNames.get(1));
        tc_supplierAddress.setText(columnNames.get(2));
        tc_supplierContact.setText(columnNames.get(3));
        tc_supplierEmail.setText(columnNames.get(4));
        tc_supplierStatus.setText(columnNames.get(5));


    }
    public void setDataOnColumn(){


        tc_supplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        tc_supplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        tc_supplierAddress.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));
        tc_supplierContact.setCellValueFactory(new PropertyValueFactory<>("supplierContactNumber"));
        tc_supplierEmail.setCellValueFactory(new PropertyValueFactory<>("supplierEmailAddress"));
        tc_supplierStatus.setCellValueFactory(new PropertyValueFactory<>("supplierStatus"));

        tableView_Suppliers.setItems(stockSuppliers);

    }

    public void displayTableView(){

        setNameInColumn();
        loadDataInObservableList();
        setDataOnColumn();
        setContextMenuOnTableView();

    }

    public void displayTableView(ObservableList<StockSupplier> stockSuppliers){

        setNameInColumn();
        loadDataInObservableList();
        setDataOnColumn();
        setContextMenuOnTableView();

        tableView_Suppliers.setItems(stockSuppliers);

    }

    @FXML
    void searchAction(KeyEvent event) {

        String newValue = tf_search.getText();
        FilteredList<StockSupplier> filteredData = new FilteredList<>(stockSuppliers);

        filteredData.setPredicate(stockSupplier -> {

            if(newValue == null|| newValue.isEmpty()){

                return true;
            }

            // search in Names

            String lowerCaseFilter = newValue.toLowerCase();

            return stockSupplier.getSupplierName().toLowerCase().contains(lowerCaseFilter) |
                   stockSupplier.getSupplierAddress().toLowerCase().contains(lowerCaseFilter)|
                   stockSupplier.getSupplierContactNumber().toLowerCase().contains(lowerCaseFilter) |
                  stockSupplier.getSupplierEmailAddress().toLowerCase().contains(lowerCaseFilter);

        });

        SortedList<StockSupplier> sortedList = new SortedList<>(filteredData);
        displayTableView(FXCollections.observableArrayList(sortedList));

    }

    public void setUiLoader(UILoader uiLoader) {
        this.uiLoader = uiLoader;
        this.stdb = uiLoader.getStdb();
    }


    public void btAddAction(){

        uiLoader.getAddSupplierController().setUiLoader(this.uiLoader);
        uiLoader.getAddSupplierController().setMode(uiLoader.getAddSupplierController().ADD_SUPPLIER_MODE);
        uiLoader.getAddSupplierController().clearTextFields();
        uiLoader.getAddSupplierController().tf_promptext.setText("");

        uiLoader.getAddSupplierStage().setTitle("Add Supplier");
        uiLoader.getAddSupplierStage().showAndWait();
    }


    public void buttonEditAction(){
        StockSupplier stockSupplier = tableView_Suppliers.getSelectionModel().getSelectedItem();

        if(stockSupplier!=null){

            uiLoader.getAddSupplierController().setUiLoader(this.uiLoader);
            uiLoader.getAddSupplierController().setMode(uiLoader.getAddSupplierController().UPDATE_SUPPLIER_MODE);
            uiLoader.getAddSupplierController().setOldStockSupplier(stockSupplier);
            uiLoader.getAddSupplierController().tf_promptext.setText("");


            uiLoader.getAddSupplierStage().setTitle("Edit Supplier");
            uiLoader.getAddSupplierStage().showAndWait();

        }




    }

    private void setContextMenuOnTableView(){

        disable_supplier.setOnAction(event -> {

            stdb.openConnection();
            StockSupplier stockSupplier = tableView_Suppliers.getSelectionModel().getSelectedItem();

            if(stockSupplier!= null){

                stdb.disableSupplier(stockSupplier);
                stdb.closeConnection();
                displayTableView();
            }

        });
        enable_supplier.setOnAction(event -> {


            stdb.openConnection();
            StockSupplier stockSupplier = tableView_Suppliers.getSelectionModel().getSelectedItem();

            if(stockSupplier!= null){

                stdb.enableSupplier(stockSupplier);
                stdb.closeConnection();
                displayTableView();
            }

        });
        edit_supplier.setOnAction(event -> {
            StockSupplier stockSupplier = tableView_Suppliers.getSelectionModel().getSelectedItem();

            if(stockSupplier!=null){

                uiLoader.getAddSupplierController().setUiLoader(this.uiLoader);
                uiLoader.getAddSupplierController().setMode(uiLoader.getAddSupplierController().UPDATE_SUPPLIER_MODE);
                uiLoader.getAddSupplierController().setOldStockSupplier(stockSupplier);
                uiLoader.getAddSupplierController().tf_promptext.setText("");


                uiLoader.getAddSupplierStage().setTitle("Edit Supplier");
                uiLoader.getAddSupplierStage().showAndWait();

            }
        });


        supplier_context.getItems().add(disable_supplier);
        supplier_context.getItems().add(enable_supplier);
        supplier_context.getItems().add(edit_supplier);

        tableView_Suppliers.setContextMenu(supplier_context);


    }


}
