package com.hardware.fiesta.Controller;

import com.hardware.fiesta.LoaderUI.UILoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardFormController {




    @FXML
    private Label label;

    private UILoader uiLoader;

    public void setLabel(){


        label.setText("Welcome back "+ this.uiLoader.getLoginEmployee().getFirstName()+", have a nice day.");

    }

    public void setUiLoader(UILoader uiLoader){
        this.uiLoader = uiLoader;
    }


}
