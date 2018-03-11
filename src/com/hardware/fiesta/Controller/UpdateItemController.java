package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.StockDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.Collator;

public class UpdateItemController {

    @FXML
    private JFXTextField tf_itemName;

    @FXML
    private Label lbl_namePromptText;

    @FXML
    private JFXComboBox<String> cb_cost;

    @FXML
    private JFXTextField tf_itemCost;

    @FXML
    private Label lbl_costPrompText;

    @FXML
    private JFXComboBox<String> cb_qty;

    @FXML
    private JFXTextField tf_itemQty;

    @FXML
    private Label lbl_qtyPrompText;

    @FXML
    private JFXTextField tf_ItemBarcode;

    @FXML
    private Label lbl_barcodePromptText;

    @FXML
    private JFXTextField tf_description;

    @FXML
    private Label lbl_descriptionPromptText;

    @FXML
    public JFXComboBox<String> cb_brand;

    @FXML
    private JFXButton bt_addbrand;

    @FXML
    private Label lbl_brandPrompt;

    @FXML
    public JFXComboBox<String> cb_category;

    @FXML
    private JFXButton bt_addCategory;

    @FXML
    private Label lbl_categoryPrompt;

    @FXML
    public JFXComboBox<String> cb_type;

    @FXML
    private JFXButton bt_addType;

    @FXML
    private Label lbl_typePrompt;

    @FXML
    public JFXComboBox<String> cb_supplier;

    @FXML
    private JFXButton bt_supplier;

    @FXML
    private Label lbl_supplierPrompt;

    @FXML
    private JFXButton bt_addItem;

    private StockItem stockItem;
    private UILoader uiLoader;
    private StockDatabaseConnector stdb;

    private String selectedBrand;
    private String selectedCategory;
    private String selectedType;
    private String selectedSupplier;



    public void initialize(){

        lbl_namePromptText.setText("");
        lbl_costPrompText.setText("");
        lbl_qtyPrompText.setText("");
        lbl_barcodePromptText.setText("");
        lbl_descriptionPromptText.setText("");

        lbl_brandPrompt.setText("");
        lbl_categoryPrompt.setText("");
        lbl_typePrompt.setText("");
        lbl_supplierPrompt.setText("");

        cb_cost.getItems().addAll("Pesos","Dollars","Euros");
        cb_cost.getSelectionModel().select(0);

        cb_qty.getItems().addAll("Pieces","Liters","Boxes","Gallons","Kilos","Meters","Feet");
        cb_qty.getSelectionModel().select(0);


    }


    public void setStockItem(StockItem stockItem){

        this.stockItem = stockItem;
        setItemsInComboBox();
        cb_qty.getSelectionModel().select(stockItem.getItemUnit());
        System.out.println(cb_qty.getSelectionModel().getSelectedItem());
        tf_itemName.setText(this.stockItem.getItemName());
        tf_itemCost.setText(Double.toString(this.stockItem.getItemCost()));
        tf_itemQty.setText(Integer.toString(this.stockItem.getItemQty()));
        tf_ItemBarcode.setText(this.stockItem.getItemBarcodeId());
        tf_description.setText(this.stockItem.getItemDescription());

        stdb.openConnection();
        selectedBrand = stdb.searchStockBrandName(stockItem.getItemBrandName()).getBrandName();
        selectedCategory = stdb.searchStockCategory(stockItem.getItemCategory()).getCategoryName();
        selectedType = stdb.searchStockType(stockItem.getItemType()).getTypeName();
        selectedSupplier = stdb.searchStockSupplier(stockItem.getItemSupplier()).getSupplierName();
        stdb.closeConnection();


        cb_brand.getSelectionModel().select(this.selectedBrand);
        cb_category.getSelectionModel().select(selectedCategory);
        cb_type.getSelectionModel().select(selectedType);
        cb_supplier.getSelectionModel().select(selectedSupplier);



    }

