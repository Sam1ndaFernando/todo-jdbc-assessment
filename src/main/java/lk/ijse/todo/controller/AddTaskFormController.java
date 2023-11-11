package lk.ijse.todo.controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lk.ijse.todo.Model.TaskModel;
import lk.ijse.todo.dto.TaskDto;

import java.sql.SQLException;

public class AddTaskFormController {
    @FXML
    private DatePicker txtDate;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtId;

    @FXML
    void btnAddTaskOnAction(ActionEvent event) {
        String id = txtId.getText();
        String date = String.valueOf(txtDate.getValue());
        String description = txtDescription.getText();

        var dto = new TaskDto(id, date, description);

        try {
            boolean isTaskSaved = TaskModel.saveTaskToDatabase(dto);
            if (isTaskSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Task saved").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save task").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
        }
    }
}
