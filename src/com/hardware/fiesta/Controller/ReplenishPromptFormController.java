package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.StockDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.StockItem;
import com.hardware.fiesta.Model.StockReplenishment;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Date;

public class ReplenishPromptFormController {

    @FXML
    private Label lbl_itemName;

    @FXML
    private Label lbl_itemRemaining;

    @FXML
    private JFXTextField tf_newQuantity;

    @FXML
    private Label lbl_promptText;

    private UILoader uiLoader;
    private StockDatabaseConnector stdb;
    private StockItem stockItem;



    private void setDataOnUI(){

        lbl_itemName.setText(this.stockItem.getItemName());
        lbl_itemRemaining.setText("Item remaining: "+this.stockItem.getItemQty()+" "+this.stockItem.getItemUnit());
        lbl_promptText.setText("");

    }

    @FXML
    private void replenishItem(){
        int addedQty = Integer.parseInt(tf_newQuantity.getText());
        Date date = new Date();

        StockReplenishment stockReplenishment = new StockReplenishment(

                this.stockItem.getItemId(),
                this.stockItem.getItemQty(),
                addedQty,
                0,
                date.toString()


        );






        if(lbl_promptText.getText().equals("")){

            this.stockItem.setItemQty(this.stockItem.getItemQty()+addedQty);

            stdb.openConnection();
            stdb.updateItem(this.stockItem, this.stockItem);

            stockReplenishment.setReplenishStockFinal(this.stockItem.getItemQty());
            stdb.updateInventory(this.stockItem.getItemId(),stockItem.getItemQty());
            stdb.addReplenish(stockReplenishment);
            stdb.closeConnection();



        }


        lbl_itemRemaining.setText("Item remaining: "+this.stockItem.getItemQty()+" "+this.stockItem.getItemUnit());
        uiLoader.getStockTableViewController().displayData();
        tf_newQuantity.setText("");
        lbl_promptText.setText("");

    }

    @FXML
    private void validateInput(){

        String quantity = tf_newQuantity.getText().trim();

        if(quantity.isEmpty()){

            lbl_promptText.setText("Input field required!");

        }else if(!quantity.matches("\\d{0,10}?")){

            lbl_promptText.setText("Invelid Input!!!!");

        }else{

            lbl_promptText.setText("");
        }



    }


    public void setUiLoader(UILoader uiLoader){
        this.uiLoader = uiLoader;
        this.stdb = uiLoader.getStdb();


    }

    public void setStockItem(StockItem stockItem){


        this.stockItem = stockItem;
        setDataOnUI();



    }




}
