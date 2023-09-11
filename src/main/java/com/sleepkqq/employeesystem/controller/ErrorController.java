package com.sleepkqq.employeesystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ErrorController {

    @FXML
    private Label errorMessage;

    public void setErrorMessage(String errorMessage) {
        this.errorMessage.setText(errorMessage);
    }

}
