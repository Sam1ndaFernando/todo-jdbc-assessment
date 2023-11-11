package lk.ijse.todo.dto.tm;

import com.jfoenix.controls.JFXButton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DueTm {

    private String description;
    private String dueDate;
    private JFXButton btnComplete;
    private JFXButton btnDelete;

    public DueTm(String description, String dueDate, JFXButton btnComplete, JFXButton btnDelete) {
        this.description = description;
        this.dueDate = dueDate;
        this.btnComplete = btnComplete;
        this.btnDelete = btnDelete;
    }

    public DueTm() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public JFXButton getBtnComplete() {
        return btnComplete;
    }

    public void setBtnComplete(JFXButton btnComplete) {
        this.btnComplete = btnComplete;
    }

    public JFXButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(JFXButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "DueTm{" +
                "description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", btnComplete=" + btnComplete +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
