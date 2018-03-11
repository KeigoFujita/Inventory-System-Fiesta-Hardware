package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.StockDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.StockBrandName;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AddBrandController {



    @FXML
    public JFXTextField tf_BrandName;

    @FXML
    public JFXButton bt_addButton;

    @FXML
    public Label lbl_prompText;


    private UILoader uiLoader;
    private StockDatabaseConnector stdb;
    private StockBrandName oldStockBrandName;

    public void btAddAction() {

        if(bt_addButton.getText().equals("Add")){

            String brandName = tf_BrandName.getText().trim();

            if (brandName.trim().isEmpty()) {

                lbl_prompText.setText("No data given!!!!");

            } else {

                StockBrandName stockBrandName = new StockBrandName(brandName);


                stdb.openConnection();
                if (stdb.addStockBrandName(stockBrandName)) {

                    lbl_prompText.setText("Brand Name Added Successfully!!!");
                    tf_BrandName.setText("");

                    stdb.closeConnection();


                    uiLoader.getAddItemController().setStdb(this.uiLoader.getStdb());
                    uiLoader.getUpdateItemController().setStdb(this.uiLoader.getStdb());




                    uiLoader.getUpdateItemController().setItemsInComboBox();

                    uiLoader.getAddItemController().setItemsInComboBox();
                    uiLoader.getAddItemController().cb_brand.getSelectionModel().select(stockBrandName.getBrandName());


                    //uiLoader.getAddItemController().setItemsInComboBox();
                    uiLoader.getUpdateItemController().cb_brand.getSelectionModel().select(stockBrandName.getBrandName());

                    uiLoader.getStockBrandNameController().setUiLoader(this.uiLoader);
                    uiLoader.getStockBrandNameController().displayTableView();




                } else {

                    lbl_prompText.setText("Category Name is already in the Database.");
                    stdb.closeConnection();

                }





            }

        }else{

           String brandName = tf_BrandName.getText().trim();
           StockBrandName stockBrandName = new StockBrandName(brandName);
           if(oldStockBrandName.getBrandName().equals(brandName)){
               lbl_prompText.setText("The value is not changed");
           }else{

               stdb.openConnection();

               if(stdb.updateBrandName(oldStockBrandName,stockBrandName)){

                   lbl_prompText.setText("Data updated Successfully!");
                   uiLoader.getStockBrandNameController().displayTableView();
                   stdb.closeConnection();

               }else{

                   lbl_prompText.setText("The data is already in the database!");
                   stdb.closeConnection();

               }






           }





        }



    }
    public void setUiLoader (UILoader uiLoader){
            this.uiLoader = uiLoader;
            this.stdb = this.uiLoader.getStdb();

        }

    public void setOldStockBrandName(StockBrandName oldStockBrandName) {
        this.oldStockBrandName = oldStockBrandName;
    }
}




