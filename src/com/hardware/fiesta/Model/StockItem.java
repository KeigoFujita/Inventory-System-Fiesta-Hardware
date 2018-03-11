package com.hardware.fiesta.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StockItem {

    private SimpleIntegerProperty itemId;
    private SimpleStringProperty  itemName;
    private SimpleDoubleProperty  itemCost;
    private SimpleIntegerProperty itemQty;
    private SimpleStringProperty  itemUnit;
    private SimpleIntegerProperty itemBrandName;
    private SimpleIntegerProperty itemType;
    private SimpleIntegerProperty itemCategory;
    private SimpleIntegerProperty itemSupplier;
    private SimpleStringProperty itemBarcodeId;
    private SimpleStringProperty  itemDescription;
    private SimpleStringProperty  itemStatus;
    private SimpleStringProperty  itemLastUpdated;

    public StockItem(int itemId, String itemName, double itemCost, int itemQty, String itemUnit ,int itemBrandName, int itemType, int itemCategory, int itemSupplier, String itemBarcodeId, String itemDescription, String itemStatus, String itemLastUpdated) {

        this.itemId = new SimpleIntegerProperty(itemId);
        this.itemName = new SimpleStringProperty(itemName);
        this.itemCost = new SimpleDoubleProperty(itemCost);
        this.itemQty =new SimpleIntegerProperty(itemQty);
        this.itemUnit = new SimpleStringProperty(itemUnit);
        this.itemBrandName =new SimpleIntegerProperty(itemBrandName);
        this.itemType = new SimpleIntegerProperty(itemType);
        this.itemCategory = new SimpleIntegerProperty(itemCategory);
        this.itemSupplier = new SimpleIntegerProperty(itemSupplier);
        this.itemBarcodeId = new SimpleStringProperty(itemBarcodeId);
        this.itemDescription = new SimpleStringProperty(itemDescription);
        this.itemStatus = new SimpleStringProperty(itemStatus);
        this.itemLastUpdated = new SimpleStringProperty(itemLastUpdated);

    }


    public StockItem(String itemName, double itemCost, int itemQty, String itemUnit, int itemBrandName, int itemType, int itemCategory, int itemSupplier, String itemBarcodeId, String itemDescription) {

        this.itemName = new SimpleStringProperty(itemName);
        this.itemCost = new SimpleDoubleProperty(itemCost);
        this.itemQty =new SimpleIntegerProperty(itemQty);
        this.itemUnit = new SimpleStringProperty(itemUnit);
        this.itemBrandName =new SimpleIntegerProperty(itemBrandName);
        this.itemType = new SimpleIntegerProperty(itemType);
        this.itemCategory = new SimpleIntegerProperty(itemCategory);
        this.itemSupplier = new SimpleIntegerProperty(itemSupplier);
        this.itemBarcodeId = new SimpleStringProperty(itemBarcodeId);
        this.itemDescription = new SimpleStringProperty(itemDescription);

    }


    public int getItemId() {
        return itemId.get();
    }

    public SimpleIntegerProperty itemIdProperty() {
        return itemId;
    }

    public String getItemName() {
        return itemName.get();
    }

    public SimpleStringProperty itemNameProperty() {
        return itemName;
    }

    public double getItemCost() {
        return itemCost.get();
    }

    public SimpleDoubleProperty itemCostProperty() {
        return itemCost;
    }

    public int getItemQty() {
        return itemQty.get();
    }

    public SimpleIntegerProperty itemQtyProperty() {
        return itemQty;
    }

    public int getItemBrandName() {
        return itemBrandName.get();
    }

    public SimpleIntegerProperty itemBrandNameProperty() {
        return itemBrandName;
    }

    public int getItemType() {
        return itemType.get();
    }

    public SimpleIntegerProperty itemTypeProperty() {
        return itemType;
    }

    public int getItemCategory() {
        return itemCategory.get();
    }

    public SimpleIntegerProperty itemCategoryProperty() {
        return itemCategory;
    }

    public int getItemSupplier() {
        return itemSupplier.get();
    }

    public SimpleIntegerProperty itemSupplierProperty() {
        return itemSupplier;
    }

    public String getItemBarcodeId() {
        return itemBarcodeId.get();
    }

    public SimpleStringProperty itemBarcodeIdProperty() {
        return itemBarcodeId;
    }

    public String getItemDescription() {
        return itemDescription.get();
    }

    public SimpleStringProperty itemDescriptionProperty() {
        return itemDescription;
    }

    public String getItemStatus() {
        return itemStatus.get();
    }

    public SimpleStringProperty itemStatusProperty() {
        return itemStatus;
    }

    public String getItemLastUpdated() {
        return itemLastUpdated.get();
    }

    public SimpleStringProperty itemLastUpdatedProperty() {
        return itemLastUpdated;
    }

    public String getItemUnit() {
        return itemUnit.get();
    }

    public SimpleStringProperty itemUnitProperty() {
        return itemUnit;
    }

    public void setItemQty(int qty){

        this.itemQty = new SimpleIntegerProperty(qty);

    }
}
