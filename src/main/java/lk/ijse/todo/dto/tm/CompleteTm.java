package lk.ijse.todo.dto.tm;



import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompleteTm {
    private String description;
    private String dueDate;
    private JFXButton btnDelete;

    {
        btnDelete = new JFXButton("Delete");
        btnDelete.setCursor(javafx.scene.Cursor.HAND);
        btnDelete.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");

        btnDelete.setPrefWidth(100);
        btnDelete.setPrefHeight(30);
    }

    public CompleteTm(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }
}
