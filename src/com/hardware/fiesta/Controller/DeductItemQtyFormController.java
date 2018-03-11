package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.StockDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.Inventory;
import com.hardware.fiesta.Model.Sales;
import com.hardware.fiesta.Model.StockItem;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Date;

public class DeductItemQtyFormController {
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
    void deductItemQty()   {

        int deductedQty = Integer.parseInt(tf_newQuantity.getText());
        Date date =new Date();

        if(lbl_promptText.getText().equals("")){

            int finalStock = this.stockItem.getItemQty() - deductedQty;


            stdb.openConnection();
            Inventory inventory = new  Inventory(

                    stockItem.getItemId(),
                    stockItem.getItemQty(),
                    stockItem.getItemLastUpdated(),
                    finalStock,
                    date.toString(),
                    deductedQty);


            stdb.updateInventory(inventory);
            int inventoryId = stdb.getInventoryId(inventory);
            System.out.println(inventoryId);
            double grossSales = Math.round(deductedQty * this.stockItem.getItemCost());
            String inclusiveDates = inventory.getInitialDate() + " - " + inventory.getFinalDate();
            Sales sales = new Sales(inventoryId,this.stockItem.getItemId(),deductedQty,grossSales,inclusiveDates);



            stdb.addSales(sales);

            inventory.setInitialStock(finalStock);
            inventory.setFinalStock(0);
            inventory.setFinalDate("Pending");
            inventory.setItemSales(0);
            inventory.setInventoryStatus("ENABLED");

            stdb.addInventory(inventory);

            stockItem.setItemQty(finalStock);

            stdb.updateItem(this.stockItem, this.stockItem);

            stdb.closeConnection();

            tf_newQuantity.setText("");
            uiLoader.getStockTableViewController().displayData();


        }



    }

    @FXML
    void validateInput() {

        String quantity = tf_newQuantity.getText().trim();

        if(quantity.isEmpty()){

            lbl_promptText.setText("Input field required!");

        }else if(!quantity.matches("\\d{0,10}?")){

            lbl_promptText.setText("Invelid Input!!!!");

        }else{
            if (Integer.parseInt(quantity) > this.stockItem.getItemQty() || Integer.parseInt(quantity) < 0){

                lbl_promptText.setText("Invalid quantity to be deducted!");
            }else {

                lbl_promptText.setText("");
            }

        }

    }
    public void setUiLoader(UILoader uiLoader) {
        this.uiLoader = uiLoader;
        this.stdb =uiLoader.getStdb();
    }
    public void setStockItem(StockItem stockItem) {
        this.stockItem = stockItem;
        setDataOnUI();
    }
}
