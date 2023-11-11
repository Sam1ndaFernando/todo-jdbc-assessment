package lk.ijse.todo.controller;




import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.todo.Model.TaskModel;
import lk.ijse.todo.Model.UserModel;
import lk.ijse.todo.dto.TaskDto;
import lk.ijse.todo.dto.UserRegisterDto;

import java.io.IOException;
import java.sql.SQLException;

public class SignupFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPw;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {

        String username = txtUserName.getText();
        String password = txtPw.getText();
        String email = txtEmail.getText();

        var dto = new UserRegisterDto(username,password,email);

        try {
            boolean isTaskSaved = UserModel.saveUserToDatabase(dto);
            if (isTaskSaved) {
                new Alert(Alert.AlertType.INFORMATION, "user saved").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save user").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
        }

    }

    @FXML
    void hyperLoginHereOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(rootNode);

        root.getChildren().clear();
        Stage primaryStage = (Stage) root.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Form");
    }
}
