package lk.ijse.todo.dto;

public class TaskDto {
    private String id;
    private String date;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskDto(String id, String date, String description) {
        this.id = id;
        this.date = date;
        this.description = description;
    }

    public TaskDto(int taskId, String description) {
    }
    @Override
    public String toString() {
        return "TaskDto{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
