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
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
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


    private ContextMenu category_context = new ContextMenu();

    private MenuItem disable_category = new MenuItem("Disable Category");
    private MenuItem enable_category = new MenuItem("Enable Category");
    private MenuItem edit_category   = new MenuItem("Edit Category");

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
        setContextMenuOnTableView();

        tableView_Category.setItems(this.stockCategories);

    }

    public void displayTableView(ObservableList<StockCategory> stockCategories){

        setNameInColumn();
        loadDataInObservableList();
        setDataOnColumn();
        setContextMenuOnTableView();

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

            String lowerCaseFilter = newValue.toLowerCase().trim();

            return stockCategory.getCategoryName().toLowerCase().contains(lowerCaseFilter);

        });

        SortedList<StockCategory> sortedList = new SortedList<>(filteredData);
        displayTableView(FXCollections.observableArrayList(sortedList));

    }


    public void buttonAddAction(){

        uiLoader.getAddStockCategoryController().setUiLoader(this.uiLoader);
        uiLoader.getAddStockCategoryController().tf_CategoryName.setAlignment(Pos.CENTER);
        uiLoader.getAddStockCategoryController().tf_CategoryName.setText("");
        uiLoader.getAddStockCategoryController().bt_addButton.setText("Add");
        uiLoader.getAddStockCategoryController().tf_prompText.setText("");

        uiLoader.getAddStockCategoryStage().setTitle("Add Category");
        uiLoader.getAddStockCategoryStage().showAndWait();

        displayTableView();


    }

    public void buttonEditAction(){

        StockCategory stockCategory = tableView_Category.getSelectionModel().getSelectedItem();

        if(stockCategory!=null){

            this.uiLoader.getAddStockCategoryController().setUiLoader(this.uiLoader);
            this.uiLoader.getAddStockCategoryController().tf_CategoryName.setAlignment(Pos.CENTER);
            this.uiLoader.getAddStockCategoryController().tf_prompText.setText("");
            this.uiLoader.getAddStockCategoryController().setOldStockCategory(stockCategory);
            this.uiLoader.getAddStockCategoryController().bt_addButton.setText("Update Data");
            this.uiLoader.getAddStockCategoryController().tf_CategoryName.setText(stockCategory.getCategoryName());

            uiLoader.getAddStockCategoryStage().setTitle("Update Data");
            uiLoader.getAddStockCategoryStage().showAndWait();

            displayTableView();

        }








    }


    private void setContextMenuOnTableView(){

        disable_category.setOnAction(event -> {

            stdb.openConnection();
            StockCategory  stockCategory = tableView_Category.getSelectionModel().getSelectedItem();

            if(stockCategory!= null){

                stdb.disableCategory(stockCategory);
                stdb.closeConnection();
                displayTableView();
            }

        });
        enable_category.setOnAction(event -> {

            stdb.openConnection();
            StockCategory  stockCategory = tableView_Category.getSelectionModel().getSelectedItem();

            if(stockCategory!= null){

                stdb.enableCategory(stockCategory);
                stdb.closeConnection();
                displayTableView();
            }

        });
        edit_category.setOnAction(event -> {

            StockCategory stockCategory = tableView_Category.getSelectionModel().getSelectedItem();

            if(stockCategory!=null){

                this.uiLoader.getAddStockCategoryController().setUiLoader(this.uiLoader);
                this.uiLoader.getAddStockCategoryController().tf_CategoryName.setAlignment(Pos.CENTER);
                this.uiLoader.getAddStockCategoryController().tf_CategoryName.setText(stockCategory.getCategoryName());
                this.uiLoader.getAddStockCategoryController().tf_prompText.setText("");
                this.uiLoader.getAddStockCategoryController().setOldStockCategory(stockCategory);
                this.uiLoader.getAddStockCategoryController().bt_addButton.setText("Update Data");


                uiLoader.getAddStockCategoryStage().setTitle("Update Data");
                uiLoader.getAddStockCategoryStage().showAndWait();

                displayTableView();

            }


        });


        category_context.getItems().add(disable_category);
        category_context.getItems().add(enable_category);
        category_context.getItems().add(edit_category);

        tableView_Category.setContextMenu(category_context);


    }






}
