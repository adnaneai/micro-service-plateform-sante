package ma.emsi.appointmentservice.exceptions;

public class PatientNotFoundException extends Exception {
    public PatientNotFoundException(String message) {
        super(message);
    }
}