    public void setItemsInComboBox(){

        cb_category.getItems().clear();
        cb_type.getItems().clear();
        cb_brand.getItems().clear();
        cb_supplier.getItems().clear();

        stdb.openConnection();

        ObservableList<String> stockCategoryItems = FXCollections.observableArrayList();
        ObservableList<String> stockTypeItems = FXCollections.observableArrayList();
        ObservableList<String> stockBrandItems = FXCollections.observableArrayList();
        ObservableList<String> stockSupplierItems = FXCollections.observableArrayList();


        for(StockCategory stockCategory: stdb.getStockCategoryList()){

            stockCategoryItems.add(stockCategory.getCategoryName());

        }
        for(StockType stockType: stdb.getStockTypeList()){

            stockTypeItems.add(stockType.getTypeName());


        }

        for(StockBrandName stockBrandName: stdb.getStockBrandNameList() ){

            stockBrandItems.add(stockBrandName.getBrandName());



        }

        for(StockSupplier stockSupplier: stdb.getStockSupplierList()){

            stockSupplierItems.add(stockSupplier.getSupplierName());

        }


        cb_category.getItems().addAll(new SortedList<>(stockCategoryItems, Collator.getInstance()));
        cb_type.getItems().addAll(new SortedList<>(stockTypeItems, Collator.getInstance()));
        cb_brand.getItems().addAll(new SortedList<>(stockBrandItems, Collator.getInstance()));
        cb_supplier.getItems().addAll(new SortedList<>(stockSupplierItems, Collator.getInstance()));

        if(selectedCategory!=null){
            cb_category.getSelectionModel().select(selectedCategory);
        }

        if(selectedType!=null){
            cb_type.getSelectionModel().select(selectedType);
        }

        if(selectedBrand!=null){

            cb_brand.getSelectionModel().select(selectedBrand);
        }

        if(selectedSupplier!=null){
            cb_supplier.getSelectionModel().select(selectedSupplier);
        }


        stdb.closeConnection();

    }

    public void setUiLoader(UILoader uiLoader){

        this.uiLoader = uiLoader;
        this.stdb = uiLoader.getStdb();

        clearValidations();

    }

    public void setStdb(StockDatabaseConnector stdb){

        this.stdb = stdb;
    }

    public void buttonAddBrandAction(){


        uiLoader.getAddBrandController().setUiLoader(this.uiLoader);
        uiLoader.getAddBrandController().bt_addButton.setText("Add");
        uiLoader.getAddBrandController().tf_BrandName.setText("");
        uiLoader.getAddBrandController().lbl_prompText.setText("");

        uiLoader.getAddStockBrandStage().setTitle("Add Brand Name");
        uiLoader.getAddStockBrandStage().showAndWait();

    }

    public void addCategoryAction(){

        uiLoader.getAddStockCategoryController().setUiLoader(this.uiLoader);
        uiLoader.getAddStockCategoryController().tf_CategoryName.setText("");
        uiLoader.getAddStockCategoryController().bt_addButton.setText("Add");
        uiLoader.getAddStockCategoryController().tf_prompText.setText("");

        uiLoader.getAddStockCategoryStage().setTitle("Add Category");
        uiLoader.getAddStockCategoryStage().showAndWait();

    }

    public void addTypeAction(){

        uiLoader.getAddStockTypeController().setUiLoader(this.uiLoader);
        uiLoader.getAddStockTypeController().tf_TypeName.setText("");
        uiLoader.getAddStockTypeController().tf_addButton.setText("Add");
        uiLoader.getAddStockTypeController().tf_prompText.setText("");

        uiLoader.getAddStockTypeStage().setTitle("Add Type");
        uiLoader.getAddStockTypeStage().showAndWait();



    }

    public void addSupplierAction(){


        uiLoader.getAddSupplierController().setUiLoader(this.uiLoader);
        uiLoader.getAddSupplierController().setMode(uiLoader.getAddSupplierController().ADD_SUPPLIER_MODE);
        uiLoader.getAddSupplierController().clearTextFields();
        uiLoader.getAddSupplierController().tf_promptext.setText("");

        uiLoader.getAddSupplierStage().setTitle("Add Supplier");
        uiLoader.getAddSupplierStage().showAndWait();

    }

    private void clearValidations(){

        lbl_namePromptText.setText("");
        lbl_costPrompText.setText("");
        lbl_qtyPrompText.setText("");
        lbl_barcodePromptText.setText("");
        lbl_brandPrompt.setText("");
        lbl_typePrompt.setText("");
        lbl_categoryPrompt.setText("");
        lbl_supplierPrompt.setText("");
        lbl_descriptionPromptText.setText("");
    }

