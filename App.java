package healthadmin;

import java.time.LocalDate;
import java.util.*;

public class App {
    private static final Scanner sc = new Scanner(System.in);
    private static final Hospital hospital = new Hospital("Trinity Health Center");

    public static void main(String[] args) {
        System.out.println("=== Welcome to Health Administration System ===");

        // 1. Log in first!
        User currentUser = simulateLogin();
        boolean canWrite = currentUser.getPermissions().contains("Write"); //

        boolean running = true;
        while (running) {
            // 2. Dynamically show the menu based on permissions
            System.out.println("\nMenu:");
            if (canWrite) {
                System.out.println("1. Register Patient");
                System.out.println("2. Register Doctor");
                System.out.println("3. Register Nurse");
                System.out.println("4. Schedule Appointment");
            }
            System.out.println("5. View appointments");
            System.out.println("6. View Patients");
            System.out.println("7. View Staff");
            System.out.println("8. Search Patient");
            System.out.println("9. View Known Departments");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine();

            // 3. Protect "write" actions
            switch (choice) {
                case "1" -> {
                    if (canWrite) registerPatient();
                    else System.out.println("Access Denied: Read-Only");
                }
                case "2" -> {
                    if (canWrite) registerDoctor();
                    else System.out.println("Access Denied: Read-Only");
                }
                case "3" -> {
                    if (canWrite) registerNurse();
                    else System.out.println("Access Denied: Read-Only");
                }
                case "4" -> {
                    if (canWrite) scheduleAppointment();
                    else System.out.println("Access Denied: Read-Only");
                }
                // "Read" actions are open to everyone
                case "5" -> hospital.displayAppointments();
                case "6" -> hospital.displayAllPatients();
                case "7" -> hospital.displayAllStaff();
                case "8" -> {
                    System.out.print("Enter the name or starting letters to search: ");
                    String searchTerm = sc.nextLine().trim();
                    hospital.searchPatients(p -> p.getName().toLowerCase().startsWith(searchTerm.toLowerCase()));
                }
                case "9" -> hospital.displayKnownDepartments();
                case "10" -> running = false;
                default -> System.out.println("Invalid choice!");
            }
        }
        System.out.println("Goodbye!");
    }

    // ... (registerPatient, registerDoctor, registerNurse, scheduleAppointment are unchanged) ...
    // Demonstrates constructor chaining (this()) and this.
    private static void registerPatient() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter age: ");
        int age = Integer.parseInt(sc.nextLine().trim());
        System.out.println("Enter email: ");
        String email = sc.nextLine();
        System.out.println("Enter Phone: ");
        String phone = sc.nextLine();
        System.out.print("Enter street: ");
        String street = sc.nextLine();
        System.out.print("Enter city: ");
        String city = sc.nextLine();

        ImmutableAddress address = new ImmutableAddress(street, city);
        Patient p = new Patient(name, age, new Contact(email, phone),address);
        hospital.addPatients(p);
    }
    
 // Add this method to App.java
    private static User simulateLogin() {
        System.out.println("Select user to log in as:");
        System.out.println("1. Admin User");
        System.out.println("2. Guest User");
        System.out.print("Choice: ");
        String choice = sc.nextLine();

        if (choice.equals("1")) {
            System.out.println("Logged in as Admin.");
            return new AdminUser("admin"); //
        } else {
            System.out.println("Logged in as Guest (Read-Only access).");
            return new GuestUser("guest"); //
        }
    }

    private static void registerDoctor() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter specialty: ");
        String spec = sc.nextLine();
        Doctor d = new Doctor(name, spec);
        hospital.addStaff(d);
    }

    private static void registerNurse() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter ward: ");
        String ward = sc.nextLine();
        Nurse n = new Nurse(name, ward);
        hospital.addStaff(n);
    }

    private static void scheduleAppointment() {
        try {
        	System.out.print("Enter patient name: ");
        	String pname = sc.nextLine();
        	System.out.print("Enter staff ID: ");
        	String staffId = sc.nextLine();
        	System.out.print("Enter date (YYYY-MM-DD): ");
        	LocalDate date = LocalDate.parse(sc.nextLine());
        	System.out.println("Select type:");
        	for (AppointmentType t : AppointmentType.values()) {
        	    System.out.println("- " + t);
        	}
        	AppointmentType type = AppointmentType.valueOf(sc.nextLine().toUpperCase());

        	hospital.scheduleAppointment(pname, staffId, date, type);

        } catch (InvalidAppointmentException e) {
            System.out.println("⚠ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e);
        }
    }
}