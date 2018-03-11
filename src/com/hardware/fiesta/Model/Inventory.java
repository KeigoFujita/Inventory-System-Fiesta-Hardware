package com.hardware.fiesta.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Inventory {

    private SimpleIntegerProperty inventoryId;
    private SimpleIntegerProperty inventoryItemId;
    private SimpleIntegerProperty initialStock;
    private SimpleStringProperty  initialDate;
    private SimpleIntegerProperty finalStock;
    private SimpleStringProperty  finalDate;
    private SimpleIntegerProperty itemSales;
    private SimpleStringProperty  inventoryStatus;


    public Inventory(int inventoryId, int itemId, int initialStock, String initialDate, int finalStock, String finalDate, int itemSales, String inventoryStatus) {

        this.inventoryId = new SimpleIntegerProperty(inventoryId);
        this.inventoryItemId = new SimpleIntegerProperty(itemId);
        this.initialStock = new SimpleIntegerProperty(initialStock);
        this.initialDate = new SimpleStringProperty(initialDate);
        this.finalStock = new SimpleIntegerProperty(finalStock);
        this.finalDate = new SimpleStringProperty(finalDate);
        this.itemSales = new SimpleIntegerProperty(itemSales);
        this.inventoryStatus = new SimpleStringProperty(inventoryStatus);
    }



    public Inventory(int itemId, int initialStock, String initialDate, int finalStock, String finalDate, int itemSales) {

        this.inventoryItemId = new SimpleIntegerProperty(itemId);
        this.initialStock = new SimpleIntegerProperty(initialStock);
        this.initialDate = new SimpleStringProperty(initialDate);
        this.finalStock = new SimpleIntegerProperty(finalStock);
        this.finalDate = new SimpleStringProperty(finalDate);
        this.itemSales = new SimpleIntegerProperty(itemSales);
    }


    public int getInventoryId() {
        return inventoryId.get();
    }



    public int getInitialStock() {
        return initialStock.get();
    }


    public String getInitialDate() {
        return initialDate.get();
    }


    public int getFinalStock() {
        return finalStock.get();
    }


    public String getFinalDate() {
        return finalDate.get();
    }



    public int getItemSales() {
        return itemSales.get();
    }

    public int getInventoryItemId() {
        return inventoryItemId.get();
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId.set(inventoryId);
    }

    public void setInventoryItemId(int inventoryItemId) {
        this.inventoryItemId.set(inventoryItemId);
    }

    public void setInitialStock(int initialStock) {
        this.initialStock.set(initialStock);
    }

    public void setInitialDate(String initialDate) {
        this.initialDate.set(initialDate);
    }

    public void setFinalStock(int finalStock) {
        this.finalStock.set(finalStock);
    }

    public void setFinalDate(String finalDate) {
        this.finalDate.set(finalDate);
    }

    public void setItemSales(int itemSales) {
        this.itemSales.set(itemSales);
    }

    public void setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = new SimpleStringProperty(inventoryStatus);
    }

    public String getInventoryStatus() {
        return inventoryStatus.get();
    }

}
