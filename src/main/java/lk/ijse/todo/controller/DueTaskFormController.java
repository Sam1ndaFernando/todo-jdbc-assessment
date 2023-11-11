package lk.ijse.todo.controller;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.todo.Model.TaskModel;
import lk.ijse.todo.dto.tm.DueTm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DueTaskFormController {

    @FXML
    private TableColumn<?, ?> colComplete;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<DueTm> tblDue;
    private Connection connection;

    public void initialize() {
        setCellValueFactory();
        loadDueTasks();
    }

    private void setCellValueFactory() {
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        colComplete.setCellValueFactory(new PropertyValueFactory<>("btnComplete"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
    }

    private void loadDueTasks() {
        var model = new DueTm();

        ObservableList<DueTm> obList = FXCollections.observableArrayList();

        try {
            List<DueTm> dtoList = TaskModel.getAllTaskd(connection);

            for (DueTm dto : dtoList) {
                obList.add(
                        new DueTm(
                                dto.getDescription(),
                                dto.getDueDate(),
                                dto.getBtnComplete(),
                                dto.getBtnDelete()
                        )
                );
            }

            tblDue.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
