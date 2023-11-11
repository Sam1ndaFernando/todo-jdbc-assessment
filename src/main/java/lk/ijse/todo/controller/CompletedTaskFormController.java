package lk.ijse.todo.controller;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.todo.Model.TaskModel;
import lk.ijse.todo.dto.TaskDto;
import lk.ijse.todo.dto.tm.CompleteTm;

import java.sql.SQLException;
import java.util.List;

public class CompletedTaskFormController {
    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<CompleteTm> tblComplete;

    public void initialize(){
        setCellValueFactory();
        loadCompletedTasks();
    }

    private void loadCompletedTasks() {
        ObservableList<CompleteTm> obList = FXCollections.observableArrayList();

        try {
            // Fetch completed tasks from the database
            List<TaskDto> completedTasks = TaskModel.getCompletedTasks(); // Assuming TaskModel has a method to retrieve completed tasks

            for (TaskDto taskDto : completedTasks) {
                CompleteTm completeTm = new CompleteTm(taskDto.getDescription(), taskDto.getDate());

                // Add event handler to the delete button
                completeTm.getBtnDelete().setOnAction(event -> {
                    try {
                        // Delete the task from the FX table
                        obList.remove(completeTm);

                        // Delete the task from the database
                        TaskModel.deleteTaskFromDatabase(taskDto.getId()); // Assuming TaskModel has a method to delete a task by ID
                    } catch (SQLException e) {
                        e.printStackTrace();
                        // Handle the SQLException (e.g., show an error message)
                    }
                });

                obList.add(completeTm);
            }
        } catch (SQLException e) {
            // Handle the SQLException (e.g., show an error message)
            e.printStackTrace();
        }

        tblComplete.setItems(obList);
    }


    private void setCellValueFactory() {
        colDescription.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("description"));
        colDueDate.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("dueDate"));
        colDelete.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("btnDelete"));
    }
}
