package com.hardware.fiesta.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StockBrandName {

    private SimpleIntegerProperty brandId;
    private SimpleStringProperty brandName;
    private SimpleStringProperty brandStatus;


    public StockBrandName(int brandId,String brandName, String brandStatus) {
        this.brandId = new SimpleIntegerProperty(brandId);
        this.brandName = new SimpleStringProperty(brandName);
        this.brandStatus = new SimpleStringProperty(brandStatus);
    }

    public StockBrandName(String brandName) {
        this.brandName = new SimpleStringProperty(brandName);
    }


    public int getBrandId() {
        return brandId.get();
    }

    public String getBrandName() {
        return brandName.get();
    }

    public String getBrandStatus() {
        return brandStatus.get();
    }
}
