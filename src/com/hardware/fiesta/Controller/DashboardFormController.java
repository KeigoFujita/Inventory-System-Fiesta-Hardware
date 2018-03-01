package com.hardware.fiesta.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardFormController {

    @FXML
    private Label label;

    public void setLabel(String labelText){

        label.setText(labelText);

    }



}
