package com.hardware.fiesta.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Sales {


    private SimpleStringProperty  itemName;
    private SimpleIntegerProperty salesId;
    private SimpleIntegerProperty salesInventoryId;
    private SimpleIntegerProperty salesItemId;
    private SimpleIntegerProperty itemSales;
    private SimpleDoubleProperty  itemsGrossSales;
    private SimpleStringProperty  inclusiveDates;


    public Sales(int salesId, int salesInventoryId, int salesItemId, int itemSales, double itemsGrossSales,String inclusiveDates) {
        this.salesId = new SimpleIntegerProperty(salesId);
        this.salesInventoryId = new SimpleIntegerProperty(salesInventoryId);
        this.salesItemId =new SimpleIntegerProperty(salesItemId);
        this.itemSales = new SimpleIntegerProperty(itemSales);
        this.itemsGrossSales = new SimpleDoubleProperty(itemsGrossSales);toString();
        this.inclusiveDates = new SimpleStringProperty(inclusiveDates);
    }

    public Sales(int salesInventoryId, int salesItemId, int itemSales, double itemsGrossSales, String inclusiveDates) {

        this.salesInventoryId = new SimpleIntegerProperty(salesInventoryId);
        this.salesItemId =new SimpleIntegerProperty(salesItemId);
        this.itemSales = new SimpleIntegerProperty(itemSales);
        this.itemsGrossSales = new SimpleDoubleProperty(itemsGrossSales);
        this.inclusiveDates = new SimpleStringProperty(inclusiveDates);
    }

    public int getSalesId() {
        return salesId.get();
    }


    public int getSalesInventoryId() {
        return salesInventoryId.get();
    }



    public int getSalesItemId() {
        return salesItemId.get();
    }



    public int getItemSales() {
        return itemSales.get();
    }



    public double getItemsGrossSales() {

        return itemsGrossSales.get();

    }

    public String getInclusiveDates() {
        return inclusiveDates.get();
    }

    public SimpleStringProperty inclusiveDatesProperty() {
        return inclusiveDates;
    }

    public void setInclusiveDates(String inclusiveDates) {
        this.inclusiveDates.set(inclusiveDates);
    }

    public void setItemName(String itemName) {
        this.itemName = new SimpleStringProperty(itemName);
    }

    public String getItemName(){

        return itemName.get();

    }

}
