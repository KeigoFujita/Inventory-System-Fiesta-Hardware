package com.hardware.fiesta.Controller;

import com.hardware.fiesta.LoaderUI.UILoader;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class InventoryMainViewController {

    @FXML
    private VBox InventoryMainViewContainer;

    @FXML
    public JFXComboBox<String> viewComboBox;

    @FXML
    private VBox tableViewContainer;

    private UILoader uiLoader;



    public void initialize(){

        viewComboBox.getItems().addAll("Inventory","Stock Replenishment");
        viewComboBox.getSelectionModel().select(0);

        viewComboBox.setOnAction(event -> {

            if(viewComboBox.getSelectionModel().getSelectedIndex()== 0){

                displayInventoryTableView();

            }else{

                displayReplenishTableView();


            }



        });

    }


    private void displayInventoryTableView(){

        uiLoader.getInventoryTableVIewController().setUiLoader(this.uiLoader);
        uiLoader.getInventoryTableVIewController().displayDataInTableView();

        tableViewContainer.getChildren().clear();
        tableViewContainer.getChildren().add(uiLoader.getInventorTableViewRootLayout());


    }

    private void displayReplenishTableView(){

        uiLoader.getReplenishmentFormController().setUiLoader(uiLoader);

        tableViewContainer.getChildren().clear();
        tableViewContainer.getChildren().add(uiLoader.getReplenishTableViewRootLayout());



    }



    public void setUiLoader(UILoader uiLoader) {
        this.uiLoader = uiLoader;
        displayInventoryTableView();
    }





}
