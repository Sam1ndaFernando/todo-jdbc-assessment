package lk.ijse.todo.Model;

import com.jfoenix.controls.JFXButton;
import lk.ijse.todo.db.DbConnection;
import lk.ijse.todo.dto.TaskDto;
import lk.ijse.todo.dto.tm.DueTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskModel {
    private static ResultSet resultSet;

    public static boolean saveTaskToDatabase(TaskDto dto) throws SQLException {
        Connection connection = null;
        boolean isSaved = false;
        System.out.println("ok");

        try {
            connection = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO tasks (tid, description, dueDate) VALUES (?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, dto.getId());
                pstm.setString(2, dto.getDescription());
                pstm.setString(3, dto.getDate());

                isSaved = pstm.executeUpdate() > 0;
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return isSaved;
    }



    public static void deleteTaskFromDatabase(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = null;

        try {
            String sql = "DELETE FROM tasks WHERE id = ?";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, id);

            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Task deleted successfully.");
            } else {
                System.out.println("Task not found in the database.");
            }
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            connection.close();
        }
    }

    public static List<TaskDto> getCompletedTasks() throws SQLException {
        List<TaskDto> completedTasks = new ArrayList<>();

        try {
            String sql = "SELECT * FROM tasks WHERE status = 'isCompleted'";

            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                int taskId = resultSet.getInt("id");
                String description = resultSet.getString("description");

                TaskDto taskDto = new TaskDto(taskId, description);
                completedTasks.add(taskDto);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();

            }

        }

        return completedTasks;
    }


    public static List<DueTm> getAllTaskd(Connection connection) throws SQLException {
        List<DueTm> dueList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM tasks")) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String description = resultSet.getString("description");
                String dueDate = resultSet.getString("dueDate");
                String btnCompleteInfo = resultSet.getString("btnComplete");
                String btnDeleteInfo = resultSet.getString("btnDelete");
                JFXButton btnComplete = createButtonFromInfo(btnCompleteInfo);
                JFXButton btnDelete = createButtonFromInfo(btnDeleteInfo);

                DueTm dueTm = new DueTm(description, dueDate, btnComplete, btnDelete);
                dueList.add(dueTm);
            }
        }

        return dueList;
    }

    private static JFXButton createButtonFromInfo(String btnCompleteInfo) {
        JFXButton button = new JFXButton(btnCompleteInfo);
        return button;
    }
}
