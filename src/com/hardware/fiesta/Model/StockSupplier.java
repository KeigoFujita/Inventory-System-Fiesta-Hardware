package com.hardware.fiesta.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StockSupplier {

    private SimpleIntegerProperty supplierId;
    private SimpleStringProperty  supplierName;
    private SimpleStringProperty  supplierAddress;
    private SimpleStringProperty  supplierContactNumber;
    private SimpleStringProperty  supplierEmailAddress;
    private SimpleStringProperty  supplierStatus;

    public StockSupplier(int supplierId, String supplierName, String supplierAddress, String supplierContactNumber, String supplierEmailAddress, String supplierStatus) {
        this.supplierId = new SimpleIntegerProperty(supplierId);
        this.supplierName = new SimpleStringProperty(supplierName);
        this.supplierAddress = new SimpleStringProperty(supplierAddress);
        this.supplierContactNumber = new SimpleStringProperty(supplierContactNumber);
        this.supplierEmailAddress = new SimpleStringProperty(supplierEmailAddress);
        this.supplierStatus = new SimpleStringProperty(supplierStatus);
    }


    public StockSupplier(String supplierName, String supplierAddress, String supplierContactNumber, String supplierEmailAddress) {
        this.supplierName = new SimpleStringProperty(supplierName);
        this.supplierAddress = new SimpleStringProperty(supplierAddress);
        this.supplierContactNumber = new SimpleStringProperty(supplierContactNumber);
        this.supplierEmailAddress = new SimpleStringProperty(supplierEmailAddress);
    }


    public int getSupplierId() {
        return supplierId.get();
    }



    public String getSupplierName() {
        return supplierName.get();
    }


    public String getSupplierAddress() {
        return supplierAddress.get();
    }



    public String getSupplierContactNumber() {
        return supplierContactNumber.get();
    }



    public String getSupplierEmailAddress() {
        return supplierEmailAddress.get();
    }


    public String getSupplierStatus() {
        return supplierStatus.get();
    }


}
