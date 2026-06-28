import java.util.Date;

public class Appointment {

    private final String appointmentId;
    private final Date appointmentDate;
    private final String description;

    
    //Constructs
    public Appointment(String appointmentId, Date appointmentDate, String description) {
        if (appointmentId == null || appointmentId.length() > 10) {
            throw new IllegalArgumentException(
                "Appointment ID must not be null and must be 10 characters or fewer.");
        }
        if (appointmentDate == null || appointmentDate.before(new Date())) {
            throw new IllegalArgumentException(
                "Appointment date must not be null and cannot be in the past.");
        }
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException(
                "Description must not be null and must be 50 characters or fewer.");
        }
        this.appointmentId   = appointmentId;
        this.appointmentDate = appointmentDate;
        this.description     = description;
    }

    //Getters
    public String getAppointmentId() {
        return appointmentId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public String getDescription() {
        return description;
    }
}
