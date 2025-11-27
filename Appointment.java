package healthadmin;

import java.time.LocalDate;

record Appointment(Patient patient, Staff doctor, LocalDate date, AppointmentType type) {
    public Appointment {
        if (date.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Appointment cannot be in the past!");
    }
}

