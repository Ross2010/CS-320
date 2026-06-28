import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    //addTask

    @Test
    void testAddTaskSuccess() {
        Task task = new Task("T001", "Fix Login", "Resolve null pointer in login flow.");
        taskService.addTask(task);
        assertNotNull(taskService.getTask("T001"));
    }

    @Test
    void testAddTaskStoredCorrectly() {
        Task task = new Task("T002", "Write Docs", "Document all public API endpoints.");
        taskService.addTask(task);
        Task retrieved = taskService.getTask("T002");
        assertEquals("T002", retrieved.getTaskId());
        assertEquals("Write Docs", retrieved.getName());
        assertEquals("Document all public API endpoints.", retrieved.getDescription());
    }

    @Test
    void testAddMultipleTasks() {
        taskService.addTask(new Task("T003", "Task A", "Description for task A."));
        taskService.addTask(new Task("T004", "Task B", "Description for task B."));
        assertNotNull(taskService.getTask("T003"));
        assertNotNull(taskService.getTask("T004"));
    }

    @Test
    void testAddDuplicateTaskIdThrows() {
        taskService.addTask(new Task("T005", "Task Name", "Some description here."));
        assertThrows(IllegalArgumentException.class,
            () -> taskService.addTask(new Task("T005", "Other Name", "Other description.")));
    }

    @Test
    void testAddNullTaskThrows() {
        assertThrows(IllegalArgumentException.class, () -> taskService.addTask(null));
    }

    //delete Task

    @Test
    void testDeleteTaskSuccess() {
        taskService.addTask(new Task("T006", "To Delete", "This task will be deleted."));
        taskService.deleteTask("T006");
        assertNull(taskService.getTask("T006"));
    }

    @Test
    void testDeleteNonExistentTaskThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> taskService.deleteTask("NONE"));
    }

    @Test
    void testDeleteOneTaskDoesNotAffectOthers() {
        taskService.addTask(new Task("T007", "Keep Me", "This task should remain."));
        taskService.addTask(new Task("T008", "Delete Me", "This task will be removed."));
        taskService.deleteTask("T008");
        assertNotNull(taskService.getTask("T007"));
        assertNull(taskService.getTask("T008"));
    }

    @Test
    void testDeleteThenReAddSameIdSucceeds() {
        taskService.addTask(new Task("T009", "Original", "Original description here."));
        taskService.deleteTask("T009");
        taskService.addTask(new Task("T009", "Re-Added", "Re-added task description."));
        assertEquals("Re-Added", taskService.getTask("T009").getName());
    }

    //updateTaskName

    @Test
    void testUpdateTaskNameSuccess() {
        taskService.addTask(new Task("T010", "Old Name", "Some description text."));
        taskService.updateTaskName("T010", "New Name");
        assertEquals("New Name", taskService.getTask("T010").getName());
    }

    @Test
    void testUpdateTaskNameDoesNotChangeDescription() {
        taskService.addTask(new Task("T011", "Task Name", "Original description."));
        taskService.updateTaskName("T011", "Updated Name");
        assertEquals("Original description.", taskService.getTask("T011").getDescription());
    }

    @Test
    void testUpdateTaskNameNonExistentThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> taskService.updateTaskName("NONE", "New Name"));
    }

    @Test
    void testUpdateTaskNameNullThrows() {
        taskService.addTask(new Task("T012", "Task Name", "Some description."));
        assertThrows(IllegalArgumentException.class,
            () -> taskService.updateTaskName("T012", null));
    }

    @Test
    void testUpdateTaskNameTooLongThrows() {
        taskService.addTask(new Task("T013", "Task Name", "Some description."));
        assertThrows(IllegalArgumentException.class,
            () -> taskService.updateTaskName("T013", "123456789012345678901")); // 21 chars
    }

    //update TaskDescription
    @Test
    void testUpdateTaskDescriptionSuccess() {
        taskService.addTask(new Task("T014", "Task Name", "Old description text."));
        taskService.updateTaskDescription("T014", "New description for this task.");
        assertEquals("New description for this task.",
            taskService.getTask("T014").getDescription());
    }

    @Test
    void testUpdateTaskDescriptionDoesNotChangeName() {
        taskService.addTask(new Task("T015", "Original Name", "Some description."));
        taskService.updateTaskDescription("T015", "Updated description text.");
        assertEquals("Original Name", taskService.getTask("T015").getName());
    }

    @Test
    void testUpdateTaskDescriptionNonExistentThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> taskService.updateTaskDescription("NONE", "New description."));
    }

    @Test
    void testUpdateTaskDescriptionNullThrows() {
        taskService.addTask(new Task("T016", "Task Name", "Some description."));
        assertThrows(IllegalArgumentException.class,
            () -> taskService.updateTaskDescription("T016", null));
    }

    @Test
    void testUpdateTaskDescriptionTooLongThrows() {
        taskService.addTask(new Task("T017", "Task Name", "Some description."));
        assertThrows(IllegalArgumentException.class,
            () -> taskService.updateTaskDescription("T017",
                "123456789012345678901234567890123456789012345678901")); // 51 chars
    }

    //task Id immutability

    @Test
    void testTaskIdRemainsUnchangedAfterUpdates() {
        taskService.addTask(new Task("T018", "Task Name", "Some description here."));
        taskService.updateTaskName("T018", "New Name");
        taskService.updateTaskDescription("T018", "New description for this task.");
        assertEquals("T018", taskService.getTask("T018").getTaskId());
    }

    //getTask edge cases
    @Test
    void testGetTaskReturnsNullWhenNotFound() {
        assertNull(taskService.getTask("MISSING"));
    }
}
