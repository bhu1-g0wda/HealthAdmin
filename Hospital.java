package healthadmin;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

class Hospital {
    private String name;
    private final List<Patient> patients = new ArrayList<>();
    private final List<Staff> staffList = new ArrayList<>();
    private final List<Appointment> appointments = new ArrayList<>();

    // 2. Using an Array
    private final String[] knownDepartments = {
        "Cardiology", "Pediatrics", "Oncology", "Neurology", "General Ward"
    };

    public Hospital(String name) {
        this.name = name;
    }

    // ... (addPatients and addStaff are unchanged) ...
    public void addPatients(Patient... pats) {
        for (var p : pats) {
            patients.add(p);
            System.out.println("✅ Patient Registered: " + p.getName());
        }
    }
    public void addPatients(Patient singlePatient) {
        patients.add(singlePatient);
        System.out.println("✅ Patient Registered: " + singlePatient.getName());
    }

    public void addStaff(Staff s) {
        staffList.add(s);
        System.out.println("✅ Staff Registered: " + s.getName());
    }

    public void scheduleAppointment(String patientName, String staffId, LocalDate date, AppointmentType type)
            throws InvalidAppointmentException {
        var patient = patients.stream().filter(p -> p.getName().equalsIgnoreCase(patientName)).findFirst();
        
        // This logic is now much cleaner thanks to getId() on the Staff interface
        var staff = staffList.stream()
                .filter(s -> s.getId().equalsIgnoreCase(staffId))
                .findFirst();

        if (patient.isEmpty() || staff.isEmpty()) {
            throw new InvalidAppointmentException("Patient or Staff ID not found.");
        }

        appointments.add(new Appointment(patient.get(), staff.get(), date, type));
        System.out.println("✅ Appointment Scheduled for " + patientName + " on " + date);
    }


    // Using method references
    public void displayAllPatients() {
        System.out.println("--- Patients ---");
        patients.forEach(System.out::println);
    }

    public void displayAllStaff() {
        System.out.println("--- Staff ---");
        staffList.forEach(System.out::println);
    }

    // Lambda with Predicate
    public void searchPatients(Predicate<Patient> predicate) {
        System.out.println("--- Patients matching criteria ---");
        patients.stream().filter(predicate).forEach(System.out::println);
    }
    
    public void displayAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
            return;
        }

        // 3. Using StringBuilder for efficient string building
        StringBuilder report = new StringBuilder();
        report.append("--- Scheduled Appointments ---\n");

        for (var appt : appointments) {
            // 6. Also using the result from the switch expression
            report.append(String.format("• %s with %s on %s [%s, ~%d min]\n",
                    appt.patient().getName(),
                    appt.doctor().getName(),
                    DateUtils.format(appt.date()), // Using our DateUtils
                    appt.type(),
                    appt.type().getEstimatedMinutes() // From switch expression
            ));
        }
        System.out.println(report.toString());
    }

    // 2. New method to demonstrate Array usage
    public void displayKnownDepartments() {
        System.out.println("--- Known Departments ---");
        // Iterating over the String[]
        for (String dept : knownDepartments) {
            System.out.println("- " + dept);
        }
    }
}