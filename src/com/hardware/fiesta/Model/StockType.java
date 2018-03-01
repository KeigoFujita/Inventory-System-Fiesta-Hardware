package com.hardware.fiesta.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StockType {

    private SimpleIntegerProperty typeId;
    private SimpleStringProperty typeName;
    private SimpleStringProperty typeStatus;


    public StockType(int typeId,String typeName, String typeStatus) {
        this.typeId = new SimpleIntegerProperty(typeId);
        this.typeName = new SimpleStringProperty(typeName);
        this.typeStatus = new SimpleStringProperty(typeStatus);
    }

    public StockType(String typeName) {
        this.typeName = new SimpleStringProperty(typeName);
    }


    public int getTypeId() {
        return typeId.get();
    }

    public String getTypeName() {
        return typeName.get();
    }

    public String getTypeStatus() {
        return typeStatus.get();
    }

}
