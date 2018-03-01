package com.hardware.fiesta.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StockCategory {

    private SimpleIntegerProperty categoryId;
    private SimpleStringProperty categoryName;
    private SimpleStringProperty categoryStatus;


    public StockCategory(int categoryId,String categoryName, String categoryStatus) {
        this.categoryId = new SimpleIntegerProperty(categoryId);
        this.categoryName = new SimpleStringProperty(categoryName);
        this.categoryStatus = new SimpleStringProperty(categoryStatus);
    }

    public StockCategory(String categoryName) {
        this.categoryName = new SimpleStringProperty(categoryName);
    }


    public int getCategoryId() {
        return categoryId.get();
    }

    public String getCategoryName() {
        return categoryName.get();
    }

    public String getCategoryStatus() {
        return categoryStatus.get();
    }

}
