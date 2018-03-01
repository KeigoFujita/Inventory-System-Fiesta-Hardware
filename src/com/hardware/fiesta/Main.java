package com.hardware.fiesta;

import com.hardware.fiesta.LoaderUI.UILoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    UILoader uiLoader;


    @Override
    public void start(Stage primaryStage) throws Exception {

        uiLoader = new UILoader();

        System.out.println(this.uiLoader+ "in Start method");

        System.out.println(this.uiLoader.getMainMenuViewRootLayout());

        Scene scene = new Scene(this.uiLoader.getMainMenuViewRootLayout());
        this.uiLoader.getMainMenuController().setUiLoader(uiLoader);


        primaryStage.setScene(scene);
        primaryStage.setTitle("FIESTA HARDWARE SYSTEM");
        primaryStage.centerOnScreen();
        primaryStage.show();

    }

    @Override
    public void init() throws Exception {


        System.out.println(uiLoader+ "in Init method");


    }

    public static void main(String[] args) {






//        StockDatabaseConnector stdb = StockDatabaseConnector.getInstance();
//        stdb.openConnection();


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


//        stdb.closeConnection();
//        System.exit(0);

        launch(args);

    }




}
