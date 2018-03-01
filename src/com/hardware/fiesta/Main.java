package com.hardware.fiesta;

import com.hardware.fiesta.Database.StockDatabaseConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("UI/mainMenu.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("FIESTA HARDWARE SYSTEM");
        primaryStage.centerOnScreen();
        primaryStage.show();

    }

    public static void main(String[] args) {

        StockDatabaseConnector stdb = StockDatabaseConnector.getInstance();
        stdb.openConnection();


//        StockCategory stockCategory = new StockCategory("Electric Tools");
//        StockType   stockType  =  new StockType("Water Pipes");
//        StockBrandName stockBrandName = new StockBrandName("Universal Robina");
//        StockSupplier  stockSupplier  = new StockSupplier("Mark Galeen","Antipolo, City","09675671234","akosibaba@gmail.com");
//
//        if(stdb.addStockCategory(stockCategory)){
//            System.out.println("Added Succesfully!!");
//        }else{
//            System.out.println("Item is already in the Database");
//        }
//
//        if( stdb.addStockType(stockType)){
//            System.out.println("Added Succesfully!!");
//        }else{
//            System.out.println("Item is already in the Database");
//        }
//
//
//        if( stdb.addStockBrandName(stockBrandName)){
//            System.out.println("Added Succesfully!!");
//        }else{
//            System.out.println("Item is already in the Database");
//        }
//
//        if( stdb.addStockSupplier(stockSupplier)){
//            System.out.println("Added Succesfully!!");
//        }else{
//            System.out.println("Item is already in the Database");
//        }

//        StockSupplier stockSupplier = stdb.searchStockSupplier(3);
//
//        System.out.println(stockSupplier.getSupplierId());
//        System.out.println(stockSupplier.getSupplierName());
//        System.out.println(stockSupplier.getSupplierAddress());
//        System.out.println(stockSupplier.getSupplierContactNumber());
//        System.out.println(stockSupplier.getSupplierEmailAddress());
//        System.out.println(stockSupplier.getSupplierStatus());


        stdb.closeConnection();
        System.exit(0);
        //launch(args);

    }




}
