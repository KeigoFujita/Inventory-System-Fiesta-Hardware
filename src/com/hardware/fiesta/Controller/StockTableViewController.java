package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.StockDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.StockItem;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class StockTableViewController {



    @FXML
    private JFXTextField tf_search;

    @FXML
    private TableView<StockItem> tableView_Stocks;

    @FXML
    private TableColumn<StockItem, Integer> tc_itemId;

    @FXML
    private TableColumn<StockItem, String> tc_itemName;

    @FXML
    private TableColumn<StockItem, Double> tc_itemCost;

    @FXML
    private TableColumn<StockItem, Integer> tc_itemQty;

    @FXML
    private TableColumn<StockItem, String> tc_itemUnit;

    @FXML
    private TableColumn<StockItem,Integer> tc_itemBrandName;

    @FXML
    private TableColumn<StockItem,Integer> tc_itemType;

    @FXML
    private TableColumn<StockItem,Integer> tc_itemCategory;

    @FXML
    private TableColumn<StockItem, Integer> tc_itemSupplier;

    @FXML
    private TableColumn<StockItem, String> tc_itemBarcodeId;

    @FXML
    private TableColumn<StockItem, String> tc_itemDescription;

    @FXML
    private TableColumn<StockItem, String> tc_itemLastUpdated;

    @FXML
    private TableColumn<StockItem, String> tc_itemStatus;

    private ObservableList<StockItem> stockItems;

    private ContextMenu item_context = new ContextMenu();

    private MenuItem disable_item = new MenuItem("Disable Item");
    private MenuItem enable_item = new MenuItem("Enable Item");
    private MenuItem edit_item = new MenuItem("Edit Item");
    private MenuItem replenish_item  = new MenuItem("Replenish Item");
    private MenuItem deduct_item  = new MenuItem("Deduct Item Qty");



    private UILoader uiLoader;
    private StockDatabaseConnector stdb;





    private void displayTableColumnNames(){

        ArrayList<String> columnNames = stdb.getStockItemColumnName();

        tc_itemId.setText(columnNames.get(0));
        tc_itemName.setText(columnNames.get(1));
        tc_itemCost.setText(columnNames.get(2));
        tc_itemQty.setText(columnNames.get(3));
        tc_itemUnit.setText(columnNames.get(4));
        tc_itemBrandName.setText(columnNames.get(5));
        tc_itemType.setText(columnNames.get(6));
        tc_itemCategory.setText(columnNames.get(7));
        tc_itemSupplier.setText(columnNames.get(8));
        tc_itemBarcodeId.setText(columnNames.get(9));
        tc_itemDescription.setText(columnNames.get(10));
        tc_itemLastUpdated.setText(columnNames.get(11));
        tc_itemStatus.setText(columnNames.get(12));

    }

    public void buttonEditAction(){

        StockItem stockItem =  tableView_Stocks.getSelectionModel().getSelectedItem();

        if(stockItem!=null) {

            this.uiLoader.getUpdateItemController().setUiLoader(this.uiLoader);
            this.uiLoader.getUpdateItemController().setStockItem(stockItem);


            this.uiLoader.getMainMenuController().mainViewContainer.getChildren().clear();
            this.uiLoader.getMainMenuController().mainViewContainer.setAlignment(Pos.TOP_LEFT);
            this.uiLoader.getMainMenuController().mainViewContainer.getChildren().add(this.uiLoader.getUpdateItemRootLayout());
        }
    }


    public void buttonAddAction(){

        this.uiLoader.getMainMenuController().mainViewContainer.getChildren().clear();
        this.uiLoader.getMainMenuController().mainViewContainer.setAlignment(Pos.TOP_LEFT);

        this.uiLoader.getAddItemController().setUiLoader(this.uiLoader);
        this.uiLoader.getMainMenuController().mainViewContainer.getChildren().add(uiLoader.getAddItemRootLayout());


    }


    public void setUiLoader(UILoader uiLoader) {
        this.uiLoader = uiLoader;
        this.stdb = uiLoader.getStdb();
    }

    @FXML
    void searchAction() {

        String newValue = tf_search.getText();
        FilteredList<StockItem> filteredData = new FilteredList<>(stockItems);

        filteredData.setPredicate(stockItem -> {

            if(newValue == null|| newValue.isEmpty()){

                return true;
            }

            // search in Names

            String lowerCaseFilter = newValue.toLowerCase().trim();

            return (stockItem.getItemName().trim().toLowerCase().contains(lowerCaseFilter) ||
                    Double.toString(stockItem.getItemCost()).trim().toLowerCase().contains(lowerCaseFilter)) ||
                    Integer.toString(stockItem.getItemQty()).trim().toLowerCase().contains(lowerCaseFilter) ||
                    stockItem.getItemBarcodeId().trim().toLowerCase().contains(lowerCaseFilter);

        });

        SortedList<StockItem> sortedList = new SortedList<>(filteredData);
        displayData(FXCollections.observableArrayList(sortedList));

    }


    public void displayData(){

        displayTableColumnNames();
        loadDatainObservableList();
        setDataInColumnNames();
        setContextMenuOnTableView();


        tableView_Stocks.setItems(this.stockItems);

    }
    private void displayData(ObservableList<StockItem> stockItems){

        displayTableColumnNames();
        loadDatainObservableList();
        setDataInColumnNames();
        setContextMenuOnTableView();


        tableView_Stocks.setItems(stockItems);

    }
    private void loadDatainObservableList(){


        stdb.openConnection();
        stockItems = FXCollections.observableArrayList(stdb.getItemList());
        stdb.closeConnection();

    }
    private void setDataInColumnNames(){

        tc_itemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        tc_itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tc_itemCost.setCellValueFactory(new PropertyValueFactory<>("itemCost"));
        tc_itemQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));
        tc_itemUnit.setCellValueFactory(new PropertyValueFactory<>("itemUnit"));
        tc_itemBrandName.setCellValueFactory(new PropertyValueFactory<>("itemBrandName"));
        tc_itemType.setCellValueFactory(new PropertyValueFactory<>("itemType"));
        tc_itemCategory.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
        tc_itemSupplier.setCellValueFactory(new PropertyValueFactory<>("itemSupplier"));
        tc_itemBarcodeId.setCellValueFactory(new PropertyValueFactory<>("itemBarcodeId"));
        tc_itemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        tc_itemStatus.setCellValueFactory(new PropertyValueFactory<>("itemStatus"));
        tc_itemLastUpdated.setCellValueFactory(new PropertyValueFactory<>("itemLastUpdated"));




    }


    private void setContextMenuOnTableView() {

        disable_item.setOnAction(event -> {

            StockItem stockItem = tableView_Stocks.getSelectionModel().getSelectedItem();

            if(stockItem!=null) {
                uiLoader.getDisableItemController().setUiLoader(this.uiLoader);
                uiLoader.getDisableItemController().setStockItem(stockItem);
                uiLoader.getDisableItemStage().showAndWait();
            }

        });

        enable_item.setOnAction(event -> {

            stdb.openConnection();
            StockItem stockItem = tableView_Stocks.getSelectionModel().getSelectedItem();

            if (stockItem != null) {

                stdb.enableItem(stockItem);
                stdb.closeConnection();
                displayData();
            }

        });
        edit_item.setOnAction(event -> buttonEditAction());

        replenish_item.setOnAction(event -> {

            StockItem stockItem = tableView_Stocks.getSelectionModel().getSelectedItem();

            if(stockItem!=null){

                uiLoader.getReplenishPromptFormController().setUiLoader(this.uiLoader);
                uiLoader.getReplenishPromptFormController().setStockItem(stockItem);
                uiLoader.getReplenishPromptStage().showAndWait();

            }


        });

        deduct_item.setOnAction(event -> {

            StockItem stockItem = tableView_Stocks.getSelectionModel().getSelectedItem();

            if(stockItem !=null){

                uiLoader.getDeductItemQtyFormController().setUiLoader(this.uiLoader);
                uiLoader.getDeductItemQtyFormController().setStockItem(stockItem);

                uiLoader.getDeductItemQtyStage().showAndWait();

            }


        });


        item_context.getItems().add(disable_item);
        item_context.getItems().add(enable_item);
        item_context.getItems().add(edit_item);
        item_context.getItems().add(replenish_item);
        item_context.getItems().add(deduct_item);

        tableView_Stocks.setContextMenu(item_context);
    }




}