    private void validateName(){

        String value = tf_itemName.getText().trim();

        if (value.isEmpty()) {

            lbl_namePromptText.setText("Invalid Input !!!");

        } else {

            if(value.equals(this.stockItem.getItemName())){

                lbl_namePromptText.setText("");
            }else{

                stdb.openConnection();
                if(stdb.validateStockItemName(value)){

                    lbl_namePromptText.setText("Item Name is already in the database!");
                    stdb.closeConnection();
                } else {

                    lbl_namePromptText.setText("");
                    stdb.closeConnection();

                }

            }


        }

    }
    private void validateCost(){

        String value = tf_itemCost.getText().trim();

        if( value.trim().isEmpty()){
            lbl_costPrompText.setText("Required input field!");
        } else  if (!value.matches("\\d{0,9}([.]\\d{0,4})?")) {

            lbl_costPrompText.setText("Invalid Input !!!");

        } else {

            lbl_costPrompText.setText("");

        }


    }
    private void validateQuantity(){

        String value = tf_itemQty.getText().trim();

        if(value.trim().isEmpty()){

            lbl_qtyPrompText.setText("Input field required!");

        }else if (!value.matches("\\d{0,10} ?")) {

            lbl_qtyPrompText.setText("Invalid Input !!!");


        }else {
            lbl_qtyPrompText.setText("");
        }

    }
    private void validateBarcode(){

        String value = tf_ItemBarcode.getText().trim();

        if(value.isEmpty()){

            lbl_barcodePromptText.setText("Input Field Required!");

        }else if (!value.matches("\\d{15}?")) {

            lbl_barcodePromptText.setText("Invalid Input !! 15 Numbers needed");

        }else {


            if( !value.equals(this.stockItem.getItemBarcodeId())){


                stdb.openConnection();
                if(stdb.validateStockItemBarcode(value)){


                    lbl_barcodePromptText.setText("Item Barcode is already in the database!");
                    stdb.closeConnection();
                }else {

                    lbl_barcodePromptText.setText("");
                    stdb.closeConnection();

                }


            }else{

                lbl_barcodePromptText.setText("");
            }


        }

    }


    private void validateBrandName(){

        if(cb_brand.getSelectionModel().getSelectedItem()== null){

            lbl_brandPrompt.setText("Please Select Brand Name!");

        }else{

            lbl_brandPrompt.setText("");
        }

    }
    private void validateCategoryName(){

        if(cb_category.getSelectionModel().getSelectedItem() == null){

            lbl_categoryPrompt.setText("Please Select Category Name!");

        }else{

            lbl_categoryPrompt.setText("");
        }

    }
    private void validateTypeName(){

        if(cb_type.getSelectionModel().getSelectedItem() == null){

            lbl_typePrompt.setText("Please Select Type Name!");

        }else{

            lbl_typePrompt.setText("");
        }

    }
    private void validateSupplierName(){

        if(cb_supplier.getSelectionModel().getSelectedItem() == null){

            lbl_supplierPrompt.setText("Please Select Supplier Name!");

        }else{

            lbl_supplierPrompt.setText("");
        }

    }


    private boolean validateFields(){

        validateName();
        validateQuantity();
        validateCost();
        validateBarcode();

        validateBrandName();
        validateCategoryName();
        validateTypeName();
        validateSupplierName();


          return lbl_namePromptText.getText().equals("")
                && lbl_costPrompText.getText().equals("")
                && lbl_qtyPrompText.getText().equals("")
                && lbl_barcodePromptText.getText().equals("")
                && lbl_brandPrompt.getText().equals("")
                && lbl_categoryPrompt.getText().equals("")
                &&  lbl_typePrompt.getText().equals("")
                && lbl_supplierPrompt.getText().equals("");

    }
    public void addItemAction(){

        validateFields();
        if(validateFields()){

            String itemName = tf_itemName.getText();
            String itemCost = tf_itemCost.getText();
            String itemQty =  tf_itemQty.getText();
            System.out.println(cb_qty.getSelectionModel().getSelectedItem());
            String itemUnit =  cb_qty.getSelectionModel().getSelectedItem();
            String itemBarcode = tf_ItemBarcode.getText();
            String itemDescription = tf_description.getText();
            String brandName = cb_brand.getSelectionModel().getSelectedItem();
            String category  = cb_category.getSelectionModel().getSelectedItem();
            String type = cb_type.getSelectionModel().getSelectedItem();
            String supplier = cb_supplier.getSelectionModel().getSelectedItem();


            stdb.openConnection();
            StockBrandName stockBrandName = stdb.searchStockBrandName(brandName);
            StockCategory  stockCategory  = stdb.searchStockCategory(category);
            StockType      stockType      = stdb.searchStockType(type);
            StockSupplier  stockSupplier  = stdb.searchStockSupplier(supplier);



            StockItem stockItem = new StockItem(itemName,
                    Double.parseDouble(itemCost),
                    Integer.parseInt(itemQty),
                    itemUnit,
                    stockBrandName.getBrandId(),
                    stockType.getTypeId(),
                    stockCategory.getCategoryId(),
                    stockSupplier.getSupplierId(),
                    itemBarcode,
                    itemDescription);



            stdb.updateItem(this.stockItem,stockItem);
            stdb.closeConnection();




        }
    }

}
