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
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
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

    private ContextMenu type_context = new ContextMenu();

    private MenuItem disable_type = new MenuItem("Disable Type");
    private MenuItem enable_type = new MenuItem("Enable Type");
    private MenuItem edit_type  = new MenuItem("Edit Type");



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
        setContextMenuOnTableView();

        tableView_Type.setItems(this.stockTypes);

    }
    public void displayTableView(ObservableList<StockType> stockTypes){

        setNameInColumn();
        loadDataInObservableList();
        setDataOnColumn();
        setContextMenuOnTableView();

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

        uiLoader.getAddStockTypeController().setUiLoader(this.uiLoader);
        uiLoader.getAddStockTypeController().tf_TypeName.setAlignment(Pos.CENTER);
        uiLoader.getAddStockTypeController().tf_TypeName.setText("");
        uiLoader.getAddStockTypeController().tf_addButton.setText("Add");
        uiLoader.getAddStockTypeController().tf_prompText.setText("");

        uiLoader.getAddStockTypeStage().setTitle("Add Type");
        uiLoader.getAddStockTypeStage().showAndWait();

    }
    public void buttonEditAction(){



        StockType stockType = tableView_Type.getSelectionModel().getSelectedItem();

        if(stockType!=null){

            this.uiLoader.getAddStockTypeController().setUiLoader(this.uiLoader);
            this.uiLoader.getAddStockTypeController().tf_TypeName.setAlignment(Pos.CENTER);
            this.uiLoader.getAddStockTypeController().tf_prompText.setText("");
            this.uiLoader.getAddStockTypeController().setOldStockType(stockType);
            this.uiLoader.getAddStockTypeController().tf_addButton.setText("Update Data");
            this.uiLoader.getAddStockTypeController().tf_TypeName.setText(stockType.getTypeName());

            uiLoader.getAddStockTypeStage().setTitle("Update Data");
            uiLoader.getAddStockTypeStage().showAndWait();

            displayTableView();

        }

    }

    public void setUiLoader(UILoader uiLoader) {
        this.uiLoader = uiLoader;
        this.stdb = uiLoader.getStdb();
    }
    private void setContextMenuOnTableView(){



        disable_type.setOnAction(event -> {

            StockType stockType = tableView_Type.getSelectionModel().getSelectedItem();
            stdb.openConnection();

            if(stockType!= null){

                stdb.disableType(stockType);
                stdb.closeConnection();
                displayTableView();


            }

        });
        enable_type.setOnAction(event -> {

            StockType stockType = tableView_Type.getSelectionModel().getSelectedItem();
            stdb.openConnection();


            if(stockType!= null){

                stdb.enableType(stockType);
                stdb.closeConnection();
                displayTableView();

            }

        });
        edit_type.setOnAction(event -> {

            StockType stockType = tableView_Type.getSelectionModel().getSelectedItem();

            if(stockType!=null){

                this.uiLoader.getAddStockTypeController().setUiLoader(this.uiLoader);
                this.uiLoader.getAddStockTypeController().tf_TypeName.setAlignment(Pos.CENTER);
                this.uiLoader.getAddStockTypeController().tf_prompText.setText("");
                this.uiLoader.getAddStockTypeController().setOldStockType(stockType);
                this.uiLoader.getAddStockTypeController().tf_addButton.setText("Update Data");
                this.uiLoader.getAddStockTypeController().tf_TypeName.setText(stockType.getTypeName());

                uiLoader.getAddStockTypeStage().setTitle("Update Data");
                uiLoader.getAddStockTypeStage().showAndWait();

                displayTableView();
            }


        });



        type_context.getItems().add(disable_type);
        type_context.getItems().add(enable_type);
        type_context.getItems().add(edit_type);
        tableView_Type.setContextMenu(type_context);


        }




    }


