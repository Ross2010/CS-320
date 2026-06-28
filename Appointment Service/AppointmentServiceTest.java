import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class AppointmentServiceTest {

    private AppointmentService service;

    // Helper: a date safely in the future (1 hour from now)
    private Date futureDate() {
        return new Date(System.currentTimeMillis() + 3_600_000);
    }

    @BeforeEach
    void setUp() {
        service = new AppointmentService();
    }

    @Test
    void testAddAppointmentSuccess() {
        Appointment appt = new Appointment("A001", futureDate(), "Annual checkup with doctor.");
        service.addAppointment(appt);
        assertNotNull(service.getAppointment("A001"));
    }

    @Test
    void testAddAppointmentStoredCorrectly() {
        Date date = futureDate();
        Appointment appt = new Appointment("A002", date, "Follow-up appointment next week.");
        service.addAppointment(appt);
        Appointment retrieved = service.getAppointment("A002");
        assertEquals("A002",                            retrieved.getAppointmentId());
        assertEquals(date,                              retrieved.getAppointmentDate());
        assertEquals("Follow-up appointment next week.", retrieved.getDescription());
    }

    @Test
    void testAddMultipleAppointmentsUniqueIds() {
        service.addAppointment(new Appointment("A003", futureDate(), "Dentist appointment visit."));
        service.addAppointment(new Appointment("A004", futureDate(), "Eye exam with specialist."));
        assertNotNull(service.getAppointment("A003"));
        assertNotNull(service.getAppointment("A004"));
    }

    @Test
    void testAddDuplicateAppointmentIdThrows() {
        service.addAppointment(new Appointment("A005", futureDate(), "First appointment entry."));
        assertThrows(IllegalArgumentException.class,
            () -> service.addAppointment(new Appointment("A005", futureDate(), "Duplicate ID entry.")));
    }

    @Test
    void testAddNullAppointmentThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.addAppointment(null));
    }

    //deleteAppointment 
    @Test
    void testDeleteAppointmentSuccess() {
        service.addAppointment(new Appointment("A006", futureDate(), "Appointment to be deleted."));
        service.deleteAppointment("A006");
        assertNull(service.getAppointment("A006"));
    }

    @Test
    void testDeleteNonExistentAppointmentThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> service.deleteAppointment("NONE"));
    }

    @Test
    void testDeleteOneAppointmentDoesNotAffectOthers() {
        service.addAppointment(new Appointment("A007", futureDate(), "Keep this appointment here."));
        service.addAppointment(new Appointment("A008", futureDate(), "Delete this appointment now."));
        service.deleteAppointment("A008");
        assertNotNull(service.getAppointment("A007"));
        assertNull(service.getAppointment("A008"));
    }

    @Test
    void testDeleteThenReAddSameIdSucceeds() {
        service.addAppointment(new Appointment("A009", futureDate(), "Original appointment entry."));
        service.deleteAppointment("A009");
        assertDoesNotThrow(() ->
            service.addAppointment(new Appointment("A009", futureDate(), "Re-added appointment entry.")));
        assertEquals("Re-added appointment entry.",
            service.getAppointment("A009").getDescription());
    }

    //getAppointment
    @Test
    void testGetAppointmentReturnsNullWhenNotFound() {
        assertNull(service.getAppointment("MISSING"));
    }

    //appointmentId immutability

    @Test
    void testAppointmentIdUnchangedAfterStorage() {
        service.addAppointment(new Appointment("A010", futureDate(), "Verify ID is immutable ok."));
        assertEquals("A010", service.getAppointment("A010").getAppointmentId());
    }
}
