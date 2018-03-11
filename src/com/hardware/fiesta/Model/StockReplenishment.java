package com.hardware.fiesta.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StockReplenishment {

    private SimpleIntegerProperty replenishId;
    private SimpleIntegerProperty replenishItemId;
    private SimpleIntegerProperty replenishStockRemaining;
    private SimpleIntegerProperty replenishStockAdded;
    private SimpleIntegerProperty replenishStockFinal;
    private SimpleStringProperty  replenishDate;

    public StockReplenishment(int replenishId, int replenishItemId, int replenishStockRemaining, int replenishStockAdded, int replenishStockFinal, String replenishDate) {
        this.replenishId = new SimpleIntegerProperty(replenishId);
        this.replenishItemId = new SimpleIntegerProperty(replenishItemId);
        this.replenishStockRemaining = new SimpleIntegerProperty(replenishStockRemaining);
        this.replenishStockAdded = new SimpleIntegerProperty(replenishStockAdded);
        this.replenishStockFinal =new SimpleIntegerProperty(replenishStockFinal) ;
        this.replenishDate = new SimpleStringProperty(replenishDate);
    }


    public StockReplenishment(int replenishItemId, int replenishStockRemaining, int replenishStockAdded, int replenishStockFinal, String replenishDate) {
        this.replenishItemId = new SimpleIntegerProperty(replenishItemId);
        this.replenishStockRemaining = new SimpleIntegerProperty(replenishStockRemaining);
        this.replenishStockAdded = new SimpleIntegerProperty(replenishStockAdded);
        this.replenishStockFinal =new SimpleIntegerProperty(replenishStockFinal) ;
        this.replenishDate = new SimpleStringProperty(replenishDate);
    }


    public int getReplenishId() {
        return replenishId.get();
    }


    public int getReplenishItemId() {
        return replenishItemId.get();
    }


    public int getReplenishStockRemaining() {
        return replenishStockRemaining.get();
    }


    public int getReplenishStockAdded() {
        return replenishStockAdded.get();
    }


    public int getReplenishStockFinal() {
        return replenishStockFinal.get();
    }


    public String getReplenishDate() {
        return replenishDate.get();
    }


    public void setReplenishStockRemaining(int stockRemaining){
        this.replenishStockRemaining = new SimpleIntegerProperty(stockRemaining);
    }

    public void setReplenishStockFinal(int replenishStockFinal){
       this.replenishStockFinal = new SimpleIntegerProperty(replenishStockFinal);
    }
}
