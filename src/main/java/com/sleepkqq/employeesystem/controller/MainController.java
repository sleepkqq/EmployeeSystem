package com.sleepkqq.employeesystem.controller;

import com.sleepkqq.employeesystem.model.Developer;
import com.sleepkqq.employeesystem.repository.DeveloperRepository;
import com.sleepkqq.employeesystem.repository.DeveloperRepositorySingleton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {

    @FXML
    private Label username;
    private String jwtToken;
    private static final DeveloperRepository developerRepository = DeveloperRepositorySingleton.getInstance();

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
        init();
    }

    private void init() {
        Developer developer = developerRepository.findByEmail(JwtUtils.getEmailFromToken(jwtToken));
        username.setText(developer.getName());
    }

}
