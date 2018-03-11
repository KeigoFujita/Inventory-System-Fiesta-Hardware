package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.EmployeesDatabaseConnector;
import com.hardware.fiesta.Database.StockDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.Account;
import com.hardware.fiesta.Model.StockItem;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DisableItemController {

    @FXML
    private JFXPasswordField tf_password;

    @FXML
    private Label lbl_prompText;
    private UILoader uiLoader;
    private StockDatabaseConnector stdb;
    private EmployeesDatabaseConnector emdb;


    private StockItem stockItem;


    @FXML
    void cancelAction(ActionEvent event) {

        uiLoader.getDisableItemStage().close();


    }

    public void initialize(){

        lbl_prompText.setText("");

    }


    @FXML
    void disableItemAction(ActionEvent event) {

        if(tf_password.getText().trim().isEmpty()){


            lbl_prompText.setText("Input field required!");


        }else{

            String password = tf_password.getText().trim();
            Account account = new Account();

            if(password.equals(account.decryptPassword(this.uiLoader.getLoginAccount().getPassword()))){

                lbl_prompText.setText("");

                stdb.openConnection();
                stdb.disableItem(stockItem);
                stdb.closeConnection();

                this.uiLoader.getStockTableViewController().displayData();
                this.uiLoader.getDisableItemStage().close();

            }else{

                lbl_prompText.setText("Invalid Password given!!!");
            }

        }




    }


    public void setUiLoader(UILoader uiLoader) {
        this.uiLoader = uiLoader;
        this.stdb = uiLoader.getStdb();
        this.emdb = uiLoader.getEmdb();
    }

    public void setStockItem(StockItem stockItem) {
        this.stockItem = stockItem;
        tf_password.setText("");
    }
}
