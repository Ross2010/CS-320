import java.util.HashMap;
import java.util.Map;

public class TaskService {

    private final Map<String, Task> tasks = new HashMap<>();
    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task must not be null.");
        }
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException(
                "A task with ID '" + task.getTaskId() + "' already exists.");
        }
        tasks.put(task.getTaskId(), task);
    }

    //Deletes the task with the given ID.
    public void deleteTask(String taskId) {
        if (!tasks.containsKey(taskId)) {
            throw new IllegalArgumentException(
                "No task found with ID '" + taskId + "'.");
        }
        tasks.remove(taskId);
    }
 
    //Updates the name of the task with the given ID.
    public void updateTaskName(String taskId, String newName) {
        Task task = getTaskOrThrow(taskId);
        task.setName(newName);
    }

    //Updates the description of the task with the given ID.
    public void updateTaskDescription(String taskId, String newDescription) {
        Task task = getTaskOrThrow(taskId);
        task.setDescription(newDescription);
    }

    //Retrieves task with the given ID.
    public Task getTask(String taskId) {
        return tasks.get(taskId);
    }

    //helpers 
    private Task getTaskOrThrow(String taskId) {
        Task task = tasks.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException(
                "No task found with ID '" + taskId + "'.");
        }
        return task;
    }
}
