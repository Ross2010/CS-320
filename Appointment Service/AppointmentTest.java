import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTest {

    // Helper: a date safely in the future (1 hour from now)
    private Date futureDate() {
        return new Date(System.currentTimeMillis() + 3_600_000);
    }

    // Helper: a date in the past (1 hour ago)
    private Date pastDate() {
        return new Date(System.currentTimeMillis() - 3_600_000);
    }

    //Constructor
    @Test
    void testAppointmentCreationSuccess() {
        Date date = futureDate();
        Appointment appt = new Appointment("A001", date, "Annual physical exam with Dr. Smith.");
        assertEquals("A001", appt.getAppointmentId());
        assertEquals(date,   appt.getAppointmentDate());
        assertEquals("Annual physical exam with Dr. Smith.", appt.getDescription());
    }

    @Test
    void testAppointmentIdAtMaxLength() {
        Appointment appt = new Appointment("1234567890", futureDate(), "Valid description.");
        assertEquals("1234567890", appt.getAppointmentId());
    }

    @Test
    void testDescriptionAtMaxLength() {
        String desc = "12345678901234567890123456789012345678901234567890";
        Appointment appt = new Appointment("A002", futureDate(), desc);
        assertEquals(desc, appt.getDescription());
    }

    //constructor appointmentId validation 
    @Test
    void testNullAppointmentIdThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Appointment(null, futureDate(), "Valid description."));
    }

    @Test
    void testAppointmentIdTooLongThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Appointment("12345678901", futureDate(), "Valid description."));
    }

    //Constructor appointmentDate validation 
    @Test
    void testNullAppointmentDateThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Appointment("A003", null, "Valid description."));
    }

    @Test
    void testPastAppointmentDateThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Appointment("A004", pastDate(), "Valid description."));
    }

    //constructor description validation
    @Test
    void testNullDescriptionThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Appointment("A005", futureDate(), null));
    }

    @Test
    void testDescriptionTooLongThrows() {
        //51 characters
        assertThrows(IllegalArgumentException.class,
            () -> new Appointment("A006", futureDate(),
                "123456789012345678901234567890123456789012345678901"));
    }

    //Immutability
    @Test
    void testAppointmentIdIsNotUpdatable() {
        // is unchanged after construction (no setter to call).
        Appointment appt = new Appointment("A007", futureDate(), "Check immutability.");
        assertEquals("A007", appt.getAppointmentId());
    }

    @Test
    void testAllFieldsReturnCorrectValues() {
        Date date = futureDate();
        Appointment appt = new Appointment("A008", date, "Dental cleaning appointment.");
        assertAll(
            () -> assertEquals("A008", appt.getAppointmentId()),
            () -> assertEquals(date,   appt.getAppointmentDate()),
            () -> assertEquals("Dental cleaning appointment.", appt.getDescription())
        );
    }
}
