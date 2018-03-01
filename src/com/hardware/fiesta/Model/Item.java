package com.hardware.fiesta.Model;

public class Item {

    private String name;
    private double cost;
    private int type;
    private boolean status;

    public Item(String name, double cost, boolean status, int type) {
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean getStatus() {
        return status;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getType() {
        return type;
    }



}
