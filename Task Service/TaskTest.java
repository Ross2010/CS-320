import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    //Constructor
    @Test
    void testTaskCreationSuccess() {
        Task task = new Task("T001", "Fix Bug", "Resolve the null pointer exception in login.");
        assertEquals("T001", task.getTaskId());
        assertEquals("Fix Bug", task.getName());
        assertEquals("Resolve the null pointer exception in login.", task.getDescription());
    }

    @Test
    void testTaskIdAtMaxLength() {
        Task task = new Task("1234567890", "Task Name", "A valid description for this task.");
        assertEquals("1234567890", task.getTaskId());
    }

    @Test
    void testNameAtMaxLength() {
        Task task = new Task("T002", "12345678901234567890", "Valid description.");
        assertEquals("12345678901234567890", task.getName());
    }

    @Test
    void testDescriptionAtMaxLength() {
        String desc = "12345678901234567890123456789012345678901234567890";
        Task task = new Task("T003", "Task Name", desc);
        assertEquals(desc, task.getDescription());
    }

    //null validation
    @Test
    void testNullTaskIdThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Task(null, "Task Name", "Description."));
    }

    @Test
    void testNullNameThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Task("T004", null, "Description."));
    }

    @Test
    void testNullDescriptionThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Task("T005", "Task Name", null));
    }

    //length validation
    @Test
    void testTaskIdTooLongThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Task("12345678901", "Task Name", "Description."));
    }

    @Test
    void testNameTooLongThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Task("T006", "123456789012345678901", "Description."));
    }

    @Test
    void testDescriptionTooLongThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Task("T007", "Task Name",
                "123456789012345678901234567890123456789012345678901"));
    }

    //Id immutability
    @Test
    void testTaskIdIsNotUpdatable() {
        Task task = new Task("T008", "Original", "Original description.");
        assertEquals("T008", task.getTaskId());
    }

    //Setters
    @Test
    void testSetNameSuccess() {
        Task task = new Task("T009", "Old Name", "Some description here.");
        task.setName("New Name");
        assertEquals("New Name", task.getName());
    }

    @Test
    void testSetDescriptionSuccess() {
        Task task = new Task("T010", "Task Name", "Old description.");
        task.setDescription("Updated description for this task.");
        assertEquals("Updated description for this task.", task.getDescription());
    }

    //null validation
    @Test
    void testSetNameNullThrows() {
        Task task = new Task("T011", "Task Name", "Description.");
        assertThrows(IllegalArgumentException.class, () -> task.setName(null));
    }

    @Test
    void testSetDescriptionNullThrows() {
        Task task = new Task("T012", "Task Name", "Description.");
        assertThrows(IllegalArgumentException.class, () -> task.setDescription(null));
    }

    //length validation
    @Test
    void testSetNameTooLongThrows() {
        Task task = new Task("T013", "Task Name", "Description.");
        assertThrows(IllegalArgumentException.class,
            () -> task.setName("123456789012345678901")); // 21 chars
    }

    @Test
    void testSetDescriptionTooLongThrows() {
        Task task = new Task("T014", "Task Name", "Description.");
        assertThrows(IllegalArgumentException.class,
            () -> task.setDescription(
                "123456789012345678901234567890123456789012345678901")); // 51 chars
    }
}
