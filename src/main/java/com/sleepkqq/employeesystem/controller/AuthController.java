package com.sleepkqq.employeesystem.controller;

import com.sleepkqq.employeesystem.model.Developer;
import com.sleepkqq.employeesystem.repository.DeveloperRepository;
import com.sleepkqq.employeesystem.repository.DeveloperRepositorySingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthController {

    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    private static final DeveloperRepository developerRepository = DeveloperRepositorySingleton.getInstance();
    private static final PasswordEncoder passwordEncoder = DeveloperRepositorySingleton.encoder();

    public void switchToRegisterWindow(ActionEvent event) {
        switchWindow(event, "register.fxml", "Registration");
    }

    public void switchToLoginWindow(ActionEvent event) {
        switchWindow(event, "login.fxml", "Login");
    }

    private void switchWindow(ActionEvent event, String fxml, String title) {
        configureStage(getStageFromEvent(event), loadToRoot(getFXMLLoader(fxml)), title);
    }

    public void register(ActionEvent event) {
        String devName = name.getText(), devEmail = email.getText(), devPassword = password.getText();

        if (devName.equals("") || devEmail.equals("") || devPassword.equals("")) {
            errorMessage("Fill in all the fields.");
            return;
        }

        if (developerRepository.findByEmail(devEmail) != null) {
            errorMessage("An account with the same email already exists");
            return;
        }

        Developer developer = new Developer(devName, devEmail, passwordEncoder.encode(devPassword));
        developerRepository.save(developer);
        switchToLoginWindow(event);
    }

    public void login(ActionEvent event) {
        if (!authenticateUser()) {
            errorMessage("Incorrect email or password.");
            return;
        }

        FXMLLoader mainLoader = getFXMLLoader("main.fxml");
        Parent mainRoot = loadToRoot(mainLoader);
        MainController mainController = mainLoader.getController();
        mainController.setJwtToken(JwtUtils.generateToken(email.getText()));

        Stage currentStage = getStageFromEvent(event);
        currentStage.close();

        configureStage(new Stage(), mainRoot, "Main");
    }

    private boolean authenticateUser() {
        Developer developer = developerRepository.findByEmail(email.getText());

        if (developer != null)
            return passwordEncoder.matches(password.getText(), developer.getPassword());

        return false;
    }

    private void errorMessage(String errorMessage) {
        FXMLLoader loader = getFXMLLoader("error.fxml");
        Parent root = loadToRoot(loader);
        ErrorController controller = loader.getController();
        controller.setErrorMessage(errorMessage);

        configureStage(new Stage(), root, "Error");
    }

    private Stage getStageFromEvent(ActionEvent event) {
        return  (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    private FXMLLoader getFXMLLoader(String fxml) {
        return new FXMLLoader(getClass().getClassLoader().getResource(fxml));
    }

    private Parent loadToRoot(FXMLLoader fxmlLoader) {
        try {
            return fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void configureStage(Stage stage, Parent root, String title) {
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.show();
    }



}
