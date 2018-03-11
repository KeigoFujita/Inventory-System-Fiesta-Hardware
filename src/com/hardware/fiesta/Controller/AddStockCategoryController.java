package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.StockDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.StockCategory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AddStockCategoryController {

    @FXML
    public JFXTextField tf_CategoryName;

    @FXML
    public JFXButton bt_addButton;

    @FXML
    public Label tf_prompText;

    private StockCategory oldStockCategory;
    private UILoader uiLoader;
    private StockDatabaseConnector stdb;


    public void btAddCategoryAction(){

    if(bt_addButton.getText().equals("Add")){

        String categoryName  = tf_CategoryName.getText().trim();

        if (categoryName.isEmpty()){

            tf_prompText.setText("No data given!!!!");
        }else{

            StockCategory stockCategory = new StockCategory(categoryName);



            stdb.openConnection();
            if(stdb.addStockCategory(stockCategory)){

                tf_prompText.setText("Category Added Successfully!!!");
                tf_CategoryName.setText("");



                uiLoader.getAddItemController().setStdb(this.uiLoader.getStdb());
                uiLoader.getUpdateItemController().setStdb(this.uiLoader.getStdb());


                uiLoader.getUpdateItemController().setItemsInComboBox();

                uiLoader.getAddItemController().setItemsInComboBox();
                uiLoader.getAddItemController().cb_category.getSelectionModel().select(stockCategory.getCategoryName());



                uiLoader.getUpdateItemController().cb_category.getSelectionModel().select(stockCategory.getCategoryName());


                uiLoader.getStockCategorieController().setUiLoader(this.uiLoader);
                uiLoader.getStockCategorieController().displayTableView();

            }else{

                tf_prompText.setText("Category Name is already in the Database.");

            }

            stdb.closeConnection();



        }

    }else{


        String categoryName  = tf_CategoryName.getText().trim();
        StockCategory stockCategory = new StockCategory(categoryName);

        if (categoryName.isEmpty()){

            tf_prompText.setText("No data given!!!!");

        }else{

            stdb.openConnection();

            if(stdb.updateCategory(oldStockCategory,stockCategory)){

                stdb.closeConnection();
                tf_prompText.setText("Data Updated Successfully!");
                tf_CategoryName.setText("");
                uiLoader.getStockCategorieController().displayTableView();

            }else{

                tf_prompText.setText("The data is already in the database!");
                stdb.closeConnection();


            }

        }




    }



    }

    public void setUiLoader(UILoader uiLoader){
        this.uiLoader = uiLoader;
        this.stdb = this.uiLoader.getStdb();

    }


    public void setOldStockCategory(StockCategory oldStockCategory) {
        this.oldStockCategory = oldStockCategory;
    }
}
