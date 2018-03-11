package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.StockDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.StockType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AddStockTypeController {

    @FXML
    public JFXTextField tf_TypeName;

    @FXML
    public JFXButton tf_addButton;

    @FXML
    public Label tf_prompText;

    private StockDatabaseConnector stdb;
    private UILoader uiLoader;
    private StockType oldStockType;



    public void bt_AddTypeAction(){


        if(tf_addButton.getText().equals("Add")){

            String typeName  = tf_TypeName.getText().trim();

            if (typeName.isEmpty()){

                tf_prompText.setText("No data given!!!!");
            }else{

                StockType stockType = new StockType(typeName);


                stdb.openConnection();
                if(stdb.addStockType(stockType)){

                    tf_prompText.setText("Category Added Successfully!!!");
                    tf_TypeName.setText("");


                    uiLoader.getUpdateItemController().setStdb(this.uiLoader.getStdb());
                    uiLoader.getAddItemController().setStdb(this.uiLoader.getStdb());



                    uiLoader.getUpdateItemController().setItemsInComboBox();

                    uiLoader.getAddItemController().setItemsInComboBox();
                    uiLoader.getAddItemController().cb_type.getSelectionModel().select(stockType.getTypeName());


                    uiLoader.getUpdateItemController().cb_type.getSelectionModel().select(stockType.getTypeName());


                    uiLoader.getStockTypeController().setUiLoader(this.uiLoader);
                    uiLoader.getStockTypeController().displayTableView();

                }else{

                    tf_prompText.setText("Category Name is already in the Database.");

                }


                stdb.closeConnection();

            }

        }else{


            String typeName  = tf_TypeName.getText().trim();
            StockType stockType = new StockType(typeName);

            if (typeName.isEmpty()){

                tf_prompText.setText("No data given!!!!");

            }else{

                stdb.openConnection();

                if(stdb.updateType(oldStockType,stockType)){

                    stdb.closeConnection();
                    tf_prompText.setText("Data Updated Successfully!");
                    tf_TypeName.setText("");
                    uiLoader.getStockTypeController().displayTableView();

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

    public void setOldStockType(StockType oldStockType) {
        this.oldStockType = oldStockType;
    }
}



