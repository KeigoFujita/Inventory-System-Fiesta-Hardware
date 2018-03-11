package com.hardware.fiesta.Controller;

import com.hardware.fiesta.Database.StockDatabaseConnector;
import com.hardware.fiesta.LoaderUI.UILoader;
import com.hardware.fiesta.Model.StockSupplier;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AddSupplierController {

    @FXML
    private JFXTextField tf_name;

    @FXML
    private JFXTextField tf_address;

    @FXML
    private JFXTextField tf_number;

    @FXML
    private JFXTextField tf_email;

    @FXML
    public Label tf_promptext;

    @FXML
    public JFXButton buttonSupplier;



    private StockDatabaseConnector stdb;
    private UILoader uiLoader;
    private StockSupplier oldStockSupplier;
    private String mode;
    public String ADD_SUPPLIER_MODE = "ADD_SUPPLIER";
    public String UPDATE_SUPPLIER_MODE = "UPDATE_SUPPLIER";




    public void buttonSupplierAction(){

        if(this.mode.equals(ADD_SUPPLIER_MODE)){


            addSuppliier();

        }else if(this.mode.equals(UPDATE_SUPPLIER_MODE)){


            updateSupplier();

        }

    }

    public void setUiLoader(UILoader uiLoader) {
        this.uiLoader = uiLoader;
        this.stdb = uiLoader.getStdb();
    }
    public void setOldStockSupplier(StockSupplier oldStockSupplier) {
        this.oldStockSupplier = oldStockSupplier;
        tf_name.setText(oldStockSupplier.getSupplierName());
        tf_address.setText(oldStockSupplier.getSupplierAddress());
        tf_number.setText(oldStockSupplier.getSupplierContactNumber());
        tf_email.setText(oldStockSupplier.getSupplierEmailAddress());
    }
    public void setMode(String mode) {
        this.mode = mode;

        if(this.mode.equals(ADD_SUPPLIER_MODE)){

            buttonSupplier.setText("Add Supplier");
            addSuppliier();

        }else if(this.mode.equals(UPDATE_SUPPLIER_MODE)){

            buttonSupplier.setText("Update Supplier");
            updateSupplier();

        }else{

            System.out.println("It is not an accepted mode!");

        }
    }
    public void clearTextFields(){

        tf_name.setText("");
        tf_address.setText("");
        tf_number.setText("");
        tf_email.setText("");
    }

    private void addSuppliier(){

        String name = tf_name.getText().trim();
        String address = tf_address.getText().trim();
        String contact = tf_number.getText().trim();
        String email = tf_email.getText().trim();

        if(name.isEmpty() || address.isEmpty() || contact.isEmpty()|| email.isEmpty() ){

            tf_promptext.setText("Please fill up the required fields!");

        }else{

            StockSupplier stockSupplier = new StockSupplier(name, address, contact, email);

                    stdb.openConnection();

                    if(stdb.validateSupplierName(stockSupplier)){

                        tf_promptext.setText("Supplier "+stockSupplier.getSupplierName()+" already exists!");
                        stdb.closeConnection();
                    }else if(stdb.validateSupplierContact(stockSupplier)){
                        tf_promptext.setText("Contact "+stockSupplier.getSupplierContactNumber()+" already exists!");
                        stdb.closeConnection();
                    }else if(stdb.validateSupplierEmail(stockSupplier)){
                        tf_promptext.setText("Email "+stockSupplier.getSupplierEmailAddress()+" already exists!");
                        stdb.closeConnection();
                    }else{

                        stdb.addStockSupplier(stockSupplier);
                        tf_promptext.setText("Supplier Added Successfully!");
                        tf_name.setText("");
                        tf_address.setText("");
                        tf_number.setText("");
                        tf_email.setText("");
                        stdb.closeConnection();


                        uiLoader.getUpdateItemController().setStdb(this.uiLoader.getStdb());
                        uiLoader.getAddItemController().setStdb(this.uiLoader.getStdb());


                        uiLoader.getUpdateItemController().setItemsInComboBox();

                        uiLoader.getAddItemController().setItemsInComboBox();
                        uiLoader.getAddItemController().cb_supplier.getSelectionModel().select(stockSupplier.getSupplierName());


                        uiLoader.getUpdateItemController().cb_supplier.getSelectionModel().select(stockSupplier.getSupplierName());
                        uiLoader.getStockSupplierController().setUiLoader(this.uiLoader);
                        uiLoader.getStockSupplierController().displayTableView();
                    }


//
//                    if(stdb.addStockSupplier(stockSupplier)) {
//
//                        uiLoader.getStockSupplierController().displayTableView();

//                    }else{
//
//                        tf_promptext.setText("");
//                        stdb.closeConnection();
//
//                    }

        }



    }
    private void updateSupplier(){


        String name = tf_name.getText().trim();
        String address = tf_address.getText().trim();
        String contact = tf_number.getText().trim();
        String email = tf_email.getText().trim();

        StockSupplier newStockSupplier = new StockSupplier(name, address, contact, email);


        if(name.isEmpty() || address.isEmpty() || contact.isEmpty()|| email.isEmpty() ) {
            tf_promptext.setText("Please fill up the required fields!");
        }else if(!this.oldStockSupplier.getSupplierName().equals(newStockSupplier.getSupplierName())){

            stdb.openConnection();
            if(stdb.validateSupplierName(newStockSupplier)){

                tf_promptext.setText("Supplier "+newStockSupplier.getSupplierName()+" already exists!");
                stdb.closeConnection();
            }else{


                stdb.updateSupplier(this.oldStockSupplier,newStockSupplier);
                tf_promptext.setText("Supplier Updated Successfully!");
                tf_name.setText("");
                tf_address.setText("");
                tf_number.setText("");
                tf_email.setText("");
                stdb.closeConnection();

                uiLoader.getAddSupplierStage().close();
                uiLoader.getStockSupplierController().displayTableView();


            }

        }else if(!this.oldStockSupplier.getSupplierContactNumber().equals(newStockSupplier.getSupplierContactNumber())){

            stdb.openConnection();
            if(stdb.validateSupplierContact(newStockSupplier)){
                tf_promptext.setText("Contact "+newStockSupplier.getSupplierContactNumber()+" already exists!");
                stdb.closeConnection();
            }else{


                stdb.updateSupplier(this.oldStockSupplier,newStockSupplier);
                tf_promptext.setText("Supplier Updated Successfully!");
                tf_name.setText("");
                tf_address.setText("");
                tf_number.setText("");
                tf_email.setText("");
                stdb.closeConnection();

                uiLoader.getAddSupplierStage().close();
                uiLoader.getStockSupplierController().displayTableView();


            }

        }else if(!this.oldStockSupplier.getSupplierEmailAddress().equals(newStockSupplier.getSupplierEmailAddress())){
            stdb.openConnection();

            if(stdb.validateSupplierEmail(newStockSupplier)){
                tf_promptext.setText("Email "+newStockSupplier.getSupplierEmailAddress()+" already exists!");
                stdb.closeConnection();
            }else{


                stdb.updateSupplier(this.oldStockSupplier,newStockSupplier);
                tf_promptext.setText("Supplier Updated Successfully!");
                tf_name.setText("");
                tf_address.setText("");
                tf_number.setText("");
                tf_email.setText("");
                stdb.closeConnection();

                uiLoader.getAddSupplierStage().close();
                uiLoader.getStockSupplierController().displayTableView();


            }

        }else{

               tf_promptext.setText("The data is not changed!!!!");

        }

//       else if(oldStockSupplier.getSupplierName().equals(stockSupplier.getSupplierName())
//                && oldStockSupplier.getSupplierContactNumber().equals(stockSupplier.getSupplierContactNumber())
//                && oldStockSupplier.getSupplierEmailAddress().equals(stockSupplier.getSupplierEmailAddress())
//                && oldStockSupplier.getSupplierAddress().equals(stockSupplier.getSupplierAddress())){
//
//            tf_promptext.setText("The data is not changed");
//
//        }else if(!oldStockSupplier.getSupplierName().equals(stockSupplier.getSupplierName())
//                || !oldStockSupplier.getSupplierContactNumber().equals(stockSupplier.getSupplierContactNumber())
//                || !oldStockSupplier.getSupplierEmailAddress().equals(stockSupplier.getSupplierEmailAddress())
//                ){
//
//
//            stdb.openConnection();
//
//            if(stdb.updateSupplier(oldStockSupplier,stockSupplier)) {
//
//                uiLoader.getStockSupplierController().displayTableView();
//                tf_promptext.setText("Supplier Added Successfully!");
//                tf_name.setText("");
//                tf_address.setText("");
//                tf_number.setText("");
//                tf_email.setText("");
//
//                stdb.closeConnection();
//
//            }else{
//
//                tf_promptext.setText("The Supplier Information is not valid, it is already in the database!");
//                stdb.closeConnection();
//
//            }
//
//
//
//        }else{
//
//
//                stdb.openConnection();
//
//                if(stdb.updateSupplier(oldStockSupplier,stockSupplier)) {
//
//                    uiLoader.getStockSupplierController().displayTableView();
//                    tf_promptext.setText("Supplier Added Successfully!");
//                    tf_name.setText("");
//                    tf_address.setText("");
//                    tf_number.setText("");
//                    tf_email.setText("");
//
//                    stdb.closeConnection();
//
//                }else{
//
//                    tf_promptext.setText("The Supplier Information is not valid, it is already in the database!");
//                    stdb.closeConnection();
//
//                }
//
//        }


    }






}
