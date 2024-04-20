import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class AppointmentScheduler {
    private static final DateTimeFormatter APPOINTMENT_SCHEDULE_FORMATTER = DateTimeFormatter.ofPattern(
            "MM/dd/yyyy HH:mm:ss");
    private static final DateTimeFormatter APPOINTMENT_DESCRIPTION_FORMATER = DateTimeFormatter.ofPattern(
            "'You have an appointment on' EEEE, MMMM d, yyyy, 'at' h:mm a.");

    public LocalDateTime schedule(String appointmentDateDescription) {
        return LocalDateTime.parse(appointmentDateDescription, APPOINTMENT_SCHEDULE_FORMATTER);
    }

    public boolean hasPassed(LocalDateTime appointmentDate) {
        return LocalDateTime.now().isAfter(appointmentDate);
    }

    public boolean isAfternoonAppointment(LocalDateTime appointmentDate) {
        return appointmentDate.getHour() >= 12 && appointmentDate.getHour() < 18;
    }

    public String getDescription(LocalDateTime appointmentDate) {
        return appointmentDate.format(APPOINTMENT_DESCRIPTION_FORMATER);
    }

    public LocalDate getAnniversaryDate() {
        return LocalDate.of(LocalDate.now().getYear(), 9, 15);
    }
}
